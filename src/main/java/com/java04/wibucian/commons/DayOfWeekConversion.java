package com.java04.wibucian.commons;

import java.util.HashMap;
import java.util.Map;

public class DayOfWeekConversion {

    private static Map<Integer, DayOfWeek> DayOfWeekMapping = new HashMap<>();

    public static DayOfWeek getDayOfWeekFromValue(int value) {
        return DayOfWeekMapping.get(value);
    }

    static {
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            DayOfWeekMapping.put(dayOfWeek.getValue(), dayOfWeek);
        }
    }
}
