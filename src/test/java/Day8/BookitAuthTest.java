package Day8;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class BookitAuthTest {

    @BeforeClass
    public void before(){
        baseURI= "https://cybertek-reservation-api-qa2.herokuapp.com/";


    }

    String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1NyIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.a_N9URDBPGOMcDdEVoaMHsJtk3jOnig0v0SCtSWcsGE";

    @Test
    public void getAllCampuses(){

   Response response=  given().header("Authorization", accessToken).
                when().get("/api/campuses");

        response.prettyPrint();
        System.out.println("response.statusCode() = " + response.statusCode());


    }
}
