package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetAndPostExamples {

	@Test
	public void getTest() {
		System.out.println("Test1:GET Request");
		
		baseURI = "https://reqres.in/";
		
		given().get("/api/users?page=2").then().
		statusCode(200).
		body("data[4].first_name",equalTo("George")).
		body("data.first_name", hasItems("George","Rachel")).
		log().all();
		System.out.println();
	}
	
	@Test
	public void postTest() {
		System.out.println("Test2:POST Request");
		//In order to pass the body parameter for POST req. we can use Map from java
		//type Map ctrl+space enter , map comes in key value pair
		//K is string here and value can be any data type thus we will use object
		//our body is, key =string= name; value=string=morpheus
		/*{ 
            "name": "morpheus",
            "job": "leader"
          }
		 * */
		
		/*
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("name", "Lajja");
		body.put("job", "CSM");
		System.out.println(body); //with this the o/p u see on console is not json we need json o/p
		//Thus we will add json-simple dependency in pom.xml
		JSONObject request = new JSONObject(body);
		System.out.println(request.toJSONString()); //this is json o/p  */
		
		//Now we will use JSON object only for passing body in Post Req.
		
		JSONObject request = new JSONObject();
		request.put("name", "Ashu");
		request.put("job", "ASM");
		System.out.println(request.toJSONString());
		
		baseURI = "https://reqres.in/";
		
		given().
		   header("Content_type","application/json").
		   contentType(ContentType.JSON).
		   accept(ContentType.JSON).
		   body(request.toJSONString()).
		when().
		   post("/api/users").
		then().
		   statusCode(201).log().all();
		
		//header("Content_type","application/json") meaning header contect type we are sending is app-json
		// OR
		//contentType(ContentType.JSON) meaning content im sending is json
		// accept(ContentType.JSON) meaning the response i will accept is also json
	}
}
