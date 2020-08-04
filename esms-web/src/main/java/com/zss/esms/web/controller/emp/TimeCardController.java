package com.zss.esms.web.controller.emp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zss.esms.comment.Constant;
import com.zss.esms.model.dto.TimeCardDTO;
import com.zss.esms.page.Pagenation;
import com.zss.esms.response.ResponseCode;
import com.zss.esms.response.ServerResponse;
import com.zss.esms.service.TimeCardService;
import com.zss.esms.util.TimeUtil;
import com.zss.esms.util.TokenUtil;
import com.zss.esms.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "时间卡控制器")
public class TimeCardController extends BaseController {

    @Reference
    private TimeCardService timeCardService;

    /**
     * 上传时间卡
     * 一天只能上传一次
     *
     * @return String
     */
    @PostMapping
    @ApiOperation("上传时间卡")
    @ApiImplicitParam(name = "timeCardDTO", dataType = "TimeCardDTO", paramType = "body", required = true)
    public ServerResponse<String> uploadTimeCard(@RequestBody @Valid TimeCardDTO timeCardDTO,
                                                 @RequestHeader("token") String token,
                                                 BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(),
                    ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            if (StringUtils.isEmpty(timeCardDTO.getWorkingDate())) {
                timeCardDTO.setWorkingDate(TimeUtil.getTimestamp());
            }
            String empId = TokenUtil.getClaim(token, "userId").asString();
            // 检查当天是否已经提交时间卡
            Boolean check = timeCardService.checkDate(empId, timeCardDTO.getWorkingDate());
            if (check) {
                return ServerResponse.createByErrorMessage("当天的时间卡已提交...");
            } else {
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

    /**
     * 分页获取个人的时间卡
     *
     * @param token 员工身份令牌
     * @param page  当前页
     * @param size  每页大小
     * @return Pagenation
     */
    @GetMapping
    @ApiOperation("分页获取个人的时间卡")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页大小", dataType = "Integer", paramType = "query")
    })
    public ServerResponse<Pagenation> getTimeCardByPage(@RequestHeader String token,
                                                        @RequestParam(value = "page",
                                                                defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer page,
                                                        @RequestParam(value = "size",
                                                                defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer size) {
        String empId = TokenUtil.getClaim(token, "userId").asString();
        Pagenation pagenation = timeCardService.showTimeCard(empId, page, size);
        if (pagenation == null) {
            return ServerResponse.createByErrorMessage("获取信息失败，请刷新重试...");
        } else {
            return ServerResponse.createBySuccess(pagenation);
        }
    }
}
