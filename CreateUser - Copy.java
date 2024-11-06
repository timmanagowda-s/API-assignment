package restassuredTests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateUser {


    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api/users";

    }
    @Test
    public void testCreateUser()
    {
        given()
                .contentType("application/json")
                .body("{\"name\": \"morpheus\", \"job\": \"leader\"}")
                .when()
                .post()
                .then().statusCode(201).body("name",equalTo("morpheus")).log().all();

    }

    @Test
    public void testUpdateUser() {

        given()
                .contentType("application/json")
                .body("{\"name\": \"morpheus\", \"job\": \"zion resident\"}")
                .when()
                .put("/2")
                .then().statusCode(200).log().all();
    }


}
