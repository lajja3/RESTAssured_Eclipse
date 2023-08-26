package tests;

import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PutPatchDelete {

	@Test(priority=1)
	public void putTest() {
		System.out.println("Test1:PUT (Update) Request");
		
		JSONObject request = new JSONObject();
		request.put("name", "Ashutosh Pandita");
		request.put("job", "ASM");
		System.out.println(request.toJSONString());
		
		baseURI = "https://reqres.in/";
		
		given().
		   header("Content_type","application/json").
		   body(request.toJSONString()).
		when().
		   put("/api/users/2").
		then().
		   statusCode(200).log().all();
		
		System.out.println("name is updated");
		System.out.println();
	}
	
	@Test(priority=2)
	public void patchTest() {
		System.out.println("Test2:PATCH (Partial Update) Request");
		
		JSONObject request = new JSONObject();
		request.put("name", "Ashutosh Pandita");
		request.put("job", "Assistant Manager ASM");
		System.out.println(request.toJSONString());
		
		baseURI = "https://reqres.in/";
		
		given().
		   contentType(ContentType.JSON).
		   accept(ContentType.JSON).
		   body(request.toJSONString()).
		when().
		   put("/api/users/2").
		then().
		   statusCode(200).log().all();
		
		System.out.println("job is partially updated");
		System.out.println();
	}
	
	@Test(priority = 3)
	public void deleteTest() {
		System.out.println("Test3:Delete Request");
				
		baseURI = "https://reqres.in/";
		
		when().
		   delete("/api/users/2").
		then().
		   statusCode(204).log().all();
		
		System.out.println("Resource deleted");
		System.out.println();
	}
	
}
