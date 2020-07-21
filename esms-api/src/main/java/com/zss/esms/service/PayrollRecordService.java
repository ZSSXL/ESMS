package com.zss.esms.service;

import com.zss.esms.model.entity.postgres.PayrollRecord;
import com.zss.esms.page.Pagenation;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 13:28
 * @desc 工资发放记录 - 服务层接口
 */
public interface PayrollRecordService {

    /**
     * 保存工资发放记录
     *
     * @param payrollRecord 工资发放记录实体
     * @return PayrollRecord
     */
    PayrollRecord savePayrollRecord(PayrollRecord payrollRecord);

    /**
     * 分页获取工资发放记录
     *
     * @param page 当前页
     * @param size 每页大小
     * @return Pagenation
     */
    Pagenation showAllPayrollRecord(Integer page, Integer size);

    /**
     * 通过员工Id获取其工资发放记录
     *
     * @param empId 员工Id
     * @param page  当前页
     * @param size  每页大小
     * @return Pagenation
     */
    Pagenation showAllPayrollRecordById(String empId, Integer page, Integer size);

    /**
     * 确认收款
     *
     * @param empId           员工Id
     * @param payrollRecordId 工资记录Id
     * @return Boolean
     */
    Boolean confimReceive(String empId, String payrollRecordId);
}
