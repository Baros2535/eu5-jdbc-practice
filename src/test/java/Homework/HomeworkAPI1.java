package Homework;

import DAY6_POJO.Spartan;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import utilities.ExcelUtil;

import java.math.BigDecimal;
import java.util.Arrays;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class HomeworkAPI1 {

        @BeforeClass
        public void beforeClass(){
            baseURI= ConfigurationReader.get("spartanBaseURL");
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
        @DataProvider
        public Object[][] data(){
            String projectPath=System.getProperty("user.dir");
            String filePath=projectPath+"/src/test/resources/Spartan.xlsx";
            System.out.println(filePath);
            ExcelUtil excelUtil=new ExcelUtil(filePath,"spartans");
            String[][] dataArray = excelUtil.getDataArrayWithoutFirstRow();


            System.out.println("Array "+Arrays.deepToString(dataArray));
            return dataArray;

        }

        @Test(dataProvider = "data")
        public void postWithDataprovider(String name,String gender,String phone){
            System.out.println("phone = " + phone);
            String phoneString=phone.substring(0,phone.indexOf("."))+phone.substring(2,phone.indexOf("E"));
            System.out.println("phoneString = " + phoneString);

            if (phoneString.length()!=10){
                phoneString=phoneString+"0";
            }
            System.out.println("phoneString = " + phoneString);
            long phone1=Long.parseLong(phoneString);
            Spartan spartanEU=new Spartan();
            spartanEU.setName(name);
            spartanEU.setGender(gender);
            spartanEU.setPhone(phone1);
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

            int idOfSpartan = response.path("data.id");
            String nameOfSpartan = response.path("data.name");
            String  genderOfSpartan = response.path("data.gender");
            System.out.println("idOfSpartan = " + idOfSpartan);
            System.out.println("nameOfSpartan = " + nameOfSpartan);
            System.out.println("genderOfSpartan = " + genderOfSpartan);
            given().accept(ContentType.JSON).and().pathParam("id",idOfSpartan)
                    .when().get("/api/spartans/{id}")
                    .then()
                    .statusCode(200);

        }


    }


