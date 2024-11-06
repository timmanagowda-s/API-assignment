package restassuredTests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class ResourceInfo {
    public ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");

    @BeforeClass
    public void setup() {
        extent.attachReporter(spark);
    }

    @Test
    public void getAllResourceInfo()
    {
        given().contentType("application/json")
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200).body("data.id",hasItems(1,2,3,4,5,6)).log().all();
        ExtentTest test= extent.createTest("Extract Resource Info");
        test.log(Status.PASS,"Resource Info");
        test.pass("Get All Resource Info");
    }

    @Test
    public void getSingleResourceInfo()
    {
        given().contentType("application/json")
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200).body("data.name",equalTo("fuchsia rose")).and().body("data.id",equalTo(2)).log().all();
    }

    @Test
    public void getResourceNotFound()
    {
        given().contentType("application/json")
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .statusCode(404).log().all();
    }

    @AfterClass
    public void afterExecution()
    {
        extent.flush();
    }

}
