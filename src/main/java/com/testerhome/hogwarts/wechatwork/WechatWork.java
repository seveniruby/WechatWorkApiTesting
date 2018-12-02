package com.testerhome.hogwarts.wechatwork;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class WechatWork {
    public static Config config=null;
    public static String token=null;
    public static void load(File file){
        ObjectMapper mapper=new ObjectMapper(new YAMLFactory());
        try {
            config=mapper.readValue(file, Config.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load(String path){
        load(new File(WechatWork.class.getResource(path).getFile()));
    }

    public static void write(String path){
        ObjectMapper mapper=new ObjectMapper(new YAMLFactory());
        try {
            mapper.writeValue(new File(path), config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadToken(){
        if(token==null) {
            token = given()
                    .queryParam("corpid", WechatWork.config.getCorpId())
                    .queryParam("corpsecret", WechatWork.config.getSecret())
                    .when().get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                    .then()
                    .statusCode(200).body("errcode", equalTo(0)).extract().path("access_token");
        }
        System.out.println(token);
    }

}
