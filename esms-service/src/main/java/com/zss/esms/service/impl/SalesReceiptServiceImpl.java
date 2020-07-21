package com.zss.esms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zss.esms.model.dto.SalesReceiptDTO;
import com.zss.esms.model.entity.postgres.SalesReceipt;
import com.zss.esms.page.Pagenation;
import com.zss.esms.repository.SalesReceiptRepository;
import com.zss.esms.service.SalesReceiptService;
import com.zss.esms.util.IdUtil;
import com.zss.esms.util.PageUtil;
import com.zss.esms.util.TimeUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
                .receiptDate(salesReceiptDTO.getDate())
                .quantity(salesReceiptDTO.getQuantity())
                .build();
        salesReceiptRepository.save(salesReceipt);
    }

    @Override
    public Pagenation showSaleReceiptByPage(String empId, Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<SalesReceipt> salesReceiptPage = salesReceiptRepository
                .findAllByEmpIdOrderByReceiptDateDesc(empId, pageRequest);
        return PageUtil.converter(salesReceiptPage);
    }

    @Override
    public Boolean checkDate(String empId, String timestamp) {
        String start = TimeUtil.getStart(timestamp);
        String end = TimeUtil.getEnd(timestamp);
        SalesReceipt checkResult = salesReceiptRepository.findByEmpIdAndReceiptDateBetween(empId, start, end);
        return checkResult != null;
    }

    @Override
    public Integer countSalesQuantity(String empId) {
        // 获取上一周周一到上一周周日的时间戳
        String beginDayOfLastWeek = TimeUtil.getBeginDayOfLastWeek();
        String endDayOfLastWeek = TimeUtil.getEndDayOfLastWeek();
        Integer count = salesReceiptRepository
                .findAllByEmpIdAndReceiptDateBetween(empId, beginDayOfLastWeek, endDayOfLastWeek);
        return count == null ? 0 : count;
    }
}
