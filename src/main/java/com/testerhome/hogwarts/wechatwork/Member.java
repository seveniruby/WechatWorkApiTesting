package com.testerhome.hogwarts.wechatwork;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Member {
    public DocumentContext member;
    public void loadMember(){
        member=WechatWork.readJson("/member.json");
    }
    public void setContent(String key, Object value){
        member.set(JsonPath.compile(key), value);

    }
    public String getContent(){
        return member.jsonString();
    }

    public Response add(String body){
        return given().contentType(ContentType.JSON)
                .queryParam("access_token", WechatWork.token)
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().statusCode(200).extract().response();
    }

    public Response delete(String id){
        return given().queryParam("userid", id).queryParam("access_token", WechatWork.token)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/delete").then().extract().response();

    }
    public Response query(String id){
        return given().queryParam("userid", id).queryParam("access_token", WechatWork.token)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/get").then().extract().response();
    }

}
