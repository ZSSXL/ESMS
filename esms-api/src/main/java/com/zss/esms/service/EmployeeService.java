package com.zss.esms.service;

import com.zss.esms.model.dto.LoginDTO;
import com.zss.esms.model.entity.postgres.Employee;
import com.zss.esms.page.Pagenation;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 13:27
 * @desc 雇员 - 服务层接口
 */
public interface EmployeeService {

    /**
     * 解析Excel文件，批量添加员工
     *
     * @param fileBytes        Excel文件
     * @param originalFilename 文件名
     * @param cells            需要的列数
     * @return Interger 最终添加成功数量
     */
    Integer createManayEmp(byte[] fileBytes, String originalFilename, Integer cells);

    /**
     * 获取所有的员工信息
     *
     * @param page 当前页
     * @param size 每页大小
     * @return Page<Profile>
     */
    Pagenation showAllEmployees(Integer page, Integer size);

    /**
     * 员工登录
     *
     * @param loginDTO 登录信息传输对象
     * @return Employee
     */
    Employee employeeLogin(LoginDTO loginDTO);
}