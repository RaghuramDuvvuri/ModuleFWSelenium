package mainPack;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;



import org.openqa.selenium.By;


import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.Logimp;



import TestReports.TestReports;

import Utilities.ErrorCollectors;

import Utilities.TestUtil;






public class KeywordsApp extends DriverApp {
	
	   public static Random randomGenerator = new Random();
	   public static Calendar cal = new GregorianCalendar();  //used for adding current date in variable and then used in paths
	   public static int date = cal.get(Calendar.DATE);  //used for adding current date in variable and then used in paths
	   public static int month = cal.get(Calendar.MONTH);  //used for adding current date in variable and then used in paths
	   public static int year = cal.get(Calendar.YEAR);  //used for adding current date in variable and then used in paths
	   public static int sec =cal.get(Calendar.SECOND);  //used for adding current date in variable and then used in paths
	   public static int day =cal.get(Calendar.HOUR_OF_DAY);  //used for adding current date in variable and then used in paths
	   public static int hour=cal.get(Calendar.HOUR);  //used for adding current date in variable and then used in paths
	   public static int min=cal.get(Calendar.MINUTE);  //used for adding current date in variable and then used in paths
	   public static String sMin = new Integer(randomGenerator.nextInt(60)).toString(); //Converted Integer value to String and then used in paths
	   public static String sSec=new Integer(randomGenerator.nextInt(60)).toString(); //Converted Integer value to String and then used in paths
	   public static String sHour=new Integer(randomGenerator.nextInt(24)).toString();  //Converted Integer value to String and then used in paths
	   public static String sDate=new Integer(date).toString();  //Converted Integer value to String and then used in paths
	   /*public static String sMin = new Integer(min).toString(); //Converted Integer value to String and then used in paths
	   public static String sSec=new Integer(sec).toString(); //Converted Integer value to String and then used in paths
	   public static String sHour=new Integer(hour).toString();  //Converted Integer value to String and then used in paths
	   */
	   
	   public static String call_id ; //Used in GetText() and DBQuerycheck() to store the call id to be used for Eval UI
	   public static String sUser=null;
	   public static String sUser_Name;

	   public static float round;
	   public static float round1;
	   public static Logimp log1 = new Logimp();
	   

	   public static String script_error=null;
	   public static int globalwait;
	   
	//Navigate to the current URL
	   
	   
	   
	public static String navigate() throws Throwable{
		log1.logmessagetodebug("Executing Navigate");
		try{
			driver.get(object);
			//WebDriverWait wait = new WebDriverWait(driver, 5000); 
		    //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(object)));
		    
		}catch(Throwable t){
			
			// Report error in Application logs
			log1.logmessagetodebug("Error while navigating -"+ object + t.getMessage());
					   
		}
		return "Pass";
		
	}
	
	//Clicking on a link or an Object
	
	public static String clickLink(){
		log1.logmessagetodebug("Executing clickLink");
		try{
		//	selenium.isElementPresent(Objects.getProperty(object));
		//	selenium.click(Objects.getProperty(object));
		driver.findElement(By.xpath(object)).click();
		}catch(Throwable t){
			
			// Report error in Application logs
			log1.logmessagetodebug("Error while clicking on an Object -"+ object + t.getMessage());
			script_error = "Timed out after "+globalwait+" miliseconds";
			
		   return "Fail - Link Not Found";
		}
		
		return "Pass";
	}
	
	//Input data Keyword
	
		public static String input() throws Exception{
			
			log1.logmessagetodebug("Executing input Keyword");
			// extract the test data
			String message = "pass";
			//String data =testData.getCellData(currentTest, data_column_name , testRepeat);
			
			
			try{
					System.out.println("Input keyword data :"+TestData);
			   driver.findElement(By.xpath(object)).sendKeys(TestData);
				}catch(Exception t){
					// report error
				log1.logmessagetodebug("Error while wrinting into input -"+ object + t.getMessage());
					
				script_error = "Timed out after "+globalwait+" miliseconds";
				
				return "Fail - "+t.getMessage();
				//return "Fail - "+t.getMessage();
					
				}
				
			
				return "Pass";
				
				
		}
		
		
		
		
	public static String softAssertTrue() throws Exception{
			
			log1.logmessagetodebug("Executing input Keyword");
			// extract the test data
			String message = "pass";
			//String data =testData.getCellData(currentTest, data_column_name , testRepeat);
			
			
			try{
					System.out.println("Assert keyword data :"+TestData);
					System.out.println(driver.findElement(By.xpath(object)).getText());
					ErrorCollectors.verifyEquals(driver.findElement(By.xpath(object)).getText(), TestData);
					System.out.println("Data matches expected was : "+driver.findElement(By.xpath(object)).getText());
				}catch(Exception t){
					// report error
					System.out.println("Inside catch");
					log1.logmessagetodebug("Error while wrinting into input -"+ object + t.getMessage());
					
					script_error = "Timed out after "+globalwait+" miliseconds";
					
					return "Fail - "+t.getMessage();
								
				}
				
			
				return "Pass";
				
				
		}
		
	
		
		
		
