package com.zss.esms.service;

import com.zss.esms.model.dto.SalesReceiptDTO;
import com.zss.esms.page.Pagenation;

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

    /**
     * 分页获取所有的数据
     *
     * @param empId 员工Id
     * @param page  当前页
     * @param size  每页显示条数
     * @return Pagenation
     */
    Pagenation showSaleReceiptByPage(String empId, Integer page, Integer size);

    /**
     * 检查是否已经提交
     *
     * @param empId     员工Id
     * @param timestamp 时间戳
     * @return Boolean
     */
    Boolean checkDate(String empId, String timestamp);

    /**
     * 统计上一周的销售数量
     *
     * @param empId 员工Id
     * @return Integer
     */
    Integer countSalesQuantity(String empId);
}
