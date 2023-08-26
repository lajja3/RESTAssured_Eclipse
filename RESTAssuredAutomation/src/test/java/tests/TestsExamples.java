//here we are working on publically available rest apis - https://reqres.in/

package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

// import io.restassured.RestAssured; was for Test1
import io.restassured.response.Response;

//Test2 we are using these 3 imports
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TestsExamples {
 
	@Test
	public void Test1_GetReq() {
		
		//Java is case sensitive thus Response not= response
		//creating response obj to save respose we get after sending request, here request type is GET
		// Response response = RestAssured.get("https://reqres.in/api/users?page=2"); no need of this "RestAssured" now coz we are importing those 3 static imports
		//Thus our new line is:-
		Response response = get("https://reqres.in/api/users?page=2");
		System.out.println("TEST1:Response from the GET request is as below:--");
		System.out.println("Status Code:-" +response.getStatusCode());
		System.out.println("Execution Time:-" +response.getTime());
		System.out.println("Response Body:-" +response.getBody().asPrettyString());
		System.out.println("Status Line:-" +response.getStatusLine());
		System.out.println("Content-Type Header value:-" +response.getHeader("Content-Type"));
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	//https://github.com/rest-assured/rest-assured/wiki/Usage
	//In order to use REST assured effectively it's recommended to statically import methods from the following classes:
	/*static io.restassured.RestAssured.*
	  static io.restassured.matcher.RestAssuredMatchers.*
	  static org.hamcrest.Matchers.* */
	// we will implement test thisway: get("/lotto").then().body("lotto.lottoId", equalTo(5));
	
	@Test
	public void Test2_ModifiedGetReq() {
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("TEST2:Response");
		baseURI = "https://reqres.in/api";
		given().
		get("/users?page=2").
		then().
		statusCode(200).body("data[0].id", equalTo(7)).
		log().all();
		//given().get("/users?page=2").then().statusCode(200).body("data[1].id", equalTo(8)).log().all(); below code is same as this whole line
	}
}
