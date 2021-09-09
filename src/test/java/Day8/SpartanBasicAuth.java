package Day8;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SpartanBasicAuth {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("spartanBaseURL");
    }
    @Test
    public void test1(){
        given().log().all()
                .and()
                .auth()
                .basic("admid","admin")
                .contentType(ContentType.JSON)
                .and()
                .get("/api/spartans/")
                .then()
                .statusCode(200);
    }
}
