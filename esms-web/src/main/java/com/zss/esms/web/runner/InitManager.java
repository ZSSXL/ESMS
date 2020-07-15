package com.zss.esms.web.runner;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zss.esms.model.dto.ManagerDTO;
import com.zss.esms.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 16:39
 * @desc 创建初始化
 */
@Slf4j
@Component
@SuppressWarnings("unused")
public class InitManager implements CommandLineRunner {

    @Reference(interfaceClass = ManagerService.class)
    private ManagerService managerService;

    /**
     * 管理员名称
     */
    private final static String MANAGER_NAME = "manager";
    /**
     * 管理员密码
     */
    private final static String MANAGER_PASSWORD = "esms123";

    @Override
    public void run(String... args) {
        Boolean existInDb = managerService.isExistInDb(MANAGER_NAME);
        if (existInDb) {
            log.info("The initial Manger already exists");
        } else {
            try {
                managerService.createManager(ManagerDTO.builder()
                        .managerName(MANAGER_NAME)
                        .managerPassword(MANAGER_PASSWORD)
                        .build());
                log.info("Initial Manager success ...");
            } catch (Exception e) {
                log.error("init admin error [{}]", e.getMessage());
            }
        }

    }
}
