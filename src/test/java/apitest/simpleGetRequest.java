package apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;



public class simpleGetRequest {

    String hrURL="http://54.237.100.89:1000/ords/hr/regions";

    @Test
    public void test1(){
        Response response = RestAssured.get(hrURL);
        System.out.println(response.statusCode());
        //print JSON body
        response.prettyPrint();
    }

    /**
     * Given accept type is json
     * When the user sends get rquest to end point
     * then response status code mustt be 200
     * and body is json format
     * */
    @Test
    public void test2(){
    Response response = RestAssured.given().accept(ContentType.JSON).when().get(hrURL);
    Assert.assertEquals(response.statusCode(),200);
    Assert.assertEquals(response.contentType(),"application/json");
    }

    @Test
    public void test3(){
        RestAssured.
                given().accept(ContentType.JSON).
                when().get(hrURL).
                then().assertThat().statusCode(200).
                and().contentType("application/json");
    }

    /**
     * Given accept type is json
     * When the user sends get request to regions/2
     * then response stattuss code must be 200
     * and body is json format
     * and response body contains Americas
     *
     * */

    @Test
    public void test4(){
        Response response = RestAssured.given().accept(ContentType.JSON).get(hrURL + "/2");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");
        Assert.assertTrue(response.prettyPrint().contains("Americas"));

    }
}
