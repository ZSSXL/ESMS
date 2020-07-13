package com.zss.esms.repository;

import com.zss.esms.model.entity.postgres.TimeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 13:38
 * @desc 时间卡 - 持久化
 */
@Repository
public interface TimeCardRepository extends JpaRepository<TimeCard, String> {
}
