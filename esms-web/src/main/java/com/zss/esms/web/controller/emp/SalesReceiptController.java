package com.zss.esms.web.controller.emp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zss.esms.response.ServerResponse;
import com.zss.esms.service.SalesReceiptService;
import com.zss.esms.web.controller.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/17 16:58
 * @desc 受新员工，销售凭条 - 控制器
 */
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
    public ServerResponse<String> uploadSalesReceipt() {
        return ServerResponse.createBySuccess();
    }
}
