package com.testerhome.hogwarts.wechatwork.api;

import com.testerhome.hogwarts.wechatwork.WechatWork;
import org.junit.BeforeClass;

public class WechatWorkBase {
    @BeforeClass
    public static void load(){
        if(WechatWork.config==null) {
            WechatWork.load("/default.yaml");
            WechatWork.loadToken();
        }
        System.out.println(WechatWork.config);
    }

}
