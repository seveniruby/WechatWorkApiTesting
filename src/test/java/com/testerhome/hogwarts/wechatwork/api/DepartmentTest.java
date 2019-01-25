package com.testerhome.hogwarts.wechatwork.api;

import com.testerhome.hogwarts.wechatwork.Department;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.tika.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.equalTo;



@RunWith(Parameterized.class)
public class DepartmentTest extends WechatWorkBase {


    public static Department department;


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"3", "北京第三期"}, {"4", "北京第四期"}, {"5", "北京第五期"}
        });
    }

    @Parameterized.Parameter
    public String number;
    @Parameterized.Parameter(1)
    public String name;

    @BeforeClass
    public static void beforeClass(){
        department=new Department();
    }
    @Test
    @DisplayName("add department")
    @Description("测试三条数据")
    public void add() {
        department.delete(number).then().statusCode(200);


        String content="";
        FileInputStream fisTargetFile = null;
        try {
            fisTargetFile = new FileInputStream(new File(getClass().getResource("/logo.png").getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            content = IOUtils.toString(fisTargetFile, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Allure.addAttachment("demo", "dddddddddddddddddd");
        Allure.addAttachment("logo", "png", content);
        department.load();
        department.setContent("$.name", name);
        department.setContent("$.id", number);
        department.add()
                .then().statusCode(200).body("errcode", equalTo(0));

        department.query(number)
                .then()
                .statusCode(200)
                .body("department.find {d -> d.id == " + number + "}.name", equalTo(name));

    }

    @Test
    public void update(){
        department.api("update").then().statusCode(200);
    }

}