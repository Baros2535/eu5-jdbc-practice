package apitest;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest {
    /**
         given accept type is Json
         And path param id is 15
         When user sends a get request to spartans/{id}
         Then status code is 200
         And content type is Json
         And json data has following
             "id": 15,
             "name": "Meta",
             "gender": "Female",
             "phone": 1938695106
          */
    @Test
    public void test1(){

    given().accept(ContentType.JSON).and().pathParam("id", 15).
            when().get(ConfigurationReader.get("spartanBaseURL") + "/api/spartans/{id}").
            then().statusCode(200).
            and().assertThat().contentType("application/json").
            and().assertThat().body("id", equalTo(15),
                              "name",equalTo("Meta"),
                                       "gender",equalTo("Female"),
                                       "phone",equalTo(1938695106));

    }

    /**
     *
     *
     *
     *              "teacherId": 10423,
     *             "firstName": "Alexander",
     *             "lastName": "Syrup",
     *             "emailAddress": "alexander@email.com",
     *             "joinDate": "05/16/2020",
     *             "password": "12345",
     *             "phone": "12345689",
     *             "subject": "Test",
     *             "gender": "male",*/
    @Test
    public void test2(){
        given().accept(ContentType.JSON).and().pathParam("id", 10423).
                when().log().all().get(ConfigurationReader.get("cbtBaseURL") + "/teacher/{id}").
                then().statusCode(200).
                and().assertThat().contentType("application/json").
                and().header("Content-Length",equalTo("236"))
                .and().header("Connection",equalTo("Keep-Alive"))
                .and().header("Date",notNullValue())
                .and().body("teachers[0].firstName",equalTo("Alexander"))
                .log().all();

    }

    @Test
    public void test3(){
        given().accept(ContentType.JSON).and().pathParam("depart_name", "Computer").
                when().log().all().get(ConfigurationReader.get("cbtBaseURL") + "/teacher/department/{depart_name}").
                then().statusCode(200).
                and().assertThat().contentType("application/json")
                .and().body("teachers.firstName",hasItems("Alexander","Marteen"));
    }
}
