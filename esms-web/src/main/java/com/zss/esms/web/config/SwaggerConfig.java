package com.zss.esms.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/4 9:45
 * @desc Swagger - 配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiEmp() {
        Docket build = new Docket(DocumentationType.SWAGGER_2)
                // 添加api详情信息，参数为ApiInfo类型的参数，
                // 这个参数包含了第二部分的所有信息比如标题、描述、版本之类的，开发中一般都会自定义这些信息
                .apiInfo(apiInfo())
                // 配置是否启用Swagger，如果是false，在浏览器将无法访问，默认是true
                .enable(true)
                // 启动用于api选择的构建器
                .select()
                // 添加过滤条件 - 选择所有
                // 也可 RequestHandlerSelectors.basePackage("com.zss.emsm.web.controller")
                .apis(RequestHandlerSelectors.basePackage("com.zss.esms.web.controller.emp"))
                // 这里是控制哪些路径的api会被显示出来
                .paths(PathSelectors.any())
                .build();
        // 分组名称
        build.groupName("esms_emp_server");
        return build.globalOperationParameters(globalOperation());
    }

    @Bean
    public Docket apiManager() {
        Docket build = new Docket(DocumentationType.SWAGGER_2)
                // 添加api详情信息，参数为ApiInfo类型的参数，
                // 这个参数包含了第二部分的所有信息比如标题、描述、版本之类的，开发中一般都会自定义这些信息
                .apiInfo(apiInfo())
                // 配置是否启用Swagger，如果是false，在浏览器将无法访问，默认是true
                .enable(true)
                // 启动用于api选择的构建器
                .select()
                // 添加过滤条件 - 选择所有
                // 也可 RequestHandlerSelectors.basePackage("com.zss.emsm.web.controller")
                // 或者 RequestHandlerSalectors.any()
                .apis(RequestHandlerSelectors.basePackage("com.zss.esms.web.controller.manager"))
                // 这里是控制哪些路径的api会被显示出来
                .paths(PathSelectors.any())
                .build();
        // 分组名称
        build.groupName("esms_manager_server");
        return build.globalOperationParameters(globalOperation());
    }

    /**
     * 配置swagger页面的头部
     *
     * @return API信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 标题内容
                .title("雇员信息管理系统")
                // 描述
                .description("雇员薪资管理系统(ESMS)")
                // 联系人信息
                .contact(new Contact("ZSS",
                        "https://github.com/ZSSXL/ESMS",
                        "1271130458@qq.com"))
                // 版本
                .version("1.0.0")
                .build();
    }

    /**
     * 接口单独传token
     *
     * @return List<Parameter>
     */
    private List<Parameter> globalOperation() {
        // 添加Header参数
        ParameterBuilder pb = new ParameterBuilder();
        List<Parameter> parameterList = new ArrayList<>();
        Parameter par = pb.name("token")
                .description("token")
                .modelRef(new ModelRef("String"))
                .parameterType("header")
                .required(false)
                .build();
        parameterList.add(par);
        return parameterList;
    }
}
