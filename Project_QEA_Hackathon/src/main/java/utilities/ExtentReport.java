package utilities;
/**
 * This class is defined to generate ExtentReports for the testcases
 * @author Coding Titans
 * Date:01/12/2020 
 */
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

public class ExtentReport {

	
	public static ExtentReports reports;
	public static ExtentHtmlReporter htmlReporter;
	public static Date currentDate=new Date();
	
	public static ExtentReports getReportInstance() {
		System.out.println("Report started");
	reports = new ExtentReports();								//Creating extent report object
		/*
		 * if(Base.browse.equalsIgnoreCase("chrome")) { htmlReporter = new
		 * ExtentHtmlReporter(System.getProperty("user.dir")+
		 * "//test-output//ExtentReport_Chrome.html"); } else
		 * if(Base.browse.equalsIgnoreCase("firefox")) { htmlReporter = new
		 * ExtentHtmlReporter(System.getProperty("user.dir")+
		 * "//test-output//ExtentReport_Firefox.html"); }
		 */
	htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//test-output//ExtentReport.html");	//Fetching file from directory
	System.out.println(htmlReporter);
	reports.attachReporter(htmlReporter);						//attaching report object to existing html file
	
	// Set the system info of the report
	reports.setSystemInfo("OS","Windows 10");
	reports.setSystemInfo("Environment","UAT");
	reports.setSystemInfo("Build number","10.8.1");
	reports.setSystemInfo("Browser","Firefox and Chrome");
	
	// Set the htmlReporter properties
	 htmlReporter.config().setChartVisibilityOnOpen(true);
	htmlReporter.config().setDocumentTitle("Calculate Trip Cost");
	htmlReporter.config().setReportName("Automation Test Report");
	htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
	System.out.println("Report started");
	return reports;
}
}