package com.zss.esms.model.entity.postgres;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 11:01
 * @desc 工资方法记录 - 实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "esms_payroll_record")
@Table(appliesTo = "esms_payroll_record", comment = "薪酬发放记录")
public class PayrollRecord implements Serializable {

    /**
     * 工资单Id
     */
    @Id
    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String payrollId;

    /**
     * 雇员Id
     */
    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String empId;

    /**
     * 应发工资
     */
    @Column(columnDefinition = "decimal(12, 2)")
    private BigDecimal payable;

    /**
     * 应扣工资
     */
    @Column(columnDefinition = "decimal(12, 2)")
    private BigDecimal deduction;

    /**
     * 实收工资
     */
    @Column(columnDefinition = "decimal(12, 2)")
    private BigDecimal actualSalary;

    /**
     * 发放日期
     */
    @Column(columnDefinition = "varchar(15)")
    private String releaseDate;

    /**
     * 发放人
     */
    @Column(columnDefinition = "varchar(20)")
    private String issuer;

    /**
     * 确认收款
     */
    @Column(columnDefinition = "boolean")
    private Boolean confirmReceipt;
}
