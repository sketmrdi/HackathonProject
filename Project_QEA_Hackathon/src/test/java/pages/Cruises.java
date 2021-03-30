/*This class contains  methods to execute cruise functionality*/
/*
 *Project Done By:- 
 *Team No: 05
 *Cohort :QEA20QE059
 */


package pages;

import org.openqa.selenium.By;

import BaseClass.Base;
import utilities.ExcelData;

public class Cruises extends Base{
	
	/****************** Select Cruise Button***********************/
	
	public void clickCruise() throws Exception 
	{
		

	
		driver.findElement(By.xpath(prop.getProperty("cruiseOption"))).click(); // Click cruise option
		

		Thread.sleep(2000);
		capture("Cruise tab");       //Taking-screenshot
	}

	/****************** Select Cruise Type***********************/
	
	public void selectCruiseType() throws Exception{
		                                                                    
		
		
		driver.findElement(By.xpath(prop.getProperty("dropMenu1"))).click(); //click 1st dropDown Menu in cruise page
		driver.findElement(By.xpath(prop.getProperty("option1"))).click();  //selecting cruise type from dropdown
		

		Thread.sleep(2000);
		
		capture("Cruise Type");	  //Taking-screenshot
	}
	
	
	/****************** Select Cruise Variant***********************/
	
	public void selectCruiseVar() throws Exception{	
		

	
		driver.findElement(By.xpath(prop.getProperty("dropMenu2"))).click();    // Clicking on 2nd drop
		driver.findElement(By.xpath(prop.getProperty("option2"))).click();     //Selecting cruise varient from dropDown  	
		

		Thread.sleep(2000);
		
		capture("Cruise Variant");   //Taking-screenshot
																			
	}
	
	/****************** Click Search Button***********************/
	
	public void clicksub() throws Exception{
		
		driver.findElement(By.xpath(prop.getProperty("search"))).click(); // Clicking Search button
		

	}

 
	/****************** Fetch Cruise Details***********************/
	
	public void getDetails() throws Exception 
	{
		

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);           // Switching control to new window
		}

		String passenger = driver.findElement(
				By.xpath(prop.getProperty("details1"))).getText();     // Storing passenger details
		String[] list = passenger.split("\\|   ", 2);                  // Separating passenger details
		

		String launched = driver.findElement(
				By.xpath(prop.getProperty("details2"))).getText();    // Storing launch details
		
		System.out.println(list[0]+" "+list[1]+" "+launched);
		
		String lang[] = new String[3];
		for (int i = 0; i < 3; i++) {
			lang[i] = driver.findElement(By.xpath("//*[@id=\"ship_reviews\"]/div/div[2]/div/div[1]/div[1]/div[3]/ul/li["+ (i + 2) +"]/label/span[1]")).getText(); 
			System.out.println(lang[i]);                                 // Storing language details
			
			if(lang[i]==null) {                                                                 
				System.out.println("No languages found");                                      
				lang[i]="no";
				break;
			}                                          
			
		}

		Thread.sleep(1000);
		capture("Cruise details");// Taking screenshot
		ExcelData.writeExcelCruise(list, launched, lang); // Passing fetched data to excel
		
	}
	}

