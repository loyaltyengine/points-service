package org.loyaltyengine.points_service.modules.redemptions.utils;

import lombok.Getter;

@Getter
public enum RedemptionType {
    CASHBACK("CASHBACK"),
    COUPON("COUPON");

    private final String value;

    RedemptionType(final String value) {
        this.value = value;
    }
}
