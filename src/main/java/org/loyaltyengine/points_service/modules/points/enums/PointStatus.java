package org.loyaltyengine.points_service.modules.points.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PointStatus {
    ACTIVE("ACTIVE"),
    PENDING("PENDING"),
    INACTIVE("INACTIVE");

    private final String value;
}
