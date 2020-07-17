package com.zss.esms.service;

import com.zss.esms.model.dto.LoginDTO;
import com.zss.esms.model.entity.postgres.Manager;

import java.util.List;

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
    void createManager(LoginDTO managerDTO);

    /**
     * 管理员登录
     *
     * @param loginDTO 管理员数据传输实体
     * @return Manager
     */
    Manager managerLogin(LoginDTO loginDTO);

    /**
     * 获取所有的管理员信息
     *
     * @return List<ManangerDTO>
     */
    List<Manager> getAllManager();
}
