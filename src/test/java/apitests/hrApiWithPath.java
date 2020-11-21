package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;



public class hrApiWithPath {

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("hr_api_url");

    }

    @Test
    public void getCountriesWithPath() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.path("count").toString(), "5");

        //print limit value
        System.out.println(response.path("limit").toString());
        System.out.println(response.path("hasMore").toString());

        System.out.println("***********************************************");

        //in order to point specific item inside array we use dot(.) and index number
        String firstCountryId = response.path("items.country_id[0]");//dot(.) points arrays
        System.out.println("firstCounnryId = " + firstCountryId);

        String lastCountryName = response.path("items.country_name[-1]");
        System.out.println("lastCountryName = " + lastCountryName);

        //print   first country name =Argentina
        String firstCountryName = response.path("items.country_name[0]");

        //print all country name
        List<String> allCountryNames = response.path("items.country_name");
        for (String allCountryName : allCountryNames) {
            System.out.println(allCountryName);

        }

        //print second links and first href

        String secondLinksFirstHref = response.path("items.links[1].href[0]");
        System.out.println("secondLinksFirstHref = " + secondLinksFirstHref);


        //print last links and first rel
        String lastLinksFirstRel = response.path("items.links[-1].rel[0]");
        System.out.println("lastLinksFirstRel = " + lastLinksFirstRel);


        List<String> allLinksRel = response.path("items.links.rel");
        System.out.println("allLinks = " + allLinksRel);

        String thirdLinksFirstHref = response.path("items.links[2].href[0]");
        System.out.println("thirdLinksFirstHref = " + thirdLinksFirstHref);

        List<String> allCountryName = response.path("items.country_name");
        System.out.println("allCountryName = " + allCountryName);

        //assert that all regions id are equal to 2
        List<Object> allRegionIds = response.path("items.region_id");
        System.out.println("allRegionIds = " + allRegionIds);


        for (Object allRegionId : allRegionIds) {
            assertEquals(allRegionId, 2);

        }

    }


    /*
       Given accept type is Json
       And parameters: q = {"job_id": "IT_PROG"}
       When users sends a GET request to "/employees"
       Then status code is 200
       And Content type is application/json
       And Payload should contain "IT_PROG"
       //make sure we have only IT_PROG as a job_id

    */
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(response.statusCode(),200);

        assertEquals(response.contentType(),"application/json");

        assertTrue(response.body().asString().contains("IT_PROG"));


        List<String> allJobIds = response.path("items.job_id");
        System.out.println("allJobIds = " + allJobIds);

        for (String allJobId : allJobIds) {
            System.out.println("allJobId = " + allJobId);
            assertEquals(allJobId,"IT_PROG");

        }

    }

}



