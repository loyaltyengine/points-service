package org.loyaltyengine.points_service.modules.redemptions.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRedemptionDto {
    private String propertyId;
    private String customerId;
    private Integer numberOfPoints;
    private String redemptionRuleId;
}
