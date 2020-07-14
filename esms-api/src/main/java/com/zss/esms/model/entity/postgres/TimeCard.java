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
 * @date 2020/7/13 10:49
 * @desc 时间卡 - 实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "esms_time_card")
@Table(appliesTo = "esms_time_card", comment = "时间卡")
public class TimeCard implements Serializable {

    /**
     * 时间卡Id
     */
    @Id
    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String timeCardId;

    /**
     * 员工Id
     */
    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String empId;

    /**
     * 工作日期
     */
    @Column(nullable = false, columnDefinition = "varchar(15)")
    private String workingDate;

    /**
     * 工作时数
     */
    @Column(nullable = false, columnDefinition = "decimal(3,1)")
    private BigDecimal workingHours;
}
