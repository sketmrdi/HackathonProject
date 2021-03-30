/*This class contains basic methods for selecting webdriver, opening url, screenshot & quiting browser*/
/*
 *Project Done By:- 
 *Team No: 05
 *Cohort :QEA20QE059
 */

package BaseClass;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExtentReport;

public class Base {
	public static Properties prop = new Properties();
	public static ExtentReports reports = ExtentReport.getReportInstance(); 
	public static WebDriver driver;
	public static ExtentTest Logger;
	public static String browse;
	public static String homePage;
	
	
	/****************** Base() constructor to initialize property file***********************/
	public Base() {

		try 
		{
		FileInputStream file = new FileInputStream(".\\src\\test\\resources\\config.property\\config");  //initializing property file path
		prop.load(file); 	     //Loading config.property file
		} 
	catch (Exception e)
		{
			e.printStackTrace();  //Printing error message
		}
	}
	


	/****************** Invoke Browser ***********************/
	
	public static WebDriver getDriver() {
		
		String browser = prop.getProperty("browser"); //get browser name from property file
		System.out.println("Browser selected: " + browser); //display selected browser in console
		browse=browser;
		
		switch (browser.toLowerCase()) {
		//Open chrome browser
		case "chrome":
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			Logger = reports.createTest("Chrome Initiated Successfully");
			Logger.log(Status.PASS,"Chrome Initiated");
			reports.flush();
			break;
			
		//Open firefox browser
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			Logger = reports.createTest("Firefox Initiated Successfully");
			Logger.log(Status.PASS,"Firefox Initiated");
			reports.flush();
			
			break;
		
		//Open IE browser
		case "ie":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			Logger = reports.createTest("Internet Explorer Initiated Successfully");
			Logger.log(Status.PASS,"IE Initiated");
			reports.flush();
			
			break;
		
		//Open chrome-headless browser	
		case "headlessbrowser":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			driver = new ChromeDriver(options);

		//Invalid browser name
		default:
			Logger.log(Status.FAIL,"Provide valid browser name");
			
			break;
		}
		
		// Maximize window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//Invoking implicit wait
		return driver;
	}
	
	
	/****************** Open URL in Browser ***********************/
	
	public void openUrl() {
		String url = prop.getProperty("URL");
		System.out.println("URL:::: " + url);
		driver.get(url);
		
	}

	
	/****************** Wait functionality ***********************/
	
	public void waitElementClickable(String elementXpath) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
	}

	
	/****************** Take Screenshot ***********************/
	
	public String capture(String fileName) {

		// Creating a screenshot driver and storing in "scrFile" temporarily.
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		//store screenshots in the destinations
		try {
			FileUtils.copyFile(scrFile, new File(".\\src\\test\\resources\\Screenshots\\" + fileName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String destination = ".\\src\\test\\resources\\Screenshots\\" + fileName;
		return destination;
	}

	


	/****************** Close Browser ***********************/
	
	public void quitBrowser() {
		reports.flush(); 
		System.out.println("Exiting the application and closing the browser");
		
		driver.quit(); //closing browser
	}
}