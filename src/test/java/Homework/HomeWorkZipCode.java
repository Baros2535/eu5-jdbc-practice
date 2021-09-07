package Homework;


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

        response.prettyPrint();

         assertEquals(response.statusCode(),200);
         assertEquals(response.contentType(),"application/json");
         assertEquals( response.header("Server"),"cloudflare");
         assertTrue(response.headers().hasHeaderWithName("Report-To"));

         JsonPath jsonPath = response.jsonPath();

         Map<String,Object> body= jsonPath.get();

         assertEquals(body.get("post code"),"22031");
         assertEquals(body.get("country"),"United States");
         assertEquals(body.get("country abbreviation"),"US");

         Map<String,Object> places=  (Map<String, Object>) ((List<Object>) body.get("places")).get(0);

         assertEquals(places.get("place name"),"Fairfax");
         assertEquals(places.get("state"),"Virginia");
         assertEquals(places.get("latitude"), "38.8604");

    }

    /**
     * Given Accept application/json
     * And path zipcode is 50000
     * When I send a GET request to /us endpoint
     * Then status code must be 404
     * And content type must be application/json
     * **/

    @Test
    public void A2(){
         given().accept(ContentType.JSON).with().pathParam("zipCode", 50000)
                .when().get("/us/{zipCode}")
                 .then().statusCode(404)
                 .and().contentType("application/json");

}
/***
 *
 * Given Accept application/json
 * And path state is va
 * And path city is farifax
 * When I send a GET request to /us endpoint
 * Then status code must be 200
 * And content type must be application/json
 * And payload should contains following information
 *     country abbreviation is US
 *     country  United States
 *     place name  Fairfax
 *     each places must contains fairfax as a value
 *     each post code must start with 22
 */

    @Test
    public void A3(){
        Response response = given().accept(ContentType.JSON).with().pathParam("state", "va").and().pathParam("city", "fairfax")
                .when().get("/us/{state}/{city}");
     assertEquals(response.getStatusCode(),200);
     assertEquals(response.contentType(),"application/json");
        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> body = jsonPath.get();
      assertEquals(body.get("country abbreviation"),"US");
      assertEquals(body.get("country"),"United States");
      assertEquals(body.get("place name"),"Fairfax");
        List<Map<String,Object>> places= (List<Map<String, Object>>) body.get("places");
        for (Map<String, Object> place : places) {
            assertTrue(place.get("place name").toString().contains("Fairfax"));
            assertTrue(place.get("post code").toString().startsWith("22"));
        }


    }

}
