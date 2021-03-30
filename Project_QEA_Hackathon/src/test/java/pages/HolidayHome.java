/*This class contains  methods to execute HolidayHome functionality*/
/*
 *Project Done By:- 
 *Team No: 05
 *Cohort :QEA20QE059
 */

package pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.Color;




import BaseClass.Base;
import utilities.ExcelData;

public class HolidayHome extends Base{

	String[] location = new String[4];
	ExcelData read=new ExcelData();

/****************** Enter City in Search field***********************/
	
public void enterCity() throws Exception 													
{	
	location = read.readExcel("Input");	              //Retrieving city name from excel file	
	driver.findElement(By.xpath(prop.getProperty("inputField"))).sendKeys(location[1]); //Passing city name to search field 
	
		
		
	
	Thread.sleep(1000);			
	driver.findElement(By.xpath(prop.getProperty("inputField"))).sendKeys(Keys.ENTER);//Click Enter 		
	

	capture("City entered successfully");	//Taking screenshot

		
}

/****************** Verify City***********************/

public void verifyCity() throws Exception{	
	
	
	String s = driver.findElement(By.xpath(prop.getProperty("result"))).getText();   //get cityName text
	
	
	if(location[1].equalsIgnoreCase(s))
		Logger.log(Status.INFO,"Entered city found successfully...");
	else
	{
		Logger.log(Status.FAIL,"No such city found...");
		System.exit(0);
	}
	
}

/****************** Click City Name in NextPage***********************/

public void selectCity() throws Exception{		
	
	
	driver.findElement(By.xpath(prop.getProperty("point"))).click();	//click first city in application					
	
	for(String winHandle : driver.getWindowHandles())									
	{
		driver.switchTo().window(winHandle);	//Switching control to new window
	}
		
	Thread.sleep(1000);
	capture("New window");	//Taking-screenshot
}

/****************** Click Holiday Home ***********************/

public void clickHolidayHomes() throws Exception{ 
	
	
	driver.findElement(By.xpath(prop.getProperty("holidayHome"))).click();	//clicking "Holiday Home" menu.			
	
		
	Thread.sleep(2000);
	capture("Holiday home selection");	//Taking-screenshot
	
		
}

/****************** Select Check-In Date ***********************/

public void clickCheckIn() throws Exception	
{
	
		
	location = read.readExcel("Input");		//Retrieving Check-In Date from excel file
		
	if((location[2].equals(location[3])) || (location[2].equals(null) || location[3].equals(null) ))//Checking input and output days
	{
		Logger.log(Status.FAIL,"Invalid Date Input");
		System.out.println("Invalid Date Input");	//Print Error message
		System.exit(0);
	}
		
	Thread.sleep(2000);

	
	driver.findElement(By.xpath(prop.getProperty("checkIn"))).click();	//Clicking on check in date field
	
	
		
	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");			//Selecting date format
	Calendar cal = Calendar.getInstance();								//Creating calendar instance
	String currentDate = sdf.format(cal.getTime());						//Fetching current date
		
	if(location[2].equals(currentDate))
	{
		cal.add(Calendar.DAY_OF_MONTH, 1);								//Selecting 1st date from given date
		String newDateIn = sdf.format(cal.getTime());					//Storing date in string

		cal.add(Calendar.DAY_OF_MONTH, 5);								//Selecting 5th date from given date
		String newDateOut = sdf.format(cal.getTime());					//Storing date in string

		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class=\"_2baLkbS8\"]/descendant::div[contains(@aria-label,'"+ newDateIn +"')]")).click();	//Selecting in-date in calendar
			
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='_2baLkbS8']/descendant::div[contains(@aria-label,'"+ newDateOut +"')]")).click();	//Selecting out-date in calendar
	}
	
	
		
	
}

/****************** Select Check-In Date ***********************/

