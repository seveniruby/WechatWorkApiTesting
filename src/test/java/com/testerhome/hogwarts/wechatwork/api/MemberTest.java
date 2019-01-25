package com.testerhome.hogwarts.wechatwork.api;

import com.testerhome.hogwarts.wechatwork.Member;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class MemberTest extends WechatWorkBase {

    static Member m=new Member();
    public static String id="zhangsan_2";
    private static String name ="xxx_2";



    @BeforeClass
    public static void beforeClassMember(){
        //remove
        m.loadMember();
        m.setContent("$.email", id+"@testerhome.com");
        m.setContent("$.mobile", String.valueOf(System.currentTimeMillis()));
        m.delete(id);
    }

    @Test
    public void demo(){
        assertThat(1, equalTo(2));
    }

    @Test
    public void addRight(){
        m.setContent("$.userid", id);
        m.setContent("$.name", name);
        m.setContent("$.external_profile.external_attr[2].miniprogram.title", "tt");
        m.add(m.getContent())
                .then().statusCode(200);

        m.query(id).then().body("name", equalTo(name));

    }

    @Test
    public void addException(){
        String udid=String.valueOf(System.currentTimeMillis());
        m.setContent("$.userid", udid);
        m.setContent("$.name", udid);
        m.setContent("$.email", udid+"@testerhome.com");
        m.setContent("$.mobile", udid);
        m.setContent("$.external_profile.external_attr[2].miniprogram.title", "tt");
        m.add(m.getContent())
                .then().statusCode(200);

        m.query(udid).then().body("name", equalTo(udid));

        m.setContent("$.userid", udid);
        m.setContent("$.name", udid);
        m.setContent("$.email", udid+"@testerhome.com");
        m.setContent("$.mobile", udid);
        m.setContent("$.external_profile.external_attr[2].miniprogram.title", "tt");
        m.add(m.getContent())
                .then().statusCode(200)
                .body("errcode", not(equalTo(0)))
                .body("errcode", equalTo(60104));
    }

    @Test
    @Ignore
    public void update(){

    }

    @AfterClass
    public static void afterMemberClass(){

    }
}
