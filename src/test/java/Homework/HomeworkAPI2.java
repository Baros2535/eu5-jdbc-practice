package Homework;

import DAY6_POJO.Spartan;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.lang.reflect.Type;
import java.util.*;

import static io.restassured.RestAssured.given;

public class HomeworkAPI2 {
//Homework-2
//-Create one mackaroo api for name,gender,phone
//send get request to retrieve random info from that api
//use those info to send post request to spartan


    @Test
    public void test() throws InterruptedException {
        Response response = given().accept(ContentType.JSON)
                .queryParam("key", "5bda3aa0")
                .when()
                .get(ConfigurationReader.get("mokaAPI"));
           Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();
       //with Json we can handle
       /* JsonPath jsonPath = response.jsonPath();
        List<Map<String,Object>> der=  jsonPath.get();

        System.out.println("der = " + der);*/

        List <Map<String,Object>> spartans= response.body().as(List.class);

        System.out.println("spartans = " + spartans);

      List<Map<String,Object>> spartansFinal=new ArrayList<>();
        for (Map<String, Object> spartan : spartans) {
            Map<String, Object> temp = new LinkedHashMap<>();
            temp.put("name", spartan.get("name"));
            temp.put("gender", spartan.get("gender"));
            temp.put("phone", Long.parseLong((String) spartan.get("phone")));
            spartansFinal.add(temp);
        }
        System.out.println("spartansFinal.get(0).get(\"phone\") = " + spartansFinal.get(0).get("phone"));

List<Map<String,Object>> actualList=new ArrayList<>();
        Response response2;
        List<Integer>idsOfSpartanAdded=new ArrayList<>();
        //we can handle with the Gson
        //Gson gson=new Gson();
        for (Map<String, Object> spartan : spartansFinal) {
           // String jsonBody =gson.toJson(spartan);
            response2 = given().accept(ContentType.JSON).
            log().all()
                    .and()
                    .contentType(ContentType.JSON)
                    .body(spartan)
                    .when()
                    .post(ConfigurationReader.get("spartanBaseURL") + "/api/spartans");

            Assert.assertEquals(response2.statusCode(),201);
         Map<String,Object> actualMap=new LinkedHashMap<>();

           // actualMap.put("id",response2.path("data.id"));
            actualMap.put("name",response2.path("data.name"));
            actualMap.put("gender",response2.path("data.gender"));
            actualMap.put("phone",response2.path("data.phone"));
            idsOfSpartanAdded.add( response2.path("data.id"));
            actualList.add(actualMap);
        }


        System.out.println("idsOfSpartanAdded = " + idsOfSpartanAdded);

        Response response1 = given().accept(ContentType.JSON)
                .when()
                .get(ConfigurationReader.get("spartanBaseURL" )+ "/api/spartans");
        List<Integer>allSpartansIds=response1.path("id");
        Assert.assertTrue(allSpartansIds.containsAll(idsOfSpartanAdded));



    }

}