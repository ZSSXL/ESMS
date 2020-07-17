package com.zss.esms.model.entity.mongdb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Map;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 11:25
 * @desc 支付方式 - 实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "esms_payment_method")
public class PaymentMethod implements Serializable {

    /**
     * 支付方式Id
     */
    private String methodId;

    /**
     * 雇员Id
     */
    private String empId;

    /**
     * 支付选择
     */
    private Map<String, String> choice;
}
