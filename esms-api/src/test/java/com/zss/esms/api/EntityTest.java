package com.zss.esms.api;

import com.zss.esms.model.entity.mongdb.Profile;
import com.zss.esms.util.MapUtil;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 14:29
 * @desc entity - 测试
 */
public class EntityTest extends BaseTest {

    @Test
    public void paymentMethodTest() {

        /*PaymentMethod pm = PaymentMethod.builder()
                .empId("1001")
                .methodId("2001")
                .choice(Bank.builder()
                        .cardNumber("2837883478884723")
                        .cardOwner("张三")
                        .build())
                .build();
        System.out.println("Payment Method : [" + pm + "]");*/
    }

    @Test
    public void profileTest() {
        Profile build = Profile.builder()
                .profileId("u9jsdncuihsdfef")
                .empId("nnjdsiuvv9ew92d")
                .empType("test")
                .empCode("2034")
                .empEmail("1233463@23.com")
                .empName("name")
                .entryTime("2020-06-22")
                .society(false)
                .weeklyDeduction(new BigDecimal("3.2"))
                .salary(MapUtil.create(
                        "hourlySalary", "40.0"
                ))
                .build();
        System.out.println("Profile : [" + build + "]");
    }
}
