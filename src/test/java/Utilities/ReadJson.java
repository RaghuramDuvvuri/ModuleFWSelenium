package Utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import mainPack.DriverApp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;




public class ReadJson extends DriverApp{

	
	    @SuppressWarnings("unchecked")
	    public static void jsonReadTC() throws ParseException, Exception
	    {
	        //JSON parser object to parse read file
	        JSONParser jsonParser = new JSONParser();
	         
	        try (FileReader reader = new FileReader("D:\\pom\\TestCaseExecute.json"))
	        {
	            //Read JSON file
	            Object obj = jsonParser.parse(reader);
	 
	            JSONArray TCList = (JSONArray) obj;
	            System.out.println(TCList);

	            
	            //Iterate over employee array
	           TCList.forEach( TC -> parseTCObject( (JSONObject) TC ) );
	 
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	    private static void parseTCObject(JSONObject TC) 
	    {
	        //Get employee object within list
	        JSONObject TCObject = (JSONObject) TC.get("TC");
	       
	        // Check runmode is Y if ye add to lists
	        String runmode = (String) TCObject.get("RunMode");
	        if (runmode.contains("Y")) {
	        	//Get employee Test Case name
	        	String TestCaseName = (String) TCObject.get("TCID");    
	        	TestNamelist.add(TestCaseName);
	         
	        	//Get Description of Test Case
	        	String Description = (String) TCObject.get("Description");  
	        	Testdesclist.add(Description);
	         
	        	//Get Browser Name
	        	String browser = (String) TCObject.get("Browser");    
	        	TestbrowList.add(browser);
	        }  // if run mode is yes
	      
	    }
	    
	    
	    public static void ReadTxtFile(String fileName) throws IOException {
	    	
	    	File f = new File(fileName);
	    	FileReader fr = new FileReader(f);
	    	BufferedReader br = new BufferedReader(fr);
	    	String line = br.readLine();
	    	while (line != null) {
	    		String[] tcDetails = line.split(",");
	    		if ((tcDetails[3]) == "Y") {
	    			TestNamelist.add(tcDetails[0]);
	    			Testdesclist.add(tcDetails[1]);
	    			TestbrowList.add(tcDetails[2]);
	    		}
	    		line = br.readLine();
	    		
	    	}
	    	
	    }
	    
	    public static void Cleararraylists() {
	    	 TestNamelist = null;
	    	 Testdesclist = null;
	    	 TestbrowList = null;
	    }
	}		

		



