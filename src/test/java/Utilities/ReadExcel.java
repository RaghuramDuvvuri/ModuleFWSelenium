package Utilities;
import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class  ReadExcel {

    public Sheet readExcelrows(String filePath,String fileName,String sheetName) throws IOException{

    //Create an object of File class to open xlsx file
    	
    	Sheet guru99Sheet = null;
   try { 	
		    File file =    new File(filePath+"\\"+fileName);
		
		    //Create an object of FileInputStream class to read excel file
		
		    FileInputStream inputStream = new FileInputStream(file);
		
		    Workbook guru99Workbook = null;
		    //Find the file extension by splitting file name in substring  and getting only extension name
		
		    String fileExtensionName = fileName.substring(fileName.indexOf("."));
		
		    //Check condition if the file is xlsx file
		
		    if(fileExtensionName.equals(".xlsx")){
		
		    //If it is xlsx file then create object of XSSFWorkbook class
		
		    guru99Workbook = new XSSFWorkbook(inputStream);
		
		    }
		
		    //Check condition if the file is xls file
		
		    else if(fileExtensionName.equals(".xls")){
		
		        //If it is xls file then create object of HSSFWorkbook class
		
		        guru99Workbook = new HSSFWorkbook(inputStream);
		
		    }
		
		  //Read sheet inside the workbook by its name

		     guru99Sheet = guru99Workbook.getSheet(sheetName);
		   
   } // try
   catch (Throwable t) {
	   System.out.println(t.getMessage());
   }
   return guru99Sheet;
    } // end of read excel
    
    
    public String getCellData(Sheet sheetName,int rownum, int colnum)
    {
    	String datavalue = null;
    	try {
	    	Logimp log = new Logimp();
	
	
	    //Find number of rows in excel file
	
	    	int rowCount = sheetName.getLastRowNum()-sheetName.getFirstRowNum();
	
	    	//Create a loop over all the rows of excel file to read it
	
	
	        Row row = sheetName.getRow(rownum);
	
	        //Create a loop to print cell values in a row
	
	
	            //Print Excel data in console
	
	            //System.out.print(row.getCell(j).getStringCellValue()+"|| ");
	        
	            log.logmessagetofile(row.getCell(colnum).getStringCellValue()+"|| ");
	            datavalue = row.getCell(colnum).getStringCellValue()+"|| ";
    	} // Try
    	catch(Throwable t) {
    		System.out.println(t.getMessage());
    	} // messsage

    	return datavalue;
    }  

    //Main function is calling readExcel function to read data from excel file



}

