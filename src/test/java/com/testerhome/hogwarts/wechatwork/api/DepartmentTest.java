package com.testerhome.hogwarts.wechatwork.api;

import com.testerhome.hogwarts.wechatwork.Department;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

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
        department.delete("2");
    }
    @Test
    public void add() {
        department.query("");
        department.delete(number);

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

}