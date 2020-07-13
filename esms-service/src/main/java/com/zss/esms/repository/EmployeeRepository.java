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
}
