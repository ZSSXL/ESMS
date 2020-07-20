package com.zss.esms.service;

import com.zss.esms.model.dto.TimeCardDTO;
import com.zss.esms.page.Pagenation;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 13:30
 * @desc 时间卡 - 服务层接口
 */
public interface TimeCardService {

    /**
     * 插入新的时间卡
     *
     * @param timeCardDTO 时间卡数据传输对象
     * @param empId       员工Id
     */
    void insertTimeCard(TimeCardDTO timeCardDTO, String empId);

    /**
     * 分页获取时间卡
     *
     * @param empId 员工Id
     * @param page  当前页
     * @param size  每页显示条数
     * @return Pagenation
     */
    Pagenation showTimeCard(String empId, Integer page, Integer size);

    /**
     * 检查当天是否已经提交
     *
     * @param empId       员工Id
     * @param workingDate 工作日期
     * @return Boolean
     */
    Boolean checkDate(String empId, String workingDate);
}
