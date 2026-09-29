package org.loyaltyengine.points_service.modules.templates.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
public class PointTemplateDto {
    private String pointTemplateId;
    private String propertyId;
    private String name;
    private String description;
    private Integer numberOfPoints;
    private Integer validNumberOfDays;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
