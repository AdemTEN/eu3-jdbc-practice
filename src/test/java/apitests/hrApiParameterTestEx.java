package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class hrApiParameterTestEx {

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("hr_api_url");
    }

    /*
       Given accept type is Json
       And parameters: q = {"region_id":2}
       When users sends a GET request to "/countries"
       Then status code is 200
       And Content type is application/json
       And Payload should contain "United States of America"
       {"region_id":2}
    */
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(response.statusCode(), 200);

        assertEquals(response.contentType(), "application/json");


    }

     /*
       Given accept type is Json
       And parameters: q = {"region_id":2}
       When users sends a GET request to "/countries"
       Then status code is 200
       And Content type is application/json
       And Payload should contain "United States of America"

    */

    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.get("items.country_name[-1]"), "United States of America");

        List<String> allCountryNames = jsonPath.getList("items.country_name");

        assertTrue(allCountryNames.contains("United States of America"));

        for (String allCountryName : allCountryNames) {
            if (allCountryName.equals("United States of America")) {
                System.out.println("allCountryName = " + allCountryName);

            }


        }


    }
    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .queryParam("limit",15)
                .when().get("/countries");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.headers().hasHeaderWithName("Date"));

        JsonPath jsonPath = response.jsonPath();

        List<String> allCountryID = jsonPath.getList("items.findAll{it.region_id==2}.country_id");
        System.out.println("allCountryID = " + allCountryID);

        String countryName = jsonPath.getString("items.country_name[7]");
        //in postman Automationindex is different ==> "items[7].country_name";
        System.out.println("countryName = " + countryName);
        
        String href = jsonPath.getString("items.links[-2].href[0]");
        System.out.println("href = " + href);

        String linkhref = jsonPath.getString("links.href[2]");
        System.out.println("linkhref = " + linkhref);




    }


}
