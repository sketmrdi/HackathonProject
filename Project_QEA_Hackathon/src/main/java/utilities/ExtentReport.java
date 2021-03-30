/*This class contains methods to generate ExtentReports for the testcases */
/*
 *Project Done By:- 
 *Team No: 05
 *Cohort :QEA20QE059
 */

package utilities;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

public class ExtentReport {

	
	public static ExtentReports reports;
	public static ExtentHtmlReporter htmlReporter;
	public static Date currentDate=new Date();
	
	/****************** Generate Report ***********************/
	
	public static ExtentReports getReportInstance() {
		System.out.println("Report started");           //print  to console
	reports = new ExtentReports();						//Creating extent report object
		
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
	htmlReporter.config().setDocumentTitle("Calculate Trip Cost");  //Set Report Document Title
	htmlReporter.config().setReportName("Automation Test Report");  //Set Report Name
	htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
	System.out.println("Report started");
	return reports;
}
}