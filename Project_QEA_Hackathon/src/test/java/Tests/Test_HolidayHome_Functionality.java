/*This class contains  TestCases for HolidayHome functionality */

/*
 *Project Done By:- 
 *Team No: 05
 *Cohort :QEA20QE059
 */

package Tests;

import pages.HolidayHome;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;


public class Test_HolidayHome_Functionality extends Parent_Class{

public HolidayHome home;

@BeforeTest
public void TC_InitiateHolidayHomes() {
	home = new HolidayHome();
}


	

@Test(priority=1)
public void TC_EnterCity() throws Exception {                     //TestCase for entering cityName
	Logger = reports.createTest("City Input Test Function");
	
	home.enterCity();
	
	Logger.log(Status.INFO,"City fetched successfully from excel...");
	Thread.sleep(1000);	
	
	Logger.log(Status.INFO,"City entered successfully...");
}

@Test(priority=2)
public void TC_VerifyCity() throws Exception {						 //TestCase for verifying cityName
	Logger = reports.createTest("City Verification");
	
	home.verifyCity();
}

@Test(priority=3)
public void TC_SelectCity() throws Exception{						 //TestCase for selecting city from application
	Logger=reports.createTest("Select City Verification");
	
	home.selectCity();
	
	Logger.log(Status.INFO,"Search result clicked successfully...");
	
	Logger = reports.createTest("Resultant Window Verification");
	Logger.log(Status.PASS,"Window handle switched successfully...");
}

@Test(priority=4)
public void TC_ClickHolidayHomes() throws Exception {					 //TestCase for entering HolidayHome button
	Logger=reports.createTest("Holiday Home verification");
	Thread.sleep(2000);
	home.clickHolidayHomes();
	
	Logger.log(Status.INFO,"Holiday Home selected successfully...");
	Logger.log(Status.PASS, MarkupHelper.createLabel("City selected Successfully", ExtentColor.BLUE));
}

@Test(priority=5)
public void TC_InDate() throws Exception {								 //TestCase for clicking check-In Date
	Logger = reports.createTest("In-Date Verification");
	
	home.clickCheckIn();
	
	Logger.log(Status.INFO,"CheckIn-Date option selected...");
}

@Test(priority=6)
public void TC_SelectCheckIn() throws Exception{						 //TestCase for selecting checkInDate
	Logger=reports.createTest("Check In Date Verification");
	
	home.selectCheckInDate();
	
	Logger.log(Status.PASS, MarkupHelper.createLabel("CheckIn-Date Verification Successfull", ExtentColor.BLUE));
}

@Test(priority=7)
public void TC_SelectCheckOut() throws Exception {						//TestCase for selecting checkOutDate
	Logger = reports.createTest("CheckOut-Date Verification");
	
	home.selectCheckOutDate();
	
	Logger.log(Status.PASS, MarkupHelper.createLabel("CheckOut-Date Verification Successfull", ExtentColor.BLUE));
	
}



@Test(priority=8)
public void TC_GuestOption() throws Exception {							//TestCase for selecting GuestCount
	Logger = reports.createTest("Guest Verification");
	
	
	home.guestOption();
	
	Logger.log(Status.INFO,"Data fetched sucessfully from excel...");
	Logger.log(Status.INFO,"Guested option clicked...");
	Logger.log(Status.INFO,"Number of Guested updated...");
}


@Test(priority=9)
public void TC_ClickApply() throws Exception {							//TestCase for ckicking "Apply" button
	Logger=reports.createTest("Apply button verification");
	
	home.clickApply();
	
	Logger.log(Status.INFO,"Clicked on Apply...");
	Logger.log(Status.PASS, MarkupHelper.createLabel("Guest Verification Successfull", ExtentColor.BLUE));
}

@Test(priority=10)
public void TC_TravellerRating() throws Exception {							//TestCase for selecting Traveller rating option
	Logger = reports.createTest("Traveller Rating Functionality");
	
	Thread.sleep(2000);
	
	home.travellerRating();
	
	Logger.log(Status.INFO,"Sorting Options selected...");
	Logger.log(Status.INFO,"Traveller rating selected...");
	
}



@Test(priority=11)
public void TC_Elevator() throws Exception {										//TestCase for selecting "Elevator" option
	Logger = reports.createTest("Elevator Testing Functionality");
	
	home.elevatorOption();
	
	Logger.log(Status.INFO,"Show more in Amenities clicked...");
	Logger.log(Status.INFO,"Elevator option selected...");
	Logger.log(Status.PASS, MarkupHelper.createLabel("Elevator Testing Functionality Successfull", ExtentColor.BLUE));
}

@Test(priority=12)
public void TC_PrintOutput() throws Exception {									//TestCase for Fetching HolidayHome details
	Logger = reports.createTest("Output data transfer Functionality");
	
	home.printOutput();
	
	
	Logger.log(Status.INFO,"List of price per night fetched...");
	Logger.log(Status.INFO,"List of hotels fetched...");
	Logger.log(Status.INFO,"List of total price fetched...");
	Logger.log(Status.PASS, MarkupHelper.createLabel("Output data transfer Functionality Successfull", ExtentColor.BLUE));
}


}


