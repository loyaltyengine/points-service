package org.loyaltyengine.points_service.common.exceptions;

import org.loyaltyengine.openapi.model.ErrorType;

public class ConflictException extends ApiException {

    public ConflictException(ErrorType errorType, String message, String description) {
        super(errorType, message, description);
    }
}
