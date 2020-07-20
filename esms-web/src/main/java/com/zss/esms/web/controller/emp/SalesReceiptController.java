package com.zss.esms.web.controller.emp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zss.esms.model.dto.SalesReceiptDTO;
import com.zss.esms.response.ResponseCode;
import com.zss.esms.response.ServerResponse;
import com.zss.esms.service.SalesReceiptService;
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
 * @date 2020/7/17 16:58
 * @desc 受新员工，销售凭条 - 控制器
 */
@Slf4j
@RestController
@RequestMapping("/emp/sales")
@SuppressWarnings("unused")
public class SalesReceiptController extends BaseController {

    @Reference
    private SalesReceiptService receiptService;

    /**
     * 上传销售凭条
     *
     * @return String
     */
    @PostMapping
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
