package com.zss.esms.web.controller.manager;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zss.esms.comment.Constant;
import com.zss.esms.page.Pagenation;
import com.zss.esms.response.ServerResponse;
import com.zss.esms.service.PayrollRecordService;
import com.zss.esms.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/17 16:52
 * @desc 薪酬记录 - 控制器
 * 这个不一定用的到，先留着吧
 */
@Slf4j
@RestController
@RequestMapping("/manager/payroll")
@SuppressWarnings("unused")
@Api(tags = "薪酬记录控制器")
public class PayrollRecordController extends BaseController {

    @Reference
    private PayrollRecordService payrollRecordService;

    /**
     * 查看工资发放记录
     *
     * @param page 当前页
     * @param size 每页大小
     * @return Pagenation
     */
    @GetMapping
    @ApiOperation("分页查询薪资发放记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页大小", dataType = "Integer", paramType = "query")
    })
    public ServerResponse<Pagenation> showAllPayrollRecord(
            @RequestParam(value = "page", defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(value = "size", defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer size) {
        Pagenation pagenation = payrollRecordService.showAllPayrollRecord(page, size);
        if (pagenation == null) {
            return ServerResponse.createByErrorMessage("查询失败，请刷新重试...");
        } else {
            return ServerResponse.createBySuccess(pagenation);
        }
    }
}
