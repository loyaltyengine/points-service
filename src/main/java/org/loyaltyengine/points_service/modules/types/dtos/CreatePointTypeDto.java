package org.loyaltyengine.points_service.modules.types.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePointTypeDto {
    private String propertyId;
    private String name;
    private String description;
    private Integer numberOfPoints;
}
