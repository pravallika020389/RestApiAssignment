package com.test.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.test.constants.SourcePath;


public class PropertiesUtility {
	private FileInputStream stream = null;
	private Properties propFile = null;
	
	public Properties loadFile(String filename) {
		String propertyFilePath=null;
		 propFile=new Properties();
	
		propertyFilePath=SourcePath.APPLICATION_PROPERTIES;
		
		try {
			stream =new FileInputStream(propertyFilePath);
			propFile.load(stream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return propFile;
	}
public String getPropertyValue(String key){
	
		String value = propFile.getProperty(key);
	
		return value;
		
	}
	
public void writeDataToPropertyFile(File path,String key,String value){
		
		
		Properties propFile=new Properties();
		propFile.setProperty(key, value);
		try {
			propFile.store(new FileOutputStream(path),"updating data");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
