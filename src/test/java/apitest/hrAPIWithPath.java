package apitest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class hrAPIWithPath {

    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("hrBaseURL");
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON).and().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");
        assertEquals(response.getStatusCode(),200);
        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));
        System.out.println("response.path(\"items[0]\") = " + response.path("items[0]"));
        System.out.println("response.path(\"items[0]\") = " + response.path("items[-1].country_id"));
        System.out.println("response.path(\"items[0]\") = " + response.path("items.country_id[-1]"));
        System.out.println("response.path(\"items[2].links[1]\") = " + response.path("items[2].links[0].href"));

        //assert that all regions ids equal to 2
        for (int i = 0; i < (int)response.path("count"); i++) {
            Object path = response.path("items["+i+"].region_id");
            assertEquals(path,2);
        }
        /**
        List<String> coutries=response.path("items.country_name");
        int i=0;
        for (String country : coutries) {
            i++;
            if (country.equals("Argentina")){
                System.out.println("response.path(\"items[\"+i+\"]\") = " + response.path("items[" + (i-1) + "]"));
            }

        }
        System.out.println(coutries);
    */
    }
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON).and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");
        assertEquals(response.getStatusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("IT_PROG"));
        List<String> jobId= response.path("items.job_id");
        for (String s : jobId) {

            assertEquals(s,"IT_PROG");
        }

    }
}
