package com.parabank.qa.tests;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.parabank.qa.base.BaseTest;
import com.parabank.qa.dataprovider.TestDataProvider;
import com.parabank.qa.pages.LoginPage;
import com.parabank.qa.pages.RegisterPage;
import com.parabank.qa.utils.Log;

public class RegisterTest extends BaseTest {
	
	private LoginPage loginPage;
	private RegisterPage registerPage;
	
	@BeforeMethod
	public void pageSetup() {
		Log.info("Initializing LoginPage and navigating to Register Page for test setup.");
		loginPage = new LoginPage(BaseTest.getDriver());
		registerPage = loginPage.clickRegisterLink();
	}
	
	@Test(priority = 1, dataProvider = "registerTestData", dataProviderClass = TestDataProvider.class,
			description = "TC_REG_001: Verify user registration with multiple data sets from Excel")
	public void testUserRegistration(String firstName, String lastName, String address, String city, 
			String state, String zipCode, String phone, String ssn, String username, String password, String confirmPassword) throws InterruptedException {
		
		Log.info("\n----- Starting Data-Driven Test: testUserRegistration for: " + firstName + " " + lastName + " -----");
		
		// FIX: Unique UUID + Timestamp combination taaki parallel run mein bhi kabhi duplicate na ho
		// Strong & 100% Unique Dynamic Username generation
		String uniqueID = UUID.randomUUID().toString().substring(0, 4);
		String dynamicUsername = firstName.trim().toLowerCase() + "_" + uniqueID + "_" + System.nanoTime();
		
		Log.info("Generated unique dynamic username for registration: " + dynamicUsername);
		
		Log.info("Filling registration form with user details...");
		registerPage.registerNewUser(firstName, lastName, address, city, state, zipCode, phone, 
				ssn, dynamicUsername, password, confirmPassword);
		
		Log.info("Clicking on Register button to submit form.");
		registerPage.clickRegisterButton();
		
		Log.info("Waiting for registration response to process on server...");
		Thread.sleep(2000);
		
		String successMsg = registerPage.getSuccessMessageText();
		Log.info("Captured Registration Success Message from UI: " + successMsg);
		
		boolean isRegistered = successMsg.toLowerCase().contains("created successfully") 
				|| successMsg.toLowerCase().contains("welcome")
				|| BaseTest.getDriver().getPageSource().contains("created successfuly");
		
		if (isRegistered) {
			Log.info("User Registration successful for dynamic username: " + dynamicUsername);
		} else {
			Log.error("User Registration failed for username: " + dynamicUsername + ". Captured message: " + successMsg);
		}
		
		Assert.assertTrue(isRegistered, "User Registration failed for username: " + dynamicUsername);
		
		Log.info("----- Test Passed: testUserRegistration for " + dynamicUsername + " -----");
	}

}