public void selectCheckInDate() throws Exception		   //Selecting Check-In date
{ 
	
	location = read.readExcel("Input");				//Fetching data from excel file
			
	String inDate = location[2];					//Storing check in date
	String splitter[] = inDate.split(" ");			//Splitting date format
	String mon = splitter[1];						//Fetching month from array 
	
	String month = prop.getProperty(mon);			//Fetching month abbreviation from properties file
	String day = splitter[2]; 						//Fetching date from array
	String yr = splitter[5];						//Fetching year from array
	String myr = month+" "+yr;						//Merging month and year

	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		 //Invoking implicit wait
			
	List<WebElement> elements = driver.findElements(By.xpath(prop.getProperty("monthCatcher"))); //Fetching month details
	Logger.log(Status.INFO,"Months fetched successfully...");
			
	String fetchMonth[] = new String[2];			//Creating array to store months
	try
	{
		fetchMonth = elements.get(0).getText().split(" ");	//Fetching 1st month
	}
	catch(IndexOutOfBoundsException e)
	{
		return;
	}
		
	String c = prop.getProperty(fetchMonth[0]);		//Storing current month
	int currentMonth = Integer.parseInt(c);			//Converting current month from string to integer
		
	String c1 = fetchMonth[1];						//Storing current year
	int currentYear = Integer.parseInt(c1);			//Converting current year from string to integer
			
	String w = prop.getProperty(month);				//Storing last month
	int lastMonth = Integer.parseInt(w);			//Converting last month from string to integer
			
	String w1 = yr;									//Storing last year
	int lastYear = Integer.parseInt(w1);			//Converting last year from string to integer
		
	if(lastYear == currentYear)						//Check if current year is same as last year
	{
		int diff = lastMonth - currentMonth;		//Calculating difference in between months
		if(diff < 0)
		{
			diff = 12 - diff;						//Calculation of number of times to click arrow button
		} 
		for(int i = 0; i < diff; i++)
		{											//Clicking the right arrow to change months
			driver.findElement(By.xpath(prop.getProperty("arrowRight"))).click();
		}
	}
	else if(lastYear > currentYear)					//Check if last year is greater than current year
	{
		int diff = lastMonth - currentMonth;
		if(diff < 0)
		{
			diff = 12 + diff;						//Calculation of number of times to click arrow button
		} 
		for(int i = 0; i < diff; i++)
		{											//Clicking the right arrow to change months
			driver.findElement(By.xpath(prop.getProperty("arrowRight"))).click();	

		}
	}
		else
		{
			System.out.println("Invalid dates");	//Error message for invalid date input
			Logger.log(Status.FAIL,"Invalid dates, please enter valid Check In, Check Out Date.");
			System.exit(0);							//Forceful termination of program
		}
			
		boolean staleElement = true;				//Initializing stale element as true
		while(staleElement)							//Enter while true
		{
		try {
			for (int i = 0; i < elements.size(); i++)	//Loop to traverse months
				{
				if(elements.get(i).getText().equals(myr))		//Matching input month with site data
				{ 
							
					List<WebElement> days = driver.findElements(By.xpath(prop.getProperty("dayCatcher")));		//Fetching the days 
					Logger.log(Status.INFO,"Dates fetched successfully...");
					for (WebElement d:days)
					{ 
						if(d.getText().equals(day))			//Matching input day with site data
						{
							d.click();						//Clicking the required day
						}
					} 
				}
						
				}
			}
			catch(StaleElementReferenceException e)			//Catching Stale Element 
			{
					Thread.sleep(1000);
					capture("Check in date entered");
					
					Logger.log(Status.INFO,"In Date entered successfully...");
					
					return;									//Returning to main 
				}
			}
			
		}



