package com.lyan.tools.entity;

/**
 * 日期
 * Created by liyanjun on 2017/6/15.
 */

public class SmallDate {
    private int year;
    private int month;
    private int day;

    public SmallDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
