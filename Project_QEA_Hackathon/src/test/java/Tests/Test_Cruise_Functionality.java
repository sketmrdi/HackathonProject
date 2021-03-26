package Tests;

import pages.Cruises;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;




public class Test_Cruise_Functionality  extends Parent_Class{

public Cruises cruise;

@BeforeTest
public void TC_InitiateCruise() {
	cruise = new Cruises();
}

@Test(priority=13)
public void TC_ClickCruise() throws Exception {
	Logger = reports.createTest("Cruise Option Verification");
	Logger.assignCategory("Functional Testing");
	
	cruise.clickCruise();
	
	Logger.log(Status.INFO, "Selected cruise option successfully...");

	
}

@Test(priority=14)
public void TC_CruiseType() throws Exception {
	Logger = reports.createTest("Cruise Type Selection Functionality");
	Logger.assignCategory("Functional Testing");
	
	cruise.selectCruiseType();
	
	Logger.log(Status.INFO, "Clicked on 1st Drop down menu...");
	Logger.log(Status.INFO, "Selected cruise Type");


}

@Test(priority=15)
public void TC_SelectCruiseVar() throws Exception{
	Logger = reports.createTest("Cruise Type Variant Functionality");
	Logger.assignCategory("Functional Testing");
	
	cruise.selectCruiseVar();
	
	Logger.log(Status.INFO, "Clicked on 2nd Drop down menu...");
	Logger.log(Status.INFO, "Selected cruise Variable");


	
}

@Test(priority=16)
public void TC_ClickSub() throws Exception {
	Logger = reports.createTest("Cruise Search Button Functionality");
	Logger.assignCategory("Regression Testing");

	Thread.sleep(500);
	
	cruise.clicksub();
	
	Logger.log(Status.INFO, "Clicked on search button...");

	Logger.log(Status.PASS, MarkupHelper.createLabel(
			"Cruise selection Functionality Successfull", ExtentColor.BLUE));
}

@Test(priority=17)
public void TC_GetDetails() throws Exception{
	Logger = reports.createTest("Cruise Output data transfer Functionality");
	
	cruise.getDetails();
	
	Logger.log(Status.INFO, "Fetched passenger details...");
	Logger.log(Status.INFO, "Fetched launch details...");
	
	
	Logger.log(Status.PASS, MarkupHelper.createLabel(
			"Output data transfer Functionality Successfull",
			ExtentColor.BLUE));
}


}