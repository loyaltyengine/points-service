package org.loyaltyengine.points_service.modules.coupons.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loyaltyengine.coupons.v1.model.Amount;
import org.loyaltyengine.coupons.v1.model.BaseCreateCouponRequest;
import org.loyaltyengine.coupons.v1.model.CouponType;
import org.loyaltyengine.coupons.v1.model.CreateFixedAmountCouponRequest;
import org.loyaltyengine.coupons.v1.model.CreatePercentageCouponRequest;
import org.loyaltyengine.coupons.v1.model.FixedAmountCoupon;
import org.loyaltyengine.coupons.v1.model.PercentageCoupon;
import org.loyaltyengine.points_service.modules.coupons.dtos.CouponDto;
import org.loyaltyengine.points_service.modules.coupons.dtos.CreateCouponDto;
import org.loyaltyengine.points_service.modules.coupons.mappers.CouponMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Map;

@Service("restCouponService")
@RequiredArgsConstructor
@Slf4j
@Primary
public class CouponServiceRestImpl implements CouponService {
    public static final int DEFAULT_COUPON_USAGE_LIMIT = 1;
    private final RestClient couponRestClient;
    private final CouponMapper couponMapper;
    private final ObjectMapper objectMapper;

    @Override
    public CouponDto createCoupon(CreateCouponDto dto) {
        log.info("Creating coupon for propertyId: {}, customerId: {}", dto.getPropertyId(), dto.getCustomerId());

        try {
            // Build request body
            BaseCreateCouponRequest request = buildCreateCouponRequest(dto);
            request.setUsageLimit(dto.getUsageLimit() != null ? dto.getUsageLimit() : DEFAULT_COUPON_USAGE_LIMIT);
            request.setDescription(dto.getDescription());

            // Calculate validFrom and expireAt
            OffsetDateTime validFrom = OffsetDateTime.now(ZoneId.systemDefault());
            OffsetDateTime expireAt = validFrom.plusDays(dto.getCouponValidNumberOfDays());

            // Set validFrom and expireAt strings
            request.setValidFrom(validFrom.toString());
            request.setExpireAt(expireAt.toString());

            // Using Map instead of CouponResponse a coupon maybe PercentageCoupon or FixedAmountCoupon
            // So body(CouponResponse.class) does not map to the specific type
            log.info("Sending request to coupons service");
            ResponseEntity<Map<String, Object>> responseEntity = couponRestClient.post()
                    .uri("/coupons-api/v1/properties/{propertyId}/customers/{customerId}/coupons",
                            dto.getPropertyId(), dto.getCustomerId())
                    .body(request)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<Map<String, Object>>() {
                    });

            log.info("Response received from coupons service: {}", responseEntity.getBody());

            // Check status
            if (responseEntity.getBody() != null && responseEntity.getStatusCode() == HttpStatus.OK) {

                @SuppressWarnings("unchecked") // Confident the response is a Map
                Map<String, Object> couponMap = (Map<String, Object>) responseEntity.getBody().get("coupon");

                if(couponMap != null) {
                    String couponType = (String) couponMap.get("couponType");

                    // Map to a specific coupon type
                    if(couponType.equalsIgnoreCase(org.loyaltyengine.points_service.shared.enums.CouponType.FIXED_AMOUNT.getValue())){
                        FixedAmountCoupon fixedAmountCoupon = objectMapper.convertValue(couponMap, FixedAmountCoupon.class);
                        return couponMapper.toDto(fixedAmountCoupon);
                    }
                    else if (couponType.equalsIgnoreCase(org.loyaltyengine.points_service.shared.enums.CouponType.PERCENTAGE.getValue())){
                        PercentageCoupon percentageCoupon = objectMapper.convertValue(couponMap, PercentageCoupon.class);
                        return couponMapper.toDto(percentageCoupon);
                    } else {
                        log.warn("Coupon creation failed: unknown coupon type: {}", couponType);
                        return null;
                    }
                }

            }

            log.warn("Coupon creation failed or status code was not 201. Response: {}", responseEntity.getBody());
            return null;

        } catch (Exception e) {
            log.error("An error occurred while creating coupon: ", e);
            return null;
        }

    }

    private BaseCreateCouponRequest buildCreateCouponRequest(CreateCouponDto dto) {
        return switch (CouponType.fromValue(dto.getCouponType())) {
            case CouponType.FIXED_AMOUNT -> buildCreateFixedAmountCouponRequest(dto);
            case CouponType.PERCENTAGE -> buildCreatePercentageCouponRequest(dto);
            default -> throw new IllegalArgumentException("Invalid coupon type: " + dto.getCouponType());
        };
    }

    private CreateFixedAmountCouponRequest buildCreateFixedAmountCouponRequest(CreateCouponDto dto) {
        // Coupon granted via the points service do not support eligible criteria
        return new CreateFixedAmountCouponRequest()
                .couponType(CouponType.FIXED_AMOUNT)
                .amount(new Amount().value(dto.getAmount().getValue()).currency(dto.getAmount().getCurrency()));

    }

    private CreatePercentageCouponRequest buildCreatePercentageCouponRequest(CreateCouponDto dto) {
        return new CreatePercentageCouponRequest()
                .couponType(CouponType.PERCENTAGE)
                .percentage(dto.getPercentage().intValue());
    }

}
