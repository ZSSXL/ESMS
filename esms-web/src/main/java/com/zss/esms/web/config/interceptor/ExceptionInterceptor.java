package com.zss.esms.web.config.interceptor;

import com.zss.esms.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/14 16:27
 * @desc 全局异常处理
 */
@Slf4j
@Component
@ControllerAdvice
public class ExceptionInterceptor {

    /**
     * 全局异常处理
     *
     * @param httpServletRequest 客户端请求
     * @param e                  异常
     * @return ModelAndView
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, Exception e) {
        log.error("{} Exception", httpServletRequest.getRequestURI(), e);
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.addObject("status", ResponseCode.ERROR.getCode());
        modelAndView.addObject("msg", "接口异常，详情请查看系统日志中的信息!!!");
        modelAndView.addObject("data", e.getMessage());
        return modelAndView;
    }
}
