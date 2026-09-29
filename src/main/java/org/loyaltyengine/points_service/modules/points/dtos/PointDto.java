package org.loyaltyengine.points_service.modules.points.dtos;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PointDto {
    private String pointId;
    private String propertyId;
    private String customerId;
    private String pointTemplateId;
    private Integer remainingPoints;
    private Integer numberOfPoints;
    private Boolean exchangeable;
    private String description;
    private OffsetDateTime createdAt;
    private OffsetDateTime validFrom;
    private OffsetDateTime expireAt;
    private OffsetDateTime updatedAt;
}
