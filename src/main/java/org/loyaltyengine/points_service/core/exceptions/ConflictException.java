package org.loyaltyengine.points_service.core.exceptions;

import org.loyaltyengine.points.client.models.ErrorType;

public class ConflictException extends ApiException {

    public ConflictException(ErrorType errorType, String message, String description) {
        super(errorType, message, description);
    }
}
