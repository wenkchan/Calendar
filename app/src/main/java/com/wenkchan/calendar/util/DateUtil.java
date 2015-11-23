package com.wenkchan.calendar.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/23 0023.
 */
public class DateUtil {
    /**
     * 获取指定月份有多少天
     * @param year 哪一年
     * @param month 哪个月
     * @return 指定月份的天数
     */
    public static int getMonthDays(int year, int month) {
        if (month > 12) {
            month = 1;
            year += 1;
        } else if (month < 1) {
            month = 12;
            year -= 1;
        }
        int[] arr = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int days = 0;

        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            arr[1] = 29; // 闰年2月29天
        }

        try {
            days = arr[month - 1];
        } catch (Exception e) {
            e.getStackTrace();
        }

        return days;
    }
    @SuppressLint("SimpleDateFormat")
    public static Date getDateFromString(int year, int month) {
        String dateString = year + "-" + (month > 9 ? month : ("0" + month))
                + "-01";
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return date;
    }

    /**
     * 获取指定月份第一天是星期几
     * @param year
     * @param month
     * @return
     */
    public static int getWeekDayFromDate(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDateFromString(year, month));
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return week_index;
    }

    /**
     * 获取当前的年份
     * @return
     */
    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取当前的月份
     * @return
     */
    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日期在当月的第几天
     * @return
     */
    public static int getDayOfMoth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
}
