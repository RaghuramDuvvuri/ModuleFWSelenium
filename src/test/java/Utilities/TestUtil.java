package Utilities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import TestReports.TestReports;
import mainPack.DriverApp;


public class TestUtil extends DriverApp{

	 public static String imageName;
	 public static String imageNameIP;
	 public static String imageError;
	 public static String subject;
	 public static String from_date=null;
	 public static String to_date=null;

	
	 public static String Handeler()   
		{
		try{	
		 InetAddress ownIP = null;
			ownIP = InetAddress.getLocalHost();
			subject=ownIP.getHostAddress(); 
			
		}catch(Throwable t){
		 t.printStackTrace();
		}
		return subject;
					
		}
	

	// returns current date and time
	public static String now(String dateFormat) {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	    return sdf.format(cal.getTime());
	    
	}
	
	// store screenshots
public static void captureScreenshot() throws IOException{
		
		Calendar cal = new GregorianCalendar();
		  int month = cal.get(Calendar.MONTH);
		  int year = cal.get(Calendar.YEAR);
		  int sec =cal.get(Calendar.SECOND);
		  int min =cal.get(Calendar.MINUTE);
		  int date = cal.get(Calendar.DATE);
		  int day =cal.get(Calendar.HOUR_OF_DAY);
	     String ImageDest = "D\\screenshots\\";
		
		  imageName = ImageDest+DriverApp.currentTest+DriverApp.currentTSID+year+"_"+date+"_"+(month+1)+"_"+day+"_"+min+"_" +sec;
		  imageError = DriverApp.currentTest+DriverApp.currentTSID+year+"_"+date+"_"+(month+1)+"_"+day+"_"+min+"_" +sec;
		  imageNameIP=DriverApp.currentTest+DriverApp.currentTSID+year+"_"+date+"_"+(month+1)+"_"+day+"_"+min+"_" +sec;
		  //selenium.captureEntirePageScreenshot(imageName+ ".jpeg"," ");
		  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		  FileUtils.copyFile(scrFile, new File(imageName+ ".jpeg"));
	      	      
	}

//GET data from TestData.xsls
/*public static Object[][] getData(String sheetName){
	// return test data;
	// read test data from xls
	
	int rows=test.testData.getRowCount(sheetName)-1;
	if(rows <=0){
		Object[][] testData =new Object[1][0];
		return testData;
		
	}
    rows = test.testData.getRowCount(sheetName);  // 3
	int cols = test.testData.getColumnCount(sheetName);
	
	Object data[][] = new Object[rows-1][cols];
	
	for( int rowNum = 2 ; rowNum <= rows ; rowNum++){
		
		for(int colNum=0 ; colNum< cols; colNum++){
			data[rowNum-2][colNum]=test.testData.getCellData(sheetName, colNum, rowNum);
		}
	}
	
	return data;
	
	
}*/


	
	// make zip of reports
	public static void zip(String filepath){
	 	try
	 	{
	 		File inFolder=new File(filepath);
	 		File outFolder=new File("Reports.zip");
	 		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
	 		BufferedInputStream in = null;
	 		byte[] data  = new byte[1000];
	 		String files[] = inFolder.list();
	 		for (int i=0; i<files.length; i++)
	 		{
	 			in = new BufferedInputStream(new FileInputStream
	 			(inFolder.getPath() + "/" + files[i]), 1000);  
	 			out.putNextEntry(new ZipEntry(files[i])); 
	 			int count;
	 			while((count = in.read(data,0,1000)) != -1)
	 			{
	 				out.write(data, 0, count);
	 			}
	 			out.closeEntry();
  }
  out.flush();
  out.close();
	 	
}
  catch(Exception e)
  {
	  e.printStackTrace();
  } 
 }	
	
public static void UpdateResult(String result) {
	try {
		
	if (result == "Pass") {
		  TestReports.addKeyword(stepDescription,keyword , result, "No Screen shot for pass values");
		  testStatus = result;
	  }
	  else if (result == "Fail") {
		  captureScreenshot();
		  TestReports.addKeyword(stepDescription,keyword , result, TestUtil.imageName); 
		  testStatus = result;						  
	  }
	} // try
	catch(Throwable t) {
		t.getMessage();
	}
}
		   
     public static float Round(float Rval, int Rpl) {
		  float p = (float)Math.pow(10,Rpl);
		  Rval = Rval * p;
		  float tmp = Math.round(Rval);
		  return (float)tmp/p;
		  }

    
     
     
    
     
}
