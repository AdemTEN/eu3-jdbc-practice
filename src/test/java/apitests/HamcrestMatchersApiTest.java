package apitests;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest {
    /*
      given accept type is Json
      And path param id is 15
      When user sends a get request to spartans/{id}
      Then status code is 200
      And content type is Json
      And json data has following
          "id": 15,
          "name": "Meta",
          "gender": "Female",
          "phone": 1938695106
       */
    //ASSERTION FOR BODY PART
    @Test
    public void OneSpartanWithHamcrest() {//CHANINING RESQUEST
        given().accept(ContentType.JSON)
                .and().pathParam("id", 15).
                when().get("http://54.197.12.112:8000/api/spartans/{id}")
                .then().statusCode(200)
                .and().assertThat().contentType("application/json;charset=UTF-8")
                .and().assertThat().body("id", equalTo(15),
                "name", equalTo("Meta"),
                "gender", equalTo("Female"),
                "phone", equalTo(1938695106));


    }

    //ASSERTION FOR HEADERS PART
    @Test
    public void teacherId() {
        given().accept(ContentType.JSON)
                .and().pathParam("id", 8261)
                //how can we log only rewuest part
                .when().log().all().get("http://api.cybertektraining.com/teacher/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType(equalTo("application/json;charset=UTF-8"))
                .and().assertThat().header("Content-Length", equalTo("240"))
                .and().header("Content-Encoding", equalTo("gzip"))
                .and().header("Connection", equalTo("Keep-Alive"))

                //how can we check dynamic date value....
                .and().header("Date", notNullValue())

                //how can we get first name
                .and().body("teachers.firstName[0]", equalTo("James"),
                "teachers.lastName[0]", equalTo("Bond"),
                "teachers.gender[0]", equalTo("Male"),
                "teachers.salary[0]", equalTo(1500))

                //how can we log (print) them on the console
                .log().all()
                .log().headers()
                .log().body();

    }

    @Test
    public void teachersWithDepartments() {

        given().accept(ContentType.JSON)
                .and().pathParam("name", "Computer")
                .when().log().all().get("http://api.cybertektraining.com/teacher/department/{name}")
                .then().statusCode(200)
                .and().contentType("application/json;charset=UTF-8")
                .and().header("Vary", equalTo("Accept-Encoding"))
                .and().body("teachers.firstName", hasItems("Alexander", "Marteen"))
                .and().body("teachers.gender", hasItems("male"))
                .and().body("teachers.phone",hasItems("12345689"));


    }

    /*
      given accept type is Json
      And path param id is 15
      When user sends a get request to spartans/{id}
      Then status code is 200
      And content type is Json
      And json data has following
          "id": 15,
          "name": "Meta",
          "gender": "Female",
          "phone": 1938695106
       */

    @Test
    public void testTrying() {
        given().accept(ContentType.JSON)
                .and().pathParam("id", "15")
                .when().log().all().get("http://54.197.12.112:8000/api/spartans/{id}")
                .then().statusCode(200)
                .and().contentType(equalTo("application/json;charset=UTF-8"))
                //BODY
                .and().body("id", equalTo(15))
                .and().body("name", equalTo("Meta"))
                .and().body("gender", equalTo("Female"))
                .and().body("phone", equalTo(1938695106))
                //HEADER
                .and().header("Content-Type",equalTo("application/json;charset=UTF-8"))
                .and().header("Date",notNullValue())
                .and().header("Transfer-Encoding",equalTo("chunked"))

                .log().body()
                .log().all();


    }
}

