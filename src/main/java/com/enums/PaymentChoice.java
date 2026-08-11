package com.enums;

public enum PaymentChoice {
    CARD(true),
    CASH(false),
    SBP(true);

    private final boolean isOnline;

    PaymentChoice(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public boolean isOnline() {
        return isOnline;
    }
}
