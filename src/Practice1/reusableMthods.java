package Practice1;

import io.restassured.path.json.JsonPath;

public class reusableMthods 
{
	public static  JsonPath rawToJson(String response)
	{
		
		JsonPath js1 =new JsonPath(response);
		
		return js1;
	}

}
