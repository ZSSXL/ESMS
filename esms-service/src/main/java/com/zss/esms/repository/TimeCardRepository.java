package com.zss.esms.repository;

import com.zss.esms.model.entity.postgres.TimeCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 13:38
 * @desc 时间卡 - 持久化
 */
@Repository
public interface TimeCardRepository extends JpaRepository<TimeCard, String> {

    /**
     * 分页获取时间卡
     *
     * @param empId    员工Id
     * @param pageable 分页请求
     * @return Page<TimeCard>
     */
    Page<TimeCard> findAllByEmpIdOrderByWorkingDateDesc(String empId, Pageable pageable);

    /**
     * 通过雇员Id获取
     *
     * @param empId 员工Id
     * @param start 开始时间
     * @param end   结束时间
     * @return TimeCard
     */
    TimeCard findByEmpIdAndWorkingDateBetween(String empId, String start, String end);
}
