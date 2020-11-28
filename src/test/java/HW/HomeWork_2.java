package HW;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class HomeWork_2 {

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("spartan_api_url");


    }
    /*
    SPARTAN API
Q1:
Given accept type is json
And path param id is 20
When user sends a get request to "/api/spartans/{id}"
Then status code is 200
And content-type is "application/json;charset=UTF-8"
And response header contains Date
And Transfer-Encoding is chunked
And response payload values match the following:
id is 20,
name is "Lothario",
gender is "Male",
phone is 7551551687
     */

    @Test
    public void Q1() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 20)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);

        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        assertTrue(response.headers().hasHeaderWithName("Date"));

        assertEquals(response.header("Transfer-Encoding"), "chunked");

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        assertEquals(id, 20);
        assertEquals(name, "Lothario");
        assertEquals(gender, "Male");
        assertEquals(phone, 7551551687l);

    }

    /*
    Q2:
Given accept type is json
And query param gender = Female
And queary param nameContains = r
When user sends a get request to "/api/spartans/search"
Then status code is 200
And content-type is "application/json;charset=UTF-8"
And all genders are Female **********
And all names contains r    *********
And size is 20
And totalPages is 1
And sorted is false
     */


    @Test
    public void Q2() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "r")
                .when().get("/api/spartans/search");

        assertEquals(response.statusCode(), 200);

        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        JsonPath jsonPath = response.jsonPath();

        List<String> allGenders = jsonPath.getList("content.gender");
        System.out.println("allGenders = " + allGenders);
        for (String allGender : allGenders) {
            assertEquals(allGender, "Female");

        }

        List<String> allNames = jsonPath.getList("content.name");
        System.out.println("allNames = " + allNames);
        for (String allName : allNames) {
            assertTrue(allName.toLowerCase().contains("r"));

        }

        assertEquals(jsonPath.getInt("size"), 20);

        assertEquals(jsonPath.getInt("totalPages"), 1);

        assertEquals(jsonPath.getBoolean("sort.sorted"), false);


    }

    /*
    Q2:
    Given accept type is json
    And query param gender = Female
    And queary param nameContains = r
    When user sends a get request to "/api/spartans/search"
    Then status code is 200
    And content-type is "application/json;charset=UTF-8"
    And all genders are Female
    And all names contains r
    And size is 20
    And totalPages is 1
    And sorted is false
   */
@Test
    public void test1(){

    Map<String,Object> queryMap = new HashMap<>();
    queryMap.put("gender", "Female");
    queryMap.put("nameContains","r");

    Response response = given().accept(ContentType.JSON)
            .and().queryParams(queryMap)
            .when().get("/api/spartans/search");

    assertEquals(response.statusCode(),200);
    assertEquals(response.contentType(),"application/json;charset=UTF-8");

    JsonPath jsonPath = response.jsonPath();
    List<String> allGenders = jsonPath.getList("content.gender");
    for (String allGender : allGenders) {
        assertEquals(allGender,"Female");

    }

    List<String> allNames = jsonPath.getList("content.name");
    for (String allName : allNames) {
        assertTrue(allName.toLowerCase().contains("r"));

    }


        assertEquals(jsonPath.getInt("size"),20);

        assertEquals(jsonPath.getInt("totalPages"),1);

        assertEquals(jsonPath.getBoolean("pageable.sort.sorted"),false);











}


}
