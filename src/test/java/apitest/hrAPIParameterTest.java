package apitest;

import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.collections.ListMultiMap;
import utilities.ConfigurationReader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class hrAPIParameterTest {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("hrBaseURL");
    }

     /**
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
        {"region_id":2}
     */

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON).and().queryParam("q", "{\"region_id\":2}").
                when().get("/countries");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("United States of America"));

    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON).and().queryParam("q", "{\"job_id\": \"IT_PROG\"}").
                when().get("/employees");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("IT_PROG"));

    }


}
