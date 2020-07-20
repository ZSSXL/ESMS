package com.zss.esms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/20 9:53
 * @desc 时间卡 - 数据传输对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeCardDTO implements Serializable {

    /**
     * 工作日期 - 不上传日期，默认当天
     */
    private String workingDate;

    /**
     * 工作时数
     */
    @NotNull
    private BigDecimal workingHours;
}
