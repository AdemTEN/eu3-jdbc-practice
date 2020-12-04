package DAY_8;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class SpartanFlow_______________________ {

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("spartan_api_url");

    }
    @Test
    public void POSTNewSpartan(){

    }

    @Test
    public void PutThatSpartan(){

    }

    @Test
    public void PATCHThatSpartan(){

    }

    @Test
    public void GETThatSpartan(){

    }

    @Test
    public void DELETEThatSpartan(){

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