		//Verifying text presence 
		
		
	           
		  public static String verifyText(){
		         log1.logmessagetodebug("Executing verifyText");
		         //String expected=APPTEXT.getProperty(object);
		         String expected=TestData;
		         String actual=driver.findElement(By.xpath(object)).getText();
		         log1.logmessagetodebug(expected);
		         log1.logmessagetodebug(actual);
		         try{
		             Assert.assertEquals(actual.trim() , expected.trim());
		         }catch(Throwable t){
		             // error
		             log1.logmessagetodebug("Error in text - "+object);
		             log1.logmessagetodebug("Actual - "+actual);
		             log1.logmessagetodebug("Expected -"+ expected);
		             return "Fail -"+ t.getMessage();
		            
		         }
		        
		         return "Pass";
		        
		     
	       
	        
	        
	        }
		
		//verifyTextOnThePage
		public void verifyTextOnThePage () throws InterruptedException{
			final WebDriverException exception =new WebDriverException();
			try{
			if(driver.findElement(By.xpath(object)).getText().contains(TestData)){
			System.out.println(TestData +" text is on this page");
			}
			else{
			System.out.println(TestData +" text is NOT on this page");
			throw new WebDriverException(exception.getMessage());
			}
			
			}
			catch (WebDriverException e) {
			throw new WebDriverException(e.getMessage());
			}
			
		} 
		
		
		
