package com.zss.esms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zss.esms.model.entity.postgres.PayrollRecord;
import com.zss.esms.page.Pagenation;
import com.zss.esms.repository.PayrollRecordRepository;
import com.zss.esms.service.PayrollRecordService;
import com.zss.esms.util.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Override
    public Pagenation showAllPayrollRecord(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "releaseDate"));
        Page<PayrollRecord> payrollRecordPage = payrollRecordRepository.findAll(pageable);
        return PageUtil.converter(payrollRecordPage);
    }

    @Override
    public Pagenation showAllPayrollRecordById(String empId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PayrollRecord> payrollRecordPage = payrollRecordRepository
                .findAllByEmpIdOrderByReleaseDateDesc(empId, pageable);
        return PageUtil.converter(payrollRecordPage);
    }

    @Override
    public Boolean confimReceive(String empId, String payrollRecordId) {
        Integer result = payrollRecordRepository.updateConfirmByEmpIdAndPayrollRecordId(empId, payrollRecordId);
        return result == 1;
    }
}
