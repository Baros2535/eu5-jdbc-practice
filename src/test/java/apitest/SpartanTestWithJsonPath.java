package apitest;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanTestWithJsonPath {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("spartanBaseURL");
    }

 /**
          Given accept type is json
          And path param spartan id is 11
          When user sends a get request to /spartans/{id}
         Then status code is 200
         And content type is Json
         And   "id": 11,
               "name": "Nona",
              "gender": "Female",
              "phone": 7959094216
    */


    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON).pathParam("id", 11)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");
        assertEquals(id,11);
        assertEquals(name,"Nona");
        assertEquals(gender,"Female");
        assertEquals(phone,7959094216l);


        JsonPath jsonPath = response.jsonPath();
        int id1 = jsonPath.getInt("id");
        String name1 = jsonPath.getString("name");
        String gender1 = jsonPath.getString("gender");
        long phone1 = jsonPath.getLong("phone");



        assertEquals(id1,11);
        assertEquals(name1,"Nona");
        assertEquals(gender1,"Female");
        assertEquals(phone1,7959094216l);

    }

}
