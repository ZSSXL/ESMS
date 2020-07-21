package com.zss.esms.repository;

import com.zss.esms.model.entity.postgres.SalesReceipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 13:37
 * @desc 销售凭证 - 持久化
 */
@Repository
public interface SalesReceiptRepository extends JpaRepository<SalesReceipt, String> {
    /**
     * 查看自己的销售凭条
     *
     * @param empId    员工Id
     * @param pageable 分页请求
     * @return Page<SalesReceipt>
     */
    Page<SalesReceipt> findAllByEmpIdOrderByReceiptDateDesc(String empId, Pageable pageable);

    /**
     * 指定雇员查询，检查时间
     *
     * @param empId 员工Id
     * @param start 开始时间
     * @param end   结束时间
     * @return SaleReceipt
     */
    SalesReceipt findByEmpIdAndReceiptDateBetween(String empId, String start, String end);

    /**
     * 统计指定范围内的销售数量
     *
     * @param empId 雇员Id
     * @param start 开始时间
     * @param end   结束时间
     * @return Integer
     */
    @Query(value = "select sum(quantity) from esms_sales_receipt where emp_id = ?1 and receipt_date between ?2 and ?3", nativeQuery = true)
    Integer findAllByEmpIdAndReceiptDateBetween(String empId, String start, String end);
}