/****************** Select Check-out Date ***********************/

	public void selectCheckOutDate() throws Exception		
	{ 	
		
		
		
			
		String outDate = location[3];						//Storing check out date

		String splitter[] = outDate.split(" ");				//Splitting date format
		String mon1 = splitter[1];							//Fetching month from array
		String month = prop.getProperty(mon1);				//Fetching month abbreviation from properties file 
		String day = splitter[2]; 							//Fetching date from array
		String yr = splitter[5];							//Fetching year from array
		String myr = month+" "+yr;							//Merging month and year
		
			//Fetching month details
		List<WebElement> elements = driver.findElements(By.xpath(prop.getProperty("monthCatcher")));
		Logger.log(Status.INFO,"Months fetched successfully...");
			
		String fetchMonth[] = new String[2];				//Creating array to store months
		try
		{
			fetchMonth = elements.get(0).getText().split(" ");		//Fetching 1st month
		}
		catch(IndexOutOfBoundsException e)
		{ 
			return;			
		}
			
		String c = prop.getProperty(fetchMonth[0]);					//Storing current month
		int currentMonth = Integer.parseInt(c);						//Converting current month from string to integer
			
		String w = prop.getProperty(month);							//Storing last month
		int lastMonth = Integer.parseInt(w);						//Converting last month from string to integer
			
		int diff = lastMonth - currentMonth;						//Calculating difference in between months
			
		for(int i = 0; i < diff; i++)
		{
			driver.findElement(By.xpath(prop.getProperty("arrowRight"))).click();//Clicking the right arrow to change months
		}
			
		boolean staleElement = true;								//Initializing stale element as true
		while(staleElement)											//Enter while true
		{
			try {
				for (int i = 0; i < elements.size(); i++)			//Loop to traverse months
				{
					if(elements.get(i).getText().equals(myr))		//Matching input month with site data
					{ 
						
					List<WebElement> days1 = driver.findElements(By.xpath(prop.getProperty("dayCatcher")));		//Fetching the days 
					Logger.log(Status.INFO,"Dates fetched successfully...");
					for (WebElement d:days1)
					{ 
						if(d.getText().equals(day))					//Matching input day with site data
						{
							d.click(); 								//Clicking the required day
						}
					} 
					   } 

				}
			}
			catch(StaleElementReferenceException e)					//Catching Stale Element 
			{
				Thread.sleep(1000);
				capture("Checkout date entered");//Taking screenshot
					
				Logger.log(Status.INFO,"Out Date entered successfully...");
					
				return;
			}
		}	
			
			
		
		}			


/****************** Select Guest Count ***********************/
	
public void guestOption() throws Exception 							
	{
	

	try {
		driver.findElement(By.xpath("//button[@class='_3VKU_-kL']")).click(); //click guest button
		
		
	}
	catch(Exception e)
	{
			
	}
	
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//Invoking implicit wait
		
		location = read.readExcel("Input");								//Fetching data from excel file
		
		
		driver.findElement(By.xpath(prop.getProperty("guest"))).click();//Clicking guest option 
	

		int defaultValue = 2;											//Default guests in input field
		String m1 = location[0];										//Storing input value from excel to string
		int m = Integer.parseInt(m1);									//Converting string value to integer
		
		if(m > defaultValue)
		{
			int diff = m - defaultValue;								//Calculating number of times to click (+) option
			for(int i = 0; i < diff; i++)
			{
				driver.findElement(By.xpath(prop.getProperty("plusIcon"))).click(); //Clicking the (+) option
			}
		}
		
}

/****************** Click Guest Apply Button***********************/

public void clickApply() throws Exception{							
	
	
		driver.findElement(By.xpath(prop.getProperty("apply"))).click();			//Clicking on apply
		
		Thread.sleep(2000);
		capture("Guests selected");	//Taking screenshot
		
}

/****************** Select Traveller Rating Sort Option***********************/

public void travellerRating() throws Exception 							
	{
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("options"))).click();		//Selecting Sort By option
		
		Thread.sleep(1000);
		driver.findElement(By.xpath(prop.getProperty("rating"))).click();	//Selecting Traveller-rating option
		
		Thread.sleep(2000);
		capture("Traveller rating selected") ;	//Taking-screenshot
	}

/****************** Select Elevator Option in Amenities***********************/

public void elevatorOption() throws Exception{								
		
		
		driver.findElement(By.xpath(prop.getProperty("showMore"))).click();		//Clicking "show more" option in Amenities
		
		Thread.sleep(1000);

		driver.findElement(By.xpath(prop.getProperty("elev"))).click();		//Clicking Elevator option	
		
		
		Thread.sleep(1000);
		capture("Elevator selected");	//Taking-screenshot
		
	}

