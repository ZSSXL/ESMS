package com.zss.esms.repository;

import com.zss.esms.model.entity.postgres.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 13:32
 * @desc 雇员 - 持久化
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    /**
     * 通过员工名称获取员工
     *
     * @param empName 员工名称
     * @return Employee
     */
    Employee findByEmpName(String empName);

    /**
     * 通过用户名和密码查找对象
     *
     * @param empName     员工名称
     * @param empPassword 员工密码
     * @return Employee
     */
    Employee findByEmpNameAndEmpPassword(String empName, String empPassword);
}
