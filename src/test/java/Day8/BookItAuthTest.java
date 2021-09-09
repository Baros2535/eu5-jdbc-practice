package Day8;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;

public class BookItAuthTest {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("bookItAPI");
    }
String accessToken;
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and()
                .queryParam("email","jalabaster7f@drupal.org")
                .and()
                .queryParam( "password","terimapam")
                .and()
                .get("/sign");
        response.prettyPrint();
        accessToken=response.path("accessToken");
        System.out.println("accessToken = " + accessToken);


    }
    @Test
    public void test2(){

        if (accessToken==null){
            test1();
        }

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().header("Authorization", accessToken)
                .get("/api/campuses");
        response.prettyPrint();
    }

}
