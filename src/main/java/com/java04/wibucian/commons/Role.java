package com.java04.wibucian.commons;

public enum Role {
    STAFF(0), ADMIN(1);

    private int value;

    private Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
