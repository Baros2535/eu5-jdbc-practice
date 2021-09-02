package apitest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.math.BigDecimal;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import static io.restassured.RestAssured.baseURI;

public class JsonToJavaCollection {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("spartanBaseURL");
    }


    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON).and().pathParam("id", 15).when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(),200);
        Map<String,Object> jsonMap = response.body().as(Map.class);
        System.out.println(jsonMap);
        Object phone =  jsonMap.get("phone");
        BigDecimal bigDecimal=new BigDecimal(String.valueOf(phone));
        System.out.println("phone = " +bigDecimal);

    }
}
