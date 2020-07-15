package com.zss.esms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zss.esms.model.dto.ManagerDTO;
import com.zss.esms.model.entity.postgres.Manager;
import com.zss.esms.repository.ManagerRepository;
import com.zss.esms.service.ManagerService;
import com.zss.esms.util.EncryptionUtil;
import com.zss.esms.util.IdUtil;
import com.zss.esms.util.TimeUtil;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 16:35
 * @desc 管理者 - 实现服务层接口方法
 */
@SuppressWarnings("unused")
@Service(interfaceClass = ManagerService.class)
public class ManagerServiceImpl implements ManagerService {

    @Resource
    private ManagerRepository managerRepository;

    @Override
    public Boolean isExistInDb(String managerName) {
        Manager manager = managerRepository.findByManagerName(managerName);
        return manager != null;
    }

    @Override
    public void createManager(ManagerDTO managerDTO) {
        managerRepository.save(Manager.builder()
                .managerId(IdUtil.getId())
                .managerName(managerDTO.getManagerName())
                .managerPassword(EncryptionUtil.encrypt(managerDTO.getManagerPassword()))
                .createTime(TimeUtil.getTimestamp())
                .build());
    }

    @Override
    public Manager managerLogin(ManagerDTO managerDTO) {
        return managerRepository.findByManagerNameAndManagerPassword(managerDTO.getManagerName(),
                EncryptionUtil.encrypt(managerDTO.getManagerPassword()));
    }

    @Override
    public List<Manager> getAllManager() {
        return managerRepository.findAll();
    }
}
