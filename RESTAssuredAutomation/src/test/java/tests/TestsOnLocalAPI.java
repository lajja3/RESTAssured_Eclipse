package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
// refer --- https://github.com/typicode/json-server -->to create whole fake local api 
//in cmd line, >cd C:\Users\Lajja Patel\OneDrive\Desktop\POSTMAN\Rest assured project
//>json-server --watch db.json
//open "http://localhost:3000/" json-server link on browser
//our data file is db.json loaction is :-C:\Users\Lajja Patel\OneDrive\Desktop\POSTMAN\Rest assured project\db.json
public class TestsOnLocalAPI {
    
	//@Test
	public void get() {
		
		System.out.println("Test: GET");
		baseURI= "http://localhost:3000/";
		
		given().get("users").then().statusCode(200).log().all();	
		System.out.println();
		}
	
	//@Test
	public void post() {
		System.out.println("Test: POST");
		JSONObject request = new JSONObject();
		request.put("firstName", "Thomas");
		request.put("lastName", "Edison");
		request.put("subjectId", 1);
		baseURI= "http://localhost:3000/";
		given()
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
		 .body(request.toJSONString()).
		when()
		 .post("users").
		then()
		 .statusCode(201)
		 .log().all();
		System.out.println();
		//reload your server on browser and data file to verify the user created
	}
	
	//@Test
	public void put() {
		System.out.println("Test: PUT");
		JSONObject request = new JSONObject();
		request.put("firstName", "Albert");
		request.put("lastName", "Einstein");
		request.put("subjectId", 2);
		baseURI= "http://localhost:3000/";
		given()
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
		 .body(request.toJSONString()).
		when()
		 .put("users/4").
		then()
		 .statusCode(200)
		 .log().all();
		System.out.println();
		//reload your server on browser and data file to verify the user created
	}
	
	//@Test
	public void patch() {
		System.out.println("Test: PATCH");
		JSONObject request = new JSONObject();
		request.put("lastName", "Doe");
		baseURI= "http://localhost:3000/";
		given()
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
		 .body(request.toJSONString()).
		when()
		 .patch("users/4").
		then()
		 .statusCode(200)
		 .log().all();
		System.out.println();
		//reload your server on browser and data file to verify the user created
	}
	
	@Test
	public void delete() {
		System.out.println("Test: DELETE");
		baseURI= "http://localhost:3000/";
		when().delete("users/4").then().statusCode(200).log().all();
		System.out.println("User with id=4 is deleted");
		System.out.println();
	}
}
