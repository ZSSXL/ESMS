package com.zss.esms.util;

/**
 * @author ZSS
 * @date 2020/3/19 19:49
 * @desc 时间工具
 */
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
}
