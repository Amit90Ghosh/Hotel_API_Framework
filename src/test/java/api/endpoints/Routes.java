package api.endpoints;

public class Routes {

	public static String baseurl = "https://restful-booker.herokuapp.com";
	
	public static String auth_url = baseurl +"/auth";
	public static String get_url = baseurl + "/booking/{ID}";
	public static String post_url = baseurl + "/booking";
	public static String patch_url = baseurl + "/booking/{ID}";
	public static String delete_url = baseurl +"/booking/{ID}";
	
}
