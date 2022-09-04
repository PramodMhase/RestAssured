package Practice1;

import File.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(Payload.CoursePrice());
		
		//Print no. of courses returned by API
		int count=js.getInt("courses.size()");
		
		System.out.println(count);
		
		//Print Purchase Amount
		int totalAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		//Print title of FirstCourse
		String titleFirstCourses= js.get("courses[0].title");
		System.out.println(titleFirstCourses);
		String titleSecondCourses= js.get("courses[1].title");
		System.out.println(titleSecondCourses);
		
		//Print all courses titles and their respective prices
		for(int i=0;i<count;i++)
		{
			String coursesTitles= js.get("courses["+i+"].title");
			int coursePrices=js.get("courses["+i+"].price");			
			System.out.println(coursesTitles+" = "+coursePrices);
		}
		
		System.out.println("Print no. of copies sold by RPA copies");
		for(int i=0;i<count;i++)
		{
			String coursesTitles= js.get("courses["+i+"].title");
			
			if(coursesTitles.equalsIgnoreCase("RPA"))
			{
				//Copies sold
				int copies=js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		}
		
	}
}