package com.testerhome.hogwarts.wechatwork;

import javafx.beans.value.WeakChangeListener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class WechatWorkTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void load() {
        String path="/tmp/1.yaml";
        assertEquals(WechatWork.config, null);
        WechatWork.config=new Config();
        WechatWork.config.setCorpId("demo");
        WechatWork.write(path);
        WechatWork.config.setCorpId("demo2");
        WechatWork.load(new File(path));
        assertEquals(WechatWork.config.getCorpId(), "demo");


    }
}