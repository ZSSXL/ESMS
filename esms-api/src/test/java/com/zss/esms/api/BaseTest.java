package com.zss.esms.api;

import org.junit.After;
import org.junit.Before;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 14:27
 * @desc 测试基类
 */
public class BaseTest {

    @Before
    public void beforeTest() {
        System.out.println("======================================== 开始测试 =======================================");
    }

    @After
    public void afterTest() {
        System.out.println("======================================== 结束测试 =======================================");
    }

}
