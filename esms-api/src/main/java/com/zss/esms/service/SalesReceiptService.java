package com.zss.esms.service;

import com.zss.esms.model.dto.SalesReceiptDTO;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 13:29
 * @desc 销售凭条 - 服务层接口
 */
public interface SalesReceiptService {

    /**
     * 插入新的销售凭条
     *
     * @param salesReceiptDTO 销售凭条数据传输对象
     * @param empId           员工ID
     */
    void insertSaleReceipt(SalesReceiptDTO salesReceiptDTO, String empId);
}
