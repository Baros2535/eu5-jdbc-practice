package DAY6_POJO;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;




public class Pojo_deserialize {


@Test
    public void oneSpartan(){
    Response response = given().accept(ContentType.JSON).pathParam("id", 15).when().get(ConfigurationReader.get("spartanBaseURL")+"/api/spartans/{id}");
assertEquals(response.statusCode(),200);
    Spartan spartan15 = response.body().as(Spartan.class);
    System.out.println("spartan15 = " + spartan15);
    System.out.println("spartan15.getName() = " + spartan15.getName());
    assertEquals(spartan15.getId(),15);
}

@Test
    public void regionToPojo(){
    Response response = given().accept(ContentType.JSON).when().get(ConfigurationReader.get("hrBaseURL")+"/regions");
    assertEquals(response.statusCode(),200);
    Regions regions = response.body().as(Regions.class);
    System.out.println("regions.getHasMore() = " + regions.getHasMore());
    System.out.println("regions.getCount() = " + regions.getCount());
    System.out.println("regions.getItems().get(0) = " + regions.getItems().get(0).getRegionId());
    List<Item> items = regions.getItems();
    System.out.println("items = " + items);
}
@Test
    public void gson_example(){
    Gson gson=new Gson();

    String myJsonDada="{\n" +
            "    \"id\": 15,\n" +
            "    \"name\": \"Meta\",\n" +
            "    \"gender\": \"Female\",\n" +
            "    \"phone\": 1938695106\n" +
            "}";
    Map<String ,Object> spartan = gson.fromJson(myJsonDada, Map.class);
    Spartan spartan1 = gson.fromJson(myJsonDada, Spartan.class);
    System.out.println(spartan1);
    System.out.println(spartan);
Spartan spartan2=new Spartan(200,"Mike","Male",123456789);
String jsonSpartan=gson.toJson(spartan2);
    System.out.println("jsonSpartan = " + jsonSpartan);
    System.out.println("gson.toJson(spartan1) = " + gson.toJson(spartan1));


}
}
