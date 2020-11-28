package HW;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class HomeWork_1 {

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("hr_api_url");


    }

    /*
    Q1:
- Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
- And Region_id is
     */

    @Test
    public void Q1() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("country_id", "US")
                .when().get("/countries/{country_id}");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        String countryID = response.path("country_id");
        String countryName = response.path("country_name");
        int regionId = response.path("region_id");

        assertEquals(countryID, "US");
        assertEquals(countryName, "United States of America");
        assertEquals(regionId, 2);

    }
    /*
    Q2:
- Given accept type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
- And Content - Type is Json
- And all job_ids start with 'SA'
- And all department_ids are 80
- Count is 25
     */


    @Test
    public void Q2() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"department_id\":80}")
                .when().get("/employees");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        JsonPath jsonPath = response.jsonPath();

        //And all job_ids start with 'SA'

        List<String> allJobIDs = jsonPath.getList("items.job_id");
        System.out.println("allJobIDs = " + allJobIDs);

        for (String allJobID : allJobIDs) {
            assertTrue(allJobID.startsWith("SA"));

        }


        //And all department_ids are 80
        List<Integer> allDEpartmentIDs = jsonPath.getList("items.department_id");
        System.out.println("allDEpartmentID = " + allDEpartmentIDs);

        for (int allDEpartmentID : allDEpartmentIDs) {
            assertEquals(allDEpartmentID, 80);

        }

        //Count is 25
        int count = jsonPath.getInt("count");
        System.out.println("count = " + count);
        assertEquals(jsonPath.getInt("count"),25);


    }

    /*
    Q3:
- Given accept type is Json
-Query param value q= {"region_id" :3}
- When users sends request to /countries
- Then status code is 200
- And all regions_id is 3
- And count is 6
- And hasMore is false
- And Country_name are;
Australia,China,India,Japan,Malaysia,Singapore
     */
    @Test
    public void Q3(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\" :3}")
                .when().get("/countries");

        assertEquals(response.statusCode(),200);
        JsonPath jsonPath = response.jsonPath();

        List<Integer> allRegionsIds = jsonPath.getList("items.region_id");
        for (int allRegionsId : allRegionsIds) {
                assertEquals(allRegionsId, 3);

        }
        /*
        int count = response.path("count");
        assertEquals(count,6);
       */
        assertEquals(jsonPath.getInt("count"),6);
        assertEquals(jsonPath.getBoolean("hasMore"),false);

        List<String > expectedCountryNames = new ArrayList<>(Arrays.asList("Australia","China","India","Japan","Malaysia","Singapore"));
        List<String> actualCountryNames = jsonPath.getList("items.country_name");

        assertEquals(actualCountryNames,expectedCountryNames);
    }


}
