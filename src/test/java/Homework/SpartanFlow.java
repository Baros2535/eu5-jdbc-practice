package Homework;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.AssertJUnit.*;

public class SpartanFlow {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("spartanBaseURL");
    }
    int id;
    Response response=null;
    Map<String ,Object> putList=new LinkedHashMap<>();
    Map<String,Object> patchMap=new HashMap<>();
    @Test(priority = 1)
    public void postSpartan(){
        String jsonBody="{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"Barış\",\n" +
                "  \"phone\": 1234567890\n" +
                "}";

    response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(jsonBody)
                .when().post("/api/spartans");

        assertEquals(response.statusCode(),201);
        id=response.path("data.id");
        System.out.println("id = " + id);


    }
    @Test(priority = 2)
    public void getSpartanAfterPost(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",id)
                .and().contentType(ContentType.JSON)
                .and()
                .get("/api/spartans/{id}")
        .then()
                .assertThat()
                .statusCode(200)
                .body("id",equalTo(id),
                        "name",equalTo(response.path("data.name")),
                        "gender",equalTo(response.path("data.gender")),
                        "phone",equalTo(response.path("data.phone")));

    }
    @Test(priority = 3)
    public void putSpartan(){

        putList.put ( "name","MrBrooks" );
        putList.put ( "gender","Female" );
        putList.put ( "phone",1234567891 );

      response= given().accept(ContentType.JSON)
                .and().pathParam("id",id)
                .and().contentType(ContentType.JSON)
                .and().body(putList)
                .when()
                .put("/api/spartans/{id}");
     assertEquals(response.statusCode(),204);

    }
    @Test(priority = 4)
    public void getSpartanAfterPut(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",id)
                .and().contentType(ContentType.JSON)
                .and()
                .get("/api/spartans/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(
                        "name",equalTo(putList.get("name")),
                        "gender",equalTo(putList.get("gender")),
                        "phone",equalTo(putList.get("phone")));



    }

    @Test(priority = 5)
    public void patchSpartan(){

        patchMap.put ( "gender","Female" );


        response= given().accept(ContentType.JSON)
                .and().pathParam("id",id)
                .and().contentType(ContentType.JSON)
                .and().body(patchMap)
                .when()
                .patch("/api/spartans/{id}");
        assertEquals(response.statusCode(),204);
    }
    @Test(priority = 6)
    public void getSpartanAfterPatch(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",id)
                .and().contentType(ContentType.JSON)
                .and()
                .get("/api/spartans/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(
                        "gender",equalTo(patchMap.get("gender"))
                       );


    }

    @Test(priority = 7)
    public void deleteSpartan(){

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .and()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204).log().all();
    }

    @Test(priority = 8)
    public void getSpartanAfterDelete(){


    }

}

