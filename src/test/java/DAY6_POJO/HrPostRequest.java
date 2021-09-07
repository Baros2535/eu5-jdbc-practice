package DAY6_POJO;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HrPostRequest {

    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("hrBaseURL");
    }

    @Test
    public void test1(){
        int region_id=31;
        String region_name="CybertekTurkey";
        RegionPost regionPost=new RegionPost();
        regionPost.setRegion_id(region_id);
        regionPost.setRegion_name(region_name);

        given().log().all().accept(ContentType.JSON).and().contentType(ContentType.JSON)
                .body(regionPost)
                .and()
                .post("/regions/")
                .then().log().all()
                .statusCode(201)
                .contentType("application/json")
                .body("region_id",is(region_id),
                       "region_name",equalTo(region_name) );

    }
}
