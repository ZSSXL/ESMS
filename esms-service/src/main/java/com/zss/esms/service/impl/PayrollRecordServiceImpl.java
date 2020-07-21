package com.zss.esms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zss.esms.model.entity.postgres.PayrollRecord;
import com.zss.esms.repository.PayrollRecordRepository;
import com.zss.esms.service.PayrollRecordService;

import javax.annotation.Resource;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 14:12
 * @desc 工资发放记录 - 实现服务层接口方法
 */
@SuppressWarnings("unused")
@Service(interfaceClass = PayrollRecordService.class)
public class PayrollRecordServiceImpl implements PayrollRecordService {

    @Resource
    private PayrollRecordRepository payrollRecordRepository;

    @Override
    public PayrollRecord savePayrollRecord(PayrollRecord payrollRecord) {
        return payrollRecordRepository.save(payrollRecord);
    }
}
