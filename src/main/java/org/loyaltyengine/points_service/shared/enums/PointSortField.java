package org.loyaltyengine.points_service.shared.enums;

import org.loyaltyengine.points.client.models.ErrorType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.loyaltyengine.points_service.core.exceptions.BadRequestException;

@Getter
@AllArgsConstructor
public enum PointSortField {
    CREATED_AT("createdAt"),
    EXPIRE_AT("expireAt"),
    VALID_FROM("validFrom");

    private final String value;

    public static PointSortField fromValue(final String value) {
        for (PointSortField sortField : PointSortField.values()) {
            if (sortField.value.equals(value)) {
                return sortField;
            }
        }

        throw new BadRequestException(ErrorType.INVALID_REQUEST, "Invalid sort field",
                "Invalid sort field: " + value + ". Supported values: [createdAt, expireAt, validFrom]");
    }

}
