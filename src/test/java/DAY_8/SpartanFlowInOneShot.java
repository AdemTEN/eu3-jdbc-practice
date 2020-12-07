package DAY_8;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class SpartanFlowInOneShot {

    int Id;

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("spartan_api_url");


    }

    @Test
    public void POSTNewSpartan() {

        //POST

        Map<String, Object> spartan = new HashMap<>();
        spartan.put("name", "Carlos");
        spartan.put("gender", "Female");
        spartan.put("phone", 1234567891l);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartan)
                .when().post("/api/spartans");

        Id = response.path("data.id");

        //PUT
        Map<String, Object> spartan1 = new HashMap<>();
        spartan1.put("name", "Carlos");
        spartan1.put("gender", "Male");
        spartan1.put("phone", 1234567891l);

        given().log().all().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParam("id", Id)
                .and().body(spartan1)
                .when().put("/api/spartans/{id}")
                .then().log().all().statusCode(204);



        //PATCH
        Map<String, Object> spartan2 = new HashMap<>();
        spartan2.put("phone", 8888888888l);

        given().log().all().contentType(ContentType.JSON)
                .and().pathParam("id", Id)
                .and().body(spartan2)
                .when().patch("api/spartans/{id}")
                .then().statusCode(204);

        //GET
        given().log().all().accept(ContentType.JSON)
                .and().pathParam("id", Id)
                .when().get("api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().body("name", is("Carlos"),
                "gender", equalTo("Male"));


        //DELETE
        given().accept(ContentType.JSON)
                .and().pathParam("id",Id)
                .when().delete("api/spartans/{id}")
                .then().statusCode(204);


    }

}
