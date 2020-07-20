package com.zss.esms.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

}
