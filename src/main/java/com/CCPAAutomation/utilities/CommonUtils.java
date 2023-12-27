package com.CCPAAutomation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.Assert;

public class CommonUtils {
	static String filepath = System.getProperty("user.dir")+"/src/main/resources/Properties/constants.properties";
	public static Properties config = new Properties();
	static {
		try {
			InputStream fis = new FileInputStream(new File(filepath));
			config.load(fis);
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

}
