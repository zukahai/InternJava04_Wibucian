package com.java04.wibucian.commons;

import java.util.HashMap;
import java.util.Map;

public enum ShiftOfDay {
    MORNING(1,
            Constant.MORNING_SHIFT_START_TIME,
            Constant.MORNING_SHIFT_END_TIME),
    AFTERNOON(2,
              Constant.AFTERNOON_SHIFT_START_TIME,
              Constant.AFTERNOON_SHIFT_END_TIME),
    EVENING(3,
            Constant.EVENING_SHIFT_START_TIME,
            Constant.EVENING_SHIFT_END_TIME);

    private final int value;
    private final String startTime;
    private final String endTime;

    private ShiftOfDay(int value, String startTime, String endTime) {
        this.value = value;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getValue() {
        return this.value;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }
}

