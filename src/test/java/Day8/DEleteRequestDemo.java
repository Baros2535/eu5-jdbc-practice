package Day8;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class DEleteRequestDemo {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("spartanBaseURL");
    }
    @Test
    public void test1(){
        Random random=new Random();
        int id= random.nextInt(200)+1;

            given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .and()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204).log().all();


    }
}
