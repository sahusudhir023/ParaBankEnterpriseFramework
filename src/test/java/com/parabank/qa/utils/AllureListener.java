package com.parabank.qa.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.parabank.qa.base.BaseTest;

import io.qameta.allure.Attachment;

public class AllureListener implements ITestListener{
	
	@Attachment(value = "Failure Screenshot", type = "image/png")
	public byte[] captureScreenshot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
	
	@Override
	public void onStart(ITestContext context) {
		System.out.println("===== Starting Test Suite: " + context.getName() + " =====");
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("===== Starting Test: " + result.getName() + " =====");
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("--> Test PASSED: " + result.getName());
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failedd: " + result.getName() + " | Caputring Screenshot...");
		
		Object testInstance = result.getInstance();
		
		if(testInstance instanceof BaseTest) {
			WebDriver driver = ((BaseTest) testInstance).getDriver();
			
			if(driver != null) {
				captureScreenshot(driver);
			}
		}
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("--> Test SKIPPED: " + result.getName());
	}
	
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("===== Finished Test Suite: " + context.getName() + " =====");
	}

}
