package com.zss.esms.service;

import com.zss.esms.model.entity.mongdb.Profile;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 13:31
 * @desc 个人资料 - 服务层接口
 */
public interface ProfileService {

    /**
     * 获取所有的员工个人信息
     *
     * @return List<Profile>
     */
    List<Profile> findAllProfile();
}
