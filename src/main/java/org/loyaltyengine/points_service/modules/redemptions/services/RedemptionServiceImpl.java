package org.loyaltyengine.points_service.modules.redemptions.services;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.loyaltyengine.points.client.models.ErrorType;
import org.loyaltyengine.points_service.core.exceptions.ApiException;
import org.loyaltyengine.points_service.core.exceptions.BadRequestException;
import org.loyaltyengine.points_service.core.exceptions.NotFoundException;
import org.loyaltyengine.points_service.modules.coupons.dtos.CouponDto;
import org.loyaltyengine.points_service.modules.coupons.dtos.CreateCouponDto;
import org.loyaltyengine.points_service.modules.coupons.services.CouponService;
import org.loyaltyengine.points_service.modules.points.dtos.DebitPointsResultDto;
import org.loyaltyengine.points_service.modules.points.services.PointService;
import org.loyaltyengine.points_service.modules.redemptions.dtos.CreateRedemptionDto;
import org.loyaltyengine.points_service.modules.redemptions.dtos.CreateRedemptionRuleDto;
import org.loyaltyengine.points_service.modules.redemptions.dtos.RedemptionDto;
import org.loyaltyengine.points_service.modules.redemptions.dtos.RedemptionRuleDto;
import org.loyaltyengine.points_service.modules.redemptions.mappers.RedemptionMapper;
import org.loyaltyengine.points_service.modules.redemptions.models.Redemption;
import org.loyaltyengine.points_service.modules.redemptions.models.RedemptionRule;
import org.loyaltyengine.points_service.modules.redemptions.repositories.RedemptionRepository;
import org.loyaltyengine.points_service.modules.redemptions.repositories.RedemptionRuleRepository;
import org.loyaltyengine.points_service.modules.redemptions.utils.RedemptionType;
import org.loyaltyengine.points_service.shared.enums.CouponType;
import org.loyaltyengine.points_service.shared.enums.TransactionReason;
import org.loyaltyengine.points_service.shared.models.Amount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Setter
@RequiredArgsConstructor
@Slf4j
@Service
public class RedemptionServiceImpl implements RedemptionService {
    private static final int MAX_PERCENTAGE_GRANT = 100;
    private final RedemptionRuleRepository redemptionRuleRepository;
    private final RedemptionRepository redemptionRepository;
    private final RedemptionMapper mapper;
    private final PointService pointService;
    private final CouponService couponService;
    private final RedemptionValidator validator;

    @Override
    @Transactional
    public RedemptionRuleDto createRedemptionRule(CreateRedemptionRuleDto dto) {
        log.info("Creating redemption rule for propertyId: {}", dto.getPropertyId());
        // Validate dto
        validator.validateRedemptionRule(dto);

        // Check if the rule already exists
        Optional<RedemptionRule> existingRule = Optional.empty();
        if (dto.getRedemptionType() == RedemptionType.COUPON) {
            existingRule = redemptionRuleRepository.findByPropertyIdAndRedemptionTypeAndCouponType(
                    dto.getPropertyId(),
                    dto.getRedemptionType(),
                    dto.getCouponType()
            );
        } else if (dto.getRedemptionType() == RedemptionType.CASHBACK) {
            existingRule = redemptionRuleRepository.findByPropertyIdAndRedemptionType(
                    dto.getPropertyId(),
                    dto.getRedemptionType());
        }

        if (existingRule.isPresent()) {
            throw new BadRequestException(ErrorType.INVALID_REQUEST, "Redemption rule already exists",
                    "Redemption rule already exists, please update the existing rule");
        }

        // Create new rule
        RedemptionRule newRule = RedemptionRule.builder()
                .propertyId(dto.getPropertyId())
                .redemptionType(dto.getRedemptionType())
                .couponType(dto.getCouponType())
                .valuePerSinglePoint(dto.getValuePerSinglePoint())
                .couponValidNumberOfDays(dto.getCouponValidNumberOfDays())
                .isActive(dto.getIsActive()==null? Boolean.TRUE : dto.getIsActive())
                .amountCurrency(dto.getAmountCurrency())
                .build();

        RedemptionRule savedRule = redemptionRuleRepository.save(newRule);

        return mapper.toDto(savedRule);
    }

