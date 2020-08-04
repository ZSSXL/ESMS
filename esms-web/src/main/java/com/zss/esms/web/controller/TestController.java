package com.zss.esms.web.controller;

import com.zss.esms.response.ServerResponse;
import com.zss.esms.util.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/15 10:20
 * @desc 测试专用 - 控制器
 */
@Slf4j
@RestController
@RequestMapping("/manager/test")
@Api(tags = "这是一个专门用来测试的Controller")
public class TestController extends BaseController {

    /**
     * 拦截器测试
     *
     * @return String
     */
    @GetMapping
    @ApiOperation("权限拦截器测试")
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
    @ApiOperation("上传Excel文件测试")
    public ServerResponse<String> uplodaExcelTest(@ApiParam(name = "file", required = true, value = "MultipartFile")
                                                          MultipartFile file) throws IOException {
        System.out.println("Hello Manager Test : " + file.isEmpty());
        byte[] bytes = file.getBytes();
        String originalFilename = file.getOriginalFilename();
        List<Map<String, String>> cellList = ExcelUtil.analysisExcel(bytes, originalFilename, 11);
        if (cellList == null) {
            return ServerResponse.createByError();
        } else {
            for (Map<String, String> tempMap : cellList) {
                for (Map.Entry<String, String> map : tempMap.entrySet()) {
                    System.out.println("Map: Key : [" + map.getKey() + "] value : [" + map.getValue() + "]");
                }
                System.out.println("===================================");
            }
            return ServerResponse.createBySuccess();
        }
    }
}
