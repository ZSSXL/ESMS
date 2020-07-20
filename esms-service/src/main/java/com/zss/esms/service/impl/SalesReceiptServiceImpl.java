package com.zss.esms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zss.esms.model.dto.SalesReceiptDTO;
import com.zss.esms.model.entity.postgres.SalesReceipt;
import com.zss.esms.repository.SalesReceiptRepository;
import com.zss.esms.service.SalesReceiptService;
import com.zss.esms.util.IdUtil;

import javax.annotation.Resource;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 14:13
 * @desc 销售凭证 - 实现服务层接口方法
 */
@SuppressWarnings("unused")
@Service(interfaceClass = SalesReceiptService.class)
public class SalesReceiptServiceImpl implements SalesReceiptService {

    @Resource
    private SalesReceiptRepository salesReceiptRepository;

    @Override
    public void insertSaleReceipt(SalesReceiptDTO salesReceiptDTO, String empId) {
        SalesReceipt salesReceipt = SalesReceipt.builder()
                .receiptId(IdUtil.getId())
                .empId(empId)
                .date(salesReceiptDTO.getDate())
                .quantity(salesReceiptDTO.getQuantity())
                .build();
        System.out.println("SaleReceipt : " + salesReceipt);
        salesReceiptRepository.save(salesReceipt);
    }
}
