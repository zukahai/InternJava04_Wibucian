package com.java04.wibucian.commons;

public class Constant {
    // ngày bắt đầu và kết thúc đăng ký ca làm (từ thứ 4 đến thứ 6)
    public static final int SHIFT_REQUEST_START_DAY = DayOfWeek.WEDNESDAY.getValue();
    public static final int SHIFT_REQUEST_END_DAY = DayOfWeek.SATURDAY.getValue();
    public static final int SHIFT_APPROVE_DAY = DayOfWeek.SATURDAY.getValue();
    public static final int MAX_SHIFT_REQUEST_PER_WEEK = 3;

    public static final int NUM_OF_DAYS_IN_WEEK = DayOfWeek.values().length;
    public static final String DD_MM_YYYY_FORMAT = "dd-MM-yyyy";
    public static final String YYYY_MM_DD_FORMAT = "yyyy-MM-dd";
    public static final String MORNING_SHIFT_START_TIME = "07:00:00";
    public static final String MORNING_SHIFT_END_TIME = "12:00:00";
    public static final String AFTERNOON_SHIFT_START_TIME = "12:30:00";
    public static final String AFTERNOON_SHIFT_END_TIME = "17:30:00";
    public static final String EVENING_SHIFT_START_TIME = "18:00:00";
    public static final String EVENING_SHIFT_END_TIME = "23:00:00";
}
