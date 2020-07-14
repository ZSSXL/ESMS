package com.zss.esms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 14:08
 * @desc esms service - 启动类
 */
@SpringBootApplication
@ImportResource("classpath:config/spring-dubbo-provider.xml")
public class EsmsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsmsServiceApplication.class, args);
    }
}
