package testcases;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import payloads.Payload;
import pojo.Login;
import routers.Routes;


public class LoginTests extends BaseClass{

	@Test
	public void testInvalidUserLogin()
	{
	Login newlogin=Payload.loginPayload();
	
		given()
		.contentType(ContentType.JSON)
		.body(newlogin)
		.when()
		.post(Routes.AUTH_LOGIN)
		.then()
		.log().body()
		.statusCode(400) //expecting 400 or 401 error code here for negative test cases
		//.body(equalTo("username or password is incorrect"));// validate message
		.body(equalTo("username and password are not provided in JSON format"));// validate message
	}
	@Test
	public void testvalidUserLogin()
	{
		//Getting valid credentials from config.prperties file
    	String username = configReader.getProperty("username");
      	String password = configReader.getProperty("password");
	   Login newlogin=new Login(username,password);
		given()
		.contentType(ContentType.JSON)
		.body(newlogin)
		.when()
		.post(Routes.AUTH_LOGIN)
		.then()
		.log().body()
		.statusCode(400) 
		.body("token",notNullValue());
	}
}
