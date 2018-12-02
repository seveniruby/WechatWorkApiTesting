package com.testerhome.hogwarts.wechatwork.api;

import org.junit.Test;
import org.hamcrest.core.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class MemberTest extends WechatWorkBase {
    @Test
    public void demo(){
        assertThat(1, equalTo(2));
    }

    @Test
    public void addMember(){
        Member member=new Member();
        member.addMember("xxx");
    }
}
