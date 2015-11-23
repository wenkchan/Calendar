package com.wenkchan.calendar.bean;

import com.wenkchan.calendar.util.DateUtil;

/**
 * Created by Administrator on 2015/11/23 0023.
 */
public class CalendarDate {


    private int month;
    private int year;
    private int day;


    public CalendarDate(int year, int month, int day) {
        if (month > 12) {//用于设置当前年份和月份
            month = 1;
            year++;
        } else if (month < 1) {
            month = 12;
            year--;
        }
        this.month = month;
        this.year = year;
        this.day = day;
    }


    public CalendarDate() {
        this.month = DateUtil.getMonth();
        this.year = DateUtil.getYear();
        this.day = DateUtil.getDayOfMoth();
    }

    public static CalendarDate modifyDayForObject(CalendarDate date) {
        CalendarDate modifyDate = new CalendarDate(date.year, date.month,0);
        return modifyDate;
    }



    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return year+"-"+month+"-"+day;
    }
}
