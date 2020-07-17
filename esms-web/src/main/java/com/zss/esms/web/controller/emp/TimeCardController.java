package com.zss.esms.web.controller.emp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zss.esms.response.ServerResponse;
import com.zss.esms.service.TimeCardService;
import com.zss.esms.web.controller.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/17 16:59
 * @desc 钟点工 - 时间卡 - 控制器
 */
@RestController
@RequestMapping("/emp/card")
@SuppressWarnings("unused")
public class TimeCardController extends BaseController {

    @Reference
    private TimeCardService timeCardService;

    /**
     * 上传时间卡
     *
     * @return String
     */
    @PostMapping
    public ServerResponse<String> uploadTimeCard() {
        return null;
    }
}
