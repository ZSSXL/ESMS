package com.zss.esms.web.controller.emp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zss.esms.response.ServerResponse;
import com.zss.esms.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/17 17:21
 * @desc 雇员 - 控制器
 */
@Slf4j
@RestController("empContro")
@RequestMapping("/emp")
@SuppressWarnings("unused")
public class EmployeeController {

    @Reference
    private EmployeeService employeeService;

    public ServerResponse<String> empLogin() {
        return null;
    }
}
