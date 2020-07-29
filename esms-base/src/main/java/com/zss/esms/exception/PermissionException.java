package com.zss.esms.exception;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/29 10:46
 * @desc 权限异常
 */
@SuppressWarnings("unused")
public class PermissionException extends Exception {

    public PermissionException() {
    }

    public PermissionException(String message) {
        super(message);
    }
}
