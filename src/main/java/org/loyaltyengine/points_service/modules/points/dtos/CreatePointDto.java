package org.loyaltyengine.points_service.modules.points.dtos;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CreatePointDto {
    private String propertyId;
    private String customerId;
    private String pointTypeId;
    private Integer numberOfPoints;
    private Boolean exchangeable;
    private String description;
    private OffsetDateTime validFrom;
    private OffsetDateTime expireAt;
}
