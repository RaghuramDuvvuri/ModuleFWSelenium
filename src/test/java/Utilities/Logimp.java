package Utilities;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import mainPack.DriverApp;

public class Logimp {
	
	public static Logger log = Logger.getLogger(DriverApp.class);
	
	
	public void logmessagetofile(String message) {
		
		BasicConfigurator.configure();
		
		log.info(message);
	}
public void logmessagetodebug(String message) {
		
		BasicConfigurator.configure();
		
		log.debug(message);
	}
}
