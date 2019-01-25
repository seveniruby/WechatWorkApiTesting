package com.testerhome.hogwarts.wechatwork;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Department {

    public DocumentContext departmentData;
    public void load(){
        departmentData =WechatWork.readJson("/department.json");
    }
    public void setContent(String key, Object value){
        departmentData.set(JsonPath.compile(key), value);

    }
    public String getContent(){
        return departmentData.jsonString();
    }

    public Response add(){
        return given().contentType(ContentType.JSON)
                .queryParam("access_token", WechatWork.token)
                .body(departmentData.jsonString())
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().statusCode(200).extract().response();
    }

    public Response query(String id){
        return given().contentType(ContentType.JSON)
                .queryParam("access_token", WechatWork.token)
                .queryParam("id", id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().statusCode(200).extract().response();

    }

    public Response delete(String id){
        return given().contentType(ContentType.JSON)
                .queryParam("access_token", WechatWork.token)
                .queryParam("id", id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().statusCode(200).extract().response();
    }

    public Response api(String key){
        return given().when().get().then().extract().response();
    }
}
