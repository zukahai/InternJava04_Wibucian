package com.java04.wibucian.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    public static String getDateFormat(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Calendar getCurrentDate() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(new Date());
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
        firstDayOfNextWeek.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        firstDayOfNextWeek.add(Calendar.DATE, Calendar.DAY_OF_WEEK);
        return firstDayOfNextWeek;
    }

    public static Calendar getLastDayOfNextWeek() {
        Calendar lastDayOfNextWeek = getFirstDayOfNextWeek();
        lastDayOfNextWeek.add(Calendar.DATE, -1 + Calendar.DAY_OF_WEEK);
        return lastDayOfNextWeek;
    }
}
