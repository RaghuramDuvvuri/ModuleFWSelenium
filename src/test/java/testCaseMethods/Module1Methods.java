package testCaseMethods;


import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.Test;

import TestReports.TestReports;
import Utilities.TestUtil;
import mainPack.DriverApp;
import mainPack.KeywordsApp;

public class Module1Methods extends DriverApp {

	
	
	@Test
	public static void LoginTest() throws NoSuchMethodException, SecurityException {
		try {
	
		currentTSID = "TS01";
		stepDescription = "Navigate to site";
		keyword = "navigate";
		FileInputStream f = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\configuration\\config.properties");
		Properties env = new Properties();
		
		FileInputStream fobj = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\configuration\\Object.properties");
		Properties objects = new Properties();
		env.load(f);
		object = env.getProperty("url");
		objects.load(fobj);
		System.out.println(keyword);
		Method method= KeywordsApp.class.getMethod(keyword);
		result = (String)method.invoke(method);
		TestUtil.UpdateResult(result);
		
		currentTSID = "TS02";
		stepDescription = "Enter the input string";
		keyword = "input";
		
		object = objects.getProperty("searchbox");
		System.out.println(keyword);
		method= KeywordsApp.class.getMethod(keyword);
		result = (String)method.invoke(method);
		TestUtil.UpdateResult(result);	
		
		currentTSID = "TS03";
		stepDescription = "Click the search string";
		keyword = "clickButton";
		object = objects.getProperty("submitbtn");
		System.out.println(keyword);
		method= KeywordsApp.class.getMethod(keyword);
		result = (String)method.invoke(method);
		TestUtil.UpdateResult(result);	
		
		} // try
		catch(Throwable t) {
		   System.out.println(t.getMessage());
		} // try catch loop
		
	} // login test
	
	
	@Test
	public static void SignTest() throws NoSuchMethodException, SecurityException {
		try {
	
		currentTSID = "TS01";
		stepDescription = "Navigate to site";
		keyword = "navigate";
		FileInputStream f = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\configuration\\config.properties");
		Properties env = new Properties();
		env.load(f);
		FileInputStream fobj = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\configuration\\Object.properties");
		Properties objects = new Properties();
		object = env.getProperty("url");
		
		System.out.println(keyword);
		Method method= KeywordsApp.class.getMethod(keyword);
		result = (String)method.invoke(method);
		TestUtil.UpdateResult(result);
		
		currentTSID = "TS02";
		stepDescription = "Enter the input string";
		keyword = "input";
		objects.load(fobj);
		object = objects.getProperty("searchbox");
		System.out.println(keyword);
		method= KeywordsApp.class.getMethod(keyword);
		result = (String)method.invoke(method);
		TestUtil.UpdateResult(result);	
		
		currentTSID = "TS03";
		stepDescription = "Click the search string";
		keyword = "clickButton";
		object = objects.getProperty("submitbtn");
		System.out.println(keyword);
		method= KeywordsApp.class.getMethod(keyword);
		result = (String)method.invoke(method);
		TestUtil.UpdateResult(result);	
		} // try
		catch(Throwable t) {
		   System.out.println(t.getMessage());
		} // try catch loop
		
	} // login test

} // class
