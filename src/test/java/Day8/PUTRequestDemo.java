package Day8;


import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

public class PUTRequestDemo {
    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("spartan_api_url");

    }

    @Test
    public void test1(){
        //Create one map for th put request json body

        Map<String, Object> putRequestMap = new HashMap<>();
        putRequestMap.put("name", "PutName");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", "12345678910");


        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id", 90)
                .and()
                    .body(putRequestMap).
                when().
                put("/api/spartans/{id}")
                .then().log().all()
                .assertThat().statusCode(204);

        //send get request to verify body

    }

    @Test
    public void PatchTest(){
        Map<String, Object> patchRequestMap = new HashMap<>();
        patchRequestMap.put("name", "TJ");patchRequestMap.put("gender", "Female");


        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id", 90)
                .and()
                .body(patchRequestMap).
                when().
                patch("/api/spartans/{id}")
                .then().log().all()
                .assertThat().statusCode(204);


    }
}
