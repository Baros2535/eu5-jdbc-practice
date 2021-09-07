package apitest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.math.BigDecimal;
import java.util.List;
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

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON).and().get("/api/spartans");

        assertEquals(response.statusCode(),200);

        List<Map<String ,Object>> allSpartans = response.body().as(List.class);
        System.out.println("allSpartans = " + allSpartans);

        System.out.println("allSpartans.get(1).get(\"name\") = " + allSpartans.get(1).get("name"));
        Map<String, Object> thirdSpartan = allSpartans.get(2);
        System.out.println("thirdSpartan = " + thirdSpartan);
    }

    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON).and().get(ConfigurationReader.get("hrBaseURL")+"/regions");
        assertEquals(response.statusCode(),200);

        Map<String ,Object> regions = response.body().as(Map.class);
        System.out.println("regions = " + regions);
        System.out.println("regions.get(\"hasMore\") = " + regions.get("hasMore"));
        System.out.println("regions.get(\"count\") = " + regions.get("count"));
        System.out.println("regions.get(\"items\") = " + regions.get("items"));
        List<Map<String,Object>> items= (List<Map<String, Object>>) regions.get("items");
        System.out.println("items = " + items);
        System.out.println("items.get(0).get(\"region_id\") = " + items.get(0).get("region_id"));


    }
}
