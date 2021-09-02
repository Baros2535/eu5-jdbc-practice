package apitest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;



import static io.restassured.RestAssured.*;
public class SpartanGetRequest {
    String spartanURL="http://54.237.100.89:8000";

    @Test
    public void test1(){
        Response response = when().get(spartanURL + "/api/spartans");
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        response.prettyPrint();
    }


    /**
     * When Users sens a get request to /api/spartans/3
     * Then status code is 200
     * And content type should be application/json
     * and json  body should contain Fidole
     *
     *
     * */
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON).
                when().get(spartanURL + "/api/spartans/3");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");
        Assert.assertTrue(response.body().asString().contains("Fidole"));


    }
     /**
        Given no headers provided
        When Users sends GET request to /api/hello
        Then response status code should be 200
        And Content type header should be “text/plain;charset=UTF-8”
        And header should contain date
        And Content-Length should be 17
        And body should be “Hello from Sparta"
        */

     @Test
    public void test3(){
         Response response = when().get(spartanURL + "/api/hello");
         Assert.assertEquals(response.statusCode(),200);
         Assert.assertEquals(response.contentType(),"text/plain;charset=UTF-8");
         Assert.assertTrue(response.headers().hasHeaderWithName("Date"));
         Assert.assertEquals(response.header("Content-Length"),"17");
         Assert.assertEquals(response.body().asString(),"Hello from Sparta");

     }
}