/****************** Print Holiday Home details console & Write to excel file***********************/

public void printOutput() throws Exception									
	{
		
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);											//Invoking Implicit wait
	
		List<WebElement> titles = driver.findElements(By.xpath(prop.getProperty("listOfHotels")));			//Fetching list of hotels
		
		List<WebElement> price = driver.findElements(By.xpath(prop.getProperty("listOfPricePerNight")));	//Fetching list of price per night
		
		List<WebElement> total = driver.findElements(By.xpath(prop.getProperty("listOfTotalPrice")));		//Fetching list of total price for 5 nights
		
		String[] name = new String[3];																		//Creating arrays to store lists
		String[] price1=new String[3];																		//Creating arrays to store lists
		String[] totalPrice=new String[3];																	//Creating arrays to store lists
		
		for (int i = 0; i < 3; i++) 
		{
			name[i] = titles.get(i).getText();	//Transferring values to arrays	
			System.out.println(name[i]);         //print hotel name to concole
			price1[i] = price.get(i).getText();																//Transferring values to arrays
			System.out.println(price1[i]);       //print price per day to console
			totalPrice[i] = total.get(i).getText();		 													//Transferring values to arrays
			System.out.println(totalPrice[i]);      //print 5day price to console
		}

	ExcelData.writeExcel(name, price1, totalPrice);												//Transferring data to excel sheet
	

	}

/****************************************Negative Test Cases for HolidayHome**************************************/


/****************** Blank Search***********************/

public void BlankSearch() throws InterruptedException {	
	
	driver.navigate().to(prop.getProperty("url"));
	Thread.sleep(2000);
	
	driver.findElement(By.xpath(prop.getProperty("findHolidayRentals"))).click(); //click search button by leaving search textbox empty
	Thread.sleep(2000);
	

	String color = driver.findElement(By.xpath(prop.getProperty("colour"))).getCssValue("color");
	String hex = Color.fromString(color).asHex();
	System.out.println(hex);		//Getting the color of blank field after submit
	if (hex.equals("#cc0000"))
		System.out.println("Please enter valid value");
	
	capture("Blank field check");  //Taking-screenshot
}

/****************** Invalid City Search***********************/

public void InvalidCity(String str) throws Exception {
	

	driver.findElement(By.xpath(prop.getProperty("city"))).sendKeys(str);	//Entering invalid city name
	Thread.sleep(1000);
	driver.findElement(By.xpath(prop.getProperty("findHolidayRentals"))).click();
	
	WebDriverWait wait1 = new WebDriverWait(driver, 40);
	wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("errorMsg"))));
	driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	String actualMessage=driver.findElement(By.xpath(prop.getProperty("errorMsg"))).getText();
	String expectedMessage="Sorry, we couldn't find \"jhcfdfjfythfgft\" worldwide";
	Assert.assertEquals(actualMessage, expectedMessage);//Verify error message
	
	capture("Invalid place"); //Taking-screenshot
}

/****************** Enter Invalid Date***********************/

public void InvalidDates(String str1) throws Exception {
	
	driver.navigate().to(prop.getProperty("url"));
	
	driver.findElement(By.xpath(prop.getProperty("city"))).sendKeys(str1);
	driver.findElement(By.xpath(prop.getProperty("cal"))).click();
	WebDriverWait wait2 = new WebDriverWait(driver, 40);
	wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("date1"))));
	driver.findElement(By.xpath(prop.getProperty("date1"))).click(); //click check-in date
	
	WebDriverWait wait3 = new WebDriverWait(driver, 40);
	wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("date2"))));
	
	driver.findElement(By.xpath(prop.getProperty("date2"))).click();   //click check-out date
	Thread.sleep(1000);
	if (driver.findElement(By.xpath(prop.getProperty("calenderDisp"))).isDisplayed())
		System.out.println("Enter valid date in Date Field");

	capture("Dates check");
}


}
