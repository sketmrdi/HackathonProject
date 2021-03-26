package Tests;


//import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;





import BaseClass.Base;


public class Parent_Class extends Base{
	
	public static ExtentReports report;
	public static WebDriver driver;
	
	
	@BeforeSuite	
	public void startTest()			//driver instantiate								
	{
		//BasicConfigurator.configure();
		driver=getDriver();	
		
		openUrl();
		
	}
	
	@AfterSuite
	public void exitBrowser()	//exit driver
	{
		
		quitBrowser();
	}

}
