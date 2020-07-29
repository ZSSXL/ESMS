package com.zss.esms.web.config;

import com.zss.esms.web.config.interceptor.EmployeeInterceptor;
import com.zss.esms.web.config.interceptor.ManagerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/15 10:09
 * @desc 用户请求 - 多拦截配置
 */
@Slf4j
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final ManagerInterceptor managerInterceptor;
    private final EmployeeInterceptor employeeInterceptor;

    @Autowired
    public InterceptorConfig(ManagerInterceptor managerInterceptor, EmployeeInterceptor employeeInterceptor) {
        this.managerInterceptor = managerInterceptor;
        this.employeeInterceptor = employeeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截管理员接口，除了登录
        registry.addInterceptor(managerInterceptor)
                .addPathPatterns("/manager/**")
                .excludePathPatterns("/manager/login");
        // 拦截雇员接口，除了登录
        registry.addInterceptor(employeeInterceptor)
                .addPathPatterns("/emp/**")
                .excludePathPatterns("/emp/login");
    }

    /*@Override
    // 在此拦截器中配置允许跨域，由于拦截器和跨域的顺序问题，还是会出现跨域的问题
    // 解决方法：将cors放在filter中
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
    }*/
}
