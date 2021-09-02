package apitest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class SpartanTestWithParameters {

    @BeforeClass
    public void beforeClass(){
     baseURI= ConfigurationReader.get("spartanBaseURL");
    }

    /**
        Given accept type is Json
        And Id parameter value is 5
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 200
        And response content-type: application/json;charset=UTF-8
        And "Blythe" should be in response payload
     */
    @Test
    public void test1(){
      //  Response response = given().accept(ContentType.JSON).when().get("api/spartans/5");
        Response response = given().accept(ContentType.JSON).pathParam("id", 5).get("api/spartans/{id}");

        assertEquals(response.getStatusCode(),200);
        assertEquals(response.getContentType(),"application/json");
        assertTrue(response.body().asString().contains("Blythe"));

    }

    /**
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json;charset=UTF-8
        And "Spartan Not Found" message should be in response payload
     */

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON).and().pathParam("id", 500).
                when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(),404);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("Not Found"));

    }

     /**
        Given accept type is Json
        And query parameter values are :
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json;charset=UTF-8
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

     @Test
    public void test3(){
         Map<String,Object> query=new LinkedHashMap<>();
         query.put("gender","Female");
         query.put("nameContains","e");
         Response response = given().accept(ContentType.JSON).and().queryParam("query").
                             when().get("api/spartans/search");
         //verify status code
         assertEquals(response.statusCode(),200);
         //verify content type
         assertEquals(response.contentType(),"application/json");
         //verify Female and Janette in response
         assertTrue(response.body().asString().contains("Female"));
         assertTrue(response.body().asString().contains("Janette"));


     }

}
