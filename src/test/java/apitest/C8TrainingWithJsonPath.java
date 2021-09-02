package apitest;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class C8TrainingWithJsonPath {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("cbtBaseURL");
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON).and().pathParam("studentId", 24111)
                .when().get("/student/{studentId}");
        assertEquals(response.statusCode(),200);
        JsonPath jsonPath = response.jsonPath();
        Map<Object, Object> map = jsonPath.getMap("students[0]");
        String firstName= jsonPath.getString("students[0].firstName");
        System.out.println(map);
        Map<Object, Object> map1 = jsonPath.getMap("students[0].contact");
        System.out.println("map1 = " + map1.get("phone"));
        System.out.println(firstName);
        //get me city and zipcode
        Map<Object, Object> map2 = jsonPath.getMap("students[0].company.address");
        assertEquals(map2.get("city"),"West Elwood");
        assertEquals(map2.get("zipCode"),7504);


    }
}
