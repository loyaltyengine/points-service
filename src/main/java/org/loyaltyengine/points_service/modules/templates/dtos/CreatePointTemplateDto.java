package org.loyaltyengine.points_service.modules.templates.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePointTemplateDto {
    private String propertyId;
    private String name;
    private String description;
    private Integer numberOfPoints;
    private Integer validNumberOfDays;
}