		public static String clickButton(){
			log1.logmessagetodebug("Executing clickButton Keyword");
			
			
			try{
				driver.findElement(By.xpath(object)).click();
				}catch(Throwable t){
					// report error
					log1.logmessagetodebug("Error while clicking on Button -"+ object + t.getMessage());
					return "Fail - "+t.getMessage();
				}
				
				return "Pass";
		}
		
		
		public static String select(){
			log1.logmessagetodebug("Executing select Keyword");
			// extract the test data
		//	String data =testData.getCellData(currentTest, data_column_name , testRepeat);
			
			
			
			try{
				driver.findElement(By.xpath(object)).sendKeys(TestData);
				}catch(Throwable t){
					// report error
					log1.logmessagetodebug("Error while Selecting from droplist -"+ object + t.getMessage());
					return "Fail - "+t.getMessage();
				}
				
				return "Pass";
		}
		
		
		
		
		
		
	
	
	/*
	
	//Clicking on a link or an Object which contains Pop up in same window
	    
    public static String clickLink_popUp() throws AddressException, MessagingException{
        
    	log1.logmessagetodebug("Executing clickLink");
          try{
         if(selenium.isElementPresent("//div[@class='gwt-PopupPanel']")){
                selenium.click("//a[@class='gwt-Anchor alertLink']");
                selenium.isElementPresent(Objects.getProperty(object));
                Thread.sleep(5000);
                selenium.click(Objects.getProperty(object));
          }else{
                selenium.isElementPresent(Objects.getProperty(object));
                selenium.click(Objects.getProperty(object));
          }
          }catch(Throwable t){
        	  
                // report error
                log1.logmessagetodebug("Error while clicking on an Object -"+ object + t.getMessage());
                script_error = "Timed out after "+globalwait+" miliseconds";
                return "Fail - Link Not Found";
          }
          
          return "Pass";
    }

	
	
	//Input data Keyword
	
	public static String input() throws Exception{
		
		log1.logmessagetodebug("Executing input Keyword");
		// extract the test data
		String message = "pass";
		String data =testData.getCellData(currentTest, data_column_name , testRepeat);
		
		
		try{
			selenium.isElementPresent(Objects.getProperty(object));
			selenium.type(Objects.getProperty(object),data);
			System.out.println("Input keyword data :"+data);
		   // driver.findElement(By.xpath(Objects.getProperty(object))).sendKeys(data);
			}catch(Exception t){
				// report error
			log1.logmessagetodebug("Error while wrinting into input -"+ object + t.getMessage());
				
			script_error = "Timed out after "+globalwait+" miliseconds";
			
			throw t;
			//return "Fail - "+t.getMessage();
				
			}
			
		
			return "Pass";
			
			
	}
	
	
	
	//Input data
	
		public static String input_number() throws Exception{
			
			log1.logmessagetodebug("Executing input Keyword");
			// extract the test data
			String message = "pass";
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
			
			
			try{
				selenium.isElementPresent(Objects.getProperty(object));
				selenium.type(Objects.getProperty(object),(data.replace(".", "")).replace("E9", ""));
			   
				}catch(Exception t){
					// report error
				log1.logmessagetodebug("Error while wrinting into input -"+ object + t.getMessage());
					
				script_error = "Timed out after "+globalwait+" miliseconds";
				
				throw t;
				//return "Fail - "+t.getMessage();
					
				}
				
			
				return "Pass";
				
				
		}
		
	
	
	
	//Implement Wait
	
	public static String waitfor() throws NumberFormatException, InterruptedException, AddressException, MessagingException{
	     log1.logmessagetodebug("Executing wait Keyword");
	  // extract the test data
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
			try{
				 
				float test = (Float.parseFloat(data));
				int test1 = (int) test;
				Thread.sleep(test1);
				globalwait = test1/1000;
			}catch(Throwable t){
				log1.logmessagetodebug("Error while waiting -"+ object + t.getMessage());
			    return "Fail - "+t.getMessage();
							}
	     return "Pass";
	}
	
	
    //Clicking on a Button
	
	public static String clickButton() throws AddressException, MessagingException{
		log1.logmessagetodebug("Executing clickButton Keyword");
		
		
		try{
			
			selenium.isElementPresent(Objects.getProperty(object));
			selenium.click(Objects.getProperty(object));
		
		}catch(Throwable t){
				// report error
				log1.logmessagetodebug("Error while clicking on Button -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	//Selecting text elements from Drop down list
	
	public static String select(){
		log1.logmessagetodebug("Executing select Keyword");
		
		// extract the test data
		String data =testData.getCellData(currentTest, data_column_name , testRepeat);
				
		try{
			
			selenium.isElementPresent(Objects.getProperty(object));
			selenium.select(Objects.getProperty(object),"label="+data);
			
		}catch(Throwable t){
				// report error
				log1.logmessagetodebug("Error while Selecting from droplist -"+ object + t.getMessage());
			
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	
	
	
	//Selecting numeric elements from Drop down list
	
		public static String select_number(){
			log1.logmessagetodebug("Executing select Keyword");
			
			// extract the test data
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
					
			try{
				
				
				selenium.isElementPresent(Objects.getProperty(object));
				selenium.select(Objects.getProperty(object),"label="+data.replaceAll("\\.0*$", ""));
				System.out.println("data printed as :"+data.replaceAll("\\.0*$", ""));
			}catch(Throwable t){
				
					// report error
					log1.logmessagetodebug("Error while Selecting from droplist -"+ object + t.getMessage());
				
					return "Fail - "+t.getMessage();
				}
				
				return "Pass";
		}
		
		
	
	
	
	
	
	public static String Refresh(){
		log1.logmessagetodebug("Executing select Keyword");
		// extract the test data
		
				
		try{
			
			selenium.refresh();
			Thread.sleep(10000);
			
		}catch(Throwable t){
			
				// report error
				log1.logmessagetodebug("Error while refreshing -"+ object + t.getMessage());
			
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	
	
	
	
	
	
	//Executing type keystrokes
	
	public static String typekeys() throws AddressException, MessagingException{
		log1.logmessagetodebug("Executing typekeys() Keyword");
		
		// extract the test data
		String data =testData.getCellData(currentTest, data_column_name , testRepeat);
				
		try{
		
		
			selenium.typeKeys(Objects.getProperty(object),data);
			
		}catch(Throwable t){
				// report error
				log1.logmessagetodebug("Error while typing data -"+ object + t.getMessage());
		
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	
	//Executing type keystrokes with current hour
	
	public static String typekeys_hrs() throws AddressException, MessagingException{
		
		log1.logmessagetodebug("Executing typekeys_hrs() Keyword");
		
		// extract the test data
		String data =testData.getCellData(currentTest, data_column_name , testRepeat);
				
		try{
				
			selenium.typeKeys(Objects.getProperty(object),sHour);
			
		}catch(Throwable t){
		
			// report error
				log1.logmessagetodebug("Error while typing data -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
				
			}
			
			return "Pass";
	}
	
	
	//Executing type keystrokes with current minute
	
	public static String typekeys_min() throws AddressException, MessagingException{
		
		log1.logmessagetodebug("Executing typekeys_min() Keyword");
		
				
		try{
				
			selenium.typeKeys(Objects.getProperty(object),sMin);
			
		}catch(Throwable t){
				// report error
				log1.logmessagetodebug("Error while typing data -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	//Executing type keystrokes with current second
	
	public static String typekeys_sec() throws AddressException, MessagingException{
		log1.logmessagetodebug("Executing typekeys_sec() Keyword");
				
		try{
			
			selenium.typeKeys(Objects.getProperty(object),sSec);
			
		}catch(Throwable t){
				// report error
				log1.logmessagetodebug("Error while typing data -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	
	
	
	//Getting text from an object and executing it based on the object
	
	public static String GetText() throws AddressException, MessagingException{
		log1.logmessagetodebug("Executing GetText Keyword");
				
		try{
			
			selenium.getText(Objects.getProperty(object));
			log1.logmessagetodebug("Got the text for:  "+object+"----"+ selenium.getText(Objects.getProperty(object)));
			
			if(object.equals("callid")){
				call_id=selenium.getText(Objects.getProperty(object));
				
				//setting the test data file to null
				DriverApp.testData=null; 
				datareader = new Xlfile_Reader("C:\\Selenium2.0\\app\\test\\Framework\\AutomationBvt_Hybrid\\src\\config\\TestData.xlsx");						
				
				//Adding generated call id in Evaluation and Audit
				
				datareader.setCellData("EvaluateaCall", "generated_call_id", 2, call_id);
				datareader.setCellData("Audit_call", "generated_call_id", 2, call_id);
				datareader.setCellData("Calibrations", "generated_call_id", 2, call_id);
				//Loading the test data file again
				DriverApp.testData  =  new Xlfile_Reader(System.getProperty("user.dir")+"\\src\\config\\TestData.xlsx");				 
				
								 
			   }else if(object.equals("evalscore")){
				String app_score=(selenium.getText(Objects.getProperty(object))).replaceAll("\\(.+\\)", "");
				System.out.println("App score is:"+app_score);
				float score= Float.parseFloat(app_score);
				round = TestUtil.Round(score,2);
				System.out.println("First round: "+round);
				
				datawriter = new Xlfile_Reader("C:\\Selenium2.0\\app\\test\\Framework\\AutomationBvt_Hybrid\\src\\config\\db_data.xlsx");						
				datawriter.setFloatCellData("Evaluation", "QAScore_Application", 2, round);
			 
				
		     		
				
				
				
                
          }

			
			
		}catch(Throwable t){
			
				// report error
				log1.logmessagetodebug("Error while fetching text -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	
	
	
	

	//Verifying text presence 
	
	public static String verifytext() throws AddressException, MessagingException{
		log1.logmessagetodebug("Executing verifytext() Keyword");
		
		try{
			
			//selenium.isTextPresent(Objects.getProperty(object));
			 
			
			if(object.equals("callid")){
			selenium.isTextPresent((call_id));
			System.out.println("Verifed Text as  :"+call_id);	
			}
			
			
		}catch(Throwable t){
				// report error
				log1.logmessagetodebug("Error while Verifying text presence -"+ object + t.getMessage());
			
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	

	// clicking on an Object that contains certain text
	
	public static String containsText_click(){
		log1.logmessagetodebug("Executing Dynamic element present Keyword");
		
		try{
					
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
			selenium.click("//div[contains(text(),'"+data+"')]");
			
			
		}catch(Throwable t){
				// report error
				log1.logmessagetodebug("Error while searching and clicking -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	

	// Forcefully clicking on an Object
	
	public static String clickat() throws AddressException, MessagingException{
		log1.logmessagetodebug("Executing Dynamic element present Keyword");
		
		try{
					
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
		//	selenium.click("//div[contains(text(),'"+data+"')]");
			selenium.clickAt(Objects.getProperty(object), data);
			
		}catch(Throwable t){
				// report error
				log1.logmessagetodebug("Error while searching and clicking -"+ object + t.getMessage());
				
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}

	
	
	
	
	
	public static String doubleclickat() throws AddressException, MessagingException{
		log1.logmessagetodebug("Executing Dynamic element present Keyword");
		
		try{
					
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
		
			selenium.doubleClickAt(Objects.getProperty(object), data);
		
		}catch(Throwable t){
				// report error
				log1.logmessagetodebug("Error while searching and clicking -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	
	
	// Clicking on a object by fireevent keyword
	
	public static String fireevent() throws AddressException, MessagingException{
		log1.logmessagetodebug("Executing Dynamic element present Keyword");
		
		try{
					
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
		
			selenium.fireEvent(Objects.getProperty(object),"click");
			
			
		}catch(Throwable t){
				// report error
				log1.logmessagetodebug("Error while clicking -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	
	// Clicking on a object by checkBox keyword
	
	public static String checkBox() throws AddressException, MessagingException{
		log1.logmessagetodebug("Executing Dynamic element present Keyword");
		
		try{
					
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
		
			selenium.check(Objects.getProperty(object));
			
		}catch(Throwable t){
				// report error
				log1.logmessagetodebug("Error while checking -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	
	
	
	// Clicking on a object by fireevent keyword by getting the data from the excel file
	
	public static String checkelementpresence() throws AddressException, MessagingException{
		log1.logmessagetodebug("Executing Dynamic element present Keyword");
		
		try{
					
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
		
			selenium.fireEvent("//table/tbody/tr/td[2]/a[contains(text(),'"+data+"')]", "click");
			
		}catch(Throwable t){
				// report error
				log1.logmessagetodebug("Error while clicking -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	// 	
	

	
*/

	
	
}
