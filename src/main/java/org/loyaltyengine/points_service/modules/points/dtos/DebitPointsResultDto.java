package org.loyaltyengine.points_service.modules.points.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DebitPointsResultDto {
    private Integer totalRemainingPoints;
    private Integer totalDebited;
}
