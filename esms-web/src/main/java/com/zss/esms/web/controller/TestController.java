package com.zss.esms.web.controller;

import com.zss.esms.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/15 10:20
 * @desc 测试专用 - 控制器
 */
@Slf4j
@RestController
@RequestMapping("/manager/test")
public class TestController {

    /**
     * 拦截器测试
     *
     * @return String
     */
    @GetMapping
    public ServerResponse<String> interceptorTest() {
        System.out.println("拦截器测试...");
        return ServerResponse.createBySuccess();
    }
}
