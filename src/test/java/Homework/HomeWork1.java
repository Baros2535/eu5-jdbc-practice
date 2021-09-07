package Homework;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.ConfigurationReader;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;
public class HomeWork1 {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("hrBaseURL");
    }

    /**
     *
     * Q1:
     * - Given accept type is Json
     * - Path param value- US
     * - When users sends request to /countries
     * - Then status code is 200
     * - And Content - Type is Json
     * - And country_id is US
     * - And Country_name is United States of America
     * - And Region_id is
     *
     *
     * */

    @Test
    public void A1(){
        Response response = given().accept(ContentType.JSON).and().pathParam("country_id", "US")
                            .when().get("/countries/{country_id}");


        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        JsonPath jsonPath = response.jsonPath();
        Map<String,Object> body=jsonPath.get();
        assertEquals(body.get("country_id"),"US");
        assertEquals(body.get("country_name"),"United States of America");
        assertEquals(body.get("region_id"),2);

    }

    /**
     * - Given accept type is Json
     * - Query param value - q={"department_id":80}
     * - When users sends request to /employees
     * - Then status code is 200
     * - And Content - Type is Json
     * - And all job_ids start with 'SA'
     * - And all department_ids are 80
     * - Count is 25
     * */

    @Test
    public void A2(){

        Response response = given().accept(ContentType.JSON).and().queryParam("q", "{\"department_id\":80}")
                .when().get("/employees");
        assertEquals(response.getStatusCode(),200);
        assertEquals(response.contentType(),"application/json");
        JsonPath jsonPath = response.jsonPath();
        List<String> listOfJobId = jsonPath.getList("items.job_id");
        for (String str : listOfJobId) {
            assertTrue(str.startsWith("SA"));
        }
        List<Integer> listOfDepartmentId = jsonPath.getList("items.department_id");
        for (int id : listOfDepartmentId) {
            assertEquals(id,80);
        }
         assertEquals(jsonPath.getInt("count"),25);
    }

    /**
     * Q3:
     * - Given accept type is Json
     * -Query param value q= region_id 3
     * - When users sends request to /countries
     * - Then status code is 200
     * - And all regions_id is 3
     * - And count is 6
     * - And hasMore is false
     * - And Country_name are;
     * Australia,China,India,Japan,Malaysia,Singapore*/

    @Test
    public void A3(){
        Response response = given().accept(ContentType.JSON).and().queryParam("q", "{\"region_id\":3}")
                .when().get("/countries");

       assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        JsonPath jsonPath = response.jsonPath();
        List<Integer> listOfRegion_Id = jsonPath.getList("items.region_id");
        for (int id : listOfRegion_Id) {
          assertEquals(id,3);
        }
        int count = jsonPath.getInt("count");
        assertEquals(count,6);
        boolean hasMore = jsonPath.getBoolean("hasMore");
        assertEquals(hasMore,false);
        List<String> listOfCountryName = jsonPath.getList("items.country_name");
        List<String> expectedCountryName= Arrays.asList("Australia", "China", "India", "Japan", "Malaysia", "Singapore");
        assertEquals(listOfCountryName,expectedCountryName);

    }

}
