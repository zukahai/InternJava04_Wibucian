package com.java04.wibucian.commons;

import java.util.HashMap;
import java.util.Map;

public class ShiftOfDayConversion {

    private static final Map<Integer, ShiftOfDay> ShiftOfDayMapping = new HashMap<>();
    private static final Map<Integer, Integer[]> ShiftOfDayToHourMapping = new HashMap<>();

    public static ShiftOfDay getShiftOfDayFromValue(int value) {
        return ShiftOfDayMapping.get(value);
    }

    static {
        for (ShiftOfDay shiftOfDay : ShiftOfDay.values()) {
            ShiftOfDayMapping.put(shiftOfDay.getValue(), shiftOfDay);
        }
    }
}
