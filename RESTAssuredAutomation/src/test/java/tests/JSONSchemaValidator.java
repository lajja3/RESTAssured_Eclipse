package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.annotations.Test;

public class JSONSchemaValidator {

	@Test
	public void get() {
		System.out.println("Test:validate Json schema from Json response");
		
		baseURI = "https://reqres.in/";
		
		given().get("/api/users?page=2").then().assertThat().body(matchesJsonSchemaInClasspath("schema.json")).
		statusCode(200);
	}
}
