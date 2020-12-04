package DAY_8;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class PUTRequestDemo {

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("spartan_api_url");

    }

    @Test
    public void test1(){
        //create one map for the put request json body
        Map<String,Object> putRequestMap = new HashMap<>();
        putRequestMap.put("name","Marco");
        putRequestMap.put("gender","Male");
        putRequestMap.put("phone",1234567891);

        given().log().all().contentType(ContentType.JSON)
                .and().pathParam("id",113)
                .and().body(putRequestMap)
                .when().put("/api/spartans/{id}")
                .then().assertThat().statusCode(204);

        //send get request to verify body
        given().log().all().accept(ContentType.JSON)
                .and().pathParam("id",113)
                .and().get("/api/spartans/{id}")
                .then().log().all().statusCode(200)
                .and().body("name",is("Marco"),
                "gender",equalTo("Male"),
                "phone",is(1234567891));



     }

     @Test
    public void PatchTest(){

         //create one map for the put request json body
         Map<String,Object> patchRequestMap = new HashMap<>();
         patchRequestMap.put("name","TJ");


         given().log().all().contentType(ContentType.JSON)
                 .and().pathParam("id",113)
                 .and().body(patchRequestMap)
                 .when().patch("/api/spartans/{id}")
                 .then().assertThat().statusCode(204);


     }


}
