package com.testerhome.hogwarts.wechatwork;

import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Media {
    public Response add(){
        return given().queryParam("type", "image")
                .queryParam("access_token", WechatWork.token)
                .multiPart(new File(getClass().getResource("/logo.txt").getFile()))
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/media/upload").then().extract().response();
    }

}
