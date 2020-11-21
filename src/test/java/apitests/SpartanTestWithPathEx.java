package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanTestWithPathEx {

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void test1(){
         /*
   Given accept type is json
   And path param id is 10
   When user sends a get request to "/api/spartans/{id}"
   Then status code is 200
   And content-type is "application/json;charset=UTF-8"
   And response payload values match the following:
           id is 10,
           name is "Lorenza",
           gender is "Female",
           phone is 3312820936
    */

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);

        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        System.out.println(response.path("id").toString());

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        assertEquals(id,10);
        assertEquals(name,"Lorenza");
        assertEquals(gender,"Female");
        assertEquals(phone, 3312820936l);

        assertTrue(response.headers().hasHeaderWithName("Transfer-Encoding"));
        assertEquals(response.header("Content-Type"),"application/json;charset=UTF-8");
        assertTrue(response.headers().hasHeaderWithName("Date"));


    }


}
