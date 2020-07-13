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

    @Id
    @Column(nullable = false, columnDefinition = "varchar(255) comment '时间卡Id'")
    private String timeCardId;

    @Column(nullable = false, columnDefinition = "varchar(255) comment '员工Id'")
    private String empId;

    @Column(nullable = false, columnDefinition = "varchar(15) comment '工作日期'")
    private String workingDate;

    @Column(nullable = false, columnDefinition = "decimal(3,1) comment '工作时数'")
    private BigDecimal workingHours;
}
