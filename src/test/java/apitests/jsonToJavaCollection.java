package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;

public class jsonToJavaCollection {

    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void SpartanToMap() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);
        /*
                .then().statusCode(200)
                .and().header("Content-Type",equalTo("application/json;charset=UTF-8"))
                .and().body("name",equalTo("Meta"));

         */

        //DE-SERIALIZATION:
        //we will convert json response to java map
        //we are keeping it Object weil it can be anything like array,int,boolean....
        Map<String, Object> jsonDataMap = response.body().as(Map.class);
        System.out.println("jsonDataMap = " + jsonDataMap);


        String name = (String) jsonDataMap.get("name");
        assertEquals(name, "Meta");
        //assertEquals(jsonDataMap.get("name"),"Meta");

        BigDecimal phone = new BigDecimal(String.valueOf(jsonDataMap.get("phone")));


        System.out.println("phone = " + phone);


    }

    @Test
    public void allSpartansToListOfMap() {

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        assertEquals(response.statusCode(), 200);

        //we need to de-serialize JSON response to List of Maps
        List<Map<String, Object>> allSpartansList = response.body().as(List.class);
        System.out.println("allSpartansList = " + allSpartansList);

        //print second spartan first name
        String secondFirstName = (String) allSpartansList.get(1).get("name");
        System.out.println("secondFirstName = " + secondFirstName);

        //save spartan 3 in a map
        Map<String, Object> thirdSpartan = allSpartansList.get(2);
        System.out.println("thirdSpartan = " + thirdSpartan);


    }

    @Test
    public void regionToMap(){
        Response response = when().get("http://54.197.12.112:1000/ords/hr/regions");
        assertEquals(response.statusCode(),200);

        //we de-serialize JSON response to Map
        Map<String,Object> regionMap = response.body().as(Map.class);

        System.out.println(regionMap.get("count"));

        System.out.println(regionMap.get("hasMore"));

        System.out.println(regionMap.get("items"));

        List<Map<String, Object>> itemList = (List<Map<String, Object>>) regionMap.get("items");
        //itemList holding listOfMap
        System.out.println(itemList.get(0).get("region_name"));


    }
}