    @Override
    @Transactional
    public RedemptionDto createRedemption(CreateRedemptionDto dto) {
        log.info("Redeeming {} points for propertyId: {}, customerId: {}", dto.getNumberOfPoints(), dto.getPropertyId(), dto.getCustomerId());
        // Get redemption rule
        RedemptionRule rule = redemptionRuleRepository.findById(dto.getRedemptionRuleId()).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND,
                "Redemption rule not found", "Redemption rule with id: " + dto.getRedemptionRuleId() + " not found"));

        // Create new redemption
        Redemption newRedemption = new Redemption();
        newRedemption.setPropertyId(dto.getPropertyId());
        newRedemption.setCustomerId(dto.getCustomerId());
        newRedemption.setRedemptionRuleId(rule.getRedemptionRuleId());
        newRedemption.setRedemptionType(rule.getRedemptionType());
        newRedemption.setNumberOfPoints(dto.getNumberOfPoints());

        // Grant coupon
        if (rule.getRedemptionType() == RedemptionType.COUPON) {
            log.info("Granting {} coupon for propertyId: {}, customerId: {}", rule.getCouponType(),
                    dto.getPropertyId(), dto.getCustomerId());

            CreateCouponDto coupon = new CreateCouponDto();
            coupon.setPropertyId(dto.getPropertyId());
            coupon.setCustomerId(dto.getCustomerId());
            coupon.setDescription("This coupon was granted through points redemption.");
            coupon.setCouponType(rule.getCouponType().getValue());
            coupon.setCouponValidNumberOfDays(rule.getCouponValidNumberOfDays());

            // Calculate value
            BigDecimal calculatedValue = BigDecimal.valueOf(dto.getNumberOfPoints())
                    .multiply(rule.getValuePerSinglePoint());

            BigDecimal debitPoints = BigDecimal.valueOf(dto.getNumberOfPoints());

            // Add coupon type values
            if (rule.getCouponType() == CouponType.FIXED_AMOUNT) {
                coupon.setAmount(Amount.builder()
                        .value(calculatedValue)
                        .currency(rule.getAmountCurrency())
                        .build());

            } else if (rule.getCouponType() == CouponType.PERCENTAGE) {
                // Prevent points from calculating to more than 100
                if (calculatedValue.compareTo(BigDecimal.valueOf(MAX_PERCENTAGE_GRANT)) > 0) {
                    calculatedValue = BigDecimal.valueOf(MAX_PERCENTAGE_GRANT);

                    // Calculate points to debit
                    debitPoints = BigDecimal.valueOf(MAX_PERCENTAGE_GRANT)
                            .divide(rule.getValuePerSinglePoint(), 0, RoundingMode.HALF_UP)
                            .divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(100));
                }

                coupon.setPercentage(calculatedValue);
            }

            newRedemption.setNumberOfPoints(debitPoints.intValue());

            // Grant coupon
            CouponDto couponDto = couponService.createCoupon(coupon);
            if (couponDto == null) {
                throw new ApiException(ErrorType.INTERNAL_SERVER_ERROR, "Failed to create coupon",
                        "Failed to create coupon when redeeming points, please try again or contact support");
            }

            // Set coupon code
            newRedemption.setCouponCode(couponDto.getCouponCode());
            // Debit points
            DebitPointsResultDto balance = pointService.debitPoints(dto.getPropertyId(),
                    dto.getCustomerId(),
                    debitPoints.intValue(),
                    TransactionReason.REDEMPTION);

            newRedemption.setTotalRemainingPoints(balance.getTotalRemainingPoints());
            newRedemption.setTotalDebitedPoints(balance.getTotalDebited());
            newRedemption.setCalculatedValue(calculatedValue);

        } else if (rule.getRedemptionType() == RedemptionType.CASHBACK) {
            log.info("Granting cashback for propertyId: {}, customerId: {}", dto.getPropertyId(), dto.getCustomerId());
            throw new UnsupportedOperationException("Cashback redemption is not supported yet");
        }

        Redemption savedRedemption = redemptionRepository.save(newRedemption);

        return mapper.toDto(savedRedemption);
    }

}
