/*This class implement call driver instantiation */

/*
 *Project Done By:- 
 *Team No: 05
 *Cohort :QEA20QE059
 */


package Tests;


import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;





import BaseClass.Base;


public class Parent_Class extends Base{
	
	public static ExtentReports report;
	public static WebDriver driver;

	
	/**Driver Installation***/
	@BeforeSuite	
	public void startTest()											
	{
	
		driver=getDriver();	
		
		openUrl();                 //open URL in selected browser
		
	}
	
	
	/****Exit Driver***/
	@AfterSuite
	public void exitBrowser()	
	{
		
		quitBrowser();             //closing browser
	}

}
