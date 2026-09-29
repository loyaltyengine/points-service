package org.loyaltyengine.points_service.modules.coupons.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.loyaltyengine.points_service.shared.models.Amount;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CreateCouponDto {
    private String propertyId;
    private String campaignId;
    private String customerId;
    private String couponType;
    private String description;
    private Integer usageLimit;
    private Boolean isMultiUser;
    private String prefix;
    private Amount amount;
    private BigDecimal percentage;
    private Integer couponValidNumberOfDays;
}
