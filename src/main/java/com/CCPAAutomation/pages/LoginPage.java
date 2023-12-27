package com.CCPAAutomation.pages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Browseractions{
   private WebDriver driver;   
   HashMap<String,String> hmap;
   public LoginPage(WebDriver driver,HashMap<String,String> testdata) {
	   this.driver=driver;
	   this.hmap=testdata;
	   PageFactory.initElements(driver, this);
   }
}
