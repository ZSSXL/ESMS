package com.zss.esms.repository;

import com.zss.esms.model.entity.mongdb.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 13:53
 * @desc 个人资料 - 持久化
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {
}
