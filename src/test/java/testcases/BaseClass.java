package testcases;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import routers.Routes;
import utils.ConfigReader;

public class BaseClass {
	
	ConfigReader configReader;
	//for logging
	RequestLoggingFilter requestLoggingFilter;
	ResponseLoggingFilter responseLoggingFilter;
	
	@BeforeClass
	public void setUp() throws FileNotFoundException
	{
		
		RestAssured.baseURI=Routes.BASE_URL;	
		configReader=new ConfigReader();
		//set filter for logging
		FileOutputStream fileOutputStream=new FileOutputStream(".\\logs\\test_logging.log");
		PrintStream log=new PrintStream(fileOutputStream,true);
		requestLoggingFilter=new RequestLoggingFilter(log);
		responseLoggingFilter=new ResponseLoggingFilter(log);
		RestAssured.filters(requestLoggingFilter,responseLoggingFilter);
	}
	 // this is method is used to decending order
	 public boolean isSortedDecending(List<Integer> list)
	 {
		 for(int i=0;i<list.size()-1;i++)
		 {
			 if(list.get(i)<list.get(i+1))
			 {
				 return false;
			 }
			 
		 }
		return true;
	 }
	 
	// this is method is used to decending order
		 public boolean isSortedAscending(List<Integer> list)
		 {
			 for(int i=0;i<list.size()-1;i++)
			 {
				 if(list.get(i)>list.get(i+1))
				 {
					 return false;
				 }
				 
			 }
			return true;
		 }

}
