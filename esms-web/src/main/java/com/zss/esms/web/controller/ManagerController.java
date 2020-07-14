package com.zss.esms.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zss.esms.model.dto.ManagerDTO;
import com.zss.esms.response.ServerResponse;
import com.zss.esms.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/14 13:58
 * @desc Manager - 控制器
 */
@Slf4j
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Reference(interfaceClass = ManagerService.class)
    private ManagerService managerService;

    /**
     * 添加管理员
     *
     * @param managerDTO 管理员DTO
     * @return String
     */
    @PostMapping
    public ServerResponse<String> createManager(@RequestBody ManagerDTO managerDTO) {
        Boolean existInDb = managerService.isExistInDb(managerDTO.getManagerName());
        if (existInDb) {
            log.info("The initial Manger already exists");
            return ServerResponse.createByErrorMessage("[" + managerDTO.getManagerName() + "]已经存在");
        } else {
            try {
                managerService.createManager(ManagerDTO.builder()
                        .managerName(managerDTO.getManagerName())
                        .managerPassword(managerDTO.getManagerPassword())
                        .build());
                log.info("Initial Manager success ...");
                return ServerResponse.createBySuccessMessage("添加成功");
            } catch (Exception e) {
                log.error("init admin error [{}]", e.getMessage());
                return ServerResponse.createByErrorMessage("添加管理员异常");
            }
        }
    }
}
