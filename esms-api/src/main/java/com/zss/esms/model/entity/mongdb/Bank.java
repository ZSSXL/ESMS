package com.zss.esms.model.entity.mongdb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 11:20
 * @desc 银行支付
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bank {

    /**
     * 银行卡号
     */
    private String cardNumber;

    /**
     * 银行卡持有人
     */
    private String cardOwner;
}
