package com.CCPAAutomation.pages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VpdDownloadRequestpage extends Browseractions {
	@FindBy(xpath="//a[contains(text(),'Continue as Guest')]")
	WebElement guestlnk;
	
	@FindBy(xpath="//button[contains(text(),'Download personal information')]")
	WebElement downloadlnk;
    
	@FindBy(xpath="//a[contains(text(),'Continue')]")
	WebElement continuebtn;
	
	private WebDriver driver;
	HashMap<String,String> dataMap;
	
	public VpdDownloadRequestpage(WebDriver driver,HashMap<String,String> dataMap) {
		this.driver = driver;
		this.dataMap= dataMap;
		PageFactory.initElements(driver, this);
	}
	
	public String GuestDownloadRequest() {
		String ret = "";
		try {
			WaitForElementToBeDisplayed(driver,guestlnk,25);
			ClickElement(driver,guestlnk,"Able to click on Guest link","Unable to click on guest link");
			WaitForElementToBeDisplayed(driver, downloadlnk, 15);
			ClickElement(driver,downloadlnk,"Able to click on download link","Unable to click on download link");
			WaitForElementToBeDisplayed(driver,continuebtn,10);
			ClickElement(driver,continuebtn,"Able to click on continue","Unable to click on continue btn");
			ret="Test Pass-Download Request placed";
			
		}catch(Exception e) {
			ret="Test Fail-Guest Download failed "+e.getMessage();
		}
		return ret;
	}

	
	public static void getMethod() {
		System.out.println("print method");
	}
}
