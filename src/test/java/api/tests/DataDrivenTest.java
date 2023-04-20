package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.BookingEndpoints;
import api.payloads.Booking;
import api.payloads.Bookingdates;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {

	
	@Test(priority=1, dataProvider="Data", dataProviderClass= DataProviders.class)
	public void createBooking(String Fname, String Lname, String Tprice, String Dpaid, String Aneeds, String Checkin, String Cout) throws Exception {
		
		Booking bookpayload = new Booking();
		Bookingdates bookdates = new Bookingdates();
		bookpayload.setFirstname(Fname);
		bookpayload.setLastname(Lname);
		bookpayload.setTotalprice(Integer.parseInt(Tprice));
		bookpayload.setDepositpaid(Boolean.parseBoolean(Dpaid));
		bookpayload.setAdditionalneeds(Aneeds);
		bookdates.setCheckin(Checkin);
		bookdates.setCheckout(Cout);
		bookpayload.setBookingdates(bookdates);
		
		Response response = BookingEndpoints.createBooking(bookpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}
}
