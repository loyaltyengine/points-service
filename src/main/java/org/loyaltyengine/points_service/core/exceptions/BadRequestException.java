package org.loyaltyengine.points_service.core.exceptions;

import org.loyaltyengine.points.client.models.ErrorType;

import java.util.List;

import org.loyaltyengine.points.client.models.ErrorDetail;
import lombok.Getter;

@Getter
public class BadRequestException extends ApiException {

    public BadRequestException(ErrorType errorType, String message, String description) {
        super(errorType, message, description);
    }

    public BadRequestException(ErrorType errorType, String message, String description,
            List<ErrorDetail> details) {
        super(errorType, message, description, details);
    }
}
