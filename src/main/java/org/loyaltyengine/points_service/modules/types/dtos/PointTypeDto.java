package org.loyaltyengine.points_service.modules.types.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PointTypeDto {
    private String pointTypeId;
    private Integer numberOfPoints;
}
