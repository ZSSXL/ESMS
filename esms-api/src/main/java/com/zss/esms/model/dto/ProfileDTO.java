package com.zss.esms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/16 14:59
 * @desc 个人资料DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO implements Serializable {

    /**
     * 名称
     */
    private String empName;
    /**
     * 邮箱
     */
    private String empEmail;
    /**
     * 雇员类型
     */
    private String empType;
    /**
     * 是否加入协会
     */
    private String society;
    /**
     * 支付方式
     */
    private String paymentMethod;
    /**
     * 银行卡账号
     */
    private String bankCardNumber;
    /**
     * 银行卡持有人
     */
    private String bankCardOwner;
    /**
     * 收件人
     */
    private String recipient;
    /**
     * 收件地址
     */
    private String recipientAddress;
    /**
     * 收件人电话
     */
    private String recipientPhone;
    /**
     * 备注
     */
    private String remark;

}
