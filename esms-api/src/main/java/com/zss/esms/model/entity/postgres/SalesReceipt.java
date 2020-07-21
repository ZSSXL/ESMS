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

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 10:56
 * @desc 销售凭条 - 实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "esms_sales_receipt")
@Table(appliesTo = "esms_sales_receipt", comment = "销售凭条")
public class SalesReceipt implements Serializable {

    /**
     * 凭条Id
     */
    @Id
    @Column(columnDefinition = "varchar(255)")
    private String receiptId;

    /**
     * 员工Id
     */
    @Column(columnDefinition = "varchar(255)")
    private String empId;

    /**
     * 日期
     */
    @Column(columnDefinition = "varchar(15)")
    private String receiptDate;

    /**
     * 销售数量
     */
    @Column(columnDefinition = "smallint")
    private Integer quantity;
}
