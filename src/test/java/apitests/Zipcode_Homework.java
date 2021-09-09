package apitests;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Zipcode_Homework {
    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("zippopotam_api_url");
    }

    @Test
    public void test1() {
    /*Given Accept application/json
And path zipcode is 22031
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And Server header is cloudflare
And Report-To header exists
And body should contains following information
    post code is 22031
    country  is United States
    country abbreviation is US
    place name is Fairfax
    state is Virginia
    latitude is 38.8604
     */
        given().accept(ContentType.JSON)
                .and().pathParam("zipcode", 22031)
                .when().log().all().get("/us/{zipcode}")
                .then().assertThat().statusCode(200)
                .and().contentType(equalTo("application/json"))
                .and().header("Server", "cloudflare")
                .and().header("Report-To", notNullValue())
                .and().body("'post code'", equalTo("22031"))
                .and().body("country", equalTo("United States"))
                .and().body("'country abbreviation'", equalTo("US"))
                .and().body("places[0].'place name'", equalTo("Fairfax"))
                .and().body("places[0].state", equalTo("Virginia"))
                .and().body("places[0].latitude", equalTo("38.8604"));


    }

    @Test
    public void test2() {
        /*
         * ===========================
         * Given Accept application/json
         * And path zipcode is 50000
         * When I send a GET request to /us endpoint
         * Then status code must be 404
         * And content type must be application/json
         *
         * ============================
         */


        given().accept(ContentType.JSON).
                and().pathParam("zipcode", 50000).
                when().log().all().get("us/{zipcode}").
                then().assertThat().statusCode(404).
                and().assertThat().contentType("application/json");

    }

    @Test
    public void test3(){
        /*
        * Given Accept application/json
And path state is va
And path city is farifax
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And payload should contains following information
    country abbreviation is US
    country  United States
    place name  Fairfax
    each places must contains fairfax as a value
    each post code must start with 22
    * */

        given().accept("application/json")
                .and().pathParams("state","va","city", "fairfax")
              //  .and().pathParam("city", "fairfax")
                .when().log().all().get("/us/")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json")
                .assertThat().body("'country abbreviation'", equalTo("US"))
;

    }


}
