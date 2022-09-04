package Practice1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import File.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class RestAssured_Post 
{
	public static void main(String[] args) 
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response= given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(Payload.AddPlace())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200)
		.body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.41 (Ubuntu)")
		.extract().response().asString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response); //for passing Json
		String placeId=js.getString("place_id");
		System.out.println("PlaceID is "+placeId);
		
		//update place
		String newAddress="70 Summer walk, Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+ placeId +"\",\r\n"
				+ "\"address\":\""+ newAddress +"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
	//Get place
		String getPlaceResponse=given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id",placeId)
		.when().get("/maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		System.out.println(getPlaceResponse);
		
		JsonPath js1 = reusableMthods.rawToJson(getPlaceResponse);
		
		String actualAddress=js1.getString("address");
		
		System.out.println(actualAddress);
		
		//Cucumber, JUnit,TestNG
		Assert.assertEquals(actualAddress, newAddress);
		
		
	}
}
