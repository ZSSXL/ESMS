package com.zss.esms.util;

import java.util.UUID;

/**
 * @author ZSS
 * @date 2019/12/3 11:28
 * @desc UUID工具类
 */
@SuppressWarnings("unused")
public class UUIDUtil {

    /**
     * 获取 UUID
     *
     * @return String
     */
    public static String getId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
