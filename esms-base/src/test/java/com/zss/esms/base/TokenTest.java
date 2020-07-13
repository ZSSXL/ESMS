package com.zss.esms.base;

import com.zss.esms.util.MapUtil;
import com.zss.esms.util.TokenUtil;
import org.junit.Test;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/10 17:02
 * @desc Token - 测试
 */
public class TokenTest extends BaseTest {

    @Test
    public void getTokenTest() {
        String token = TokenUtil.createToken(MapUtil.create(
                "username", "zss",
                "password", "123456"
        ));
        System.out.println("userToken : [" + token + "]");
    }

    @Test
    public void getClaims() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlc21zIiwicGFzc3dvcmQiOiIxMjM0NTYiLCJpc3MiOiJ6c3MiLCJleHAiOjE1OTcxOTU5NDksInVzZXJuYW1lIjoienNzIn0.d_YY06TuhhwyUmeVwmHbqkH4woOiamIL8mN8s13wYAA";
        String username = TokenUtil.getClaim(token, "username").asString();
        System.out.println("Get username from token : [" + username + "]");
    }
}