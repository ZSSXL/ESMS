package com.zss.esms.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author ZSS
 * @date 2020/3/19 19:49
 * @desc 时间工具
 */
@Slf4j
@SuppressWarnings("unused")
public class TimeUtil {

    /**
     * 获取当前时间
     *
     * @return String
     */
    public static String getTimestamp() {
        long timeMillis = System.currentTimeMillis();
        return String.valueOf(timeMillis);
    }

    /**
     * 获取一天的起始时间 00:00:00
     *
     * @param timestamp 时间戳
     * @return String
     */
    public static String getStart(String timestamp) {
        Date date = new Date(Long.parseLong(timestamp));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        try {
            Date parse = simpleDateFormat.parse(format);
            return String.valueOf(parse.getTime());
        } catch (ParseException e) {
            log.error("时间转化失败... [{}]", e.getMessage());
            return null;
        }
    }

    /**
     * 获取一天中23：59：59的时间戳
     *
     * @param timestamp 时间戳
     * @return String
     */
    public static String getEnd(String timestamp) {
        Date date = new Date(Long.parseLong(timestamp));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date) + " 23:59:59";
        try {
            Date parse = dateFormat.parse(format);
            return String.valueOf(parse.getTime());
        } catch (Exception e) {
            log.error("时间转化失败... [{}]", e.getMessage());
            return null;
        }
    }

    /**
     * 获取本周的开始时间
     *
     * @return Date
     */
    public static String getBeginDayOfWeek() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getStart(String.valueOf(cal.getTime().getTime()));
    }

    /**
     * 获取本周的结束时间
     *
     * @return Date
     */
    public static String getEndDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(Long.parseLong(getBeginDayOfWeek())));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getEnd(String.valueOf(weekEndSta.getTime()));
    }

    /**
     * 获取上周的开始时间
     *
     * @return Date
     */
    public static String getBeginDayOfLastWeek() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek - 7);
        return getStart(String.valueOf(cal.getTime().getTime()));
    }

    /**
     * 获取上周的结束时间
     *
     * @return Date
     */
    public static String getEndDayOfLastWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(Long.parseLong(getBeginDayOfLastWeek())));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getEnd(String.valueOf(weekEndSta.getTime()));
    }

    /**
     * 获取本月的结束时间
     *
     * @return String
     */
    public static String getEndDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        int day = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(getNowYear(), getNowMonth() - 1, day);
        return getEnd(String.valueOf(calendar.getTime().getTime()));
    }

    /**
     * 获取今年是哪一年
     *
     * @return Integer
     */
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(Calendar.YEAR);
    }

    /**
     * 获取本月是哪一月
     *
     * @return int
     */
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(Calendar.MONTH) + 1;
    }

    /**
     * 判断今天是星期几
     *
     * @param week 星期
     * @return Boolean
     */
    public static Boolean dayOfWeek(Integer week) {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK) == week;
    }

    /**
     * 判断今天是不是月底
     *
     * @return Boolean
     */
    public static Boolean isLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH) == calendar
                .getActualMaximum(Calendar.DAY_OF_MONTH);
    }

}
