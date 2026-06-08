package org.loyaltyengine.points_service.modules.redemptions.utils;

public enum RedemptionType {
    CASHBACK("CASHBACK"),
    COUPON("COUPON");

    private final String value;

    RedemptionType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
