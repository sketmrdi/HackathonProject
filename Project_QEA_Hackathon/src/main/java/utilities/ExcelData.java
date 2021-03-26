package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import BaseClass.Base;

public class ExcelData extends Base{
	static WebDriver driver;
	public static String path = "./src/test/resources/ExcelData/Inputread.xlsx";		//Fetching excel file from directory
	public static File file;
	public static FileInputStream fin;

	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static FileOutputStream fout;
	static XSSFRow row = null;

	public String[] readExcel(String sheetName) throws Exception			//Reading data from excel sheet
	{
		file = new File(path);
		fin = new FileInputStream(file);
		wb = new XSSFWorkbook(fin);
		sh = wb.getSheet(sheetName);
		String[] s = new String[4];
		double n = sh.getRow(1).getCell(0).getNumericCellValue();				//Fetching numeric values from excel
		int m = (int)n;															//Converting numeric value to integer format
		s[0] = Integer.toString(m);												//Converting numeric value to string format
		s[1] = sh.getRow(1).getCell(1).getStringCellValue();					//Fetching string values from excel
		s[2] = String.valueOf(sh.getRow(1).getCell(2).getDateCellValue());		//Fetching date format values from excel
		s[3] = String.valueOf(sh.getRow(1).getCell(3).getDateCellValue());		//Fetching date format values from excel
		 
		 return s;																//Returning string array of values
	}

	public static void writeExcel(String[] name,String[] price1,String[] totalPrice) throws Exception 	//Passing values to excel sheet to save it
	{

		String path1 = "./src/test/resources/ExcelData/Output.xlsx";						//Fetching excel file from directory
		file = new File(path1);
		fin = new FileInputStream(file);
		wb = new XSSFWorkbook(fin);
		if(browse.equalsIgnoreCase("chrome"))
			sh = wb.getSheet("ChromeOutput");
		else if(browse.equalsIgnoreCase("firefox"))
			sh = wb.getSheet("FirefoxOutput");
		else if(browse.equalsIgnoreCase("ie"))
			sh = wb.getSheet("IE-Output");
		
		for (int i = 0; i < 3; i++)										//Loop for storing manual input values to excel
		{
			row = sh.createRow(i);
			row.createCell(0).setCellValue("Holiday Homes");			//Manual value stored in excel
			row.createCell(1).setCellValue("Price per Night");			//Manual value stored in excel
			row.createCell(2).setCellValue("Total Price");				//Manual value stored in excel
		}
		for (int i = 0; i <3; i++) 										//Loop for storing extracted values to excel
		{
			row = sh.createRow(i+1);
			row.createCell(0).setCellValue(name[i]);					//Storing extracted data in excel
			row.createCell(1).setCellValue(price1[i]);					//Storing extracted data in excel
			row.createCell(2).setCellValue(totalPrice[i]);				//Storing extracted data in excel
		}
		sh.autoSizeColumn(0);											//Resizing the columns
		sh.autoSizeColumn(1);											//Resizing the columns
		sh.autoSizeColumn(2);											//Resizing the columns
		
		fout = new FileOutputStream(path1);								//Writing the output to Excel file using FileOutputStream
		wb.write(fout);
		fout.close();													//Closing the excel sheet
		
	}
	public static void writeExcelCruise(String[] list,String launched,String[] lang) throws Exception 
	{

		String path1 = "./src/test/resources/ExcelData/Output.xlsx";			//Fetching excel file from directory
		file = new File(path1);
		fin = new FileInputStream(file);
		wb = new XSSFWorkbook(fin);
		
		if(browse.equalsIgnoreCase("chrome"))
			sh = wb.getSheet("ChromeOutput");
		else if(browse.equalsIgnoreCase("firefox"))
			sh = wb.getSheet("FirefoxOutput");
		else if(browse.equalsIgnoreCase("ie"))
			sh = wb.getSheet("IE-Output");
		
		
		sh.createRow(5).createCell(0).setCellValue("Cruise Details");	//Manual value stored in excel		
		sh.createRow(6).createCell(0).setCellValue(list[0]);			//Storing extracted data in excel
		sh.createRow(7).createCell(0).setCellValue(list[1]);			//Storing extracted data in excel
		sh.createRow(8).createCell(0).setCellValue(launched);			//Storing extracted data in excel
		sh.createRow(9).createCell(0).setCellValue("Languages");		//Manual value stored in excel
		
		for (int i = 0; i <3; i++) 										//Loop for storing extracted values to excel
		{
			row = sh.createRow(i+10);
			row.createCell(0).setCellValue(lang[i]);					//Storing extracted data in excel
			
		}
		sh.autoSizeColumn(0);											//Resizing the columns
		
		fout = new FileOutputStream(path1);								// Writing the output to Excel file using FileOutputStream
		wb.write(fout);
		fout.close();													//Closing the excel sheet
		
	}
}
