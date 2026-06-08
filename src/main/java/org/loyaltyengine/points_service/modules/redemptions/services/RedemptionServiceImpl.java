package org.loyaltyengine.points_service.modules.redemptions.services;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.loyaltyengine.openapi.model.ErrorType;
import org.loyaltyengine.points_service.common.exceptions.BadRequestException;
import org.loyaltyengine.points_service.modules.redemptions.dtos.CreateRedemptionRuleDto;
import org.loyaltyengine.points_service.modules.redemptions.dtos.RedemptionRuleDto;
import org.loyaltyengine.points_service.modules.redemptions.models.RedemptionRule;
import org.loyaltyengine.points_service.modules.redemptions.repositories.RedemptionRepository;
import org.loyaltyengine.points_service.modules.redemptions.repositories.RedemptionRuleRepository;
import org.loyaltyengine.points_service.modules.redemptions.utils.RedemptionType;

import java.util.Optional;

@Setter
@RequiredArgsConstructor
@Slf4j
public class RedemptionServiceImpl implements RedemptionService {
    private final RedemptionRuleRepository redemptionRuleRepository;
    private final RedemptionRepository redemptionRepository;


    @Override
    public RedemptionRuleDto createRedemptionRule(CreateRedemptionRuleDto dto) {
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

        RedemptionRule newRule=RedemptionRule.builder()
                .propertyId(dto.getPropertyId()).
                redemptionType(dto.getRedemptionType())
                .couponType(dto.getCouponType())
                .pointsRequired(dto.getPointsRequired())
                .equivalentValue(dto.getEquivalentValue()) // TODO: Validate the equivalent value
                .build();

        RedemptionRule savedRule = redemptionRuleRepository.save(newRule);

        return null;
    }
}
