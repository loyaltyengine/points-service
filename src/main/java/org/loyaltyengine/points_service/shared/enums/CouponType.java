package org.loyaltyengine.points_service.shared.enums;

import lombok.Getter;
import org.loyaltyengine.points.v1.model.ErrorType;
import org.loyaltyengine.points_service.common.exceptions.BadRequestException;

@Getter
public enum CouponType {
    FIXED_AMOUNT("fixed_amount"),
    PERCENTAGE("percentage");

    private final String value;

    CouponType(final String value) {
        this.value = value;
    }

    public static CouponType fromValue(final String value) {
        for (CouponType couponType : CouponType.values()) {
            if (couponType.value.equals(value)) {
                return couponType;
            }
        }

        throw new BadRequestException(ErrorType.INVALID_REQUEST, "Invalid coupon type", "Invalid coupon type: " + value
                + ". Supported values: [fixed_amount, percentage]");
    }
}
