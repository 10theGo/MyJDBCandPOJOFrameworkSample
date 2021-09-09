package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.*;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class Homework1 {
    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("hr_api_url");
    }

    @Test
    public void test1() {
        /*
* ORDS API:
Q1:
- Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
- And Region_id is
* */
        Response response = given().accept(ContentType.JSON)
                .pathParam("country_id","US")
                .when().get("/countries/{country_id}");
//- Then status code is 200
        Assert.assertEquals(response.statusCode(), 200);
//- And Content - Type is Json
        Assert.assertEquals(response.contentType(), "application/json");
//- And country_id is US
        String countryId = response.path("country_id");
        Assert.assertEquals(countryId, "US");

//- And Country_name is United States of America


    }

    @Test
    public void test2() {
        /*
Q2:
- Given accept type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
- And Content - Type is Json
- And all job_ids start with 'SA'
- And all department_ids are 80
- Count is 25
*/

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"department_id\":80}")
                .when().get("/employees");

        Assert.assertEquals(response.statusCode(), 200);

        Assert.assertEquals(response.contentType(), "application/json");

        // And all job_ids start with 'SA'
        List<String> jobIds = response.path("items.job_id");
        List<String> startsWithSA = new ArrayList<>();
        int i = 0;
        for (String jobId : jobIds) {
            String first2letter = jobId.substring(0, 2);
            if (first2letter.equals("SA")) {
                startsWithSA.add(i++, jobId);
            }

        }
        System.out.println(startsWithSA.size());

        //- Count is 25
        Assert.assertEquals(startsWithSA.size(), 25);


    }

    @Test
    public void test3() {
        /*
Q3:
- Given accept type is Json
-Query param value q= region_id 3
- When users sends request to /countries
- Then status code is 200
- And all regions_id is 3
- And count is 6
- And hasMore is false
- And Country_name are;
Australia,China,India,Japan,Malaysia,Singapore*/


        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":3}")
                .when().get("/countries");

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(response.statusCode(), 200);

        //- And all regions_id is 3
        List<Integer> regionIDs = response.path("items.region_id");
        for (int regionID : regionIDs) {
            System.out.println(regionID);
            assertEquals(regionID, 3);
        }

        //  - And count is 6
        JsonPath jasonpath = response.jsonPath();
        int count = jasonpath.getInt("count");
        assertEquals(count, 6);

        //- And hasMore is false
        boolean hasMore = jsonPath.getBoolean("hasMore");
        assertFalse(hasMore);

        //- And Country_name are Australia,China,India,Japan,Malaysia,Singapore
        List<String> expectedCountries = Arrays.asList("Australia", "China", "India", "Japan", "Malaysia", "Singapore");
        List<String> actualCountries = jsonPath.getList("items.country_name");

        System.out.println("expectedCountries = " + expectedCountries);
        System.out.println("actualCountries = " + actualCountries);
        assertEquals(expectedCountries, actualCountries);


    }

}


