package com.java04.wibucian.commons;

import java.util.HashMap;
import java.util.Map;

public enum DayOfWeek {
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7),
    SUNDAY(1);

    private int value;

    private DayOfWeek(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

