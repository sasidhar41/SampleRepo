package com.CCPAAutomation.pages;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.openqa.selenium.TakesScreenshot;

import com.CCPAAutomation.utilities.CommonUtils;
import com.google.common.base.Function;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browseractions {
	private WebDriver driver; 
	String vpdurl = CommonUtils.config.getProperty("vpdurl");
	static Logger log = Logger.getLogger(Browseractions.class);
	public static final org.slf4j.Logger logger = LoggerFactory.getLogger(Browseractions.class);
	public  WebDriver getBrowser(HashMap<String,String> Testdata) {
		 driver = null;
		 try {
			 System.out.println(Testdata);
			 if(Testdata.get("Browser").equalsIgnoreCase("chrome")) {
				 ChromeOptions options = new ChromeOptions();
				 DesiredCapabilities caps = new DesiredCapabilities();
				 LoggingPreferences logPrefs = new LoggingPreferences();
				 logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
				 caps.setCapability("goog:loggingPrefs", logPrefs);
				 caps.setCapability(ChromeOptions.CAPABILITY, options);
				 System.out.println("userName : "+System.getenv("userName"));
				 WebDriverManager.chromedriver().setup();
				// System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver.exe");
				 driver = new ChromeDriver();							 
			 }else if(Testdata.get("Browser").equalsIgnoreCase("firefox")) {
				 
			 }else {
				 
			 }
		 }catch(Exception e) {
			 Assert.fail("Test Fail-Browser launch failed: "+e);
			 driver=null;
		 }
		 driver.manage().window().maximize();
		 driver.manage().deleteAllCookies();
		 driver.get(vpdurl);
		 //driver.navigate().refresh();
		 return driver;
	}
	public WebElement ClickElement(WebDriver driver,WebElement element,String successmsg,String errormsg) {
	     WebElement web= null;
	     boolean found = false;
	     final long startTime= System.currentTimeMillis();
	     try {
	    	 scrollToElement(driver,element);
	    	 element.click();	    	
	    	 found=true;
	     }catch(NoSuchElementException e1) {
	    	// logger.error(errormsg+" Exception: Application Error:--->No Such Element Exception"); 
	    	 Assert.fail(errormsg+" Failed with Exception:----> No Such Element Exception");
	     }catch(ElementNotVisibleException e2) {
	    	 StringWriter sw = new StringWriter();
	    	 PrintWriter pw = new PrintWriter(sw);
	    	 e2.printStackTrace(pw);
	    	 //logger.info(sw.toString());
	    	 //logger.error(errormsg+" Exception: Application Error:--->Element Not Visible Exception"); 
	    	 Assert.fail(errormsg+" Failed with Exception:----> Element Not Visible Exception");
	     }catch(StaleElementReferenceException e3) {
	    	 //logger.error(errormsg+" Exception: Application Error:--->Stale Element Reference Exception"); 
	    	 Assert.fail(errormsg+" Failed with Exception:----> Stale Element Reference Exception");
	     }catch(TimeoutException e4) {
	    	 StringWriter sw = new StringWriter();
	    	 PrintWriter pw = new PrintWriter(sw);
	    	 e4.printStackTrace(pw);
	    	 //logger.info(sw.toString());
	    	// logger.error(errormsg+" Exception: Application Error:--->Time out Exception"); 
	    	 Assert.fail(errormsg+" Failed with Exception:----> Time out Exception");
	     }
	     long endTime = System.currentTimeMillis();
	     long totalTime = endTime-startTime;
	     if(found) {
	    	 //logger.info(successmsg+"========> Element Found after waiting for "+totalTime+" milliseconds.");
	     }else{
	    	 //logger.info("Failed to find element after "+totalTime+" milliseconds.");
	     }
	     return web;
	}	
	public WebElement scrollToElement(WebDriver driver,WebElement element) {
		WebElement web=null;
		Actions act = new Actions(driver);
		try {
			act.moveToElement(element);	
	}catch(Exception e) {
		//logger.info(" Exception: Unable to scroll to element"+e); 
		Assert.fail("Failed with Exception:---->"+e);
	}
	return web;
}
	public WebElement SetElement(WebDriver driver,WebElement element,String text,String successmsg,String errormsg) {
		WebElement web= null;
		boolean found = false;
		final long startTime= System.currentTimeMillis();
		try {
			scrollToElement(driver,element);
	    	 element.click();
	    	 element.clear();
	    	 element.sendKeys(text);
	    	// logger.info(successmsg);
	    	 found=true;
		}catch(NoSuchElementException e1) {
	    	 //logger.error(errormsg+" Exception: Application Error:--->No Such Element Exception"); 
	    	 Assert.fail(errormsg+" Failed with Exception:----> No Such Element Exception");
	     }catch(ElementNotVisibleException e2) {
	    	 StringWriter sw = new StringWriter();
	    	 PrintWriter pw = new PrintWriter(sw);
	    	 e2.printStackTrace(pw);
	    	 //logger.info(sw.toString());
	    	 //logger.error(errormsg+" Exception: Application Error:--->Element Not Visible Exception"); 
	    	 Assert.fail(errormsg+" Failed with Exception:----> Element Not Visible Exception");
	     }catch(StaleElementReferenceException e3) {
	    	 //logger.error(errormsg+" Exception: Application Error:--->Stale Element Reference Exception"); 
	    	 Assert.fail(errormsg+" Failed with Exception:----> Stale Element Reference Exception");
	     }catch(TimeoutException e4) {
	    	 StringWriter sw = new StringWriter();
	    	 PrintWriter pw = new PrintWriter(sw);
	    	 e4.printStackTrace(pw);
	    	 //logger.info(sw.toString());
	    	 //logger.error(errormsg+" Exception: Application Error:--->Time out Exception"); 
	    	 Assert.fail(errormsg+" Failed with Exception:----> Time out Exception");
	     }
		long endTime = System.currentTimeMillis();
	     long totalTime = endTime-startTime;
	     if(found) {
	    	 //logger.info("Element Found after waiting for "+totalTime+" milliseconds.");
	     }else {
	    	 //logger.info("Failed to find element after "+totalTime+" milliseconds.");
	     }
	     return web;
	}
	
	public String getText(WebDriver driver,WebElement element) {
		String text="";
		try {
			text = element.getText();
		}catch(Exception e) {
			//logger.error(" Exception: Failed to get Text from element"); 
	    	 Assert.fail(" Failed with Exception:----> "+e);
		}
		return text;
	}
	public void WaitForElementToBeDisplayed(WebDriver driver,final WebElement element,Integer maxTimeOutInSeconds) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.withTimeout(maxTimeOutInSeconds, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS)
		    .ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class);
		wait.until(new Function<WebDriver,WebElement>(){
			public WebElement apply(WebDriver driver) {
				if(element.isDisplayed()) {
					return element;
				}else {
					return null;
				}
			}
		});
	}
	
	public void CaptureScreenshot(WebDriver driver,String path) {
		try {
			String filepath = System.getProperty("user.dir")+"/Screenshots/"+path+".png";
			File scrfile = (((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE));
			FileUtils.copyFile(scrfile, new File(filepath));
		}catch(Exception e) {
			Assert.fail(e.getMessage());
		}
	}
}