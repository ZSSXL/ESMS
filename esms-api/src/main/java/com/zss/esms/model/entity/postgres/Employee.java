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
 * @date 2020/7/13 10:41
 * @desc 雇员 - 实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "esms_employee")
@Table(appliesTo = "esms_employee", comment = "雇员表")
public class Employee implements Serializable {

    @Id
    @Column(nullable = false, columnDefinition = "varchar(255) comment '雇员Id'")
    private String empId;

    @Column(nullable = false, unique = true, columnDefinition = "varchar(20) comment '雇员姓名'")
    private String empName;

    @Column(nullable = false, columnDefinition = "varchar(255) comment '雇员密码'")
    private String empPassword;
}
