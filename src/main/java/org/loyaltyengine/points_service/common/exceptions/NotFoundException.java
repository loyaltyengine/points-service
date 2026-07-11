package org.loyaltyengine.points_service.common.exceptions;

import org.loyaltyengine.points.v1.model.ErrorType;

import lombok.Getter;

@Getter
public class NotFoundException extends ApiException {

    public NotFoundException(ErrorType errorType, String message, String description) {
        super(errorType, message, description);
    }
}
