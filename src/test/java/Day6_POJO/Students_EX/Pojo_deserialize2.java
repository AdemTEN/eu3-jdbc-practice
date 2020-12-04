package Day6_POJO.Students_EX;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;


public class Pojo_deserialize2 {

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15624)
                .when().get("http://api.cybertektraining.com/student/{id}");

        assertEquals(response.statusCode(),200);

        Students students = response.body().as(Students.class);

        System.out.println(students.getStudents().get(0).getContact().getContactId());

        System.out.println(students.getStudents().get(0).getCompany().getAddress().getZipCode());

       int addressId =  (students.getStudents().get(0).getCompany().getAddress().getAddressId());

        System.out.println("addressId = " + addressId);

        assertEquals(addressId,15564);


    }




}
