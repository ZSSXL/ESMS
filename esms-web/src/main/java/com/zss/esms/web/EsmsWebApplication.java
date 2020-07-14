package com.zss.esms.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 10:34
 * @desc ESMS Web - 启动类
 */
@SpringBootApplication
@ImportResource("classpath:config/spring-dubbo-consumer.xml")
public class EsmsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsmsWebApplication.class, args);
    }
}
