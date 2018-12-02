package com.testerhome.hogwarts.wechatwork.api;

import com.testerhome.hogwarts.wechatwork.WechatWork;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.BeforeClass;

public class WechatWorkBase {

    @BeforeClass
    public static void load(){
        if(WechatWork.config==null) {
            WechatWork.load("/default.yaml");
            WechatWork.loadToken();
            RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        }
        System.out.println(WechatWork.config);
    }

}
