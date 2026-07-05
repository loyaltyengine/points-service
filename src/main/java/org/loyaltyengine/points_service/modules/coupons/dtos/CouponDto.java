package org.loyaltyengine.points_service.modules.coupons.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CouponDto {
    private String id;
    private String propertyId;
    private String customerId;
    private String couponCode;
    private String couponType;
    private Integer percentage;
    private String campaignId;
    private String prefix;
    private Boolean isActive;
}
