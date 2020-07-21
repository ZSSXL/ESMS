package com.zss.esms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zss.esms.model.entity.mongdb.Profile;
import com.zss.esms.repository.ProfileRepository;
import com.zss.esms.service.ProfileService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 14:13
 * @desc 个人信息 - 实现服务层接口方法
 */
@SuppressWarnings("unused")
@Service(interfaceClass = ProfileService.class)
public class ProfileServiceImpl implements ProfileService {

    @Resource
    private ProfileRepository profileRepository;

    @Override
    public List<Profile> findAllProfile() {
        return profileRepository.findAll();
    }
}
