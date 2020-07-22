package com.zss.esms.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @param fileBytes        文件字节流
     * @param originalFilename 文件名
     * @param cells            需要获取的列数
     * @return List<List < String>>
     */
    public static List<Map<String, String>> analysisExcel(byte[] fileBytes, String originalFilename, Integer cells) {
        List<Map<String, String>> result = new ArrayList<>();
        // 获取文件名
        if (StringUtils.isEmpty(originalFilename)) {
            return null;
        } else {
            try {
                // 获取输入流
                InputStream inputStream = new ByteArrayInputStream(fileBytes);
                // 判断Excel版本
                Workbook workbook;
                if (judgeExcelEdition(originalFilename)) {
                    workbook = new XSSFWorkbook(inputStream);
                } else {
                    workbook = new HSSFWorkbook(inputStream);
                }

                // 获取第一张工作表
                Sheet sheet = workbook.getSheetAt(0);
                // 获取第2行
                Row tableHeader = sheet.getRow(1);
                // 遍历表格 - 行
                for (Row row : sheet) {
                    int rowNum = row.getRowNum();
                    // 跳过第1行和第2行
                    if (rowNum == 0 || rowNum == 1) {
                        continue;
                    }
                    Map<String, String> cellMap = filterProfileData(row, tableHeader, cells);
                    result.add(cellMap);
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

    /**
     * 筛选个人资料数据
     *
     * @param cells       需要的列数
     * @param tableHeader 表头
     * @param row         每一行
     * @return Map<String, String>
     */
    private static Map<String, String> filterProfileData(Row row, Row tableHeader, Integer cells) {
        Map<String, String> map = new HashMap<>(cells);
        DecimalFormat format = new DecimalFormat("0");
        for (int i = 0; i < cells; i++) {
            Cell cell = row.getCell(i);
            if (cell == null) {
                continue;
            }
            // 表头名称
            String headerName = tableHeader.getCell(i).getStringCellValue();
            // 列内容
            String cellValue = null;
            if (cell.getCellType() == CellType.STRING) {
                // 如果是字符类型
                cellValue = cell.getStringCellValue();
            } else if (cell.getCellType() == CellType.NUMERIC) {
                // 如果是整数类型
                cellValue = format.format(cell.getNumericCellValue());
            } else if (cell.getCellType() == CellType.BLANK) {
                // 如果为空表格，则跳过
                continue;
            }
            map.put(headerName, cellValue);
        }
        return map;
    }
}
