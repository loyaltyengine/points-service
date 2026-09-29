package org.loyaltyengine.points_service.modules.redemptions.services;

import org.loyaltyengine.points.client.models.ErrorDetail;
import org.loyaltyengine.points.client.models.ErrorType;
import org.loyaltyengine.points_service.core.exceptions.BadRequestException;
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
            errors.add(new ErrorDetail().field("amountCurrency").issue("Amount currency is required"));
        }

        if (isCurrencyRequired && !SharedUtils.isValidCurrency(dto.getAmountCurrency())) {
            errors.add(new ErrorDetail().field("amountCurrency").issue("Invalid currency code"));
        }

        if(dto.getRedemptionType()==RedemptionType.COUPON && dto.getCouponValidNumberOfDays()==null){
            errors.add(new ErrorDetail().field("couponValidNumberOfDays").issue("Coupon valid number of days is required"));
        }

        if (!errors.isEmpty()) {
            throw new BadRequestException(ErrorType.VALIDATION_ERROR, "Redemption rule validation failed",
                    "Validation failed while creating redemption rule", errors);
        }

    }
}
