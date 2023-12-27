package com.CCPAAutomation.utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Customlistener implements ITestListener {
	public ExtentHtmlReporter htmlreport;
	public ExtentReports report;
	public ExtentTest test;
    String filepath;
	public void onTestStart(ITestResult result) {
		report = new ExtentReports();
		report.attachReporter(htmlreport);
		report.setSystemInfo("HostName", "Localhost");
		report.setSystemInfo("Environment", "Preprod");
		report.setSystemInfo("OS", "Windows");
		report.setSystemInfo("Browser", "Chrome");
		
	}

	public void onTestSuccess(ITestResult result) {
		filepath=System.getProperty("user.dir")+"/Screenshots/"+result.getTestName()+".png";
		test = report.createTest(result.getTestName());
		System.out.println(result.getTestName());		
		test.log(Status.PASS, "Test PASSED:"+result.getName());
		try {			
			test.addScreenCaptureFromPath(filepath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void onTestFailure(ITestResult result) {
		filepath=System.getProperty("user.dir")+"/Screenshots/"+result.getTestName()+".png";
		test = report.createTest(result.getTestName());
		test.log(Status.FAIL, "Test Failed:"+result.getTestName());
		test.log(Status.FAIL, result.getThrowable());
		try {
			test.addScreenCaptureFromPath(filepath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = report.createTest(result.getTestName());
		test.log(Status.SKIP, "Test Failed:"+result.getTestName());		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		String timestamp = new SimpleDateFormat("ddMMYYYYhhmmss").format(new Date());
		htmlreport = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/CCPAReport"+timestamp+".html");
		htmlreport.config().setDocumentTitle("CCPAReport");
		htmlreport.config().setReportName("Automation Report");
		htmlreport.config().setTheme(Theme.DARK);	
		htmlreport.config().setEncoding("UTF-8");
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		report.flush();			
	}

}
