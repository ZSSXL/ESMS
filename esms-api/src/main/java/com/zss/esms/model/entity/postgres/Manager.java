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
 * @date 2020/7/13 16:30
 * @desc 管理者 - 实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "esms_manager")
@Table(appliesTo = "esms_manager", comment = "管理者")
public class Manager implements Serializable {

    /**
     * 管理员Id
     */
    @Id
    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String managerId;

    /**
     * 管理员名称
     */
    @Column(nullable = false, unique = true, columnDefinition = "varchar(20)")
    private String managerName;

    /**
     * 管理员密码
     */
    @Column(columnDefinition = "varchar(255)")
    private String managerPassword;

    /**
     * 创建时间
     */
    @Column(columnDefinition = "varchar(15)")
    private String createTime;
}
