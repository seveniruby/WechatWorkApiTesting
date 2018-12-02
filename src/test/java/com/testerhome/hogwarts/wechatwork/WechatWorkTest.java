package com.testerhome.hogwarts.wechatwork;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class WechatWorkTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void load() {
        String path = "/tmp/1.yaml";
        assertEquals(WechatWork.config, null);
        WechatWork.config = new Config();
        WechatWork.config.setCorpId("demo");
        WechatWork.write(path);
        WechatWork.config.setCorpId("demo2");
        WechatWork.load(new File(path));
        assertEquals(WechatWork.config.getCorpId(), "demo");
    }

    @Test
    public void json() {
        String json = "{\"objs\" : [{\"obj\" : 1411455611975}]}";
        DocumentContext ext = JsonPath.parse(json);
        JsonPath p = JsonPath.compile("$.objs[0].obj");
        ext.set(p, 141145561197333L);
        String author = ext.jsonString();
        System.err.println(author);
    }

}