package com.parabank.qa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties prop;
	
	static {
		try {
			FileInputStream fis = new FileInputStream("./src/main/resources/config.properties");
			prop = new Properties();
			prop.load(fis);
		} catch (IOException e) {
			System.err.println("Configuration file not found at path: ./src/main/resources/config.properties" );
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		if(prop != null && prop.containsKey(key)) {
			return prop.getProperty(key).trim();
		}
		return null;
	}
	
	public static int getIntProperty(String key, int defaultValue) {
		String value = getProperty(key);
		
		if(value != null) {
			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException e) {
				System.err.println("Invalid integer format for key: " + key + ". Using default: " + defaultValue);
			}
		}
		return defaultValue;
	}

}
