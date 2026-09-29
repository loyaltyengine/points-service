package org.loyaltyengine.points_service.core.exceptions;

import org.loyaltyengine.points.client.models.ErrorType;

import lombok.Getter;

@Getter
public class NotFoundException extends ApiException {

    public NotFoundException(ErrorType errorType, String message, String description) {
        super(errorType, message, description);
    }
}
