package com.zss.esms.web.controller.emp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zss.esms.comment.Constant;
import com.zss.esms.page.Pagenation;
import com.zss.esms.response.ServerResponse;
import com.zss.esms.service.PayrollRecordService;
import com.zss.esms.util.TokenUtil;
import com.zss.esms.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/21 14:54
 * @desc 工资发放记录 - （员工）控制器
 */
@Slf4j
@RestController("empPayrollRecord")
@RequestMapping("emp/record")
@SuppressWarnings("unused")
public class PayrollRecordController extends BaseController {

    @Reference
    private PayrollRecordService payrollRecordService;

    /**
     * 员工获取自己的工资发放记录
     *
     * @param token 员工身份校验信息
     * @param page  当前页
     * @param size  每页大小
     * @return Pagenation
     */
    @GetMapping
    public ServerResponse<Pagenation> showAllRecordServiceById(
            @RequestHeader String token,
            @RequestParam(value = "page", defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(value = "size", defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer size) {
        String empId = TokenUtil.getClaim(token, "userId").asString();
        Pagenation pagenation = payrollRecordService.showAllPayrollRecordById(empId, page, size);
        if (pagenation == null) {
            return ServerResponse.createByErrorMessage("查询工资发放记录失败，请刷新重试...");
        } else {
            return ServerResponse.createBySuccess(pagenation);
        }
    }

    /**
     * 确认收款
     *
     * @param token           身份校验信息
     * @param payrollRecordId 工资条Id
     * @return String
     */
    @PutMapping
    public ServerResponse<String> confirmReceive(@RequestHeader String token,
                                                 @RequestBody String payrollRecordId) {
        String empId = TokenUtil.getClaim(token, "userId").asString();
        if (StringUtils.isEmpty(payrollRecordId)) {
            return ServerResponse.createByErrorMessage("参数错误");
        } else {
            Boolean confirmResult = payrollRecordService.confimReceive(empId, payrollRecordId);
            if (confirmResult) {
                return ServerResponse.createBySuccessMessage("已确认成功");
            } else {
                return ServerResponse.createByErrorMessage("确认失败，请刷新重试...");
            }
        }
    }
}
