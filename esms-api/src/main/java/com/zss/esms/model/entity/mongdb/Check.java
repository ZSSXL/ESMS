package com.zss.esms.model.entity.mongdb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 11:21
 * @desc 支票支付
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Check {

    /**
     * 收件地址
     */
    private String recipientAddress;

    /**
     * 收件人电话
     */
    private String recipientPhone;

    /**
     * 收件人
     */
    private String recipient;

    /**
     * 备注
     */
    private String remark;
}
