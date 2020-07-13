package com.zss.esms.repository;

import com.zss.esms.model.entity.mongdb.PaymentMethod;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 13:39
 * @desc 支付方法 - 持久化
 */
@Repository
public interface PaymentMethodRepository extends MongoRepository<PaymentMethod, String> {
}
