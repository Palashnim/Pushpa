package org.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class ReadProp {
	public static String getProp(String key) {
		
		try {
			FileInputStream fis = new FileInputStream("Data/Input.properties");
			Properties prop = new Properties();
			prop.load(fis);
			return prop.getProperty(key);
		} catch (IOException e) {
		}
		return null;
	}
	public static FileOutputStream fis;
	public static Properties prop = new Properties();
//	public static HashMap<String,String> data = new HashMap<String,String>();
public static void setProp(String key, String value, String movie) {	
		try {
			fis = new FileOutputStream("Data/Output.properties");
			prop.setProperty(key, value);
			prop.store(fis, movie);
			fis.close();
		} catch (IOException e) {
		}
	}
}
