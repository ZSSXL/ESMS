package com.zss.esms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zss.esms.model.dto.TimeCardDTO;
import com.zss.esms.model.entity.postgres.TimeCard;
import com.zss.esms.page.Pagenation;
import com.zss.esms.repository.TimeCardRepository;
import com.zss.esms.service.TimeCardService;
import com.zss.esms.util.IdUtil;
import com.zss.esms.util.PageUtil;
import com.zss.esms.util.TimeUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 14:14
 * @desc 时间卡 - 实现服务层接口方法
 */
@SuppressWarnings("unused")
@Service(interfaceClass = TimeCardService.class)
public class TimeCardServiceImpl implements TimeCardService {

    @Resource
    private TimeCardRepository timeCardRepository;

    @Override
    public void insertTimeCard(TimeCardDTO timeCardDTO, String empId) {
        TimeCard timeCard = TimeCard.builder()
                .timeCardId(IdUtil.getId())
                .empId(empId)
                .workingDate(timeCardDTO.getWorkingDate())
                .workingHours(timeCardDTO.getWorkingHours())
                .build();
        timeCardRepository.save(timeCard);
    }

    @Override
    public Pagenation showTimeCard(String empId, Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<TimeCard> timeCardPage = timeCardRepository.findAllByEmpIdOrderByWorkingDateDesc(empId, pageRequest);
        return PageUtil.converter(timeCardPage);
    }

    @Override
    public Boolean checkDate(String empId, String workingDate) {
        String start = TimeUtil.getStart(workingDate);
        String end = TimeUtil.getEnd(workingDate);
        TimeCard checkResult = timeCardRepository.findByEmpIdAndWorkingDateBetween(empId, start, end);
        return checkResult != null;
    }
}
