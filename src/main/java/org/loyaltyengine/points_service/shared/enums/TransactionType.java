package org.loyaltyengine.points_service.shared.enums;

public enum TransactionType {
    CREDIT("CREDIT"),
    DEBIT("DEBIT");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String getName() {
        return value;
    }
}
