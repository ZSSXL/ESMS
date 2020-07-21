package com.zss.esms.comment;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/15 9:59
 * @desc 常量类
 */
@SuppressWarnings("unused")
public class Constant {

    /**
     * esms
     */
    public static final String ESMS = "ESMS";

    /**
     * 默认当前页
     */
    public static final String DEFAULT_PAGE_NUMBER = "0";

    /**
     * 默认分页条数
     */
    public static final String DEFAULT_PAGE_SIZE = "24";

    /**
     * 角色
     */
    public interface Role {

        // 管理员
        String MANAGER = "manager";

        // 雇员
        String EMPLOYEE = "employee";
    }

    /**
     * 支付方式
     */
    public interface PaymentMethod {
        // 银行转账
        String BANK_TRANSFER = "银行转账";
        // 支票支付
        String CHECK_PAYMENT = "支票支付";
    }

    /**
     * 员工类型
     */
    public interface EmployeeType {
        // 钟点工
        String HOURLY = "钟点工";
        // 月薪工
        String MONTHLY = "月薪工";
        // 受薪工
        String SALARIED = "受薪工";
    }

    /**
     * 个人资料
     */
    public interface Profile {
        // 名称
        String EMP_NAME = "名称";
        // 邮箱
        String EMP_EMAIL = "邮箱";
        // 雇员类型
        String EMP_TYPE = "员工类型";
        // 是否加入协会
        String SOCIETY = "是否加入协会";
        //支付方式
        String PAYMENT_METHOD = "支付方式";
        // 银行卡账号
        String BANK_CARD_NUMBER = "银行卡号";
        // 银行卡持有人
        String BANK_CARD_OWNER = "银行卡持有人";
        // 收件人
        String RECIPIENT = "收件人姓名";
        // 收件地址
        String RECIPIENT_ADDRESS = "收件地址";
        // 收件人电话
        String RECIPIENT_PHONE = "收件人电话";
        // 备注
        String REMARK = "是否在出纳支取";
    }

}
