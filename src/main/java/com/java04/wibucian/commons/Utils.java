package com.java04.wibucian.commons;

import com.java04.wibucian.exception.BadRequestException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static String getDateFormat(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Calendar getCurrentDate() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(new Date());
        if (currentDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
//            currentDate.add(Calendar.DATE, -1);
//            currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        } else {
//            currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        }

        return currentDate;
    }

    public static Calendar currentDateWithoutTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar currentDateWithoutTime = Calendar.getInstance();
        try {
            currentDateWithoutTime.setTime(
                    sdf.parse(sdf.format(getCurrentDate().getTime())));
            return currentDateWithoutTime;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Calendar getFirstDayOfNextWeek() {
        Calendar currentDateWithoutTime = currentDateWithoutTime();
        Calendar firstDayOfNextWeek = Calendar.getInstance();
        firstDayOfNextWeek.setTime(currentDateWithoutTime.getTime());
        if (firstDayOfNextWeek.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            firstDayOfNextWeek.add(Calendar.DATE, 1);
        } else {
            firstDayOfNextWeek.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            firstDayOfNextWeek.add(Calendar.DATE, Calendar.DAY_OF_WEEK);
        }
        return firstDayOfNextWeek;
    }

    public static Calendar getLastDayOfNextWeek() {
        Calendar lastDayOfNextWeek = getFirstDayOfNextWeek();
        lastDayOfNextWeek.add(Calendar.DATE, -1 + Calendar.DAY_OF_WEEK);
        return lastDayOfNextWeek;
    }

    public static String[] getShiftTimeRangeFromShiftDateAndCode(Date shiftDate,
                                                                 int shiftCode) {
        String[] result = new String[2];
        String date = getDateFormat(shiftDate, Constant.YYYY_MM_DD_FORMAT);
        ShiftOfDay shiftOfDay = ShiftOfDayConversion.getShiftOfDayFromValue(shiftCode);
        result[0] = date + "T" + shiftOfDay.getStartTime();
        result[1] = date + "T" + shiftOfDay.getEndTime();
        return result;
    }

    public static Map<Integer, String> getWeekDayMapping(Calendar weekStart,
                                                         Calendar weekEnd) {
        Map<Integer, String> weekDayMapping = new HashMap<>();
        while (weekStart.compareTo(weekEnd) <= 0) {
            weekDayMapping.put(weekStart.get(Calendar.DAY_OF_WEEK),
                               Utils.getDateFormat(weekStart.getTime(),
                                                   Constant.DD_MM_YYYY_FORMAT));
            weekStart.add(Calendar.DATE, 1);
        }
        return weekDayMapping;
    }


    public static Calendar getCalendarInstanceFromFormat(String input, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(input));
            return calendar;
        } catch (ParseException e) {
            throw new BadRequestException();
        }
    }

    public static long hoursBetween(Calendar from, Calendar to) {
        long result = ChronoUnit.HOURS.between(from.toInstant(), to.toInstant());
        return result;
    }

    public static Calendar getCalendarInstanceFromDateAndHHMMSSTimeString(Date date,
                                                                          String hh_mm_ss) {
        return Utils.getCalendarInstanceFromFormat(
                Utils.getDateFormat(date, Constant.DD_MM_YYYY_FORMAT) + " " + hh_mm_ss,
                Constant.DD_MM_YYYY_HH_MM_SS_FORMAT);
    }

    public static Calendar getWeekStartFromDate(Date date) {
        Calendar weekStart = Calendar.getInstance();
        weekStart.setTime(date);
        int dayOfWeek = weekStart.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SUNDAY) {
            weekStart.add(Calendar.DATE, -1);
        }
        weekStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return weekStart;
    }

    public static Calendar getWeekEndFromDate(Date date) {
        Calendar weekEnd = getWeekStartFromDate(date);
        weekEnd.add(Calendar.DATE, -1 + Calendar.DAY_OF_WEEK);
        return weekEnd;
    }

}
