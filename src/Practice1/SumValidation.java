package Practice1;

import org.testng.Assert;
import org.testng.annotations.Test;

import File.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation 
{
	@Test
	public void sumOfCourses()
	{
		int sum=0;
		JsonPath js = new JsonPath(Payload.CoursePrice());
		int count=js.getInt("courses.size()");
		for(int i=0;i<count;i++) 
		{
			int price=js.get("courses["+i+"].price");
			int copies=js.get("courses["+i+"].copies");
			int amount =price*copies;
			sum=sum+amount;
		}
		System.out.println(sum);
		int purchaseAmount=js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmount);
	}
}