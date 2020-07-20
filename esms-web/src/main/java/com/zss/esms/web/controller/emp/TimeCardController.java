package com.zss.esms.web.controller.emp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zss.esms.model.dto.TimeCardDTO;
import com.zss.esms.response.ResponseCode;
import com.zss.esms.response.ServerResponse;
import com.zss.esms.service.TimeCardService;
import com.zss.esms.util.TimeUtil;
import com.zss.esms.util.TokenUtil;
import com.zss.esms.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/17 16:59
 * @desc 钟点工 - 时间卡 - 控制器
 */
@Slf4j
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
    public ServerResponse<String> uploadTimeCard(@RequestBody @Valid TimeCardDTO timeCardDTO, @RequestHeader("token") String token, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            if (StringUtils.isEmpty(timeCardDTO.getWorkingDate())) {
                timeCardDTO.setWorkingDate(TimeUtil.getTimestamp());
            }
            String empId = TokenUtil.getClaim(token, "userId").asString();
            try {
                timeCardService.insertTimeCard(timeCardDTO, empId);
                return ServerResponse.createBySuccessMessage("上传时间卡成功...");
            } catch (Exception e) {
                log.error("保存时间卡发生以下异常情况，请查看日志... [{}]", e.getMessage());
                return ServerResponse.createByErrorMessage("上传时间卡失败，请刷新重试...");
            }

        }
    }
}
