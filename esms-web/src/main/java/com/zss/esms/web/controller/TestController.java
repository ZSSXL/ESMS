package com.zss.esms.web.controller;

import com.zss.esms.response.ServerResponse;
import com.zss.esms.web.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/15 10:20
 * @desc 测试专用 - 控制器
 */
@Slf4j
@RestController
@RequestMapping("/manager/test")
public class TestController extends BaseController {

    /**
     * 拦截器测试
     *
     * @return String
     */
    @GetMapping
    public ServerResponse<String> interceptorTest() {
        System.out.println("拦截器测试...");
        return ServerResponse.createBySuccess();
    }

    /**
     * 上传Excel文件测试
     *
     * @param file 上传的文件流
     * @return String
     */
    @PostMapping
    public ServerResponse<String> uplodaExcelTest(MultipartFile file) {
        List<List<String>> arrayLists = ExcelUtil.analysisExcel(file, 5);
        if (arrayLists == null) {
            return ServerResponse.createByError();
        } else {
            for (List<String> strList : arrayLists) {
                for (String str : strList) {
                    System.out.println("Cell : [" + str + "]");
                }
            }
            return ServerResponse.createBySuccess();
        }
    }
}
