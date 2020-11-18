package com.zss.esms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 14:08
 * @desc esms service - 启动类
 */
@SpringBootApplication(scanBasePackages = {"com.zss.esms", "com.zss.sub"})
@ImportResource("classpath:config/spring-dubbo-provider.xml")
public class EsmsServiceApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EsmsServiceApplication.class, args);
    }

    /**
     * 实现使用外置Tomcat运行SpringBoot
     *
     * @param builder Spring程序构建对象
     * @return WTF
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EsmsServiceApplication.class);
    }
}
