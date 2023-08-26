// Google: Sample SOAP web service for testing
// https://ecs.syr.edu/faculty/fawcett/Handouts/cse775/code/calcWebService/Calc.asmx - im using this here
////http://www.dneonline.com/calculator.asmx?WSDL) - however this link has all 4 methods add,sub,mul,div im not using this is for reference only
// when u click serice description it will open WSDL and now u can open wizdler side by side to get code for xml file
// For SOAP : XML & Rest : JSON,XML formats

package tests;

// type this static import first then only u will get suggestions
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.equalTo;
public class SoapXMLRequest {

   @Test
   public void validateSoapXML() throws IOException {
	   
	   File file = new File("./SoapRequest/Add_soap_reqBody.xml"); // ./ meaning file is within this project only not from external drive
	   
	   if(file.exists()) {
		   System.out.println("     >>  File Exists");
	   }
	   
	   FileInputStream fileInputStream = new FileInputStream(file);
	   String requestBody = IOUtils.toString(fileInputStream, "UTF-8"); //ioutils is from apache-commons
	   
	   baseURI = "https://ecs.syr.edu";
	   
	   //here our body request is coming from a xml file we have created that file: Add_soap_reqBody
	   given()
	     .contentType("text/xml").accept(ContentType.XML)
	     .body(requestBody).
	   when()
	     .post("/faculty/fawcett/Handouts/cse775/code/calcWebService/Calc.asmx").
	   then()
	     .statusCode(200).log().all().
	   and()
	     .body("//*:AddResult.text()", equalTo("5")); //.body(String path, Matcher<?> matcher, Object..) and we have to give our xpath here
	 
   }
}
// How to validate SOAP XML Response Body
// To do validation we will use xpath tester
//Google: freeformatter.com -> xpath Tester -> https://www.freeformatter.com/xpath-tester.html
//Copy your Response body from console output and paste it into XML Input section in xPath tester
// This is the body:-
/*<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
<soap:Body>
<AddResponse xmlns="http://tempuri.org/">
  <AddResult>5</AddResult>
</AddResponse>
</soap:Body>
</soap:Envelope> */
// In xpath expression type : "//*:AddResult/text()" this is ur xpath --> o/p u get is 5 after Test xpath
//Now add some lines to ur code after log().all() part is used for validating soap xml response body