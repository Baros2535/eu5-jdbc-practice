package apitest;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class hrAPIWithJsonPath {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("hrBaseURL");
    }


    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON).when().get("countries");
        JsonPath jsonPath = response.jsonPath();
        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);
        List<Object> list = jsonPath.getList("items.country_id");
        System.out.println("list = " + list);

        List<Object> list1 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        List<Object> list2 = jsonPath.getList("items.findAll {it.region_id==2}");
        System.out.println("list2 = " + list2);
        System.out.println("list1 = " + list1);

    }

    @Test
    public void test2(){
        Response response = given().queryParam("limit", 107).when().get("/employees");
        JsonPath jsonPath = response.jsonPath();
        List<Object> list = jsonPath.getList("items.first_name");
        System.out.println("list = " + list);
        List<Object>  emailsOfIT_PROG= jsonPath.getList("items.findAll {it.job_id=\"IT_PROG\"}.email");
        System.out.println("emailsOfIT_PROG = " + emailsOfIT_PROG);
        List<Object> listOfNames = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("listOfNames = " + listOfNames);
        String highSalaryPerson = jsonPath.getString("items.max {it.salary}");
        System.out.println("highSalaryPerson = " + highSalaryPerson);
        Map<Object, Object> map = jsonPath.getMap("items.max {it.salary}");
        System.out.println("map = " + map);

    }

}
