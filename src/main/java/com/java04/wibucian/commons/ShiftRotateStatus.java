package com.java04.wibucian.commons;

public enum ShiftRotateStatus {
    CREATED(0), ACCEPTED(1), APPROVED(2), REJECTED(3), CANCELLED(4);

    private final int value;

    private ShiftRotateStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
