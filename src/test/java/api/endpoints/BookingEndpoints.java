package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import api.payloads.Booking;
import api.payloads.User;
import io.restassured.response.Response;


//created for performing CRUD operations.

public class BookingEndpoints {
	
	static String getURL(String url) throws Exception{
		FileReader readFile=new FileReader("C:\\Users\\90ami\\eclipse-workspace\\Hotel_API_Framework\\src\\test\\resources\\routes.properties");
		//FileInputStream fis = new FileInputStream(src);
		Properties Pro = new Properties();
		Pro.load(readFile);
		String X= Pro.getProperty(url);
		return X;
	}

	public static Response createBooking(Booking payload) throws Exception{
		
		String post_url = getURL("post_url");
		Response response = given()
			.contentType("application/json")
			.body(payload)
		.when()
			.post(post_url);
		
		return response;
	}
	
	public static Response getBooking(int id) throws Exception{
		
		String get_url = getURL("get_url");
		Response response = given()
			.pathParam("ID", id)
		.when()
			.get(get_url);
		
		return response;
	}
	
	public static Response updateBooking(int id, Booking payload, String auth) throws Exception{
		
		
		String patch_url = getURL("patch_url");
		Response response = given()
			.contentType("application/json")
			.accept("application/json")
			.header("Cookie", "token="+auth)
			//.cookie("token="+auth)
			.pathParam("ID",id)
			.body(payload)
		.when()
			.patch(patch_url);
		
		return response;
	}
	
	public static Response deleteBooking(int id, String auth) throws Exception{
		
		String delete_url = getURL("delete_url");
		Response response = given()
			.header("Cookie", "token="+auth)	
			.pathParam("ID", id)
		.when()
			.delete(delete_url);
		
		return response;
	}
	
	public static Response generateToken(User payload) throws Exception {
		
		String auth_url = getURL("auth_url");
		Response response = given()
				.contentType("application/json")
				.body(payload)
			.when()
				.post(auth_url);
		
		return response;
	}
	
}
