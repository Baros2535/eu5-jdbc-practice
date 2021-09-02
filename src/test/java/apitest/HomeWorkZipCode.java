package apitest;


import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import static org.testng.Assert.*;
public class HomeWorkZipCode {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("zipCodeBaseURL");
    }


    /**
     * Given Accept application/json
     * And path zipcode is 22031
     * When I send a GET request to /us endpoint
     * Then status code must be 200
     * And content type must be application/json
     * And Server header is cloudflare
     * And Report-To header exists
     * And body should contains following information
     *     post code is 22031
     *     country  is United States
     *     country abbreviation is US
     *     place name is Fairfax
     *     state is Virginia
     *     latitude is 38.8604
     * */
    @Test
    public void A1(){
        Response response = given().accept(ContentType.JSON).with().pathParam("zipCode", 22031)
                .when().get("/us/{zipCode}");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
       assertEquals( response.header("Server"),"cloudflare");


      assertTrue(response.headers().hasHeaderWithName("Report-To"));
        JsonPath jsonPath = response.jsonPath();
      Map<String,Object> body= jsonPath.get();
        System.out.println("body = " + body);
        assertEquals(body.get("post code"),"22031");
        assertEquals(body.get("country"),"United States");
        assertEquals(body.get("country abbreviation"),"US");




    }

}
