package mainPack;




import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Utilities.Logimp;

//import jdk.internal.jline.internal.Log;
import TestReports.TestReports;
import Utilities.TestUtil;
import mainPack.KeywordsApp;
import Utilities.ReadJson;
import testCaseMethods.Module1Methods;
public class DriverApp {
	
	public static Random randomGenerator = new Random(); 
	public static String currentTest;
	public static String currentTSID;
	public static String TestDescription;
	public static String TestData;
	public static String stepDescription;
	public static String proceedOnFail;
	public static String testStatus;
	public static int  testRepeat;
//	public static int nSelPort;
	//public static String sSelPort;
	public static Calendar cal = new GregorianCalendar();
	public static  int month = cal.get(Calendar.MONTH);
	public static int year = cal.get(Calendar.YEAR);
	public static  int sec =cal.get(Calendar.SECOND);
	public static  int min =cal.get(Calendar.MINUTE);
	public static  int date = cal.get(Calendar.DATE);
	public static  int day =cal.get(Calendar.HOUR_OF_DAY);
	public static String strDate;
	public static String result;
	public static String mailresult=" - Script successfully executed - no errors found";
	public static RemoteWebDriver driver = null;
	//public static WebDriver driver = null;
	public static String keyword;
	public static String object;	
	public static  ArrayList<String> TestNamelist = new ArrayList<String>();
	public static  ArrayList<String> Testdesclist = new ArrayList<String>();
	public static ArrayList<String> TestbrowList = new ArrayList<String>();

	//Get the current system time - used for generated unique file ids (ex: Screenshots, Reports etc on every test run)
	public static String getCurrentTimeStamp()
    { 
          SimpleDateFormat CurrentDate = new SimpleDateFormat("MM-dd-yyyy"+"_"+"HH-mm-ss");
          Date now = new Date(); 
         String CDate = CurrentDate.format(now); 
          return CDate; 
    }
	
   	@BeforeSuite
	public void startTesting() throws Exception{

		 strDate=getCurrentTimeStamp();
     	System.out.println("date time stamp :"+strDate);
		 
		 // Start testing method will start generating the Test Reports from the beginning       
     	TestReports.startTesting(System.getProperty("user.dir")+"\\src\\TestReports" +strDate+ ".html",TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"),"3.1");
		
	}
	
	//public static Logger log = Logger.getLogger(test.class);
//	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@Test(dataProvider="getData",threadPoolSize=3)
		public void testlaunch (String TestCase, String description, String browserval) throws InterruptedException, IOException {

			int i = 1;		
			String startTime=null;
			TestReports.startSuite("Suite " + String.valueOf(i));
			i = i +1;
			URL url = new URL("http://localhost:4444/wd/hub");
		try {
			// log messages
			Logimp log = new Logimp();
			// Read excel file
	    	
	    startTime=TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa");
	    
	  /*  TestCase = TestCase.substring(0, (TestCase.length() -3));
	    description = description.substring(0, (description.length() -3));
	    browserval = browserval.substring(0, (browserval.length() -3));*/
	    
	    
	    if (browserval.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\Gecko Driver\\geckodriver.exe");
	    	FirefoxOptions options1 = new FirefoxOptions();
	    	options1.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
	    	options1.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
	    	options1.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	    	driver = new RemoteWebDriver(url, options1);
	    	System.out.println("The thread ID for Firefox is "+ Thread.currentThread().getId());
			
		}else if (browserval.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
			options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			System.out.println("The thread ID for chrome is "+ Thread.currentThread().getId());
			//cap.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new RemoteWebDriver(url, options);		    
			 	
		}

	    	currentTest = TestCase;
	    	TestDescription=description;
			 // Thread.sleep(5000);
			driver.manage().window().maximize();
	       
			driver.manage().timeouts().pageLoadTimeout(300,TimeUnit.SECONDS);

			Method method = Module1Methods.class.getMethod(TestCase);
			method.invoke(method);
			
			 driver.close();
			 
				TestReports.addTestCase(TestDescription, currentTest, 
						TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), 
						TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"),
						testStatus );
		}
		catch(Throwable t) {
			System.out.println(t.getMessage());
		}
			
		} 
		
		
		@AfterSuite
		public static void endScript() throws Exception{

			TestReports.endSuite();
			
			// Once the test is completed update the end time in HTML report
			TestReports.updateEndTime(TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"));			

		} 
		
		@DataProvider(parallel=true)
		public Object[][] getData(){
			 Object[][] data = null;
			
			try {

				//ReadJson.jsonReadTC();
				String fileName = (System.getProperty("user.dir")+"\\src\\test\\java\\configuration\\TCases.txt");
		    	ReadJson.ReadTxtFile(fileName);
			    data = new Object[TestNamelist.size()][3];
				
			    for (int datarow =0; datarow <=(TestNamelist.size() -1);datarow++) {
			    	data[datarow][0]=TestNamelist.get(datarow);
			    	data[datarow][1]=Testdesclist.get(datarow);
			    	data[datarow][2] = TestbrowList.get(datarow);
			    }
			    
			    // To clear all array list after reading data object from arraylist
			    ReadJson.Cleararraylists();

			}
			catch(Throwable t) {
				System.out.println(t.getMessage());
			}
			
			return data;
}

}
