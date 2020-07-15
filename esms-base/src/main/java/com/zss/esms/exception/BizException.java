package com.zss.esms.exception;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/15 10:27
 * @desc 业务逻辑处理异常 - 自定义异常
 */
@SuppressWarnings("unused")
public class BizException extends Exception {

    public BizException() {
    }

    public BizException(String message) {
        super(message);
    }

}
