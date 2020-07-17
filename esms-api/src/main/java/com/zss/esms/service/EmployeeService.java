package com.zss.esms.service;


import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 13:27
 * @desc 雇员 - 服务层接口
 */
public interface EmployeeService {

    /**
     * 服务测试
     *
     * @param content 内容
     * @return String
     */
    String serviceTest(String content);

    /**
     * 解析Excel文件，批量添加员工
     *
     * @param fileBytes        Excel文件
     * @param originalFilename 文件名
     * @param cells            需要的列数
     * @return Interger 最终添加成功数量
     */
    Integer createManayEmp(byte[] fileBytes, String originalFilename, Integer cells);
}
