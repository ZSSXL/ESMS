package com.zss.esms.repository;

import com.zss.esms.model.entity.postgres.PayrollRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 13:34
 * @desc 工资发放记录 - 持久化
 */
@Repository
public interface PayrollRecordRepository extends JpaRepository<PayrollRecord, String> {
}
