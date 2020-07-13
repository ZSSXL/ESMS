package com.zss.esms.util;

import java.util.UUID;

/**
 * @author ZSS
 * @date 2020/3/17 20:34
 * @desc Id 工具包
 */
@SuppressWarnings("unused")
public class IdUtil {

    /**
     * 获取uuid
     *
     * @return String
     */
    public static String getId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
