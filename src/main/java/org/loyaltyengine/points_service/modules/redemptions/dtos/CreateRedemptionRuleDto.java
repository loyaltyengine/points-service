package org.loyaltyengine.points_service.modules.redemptions.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.loyaltyengine.points_service.modules.redemptions.utils.CouponType;
import org.loyaltyengine.points_service.modules.redemptions.utils.RedemptionType;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class CreateRedemptionRuleDto {
    private String propertyId;
    private RedemptionType redemptionType;
    private CouponType couponType;
    private Integer pointsRequired;
    private BigDecimal equivalentValue;
}
