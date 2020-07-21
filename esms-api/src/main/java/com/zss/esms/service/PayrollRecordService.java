package com.zss.esms.service;

import com.zss.esms.model.entity.postgres.PayrollRecord;

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
}
