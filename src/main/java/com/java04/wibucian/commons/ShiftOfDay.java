package com.java04.wibucian.commons;

import java.util.HashMap;
import java.util.Map;

public enum ShiftOfDay {
    MORNING(1),
    AFTERNOON(2),
    EVENING(3);

    private int value;

    private ShiftOfDay(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

