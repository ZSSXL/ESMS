package com.zss.esms.web.controller.emp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zss.esms.comment.Constant;
import com.zss.esms.model.dto.SalesReceiptDTO;
import com.zss.esms.page.Pagenation;
import com.zss.esms.response.ResponseCode;
import com.zss.esms.response.ServerResponse;
import com.zss.esms.service.SalesReceiptService;
import com.zss.esms.util.TimeUtil;
import com.zss.esms.util.TokenUtil;
import com.zss.esms.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/17 16:58
 * @desc 受新员工，销售凭条 - 控制器
 */
@Slf4j
@RestController
@RequestMapping("/emp/sales")
@SuppressWarnings("unused")
@Api(tags = "销售凭条控制器")
public class SalesReceiptController extends BaseController {

    @Reference
    private SalesReceiptService receiptService;

    /**
     * 上传销售凭条
     *
     * @return String
     */
    @PostMapping
    @ApiOperation("上传销售品条")
    @ApiImplicitParam(name = "salesReceiptDTO", value = "销售凭条数据传输对象", dataType = "SalesReceiptDTO", required = true)
    public ServerResponse<String> uploadSalesReceipt(@RequestBody @Valid SalesReceiptDTO salesReceiptDTO,
                                                     @RequestHeader("token") String token,
                                                     BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            if (StringUtils.isEmpty(salesReceiptDTO.getDate())) {
                salesReceiptDTO.setDate(TimeUtil.getTimestamp());
            }
            String empId = TokenUtil.getClaim(token, "userId").asString();

            // 检查当天是否以提交
            Boolean check = receiptService.checkDate(empId, salesReceiptDTO.getDate());
            if (check) {
                return ServerResponse.createByErrorMessage("已提交当天的销售清单...");
            } else {
                try {
                    receiptService.insertSaleReceipt(salesReceiptDTO, empId);
                    return ServerResponse.createBySuccessMessage("上传销售凭条成功...");
                } catch (Exception e) {
                    log.error("保存销售凭条发生以下异常情况，请查看日志... [{}]", e.getMessage());
                    return ServerResponse.createByErrorMessage("上传销售凭条失败，请刷新重试...");
                }
            }
        }
    }

    /**
     * 获取个人销售凭条
     *
     * @param token 身份令牌
     * @param page  当前页
     * @param size  每页大小
     * @return Pagenation
     */
    @GetMapping
    public ServerResponse<Pagenation> getSalesReceiptByPage(@RequestHeader String token,
                                                            @RequestParam(value = "page", defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer page,
                                                            @RequestParam(value = "size", defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer size) {
        String empId = TokenUtil.getClaim(token, "userId").asString();
        Pagenation pagenation = receiptService.showSaleReceiptByPage(empId, page, size);
        if (pagenation == null) {
            return ServerResponse.createByErrorMessage("获取信息失败，请刷新重试...");
        } else {
            return ServerResponse.createBySuccess(pagenation);
        }
    }

}
