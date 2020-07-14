package com.zss.esms.repository;

import com.zss.esms.model.entity.postgres.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 16:34
 * @desc 管理者 - 持久化
 */
@Repository
public interface ManagerRepository extends JpaRepository<Manager, String> {

    /**
     * 通过管理员名称查询
     *
     * @param managerName 管理员名称
     * @return Manager
     */
    Manager findByManagerName(String managerName);

}
