package apitests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class hrApiWithJsonPath {

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("hr_api_url");

    }

    @Test
    public void test1() {

        Response response = get("/countries");

        assertEquals(response.statusCode(), 200);

        //assign to jsonpath
        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items.country_name[1]");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        List<String> allCountryIDs = jsonPath.getList("items.country_id");
        System.out.println("allCountryIDs = " + allCountryIDs);

        //get all country names where their region id is equal to 2

        List<String> allCountryNamesWithRegionID2WithPath = response.path("items.findAll{it.region_id==2}.country_name");
        System.out.println("allCountryNamesWithRegionID2WithPath = " + allCountryNamesWithRegionID2WithPath);


        List<String> allCountryNamesWithRegionID2 = jsonPath.getList("items.findAll{it.region_id==2}.country_name");
        System.out.println("allCountryNamesWithRegionID2 = " + allCountryNamesWithRegionID2);


    }

    @Test
    public void test2(){

        Response response = given().queryParam("limit",107)
                            .when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

        //get me all email of employees who is working as IT_PROG
        List<String> allEmailsWithIT_PROG = jsonPath.getList("items.findAll{it.job_id==\"IT_PROG\"}.email");
        System.out.println("allEmailsWithIT_PROG = " + allEmailsWithIT_PROG);

        //get me all firstname of emplyoees who is making more than 10000
        List<String> allFirstName = jsonPath.getList("items.findAll{it.salary>10000}.first_name");
        System.out.println("allFirstName = " + allFirstName);


        //get me first name of who is making highest salary
        String FirstNameWithMaxSalary = jsonPath.getString("items.max{it.salary}.first_name");
        System.out.println("FirstNameWithMaxSalary = " + FirstNameWithMaxSalary);

        String poorMan = jsonPath.getString("items.min{it.salary}.first_name");
        System.out.println("poorMan = " + poorMan);

    }


}
