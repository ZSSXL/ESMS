package com.zss.esms.web.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/15 14:07
 * @desc Excel表格工具 - 工具类
 */
@Slf4j
@SuppressWarnings("unused")
public class ExcelUtil {

    /**
     * 正则表达式，匹配文件 .xlsx
     */
    private final static String MATCH = "^.+\\.(?i)(xlsx)$";

    /**
     * 解析Excel文件中的数据
     *
     * @param file  multipartfile
     * @param cells 需要获取的列数
     * @return List<List < String>>
     */
    public static List<List<String>> analysisExcel(MultipartFile file, Integer cells) {
        List<List<String>> result = new ArrayList<>();
        // 获取文件名
        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isEmpty(originalFilename)) {
            return null;
        } else {
            try {
                // 获取输入流
                InputStream inputStream = file.getInputStream();
                // 判断Excel版本
                Workbook workbook;
                if (judgeExcelEdition(originalFilename)) {
                    workbook = new XSSFWorkbook(inputStream);
                } else {
                    workbook = new HSSFWorkbook(inputStream);
                }

                DecimalFormat format = new DecimalFormat("0");
                // 获取第一张工作表
                Sheet sheet = workbook.getSheetAt(0);
                // 遍历表格 - 行
                for (Row row : sheet) {
                    List<String> cellList = new ArrayList<>();
                    int rowNum = row.getRowNum();
                    // 跳过第一行
                    if (rowNum == 0) {
                        continue;
                    }
                    // 遍历表格 - 列
                    for (int i = 0; i < cells; i++) {
                        Cell cell = row.getCell(i);
                        if (cell.getCellType() == CellType.STRING) {
                            cellList.add(cell.getStringCellValue());
                        } else if (cell.getCellType() == CellType.NUMERIC) {
                            cellList.add(format.format(cell.getNumericCellValue()));
                        }
                    }
                    result.add(cellList);
                }
                workbook.close();
            } catch (FileNotFoundException e) {
                log.error("文件未找到： [{}]", e.getMessage());
            } catch (IOException e) {
                log.error("文件上传失败： [{}]", e.getMessage());
            }
        }
        return result;
    }

    /**
     * 判断上传的excel文件版本（xls为2003，xlsx为2007）
     *
     * @param fileName 文件路径
     * @return excel2007以及以上版本返回true，以下版本返回false
     */
    private static boolean judgeExcelEdition(String fileName) {
        return fileName.matches(MATCH);
    }
}
