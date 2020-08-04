package com.zss.esms.web.config.interceptor;

import com.zss.esms.comment.Constant;
import com.zss.esms.exception.BizException;
import com.zss.esms.exception.PermissionException;
import com.zss.esms.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/15 10:07
 * @desc Employee - 请求拦截
 */
@Slf4j
@Component
public class EmployeeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            log.warn("非法访问接口，身份校验信息不存在！！！");
            throw new BizException("非法访问接口，身份校验信息不存在！！！");
        } else {
            if (TokenUtil.isValid(token)) {
                String role = TokenUtil.getClaim(token, "role").asString();
                if (StringUtils.equals(role, Constant.Role.EMPLOYEE)) {
                    return true;
                } else {
                    throw new PermissionException("无访问权限, 请重新尝试!!!");
                }
            } else {
                log.warn("身份校验信息已经过期！！！");
                throw new BizException("身份校验信息已经过期！！！");
            }
        }
    }
}
