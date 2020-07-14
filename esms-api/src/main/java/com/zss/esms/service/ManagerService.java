package com.zss.esms.service;

import com.zss.esms.model.dto.ManagerDTO;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 16:35
 * @desc 管理者 - 服务层接口
 */
public interface ManagerService {

    /**
     * 管理员已存在
     *
     * @param managerName 管理员名称
     * @return Boolean
     */
    Boolean isExistInDb(String managerName);

    /**
     * 创建管理员
     *
     * @param managerDTO 管理员DTO
     */
    void createManager(ManagerDTO managerDTO);
}
