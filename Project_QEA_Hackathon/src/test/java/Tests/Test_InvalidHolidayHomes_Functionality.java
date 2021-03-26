package Tests;

import pages.HolidayHome;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;



public class Test_InvalidHolidayHomes_Functionality extends Parent_Class{
	public HolidayHome home;
	

	@BeforeTest
	public void TC_InitiateInvalidHolidayHomes() {
		
		home = new HolidayHome();
		
	}
	@Test(priority=18)
	public void TC_Negative1() throws Exception{
		
		Logger = reports.createTest("Blank field check verification");
		
		home.NegOne();
		
		Logger.log(Status.INFO, "Clicked on 'Find Holiday rentals' button");
		Logger.log(Status.PASS, MarkupHelper.createLabel("Blank field check successfull", ExtentColor.BLUE));
		
		
	}

	@Test(priority=19)
	public void TC_Negative2() throws Exception {
		
		Logger = reports.createTest("Invalid place name verification");
		
		home.NegTwo("jhcfdfjfythfgft");
		
		Logger.log(Status.INFO, "Clicked on 'Find Holiday rentals' button");
		Logger.log(Status.PASS, MarkupHelper.createLabel("Invalid Place name check successfull", ExtentColor.BLUE)); 
	}

	@Test(priority=20)
	public void TC_Negative3() throws Exception {
		Logger = reports.createTest("Check Out field greater than Check in field verification");
	
		home.NegThree("nairobi");
		
		Logger.log(Status.INFO, "Clicked on 'Find Holiday rentals' button");
		Logger.log(Status.PASS, MarkupHelper.createLabel("Valid date check successfull", ExtentColor.BLUE));
}
}