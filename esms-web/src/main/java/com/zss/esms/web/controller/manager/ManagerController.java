package com.zss.esms.web.controller.manager;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zss.esms.comment.Constant;
import com.zss.esms.model.dto.LoginDTO;
import com.zss.esms.model.entity.postgres.Manager;
import com.zss.esms.response.ServerResponse;
import com.zss.esms.service.ManagerService;
import com.zss.esms.util.MapUtil;
import com.zss.esms.util.TokenUtil;
import com.zss.esms.web.util.GeneralConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/15 10:52
 * @desc
 */
@Slf4j
@RestController
@RequestMapping("/manager")
@SuppressWarnings("unused")
public class ManagerController {

    @Reference
    private ManagerService managerService;

    private final GeneralConverter generalConverter;

    @Autowired
    public ManagerController(GeneralConverter generalConverter) {
        this.generalConverter = generalConverter;
    }

    /**
     * 管理员登录
     *
     * @param loginDTO 管理员数据传输对象
     * @return String
     */
    @PostMapping("/login")
    public ServerResponse<String> managerLogin(@RequestBody @Valid LoginDTO loginDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.createByErrorMessage("用户名或密码不能为空");
        } else {
            Manager manager = managerService.managerLogin(loginDTO);
            if (manager != null) {
                String token = TokenUtil.createToken(MapUtil.create(
                        "role", Constant.Role.MANAGER,
                        "username", manager.getManagerName()));
                return ServerResponse.createBySuccess(token);
            } else {
                return ServerResponse.createByErrorMessage("登录失败...");
            }
        }
    }

    /**
     * 添加管理员
     *
     * @param loginDTO 管理员数据传输对象
     * @param result   校验结果
     * @return String
     */
    @PostMapping("/create")
    public ServerResponse<String> createManager(@RequestBody @Valid LoginDTO loginDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.createByErrorMessage("用户名或密码不能为空");
        } else {
            Boolean existInDb = managerService.isExistInDb(loginDTO.getUsername());
            if (existInDb) {
                return ServerResponse.createByErrorMessage("[ " + loginDTO.getUsername() + " ]管理员已经存在...");
            } else {
                try {
                    managerService.createManager(loginDTO);
                    return ServerResponse.createBySuccessMessage("添加成功");
                } catch (Exception e) {
                    log.error("添加管理员异常...[{}]", e.getMessage());
                    return ServerResponse.createByErrorMessage("添加管理员失败，请刷新重试...");
                }
            }
        }
    }

    /**
     * 查看所有的管理员
     *
     * @return List<ManagetDTO>
     */
    @Deprecated
    @GetMapping("/show")
    public ServerResponse<List<LoginDTO>> showAllMananger() {
        List<Manager> allManager = managerService.getAllManager();
        List<LoginDTO> converter = generalConverter.converter(allManager, LoginDTO.class);
        return ServerResponse.createBySuccess(converter);
    }

}
