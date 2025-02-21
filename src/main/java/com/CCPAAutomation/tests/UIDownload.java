package com.CCPAAutomation.tests;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.CCPAAutomation.pages.Browseractions;
import com.CCPAAutomation.pages.LoginPage;
import com.CCPAAutomation.pages.VpdDownloadRequestpage;
import com.CCPAAutomation.utilities.ReadExcelData;

public class UIDownload extends Browseractions implements ITest{
	HashMap<String,String> dataMap;
	WebDriver driver;
    LoginPage login;
    VpdDownloadRequestpage downloadPage;
    @Factory(dataProviderClass=ReadExcelData.class,dataProvider="testData")
	public UIDownload(HashMap<String,String> dataMap) {
		this.dataMap=dataMap;
	}       
    
    @Test
    public void VPDDownload() { 	
    
    	driver = getBrowser(dataMap);
    	login = new LoginPage(driver,dataMap);
    	downloadPage = new VpdDownloadRequestpage(driver,dataMap);    	
    	try {
    		String ret ="Test Fail-Error Page Displayed";
    		if(dataMap.get("TransactionType").equalsIgnoreCase("GuestDownload")) {
        		ret = downloadPage.GuestDownloadRequest();
        	}
    		if(ret.contains("Test Fail")) {
    			//logger.error("Test Fail: "+ret);
    			Assert.fail("Test Fail: "+ret);
    		}
    		else {
    			Assert.assertTrue(true, "Test Pass- "+dataMap.get("TestCaseDescription"));
    		}
    	}
    	catch(Exception e) {
    		//logger.info("Test Fail: "+e.getMessage());
    		Assert.fail("Test Fail: "+e.toString());
    	}
    	finally {
    		CaptureScreenshot(driver,dataMap.get("TestCaseDescription"));
    		driver.quit();
    	}
    	
    }
    
    
    public String getTestName() {
    	return dataMap.get("TestCaseDescription");
    }
}
