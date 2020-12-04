package DAY_8;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class DELETERequestDemo {

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("spartan_api_url");

    }

    @Test
    public void deleteRequest1() {
        given().log().all()
                .and().pathParam("id", 137)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204).log().all();


    }
}
