package org.loyaltyengine.points_service.modules.redemptions.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.loyaltyengine.points_service.modules.redemptions.utils.RedemptionType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Setter
@Getter
@Builder
public class RedemptionDto {
    private String redemptionId;
    private String redemptionRuleId;
    private RedemptionType redemptionType;
    private String couponCode;
    private String propertyId;
    private String customerId;
    private Integer numberOfPoints;
    private BigDecimal calculatedValue;
    private Integer totalRemainingPoints;
    private Integer totalDebitedPoints;
    private Integer couponValidNumberOfDays;
    private OffsetDateTime createdAt;
}
