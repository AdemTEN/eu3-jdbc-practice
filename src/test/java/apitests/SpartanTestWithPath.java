package apitests;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanTestWithPath {

    @BeforeClass
    public void beforeClass() {
        baseURI = "http://54.197.12.112:8000";

    }


     /*
   Given accept type is json
   And path param id is 10
   When user sends a get request to "/api/spartans/{id}"
   Then status code is 200
   And content-type is "application/json;charset=UTF-8"
   And response payload values match the following:
           id is 10,
           name is "Lorenza",
           gender is "Female",
           phone is 3312820936
    */


    //1. GET THE VALUE WITH path() METHOD
    @Test
    public void getOneSpartan_path() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        //response.prettyPrint();
        //printing each key value in the json body/payload
        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.body().path("gender").toString());//we don't need to write body--optional
        System.out.println(response.body().path("phone").toString());


        //save json key values
        int id = response.path("id");//path() beauty is we dont need to cast any value
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");//we need to check api documentation for decide values int or long

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        //assert one by one
        //assertEquals(response.path("name"),"Lorenza");
        //assertEquals(response.path("id"),"10");
        assertEquals(id, 10);
        assertEquals(name, "Lorenza");
        assertEquals(gender, "Female");
        assertEquals(phone, 3312820936l);


    }




    @Test
    public void getAllSpartansWithPath(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");


        assertEquals(response.statusCode(),200);

        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        assertEquals(response.header("Content-Type"),"application/json;charset=UTF-8");


        //verify content type
        //They are inside the array so we have to use index in order to reach specific value
        int firstId =response.path("id[0]");
        System.out.println("firstId = " + firstId);

        String firstName =  response.path("name[0]");
        System.out.println("firstname = " + firstName);

        String lastName = response.path("name[-1]");
        System.out.println("lastName = " + lastName);

        int lastId = response.path("id[-1]");

        String beforeLastName = response.path("name[-2]");
        System.out.println("beforeLastName = " + beforeLastName);


        String thirdName = response.path("name[2]");
        System.out.println("thirdname = " + thirdName);

        //print all id in one shot
        List<Integer> id = response.path("id");
        System.out.println("id = " + id);
        for (Integer i : id) {
            System.out.println(i);

        }

        //print all name in one shot
        List<String> listNames = response.path("name");
        System.out.println("listNames = " + listNames);

        for (String listName : listNames) {
            System.out.println(listName);

        }

        //print all phone in one shot
        List<Object> phones=  response.path("phone");
        for (Object phone : phones) {
            System.out.println(phone);

        }

    }


}
