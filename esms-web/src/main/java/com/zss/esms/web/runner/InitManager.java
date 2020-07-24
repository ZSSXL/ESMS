package com.zss.esms.web.runner;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zss.esms.model.dto.LoginDTO;
import com.zss.esms.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 16:39
 * @desc 创建初始化
 */
@Component
@SuppressWarnings("unused")
public class InitManager implements CommandLineRunner {

    private final static Logger log = LoggerFactory.getLogger(InitManager.class);

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
                managerService.createManager(LoginDTO.builder()
                        .username(MANAGER_NAME)
                        .password(MANAGER_PASSWORD)
                        .build());
                log.info("Initial Manager success ...");
            } catch (Exception e) {
                log.error("init admin error [{}]", e.getMessage());
            }
        }

    }
}
