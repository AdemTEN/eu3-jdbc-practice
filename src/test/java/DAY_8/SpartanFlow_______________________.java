package DAY_8;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class SpartanFlow_______________________ {

    int Id;


    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("spartan_api_url");



    }
    @Test
    public void POSTNewSpartan(){

        Map<String, Object> spartan = new HashMap<>();
        spartan.put("name","Carlos");
        spartan.put("gender","Female");
        spartan.put("phone",1234567891l);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartan)
                .when().post("/api/spartans");


        JsonPath jsonPath = response.jsonPath();
        Id = jsonPath.getInt("data.id");
        System.out.println("Id = " + Id);


                /*
                .then().assertThat().statusCode(201)
                .and().contentType("application/json")
                .and().body("data.name",equalTo("Carlos"),
                "data.gender",is("Female"),"data.phone",is(1234567891));
*/

    }

    @Test
    public void PutThatSpartan(){

        Map<String,Object> spartan = new HashMap<>();
        spartan.put("name","Carlos");
        spartan.put("gender","Male");
        spartan.put("phone",1234567891l);

            given().log().all().accept(ContentType.JSON)
                    .and().contentType(ContentType.JSON)
                    .and().pathParam("id",Id)
                    .and().body(spartan)
                    .when().put("/api/spartans/{id}")
                    .then().log().all().statusCode(204);


    }

    @Test
    public void PATCHThatSpartan(){

        Map<String,Object> spartan = new HashMap<>();
        spartan.put("phone",8888888888l);

        given().log().all().contentType(ContentType.JSON)
                .and().pathParam("id",166)
                .and().body(spartan)
                .when().patch("api/spartans/{id}")
                .then().statusCode(204);





    }

    @Test
    public void GETThatSpartan(){
        given().log().all().accept(ContentType.JSON)
                .and().pathParam("id", 169)
                .when().get("api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().body("name",is("Carlos"),
                "gender",equalTo("Female"));






    }

    @Test
    public void DELETEThatSpartan(){

        given().accept(ContentType.JSON)
                .and().pathParam("id",169)
                .when().delete("api/spartans/{id}")
                .then().statusCode(204);





    }

    @Test
    public void deleteRequest1() {
        Random random = new Random();
        int idToDelete = random.nextInt(200)+1;

        given().log().all()
                .and().pathParam("id", idToDelete)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204).log().all();


    }
}
