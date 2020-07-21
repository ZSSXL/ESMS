package com.zss.esms.repository;

import com.zss.esms.model.entity.postgres.PayrollRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 13:34
 * @desc 工资发放记录 - 持久化
 */
@Repository
public interface PayrollRecordRepository extends JpaRepository<PayrollRecord, String> {

    /**
     * 通过员工Id获取其工资发放记录
     *
     * @param empId    员工Id
     * @param pageable 分页信息
     * @return Page<PayrollRecord>
     */
    Page<PayrollRecord> findAllByEmpIdOrderByReleaseDateDesc(String empId, Pageable pageable);

    /**
     * 更新确认收款
     *
     * @param empId           员工Id
     * @param payrollRecordId 工资发放记录Id
     * @return Integer
     */
    @Modifying
    @Transactional
    @Query(value = "update esms_payroll_record set confirm_receipt = true where emp_id = ?1 and payroll_id = ?2 ", nativeQuery = true)
    Integer updateConfirmByEmpIdAndPayrollRecordId(String empId, String payrollRecordId);
}
