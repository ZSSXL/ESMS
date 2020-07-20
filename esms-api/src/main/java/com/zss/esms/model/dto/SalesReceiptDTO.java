package com.zss.esms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/20 10:09
 * @desc 销售凭条 - 数据传输对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesReceiptDTO implements Serializable {

    /**
     * 日期 - 不上传日期，默认当天
     */
    private String date;

    /**
     * 销售数量
     */
    @NotNull
    private Integer quantity;
}
