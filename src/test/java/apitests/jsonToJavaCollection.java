package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;

public class jsonToJavaCollection {

    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void SpartanToMap() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        /*
                .then().statusCode(200)
                .and().header("Content-Type",equalTo("application/json;charset=UTF-8"))
                .and().body("name",equalTo("Meta"));

         */

        //we will convert json response to java map
        Map<String,Object> jsonDataMap = response.body().as(Map.class);


    }
}