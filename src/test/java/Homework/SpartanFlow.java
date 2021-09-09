package Homework;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.time.LocalDate;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SpartanFlow {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("spartanBaseURL");
    }
    int id;
    @Test(priority = 1)
    public void postSpartan(){

    }
    @Test(priority = 2)
    public void getSpartanAfterPost(){

    }
    @Test(priority = 3)
    public void putSpartan(){

    }
    @Test(priority = 4)
    public void getSpartanAfterPut(){

    }
    @Test(priority = 5)
    public void patchSpartan(){

    }
    @Test(priority = 6)
    public void getSpartanAfterPatch(){

    }

    @Test(priority = 7)
    public void deleteSpartan(){

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .pathParam("id",76)
                .and()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204).log().all();


    }
    @Test(priority = 8)
    public void getSpartanAfterDelete(){

    }

}

