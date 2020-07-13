package com.zss.esms.repository;

import com.zss.esms.model.entity.postgres.SalesReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 13:37
 * @desc 销售凭证 - 持久化
 */
@Repository
public interface SalesReceiptRepository extends JpaRepository<SalesReceipt, String> {
}
