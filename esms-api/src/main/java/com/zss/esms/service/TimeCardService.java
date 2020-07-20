package com.zss.esms.service;

import com.zss.esms.model.dto.TimeCardDTO;

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
}
