package Day6_POJO;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class HrPostRequest {

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("hr_api_url");

    }

    @Test
    public void postRegion1(){

        RegionPost regionPost = new RegionPost();
        regionPost.setRegionId(157);
        regionPost.setRegionName("Cybertek Germany");


        given().log().all().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(regionPost)
                .when().post("/regions/")
                .then().log().all().statusCode(201)
                .and().contentType("application/json")
                .and().body("region_id",equalTo(157),
                "region_name",equalTo("Cybertek Germany"));


    }

}
