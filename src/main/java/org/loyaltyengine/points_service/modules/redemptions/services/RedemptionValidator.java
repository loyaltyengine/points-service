package org.loyaltyengine.points_service.modules.redemptions.services;

import org.loyaltyengine.points.v1.model.ErrorDetail;
import org.loyaltyengine.points.v1.model.ErrorType;
import org.loyaltyengine.points_service.common.exceptions.BadRequestException;
import org.loyaltyengine.points_service.modules.redemptions.dtos.CreateRedemptionRuleDto;
import org.loyaltyengine.points_service.modules.redemptions.utils.RedemptionType;
import org.loyaltyengine.points_service.shared.enums.CouponType;
import org.loyaltyengine.points_service.shared.utils.SharedUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RedemptionValidator {

    public void validateRedemptionRule(CreateRedemptionRuleDto dto) {
        List<ErrorDetail> errors = new ArrayList<>();

        // Validate currency
        boolean isCurrencyRequired = dto.getRedemptionType() == RedemptionType.CASHBACK
                || dto.getRedemptionType() == RedemptionType.COUPON && dto.getCouponType() == CouponType.FIXED_AMOUNT;

        if (isCurrencyRequired && dto.getAmountCurrency() == null) {
            errors.add(new ErrorDetail("amountCurrency", "Amount currency is required"));
        }

        if (isCurrencyRequired && !SharedUtils.isValidCurrency(dto.getAmountCurrency())) {
            errors.add(new ErrorDetail("amountCurrency", "Invalid currency code"));
        }

        if (!errors.isEmpty()) {
            throw new BadRequestException(ErrorType.VALIDATION_ERROR, "Redemption rule validation failed",
                    "Validation failed while creating redemption rule", errors);
        }

    }
}
