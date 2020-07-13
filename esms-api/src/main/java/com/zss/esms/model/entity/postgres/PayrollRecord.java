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

    @Id
    @Column(nullable = false, columnDefinition = "varchar(255) comment '工资单Id'")
    private String payrollId;

    @Column(nullable = false, columnDefinition = "varchar(255) comment '雇员Id'")
    private String empId;

    @Column(columnDefinition = "decimal(12, 2) comment '应发工资'")
    private BigDecimal payable;

    @Column(columnDefinition = "decimal(12, 2) comment '应扣工资'")
    private BigDecimal deduction;

    @Column(columnDefinition = "decimal(12, 2) comment '实收工资'")
    private BigDecimal actualSalary;

    @Column(columnDefinition = "varchar(15) comment '发放日期'")
    private String releaseDate;

    @Column(columnDefinition = "varchar(20) comment '发放人'")
    private String issuer;

    @Column(columnDefinition = "boolean comment '确认收款'")
    private Boolean confirmReceipt;
}
