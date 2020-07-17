package com.zss.esms.web.controller.manager;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zss.esms.response.ServerResponse;
import com.zss.esms.service.EmployeeService;
import com.zss.esms.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/15 17:13
 * @desc 管理员对雇员的操作 - 控制器类
 */
@Slf4j
@SuppressWarnings("unused")
@RestController("manangerEmpContro")
@RequestMapping("/manager/emp")
public class EmployeeController extends BaseController {

    @Reference
    private EmployeeService employeeService;

    /**
     * 获取Excel表格中的雇员数据，并添加
     *
     * @param excelFile excel文件
     * @return String
     */
    @PostMapping
    public ServerResponse<String> createEmpFromExcel(MultipartFile excelFile) {
        if (excelFile == null) {
            return ServerResponse.createByErrorMessage("参数不能为空");
        } else {
            byte[] buffer;
            try {
                buffer = excelFile.getBytes();
            } catch (IOException e) {
                log.error("Get Excel File Bytes Has Error... [{}]", e.getMessage());
                return ServerResponse.createByErrorMessage("文件获取失败");
            }
            Integer successCount = employeeService.createManayEmp(buffer, excelFile.getOriginalFilename(), 11);
            if (successCount == null) {
                return ServerResponse.createByError();
            } else {
                return ServerResponse.createBySuccess("成功添加员工[ " + successCount + " ]人...");
            }
        }
    }
}
