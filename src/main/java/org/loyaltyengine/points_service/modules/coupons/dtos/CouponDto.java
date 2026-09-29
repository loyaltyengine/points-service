package org.loyaltyengine.points_service.modules.coupons.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.loyaltyengine.points_service.shared.models.Amount;

@Getter
@Setter
@Builder
public class CouponDto {
    private String propertyId;
    private String customerId;
    private String couponCode;
    private String couponType;
    private Integer percentage;
    private Amount amount;
}
