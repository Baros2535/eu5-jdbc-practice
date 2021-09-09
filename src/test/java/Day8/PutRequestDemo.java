package Day8;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;



public class PutRequestDemo {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("spartanBaseURL");
    }
    @Test
    public void test1(){
        Map<String,Object> putRequestMap=new LinkedHashMap<>();
        putRequestMap.put("name","Barış");
        putRequestMap.put("gender","Male");
        putRequestMap.put("phone",1111111111);

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .pathParam("id",45)
                .body(putRequestMap)
                .and()
                .put("/api/spartans/{id}")
                .then()
                .statusCode(204);


    }

    @Test
    public void test2(){
        Map<String,Object> putRequestMap=new LinkedHashMap<>();
        putRequestMap.put("name","Jamal");


        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .pathParam("id",45)
                .body(putRequestMap)
                .and()
                .patch("/api/spartans/{id}")
                .then()
                .statusCode(204);


    }


}
