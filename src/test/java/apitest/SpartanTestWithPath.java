package apitest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanTestWithPath {
    @BeforeClass
    public void beforeClass(){
        baseURI="http://54.237.100.89:8000";
    }



     /**
   Given accept type is json
   And path param id is 10
   When user sends a get request to "api/spartans/{id}"
   Then status code is 200
   And content-type is "application/json;charset=UTF-8"
   And response payload values match the following:
           id is 10,
           name is "Lorenza",
           gender is "Female",
           phone is 3312820936
    */

     @Test
    public void test1(){
         Response response = given().accept(ContentType.JSON).and().pathParam("id", 10).
                 when().get("/api/spartans/{id}");
         assertEquals(response.getStatusCode(),200);
         assertEquals(response.contentType(),"application/json");
         response.prettyPrint();
         int id = response.path("id");
         String name = response.path("name");
         String  gender = response.path("gender");
         long phone =  response.path("phone");
         assertEquals(id,10);
         assertEquals(name,"Lorenza");
         assertEquals(gender,"Female");
         assertEquals(phone,3312820936l);


     }
     @Test
     public void test2(){
         Response response = given().accept(ContentType.JSON).when().get("/api/spartans");
         assertEquals(response.getStatusCode(),200);
         assertEquals(response.contentType(),"application/json");

         System.out.println("response.path(\"id[0]\").toString() = " + response.path("id[0]").toString());
         System.out.println("response.path(\"name[0]\").toString() = " + response.path("name[0]").toString());
         System.out.println("response.path(\"name[-1]\").toString() = " + response.path("name[-2]").toString());
        List<String> names=response.path("name");
         System.out.println(names);

         List<Object> phones= response.path("phone");
         for (Object phone : phones) {
             System.out.println(phone);
         }

     }


}
