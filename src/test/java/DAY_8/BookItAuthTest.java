package DAY_8;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

import static io.restassured.RestAssured.baseURI;

public class BookItAuthTest {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://cybertek-reservation-api-qa2.herokuapp.com";

    }

    @Test
    public void sign(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParams("email","sbirdbj@fc2.com")
                .and().queryParams("password","asenorval")
                .when().get("/sign");

        response.prettyPrint();
        String accesToken = response.path("accessToken");
        System.out.println("accesToken = " + accesToken);
    }

    String accesTokenManual = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1NyIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.a_N9URDBPGOMcDdEVoaMHsJtk3jOnig0v0SCtSWcsGE";

    @Test
    public void getAllCampuses(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParams("email","sbirdbj@fc2.com")
                .and().queryParams("password","asenorval")
                .when().get("/sign");

        response.prettyPrint();
        String accesTokenDynamic = response.path("accessToken");
        System.out.println("accesToken = " + accesTokenDynamic);
        //we can pass Authorization with the header method.
        Response responseampuses = given().header("Authorization","Bearer "+ accesTokenDynamic)
                .when().get("api/campuses");


        responseampuses.prettyPrint();




    }
}
