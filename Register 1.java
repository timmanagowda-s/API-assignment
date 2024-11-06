package restassuredTests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Register {

    public ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");

    @BeforeClass
    public void setup() {
        RestAssured.baseURI ="https://reqres.in/api/register";
        extent.attachReporter(spark);
    }

    @Test
    public void getRegisterSuccessful()
    {
        given().contentType("application/json").body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}")
               .when()
               .post()
            .then()
               .statusCode(200).body("token",equalTo("QpwL5tke4Pnpja7X4")).log().all();
        ExtentTest test= extent.createTest("RegisterSuccessful");
        test.log(Status.PASS,"User able to Register");
        test.pass("Registration Done");
    }
    @Test
    public void getUnsuccessfulMessage()
    {
        given().contentType("application/json").body("{\"email\": \"sydney@fife\"}")
                .when()
                .post()
                .then()
                .statusCode(400).body("error",equalTo("Missing password")).log().all();
        ExtentTest test= extent.createTest("RegisterUnsuccessful");
        test.log(Status.PASS,"User unable to Register");
        test.pass("Registration Unsuccessful");
    }

    @AfterClass
    public void afterExecution()
    {
     extent.flush();
    }




}
