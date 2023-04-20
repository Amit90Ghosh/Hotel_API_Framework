package api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.BookingEndpoints;
import api.payloads.Booking;
import api.payloads.Bookingdates;
import api.payloads.User;
import io.restassured.response.Response;

public class BookingTests {
	
	Faker fake;
	Booking bookpayload;
	Booking updatepayload;
	Bookingdates bookdates;
	int bookingid;
	User auth;
	String Token;

	@BeforeClass
	public void setupData() throws Exception
	{
		fake = new Faker();
		bookpayload = new Booking();
		bookdates = new Bookingdates();
		auth = new User();
		updatepayload = new Booking();
		
		bookpayload.setFirstname(fake.name().firstName());
		bookpayload.setLastname(fake.name().lastName());
		bookpayload.setTotalprice(fake.number().numberBetween(100, 999));
		bookpayload.setDepositpaid(fake.random().nextBoolean());
		bookpayload.setAdditionalneeds("lunch");
		bookdates.setCheckin("2023-04-20");
		bookdates.setCheckout("2023-04-24");
		bookpayload.setBookingdates(bookdates);
		
		
		auth.setUsername("admin");
		auth.setPassword("password123");
		
		Response response1 = BookingEndpoints.generateToken(auth);
		Token = response1.body().jsonPath().getString("token");
		
	}
	
	@Test(priority=1)
	public void testBooking() throws Exception {
		
		Response response = BookingEndpoints.createBooking(bookpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		bookingid = response.body().jsonPath().getInt("bookingid");
		
	}
	
	@Test(priority=2)
	public void updateBookingTest() throws Exception {
		
		bookpayload.setFirstname(fake.name().firstName());
		bookpayload.setLastname(fake.name().lastName());
		
		//System.out.println(Token);
		Response response = BookingEndpoints.updateBooking(bookingid, bookpayload, Token);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test(priority=3)
	public void getBooking() throws Exception {
		
		Response response = BookingEndpoints.getBooking(bookingid);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test(priority=4)
	public void deleteBooking() throws Exception {
		
		Response response = BookingEndpoints.deleteBooking(bookingid, Token);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 201);
	}
	
	@Test(priority=5)
	public void checkDeleteBooking() throws Exception {
		
		Response response = BookingEndpoints.getBooking(bookingid);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 404);
	}
	
}
