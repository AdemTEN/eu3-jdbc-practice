package apitests;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class SimpleGetRequest {

    String hrurl = "http://54.197.12.112:1000/ords/hr/regions";

    @Test
    public void test1(){

        Response response = RestAssured.get(hrurl);
        //print the status code
        System.out.println("response.getStatusCode() = " + response.statusCode());
        //print the json body
        response.prettyPrint();


    }

    /*
        Given accept type is json
        When user sends get request to regions endpoint
        Then response status code must be 200
        and body is json format
     */

    @Test
    public void test2(){

        //response part      //request part
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(hrurl);
        //verify response status code is 200
        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals(response.statusCode(), 200);

        //verify content-type is application/json
        System.out.println("response.contentType() = " + response.contentType());
        Assert.assertEquals(response.contentType(),"application/json");


    }


    @Test
    public void test3(){
        //after then  is response part
        RestAssured.given().accept(ContentType.JSON)
                    .when().get(hrurl).then()
                    .assertThat().statusCode(200)
                    .and().contentType("application/json");
    }

     /*
        Given accept type is json
        When user sends get request to regions/2
        Then response status code must be 200
        and body is json format
        and response body contains Americas
     */
    @Test
    public void Test4(){
       // Given accept type is json
        //When user sends get request to regions/2

        Response response = given().accept(ContentType.JSON)
                .when().get(hrurl+"/2");

        //Then response status code must be 200
        Assert.assertEquals(response.statusCode(),200);

        //and body is json format
        Assert.assertEquals(response.contentType(),"application/json");

        //and response body contains Americas
        //asString()==> convert all body as String
        Assert.assertTrue(response.body().asString().contains("Americas"));

    }

    


}
