package com.testerhome.hogwarts.wechatwork.api;

import com.testerhome.hogwarts.wechatwork.Media;
import org.junit.Test;

import java.io.File;

public class MediaTest extends WechatWorkBase{

    @Test
    public void add() {

        System.out.println(getClass().getResource("/logo.txt"));
        System.out.println(getClass().getResource("/member.json"));
        System.out.println(getClass().getResource("/logo.txt").getFile());
        System.out.println(new File(getClass().getResource("/logo.txt").getFile()));
        Media m=new Media();
        m.add().then().statusCode(200);
    }
}