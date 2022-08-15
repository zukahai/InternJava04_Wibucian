package com.java04.wibucian.commons;

public class Constant {
    // ngày bắt đầu và kết thúc lên kế hoạch làm việc, từ thứ 2 đến cuối thứ 3
    public static final int WORK_PLAN_START_DAY = DayOfWeek.MONDAY.getValue();
    public static final int WORK_PLAN_END_DAY = DayOfWeek.TUESDAY.getValue();

    // ngày bắt đầu và kết thúc đăng ký ca làm (từ thứ 4 đến thứ 6)
    public static final int SHIFT_REQUEST_START_DAY = DayOfWeek.WEDNESDAY.getValue();
    public static final int SHIFT_REQUEST_END_DAY = DayOfWeek.FRIDAY.getValue();

    // ngày approve đăng ký ca làm việc (thứ 7)
    public static final int SHIFT_APPROVE_DAY = DayOfWeek.SATURDAY.getValue();

    // số lượng tối đa ca làm việc chính thức trong tuần
    public static final int MAX_SHIFT_REQUEST_PER_WEEK = 3;

    public static final int NUM_OF_DAYS_IN_WEEK = DayOfWeek.values().length;

    // thời gian tối thiểu trước giờ bắt đầu ca làm việc để có thể yêu cầu xoay ca, đơn
    // vị giờ
    public static final int MIN_HOURS_TO_CREATE_SHIFT_ROTATE_REQUEST = 12;

    // định dạng ngày

    public static final String YYYY_MM_FORMAT = "yyyy-MM";

    public static final String MM_YYYY_FORMAT = "MM-yyyy";
    public static final String DD_MM_YYYY_FORMAT = "dd-MM-yyyy";
    public static final String YYYY_MM_DD_FORMAT = "yyyy-MM-dd";
    public static final String DD_MM_YYYY_HH_MM_SS_FORMAT = "dd-MM-yyyy hh:mm:ss";

    // các mốc thời gian bắt đầu và kết thúc ca làm việc
    public static final String MORNING_SHIFT_START_TIME = "07:00:00";
    public static final String MORNING_SHIFT_END_TIME = "12:00:00";
    public static final String AFTERNOON_SHIFT_START_TIME = "12:30:00";
    public static final String AFTERNOON_SHIFT_END_TIME = "17:30:00";
    public static final String EVENING_SHIFT_START_TIME = "18:00:00";
    public static final String EVENING_SHIFT_END_TIME = "23:00:00";

    // các giá trị cron job

    // thời gian mở lập kế hoạch làm việc, vào 00:00:00 thứ 2 hàng tuần
    public static final String WORK_PLAN_OPEN_CRON = "00 00 00 ? * MON";

    // thời gian đóng lập kế hoạch làm việc, vào 23:59:59 thứ 3 hàng tuần
    public static final String WORK_PLAN_CLOSE_CRON = "59 59 23 ? * TUE";

    // thời gian mở đăng ký làm việc, vào 00:00:00 thứ 4 hàng tuần
    public static final String SHIFT_REQUEST_OPEN_CRON = "00 00 00 ? * WED";

    // thời gian đóng đăng ký làm việc, vào 23:59:59 thứ 6 hàng tuần
    public static final String SHIFT_REQUEST_CLOSE_CRON = "59 59 23 ? * FRI";

    // thời gian mở review đăng ký ca làm việc, vào 00:00:00 thứ 7 hàng tuần
    public static final String SHIFT_REQUEST_REVIEW_OPEN_CRON = "00 00 00 ? * SAT";

    // thời gian đóng review đăng ký ca làm việc, vào 23:59:59 thứ 7 hàng tuần
    public static final String SHIFT_REQUEST_REVIEW_CLOSE_CRON = "59 59 23 ? * SAT";

    // các mốc thời gian hạn cuối để phê duyệt một yêu cầu xoay ca, trước lúc bắt đầu
    // các ca làm 3 tiếng
    public static final String MORNING_SHIFT_ROTATE_CLOSE_CRON = "00 00 04 ? * *";

    public static final String AFTERNOON_SHIFT_ROTATE_CLOSE_CRON = "00 30 09 ? * *";

    public static final String EVENING_SHIFT_ROTATE_CLOSE_CRON = "00 15 00 ? * *";
}
