package com.zss.esms.exception;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/15 11:31
 * @desc 数据库插入异常 - 自定义异常类
 */
@SuppressWarnings("unused")
public class InsertException extends Exception {

    public InsertException() {
    }

    public InsertException(String message) {
        super(message);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }
}
