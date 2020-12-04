package Day6_POJO;

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

public class PostRequestDemo {

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("spartan_api_url");

    }

     /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"MikeEU",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */


    // 1. WAY (Not recommended):

    @Test
    public void postNewSpartan() {

        String jsonBody = "{\n" +
                "      \"gender\":\"Male\",\n" +
                "      \"name\":\"MikeEU\",\n" +
                "      \"phone\":8877445596\n" +
                "   }";


        Response response = given().log().all().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(jsonBody)
                .when().post("/api/spartans");

        response.prettyPrint();

        assertEquals(response.statusCode(), 201);

        assertEquals(response.contentType(), "application/json");

        //verify successful message
        assertEquals(response.path("success"), "A Spartan is Born!");

        assertEquals(response.path("data.name"), "MikeEU");
        assertEquals(response.path("data.gender"), "Male");
        assertEquals((long) (response.path("data.phone")), 8877445596l);


    }

    //2.WAY:

    @Test
    public void postNewSpartans2() {

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("name", "MikeEU");
        requestMap.put("gender", "Male");
        requestMap.put("phone", 8877445596l);

        given().log().all().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(requestMap)//this line make automatically serialization ===> JAVA to JSON
                .when().post("/api/spartans")
                .then().log().all().statusCode(201)
                .and().contentType("application/json")

                //we can use is instead of equalTo or is(equalTo) same
                .and().body("success", is("A Spartan is Born!"),
                "data.name", equalTo("MikeEU"),
                "data.gender", equalTo("Male"),
                "data.phone", equalTo(8877445596l));


    }

    //3.WAY
    @Test
    public void postNewSpartan3() {

        //Optional homeworks
        //Homework-1
        //1-Create csv file from mackaroo website, which includes name,gender,phone
        //2-Download excel file
        //3- using testng data provider and apache poi create data driven posting from spartan



        //Homework-2
        //-Create one mackaroo api for name,gender,phone
        //send get request to retrieve random info from that api
        //use those info to send post request to spartan


        Spartan spartanEU = new Spartan();
        spartanEU.setName("MikeEU");
        spartanEU.setGender("Male");
        spartanEU.setPhone(8877445596l);

        given().log().all().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartanEU)//this line make automatically serialization ===> JAVA to JSON
                .when().post("/api/spartans")
                .then().log().all().statusCode(201)
                .and().contentType("application/json")

                //we can use is instead of equalTo or is(equalTo) same
                .and().body("success", is("A Spartan is Born!"),
                "data.name", equalTo("MikeEU"),
                "data.gender", equalTo("Male"),
                "data.phone", equalTo(8877445596l));


    }

    @Test
    public void PostNewSpartan4(){

        Spartan spartan = new Spartan();
        spartan.setName("MikeEU3");
        spartan.setGender("Male");
        spartan.setPhone(8877445596l);

        Response response = given().log().all()
                .accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartan)
                .when().post("/api/spartans");

        //END OF POST REQUEST



        //get request
        int id = response.path("data.id");
        System.out.println("id = " + id);

        //after post request, send a get request to generated spartan
        given().accept(ContentType.JSON)
                .and().pathParam("id",id)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).log().all();


    }





}
