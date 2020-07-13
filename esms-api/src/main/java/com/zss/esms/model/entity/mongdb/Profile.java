package com.zss.esms.model.entity.mongdb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 11:31
 * @desc 雇员个人资料 - 实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "esms_profile")
public class Profile implements Serializable {

    /**
     * 资料Id
     */
    private String profileId;

    /**
     * 雇员Id
     */
    private String empId;

    /**
     * 雇员姓名
     */
    private String empName;

    /**
     * 员工编号
     */
    private String empCode;

    /**
     * 员工类型：钟点工（周五发工资/月底发工资）/受薪工 HOURLY_W/HOURLY_M/SALARIED
     */
    private String empType;

    /**
     * 邮箱
     */
    private String empEmail;

    /**
     * 是否加入协会
     */
    private Boolean society;

    /**
     * 入职时间
     */
    private String entryTime;

    /**
     * 每周应扣费用
     */
    private BigDecimal weeklyDeduction;

    /**
     * 薪资
     */
    private Map<String, String> salary;

}
