package com.zss.esms.web.controller.emp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zss.esms.comment.Constant;
import com.zss.esms.model.dto.LoginDTO;
import com.zss.esms.model.entity.postgres.Employee;
import com.zss.esms.response.ServerResponse;
import com.zss.esms.service.EmployeeService;
import com.zss.esms.util.MapUtil;
import com.zss.esms.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    /**
     * 员工登录
     *
     * @param loginDTO 登录数据传输对象
     * @param result   检验结果
     * @return String
     */
    @PostMapping("/login")
    public ServerResponse<String> empLogin(@RequestBody @Valid LoginDTO loginDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.createByErrorMessage("用户名或密码不能为空");
        } else {
            Employee employee = employeeService.employeeLogin(loginDTO);
            if (employee == null) {
                return ServerResponse.createByErrorMessage("用户名或密码错误，请重新尝试...");
            } else {
                String token = TokenUtil.createToken(MapUtil.create(
                        "role", Constant.Role.EMPLOYEE,
                        "userId", employee.getEmpId(),
                        "username", employee.getEmpName()
                ));
                return ServerResponse.createBySuccess("登录成功", token);
            }
        }
    }
}
