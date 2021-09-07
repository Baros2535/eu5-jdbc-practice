package DAY6_POJO;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;
public class PostRequestDemo {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("spartanBaseURL");
    }


    /**
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"MikeEU",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */

    @Test
    public void test1(){
        String myJsonDada="{\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(myJsonDada)
                .when()
                .post("/api/spartans");
        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json");
      assertEquals(response.path("success"),"A Spartan is Born!");
        String name = response.path("data.name");
      String gender=  response.path("data.gender");
      int phone=response.path("data.phone");
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);
        assertEquals(name,"Meta");
        assertEquals(gender,"Female");
        assertEquals(phone,1938695106);
        JsonPath jsonPath = response.jsonPath();
        Map<String,Object> spartan=jsonPath.get();
        System.out.println("spartan = " + spartan);


    }
    @Test
    public void test2(){
        Map<String,Object> requestMap=new LinkedHashMap<>();
        requestMap.put("gender","Male");
        requestMap.put("name","MikeEU");
        requestMap.put("phone",1938695106);

        given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap)

        .when()
                .post("/api/spartans")
        .then()
                .statusCode(201)
                .and()
                .contentType("application/json")
                .body("success",is("A Spartan is Born!"),
                       "data.name",is("MikeEU"),
                       "data.gender",is("Male"),
                        "data.phone",is(1938695106));
    }

    @Test
    public void test3(){

       Spartan spartanEU=new Spartan();
       spartanEU.setName("Esat");
       spartanEU.setGender("Male");
       spartanEU.setPhone(1958695106);
        System.out.println("spartanEU = " + spartanEU);
        given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .log().all()
                .body(spartanEU)
                .when()
                .post("/api/spartans")
                .then()
                .log().all()
                .statusCode(201)
                .and()
                .contentType("application/json")
                .body("success",is("A Spartan is Born!"),
                        "data.name",equalTo("Esat"),
                        "data.gender",equalTo("Male"),
                        "data.phone",equalTo(1958695106));




    }

    //Optional homeworks
    //Homework-1
    //1-Create csv file from mackaroo website, which includes name,gender,phone
    //2-Download excel file
    //3- using testng data provider and apache poi create data driven posting from spartan



    //Homework-2
    //-Create one mackaroo api for name,gender,phone
    //send get request to retrieve random info from that api
    //use those info to send post request to spartan

    @Test
    public void test4(){

        Spartan spartanEU=new Spartan();
        spartanEU.setName("Esat");
        spartanEU.setGender("Male");
        spartanEU.setPhone(1958695106);
        System.out.println("spartanEU = " + spartanEU);

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .log().all()
                .body(spartanEU)
                .when()
                .post("/api/spartans");

        int id = response.path("data.id");
        String name = response.path("data.naem");
        String  gender = response.path("data.gender");

        given().accept(ContentType.JSON).and().pathParam("id",id)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(200);

    }


}
