package com.parabank.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.parabank.qa.base.BaseTest;
import com.parabank.qa.dataprovider.TestDataProvider;
import com.parabank.qa.pages.AccountsOverviewPage;
import com.parabank.qa.pages.LoginPage;
import com.parabank.qa.utils.ConfigReader;
import com.parabank.qa.utils.Log;

public class LoginTest extends BaseTest {

	private LoginPage loginPage;
	private AccountsOverviewPage accountsOverviewPage;

	@BeforeMethod
	public void pageSetup() {
		Log.info("Initializing LoginPage object for test setup.");
		loginPage = new LoginPage(BaseTest.getDriver());
	}

	@Test(priority = 1, description = "TC_LOG_001: Verify login page title")
	public void testLoginPagetitle() {
		Log.info("\n ----- Starting Test: testLoginPagetitle -----");

		String actualTitle = loginPage.getLoginPageTitle();
		Log.info("Fetched actual Login Page Title: " + actualTitle);

		Assert.assertEquals(actualTitle, "ParaBank | Welcome | Online Banking", "Login page title mismatch!");
		Log.info("----- Test Passed: testLoginPagetitle -----");
	}

	@Test(priority = 2, description = "TC_LOG_002: Verify valid login funtionality from Config")
	public void testValidLogin() {
		Log.info("\n ----- Starting Test: testValidLogin -----");

		String validUsername = ConfigReader.getProperty("username");
		String validPassword = ConfigReader.getProperty("password");
		Log.info("Retrieved valid credentials from config properties for user: " + validUsername);

		// 1. Login Performance with Page Chaining
		Log.info("Performing login actions...");
		accountsOverviewPage = loginPage.doLogin(validUsername, validPassword);

		// 2. Verification 1: Check if logout link exists
		boolean isLogOutPresent = accountsOverviewPage.isLogOutLinkExist();
		Assert.assertTrue(isLogOutPresent, "Login Failed: Logout link is not displayed!");
		Log.info("Logout link successfully verified after login.");

		// 3. Verification 2: Total Balance fetch and validate
		String totalBalance = accountsOverviewPage.getTotalBalanceAmount();
		Log.info("Accounts Overview Total Balance fetched: " + totalBalance);

		// Assert: ensure balance is not empty and contains currency symbol ($)
		Assert.assertNotNull(totalBalance, "Total balance amount is null!");
		Assert.assertTrue(totalBalance.contains("$"), "Balance amount format is incorrect: " + totalBalance);
		Log.info("Total balance successfully validated with currency symbol ($).");

		// 4. Clean up: Next Test to logout
		Log.info("Performing logout to clean up session.");
		loginPage = accountsOverviewPage.clickLogOutLink();

		Log.info("----- Test Passed: testValidLogin -----");
	}

	@Test(priority = 3, dataProvider = "loginTestData", dataProviderClass = TestDataProvider.class, description = "TC_LOG_003: Verify login with multiple test data sets from Excel")
	public void testLoginwithExcelData(String username, String password) {
		Log.info("\n ----- Starting Data-Driven Login Test for Username: " + username + " -----");

		accountsOverviewPage = loginPage.doLogin(username, password);

		boolean isLogOutPresent = accountsOverviewPage.isLogOutLinkExist();
		Assert.assertTrue(isLogOutPresent, "Login failed for username: " + username);
		Log.info("Login verified successfully for data-driven user: " + username);

		Log.info("Logging out current session.");
		loginPage = accountsOverviewPage.clickLogOutLink();

		Log.info("----- Data-Driven Test Completed for: " + username + " -----");
	}

	@Test(priority = 4, description = "TC_LOG_004: Verify login with invalid password")
	public void testInvalidPasswordLogin() {
		Log.info("\n ----- Starting Test: testInvalidPasswordLogin -----");

		Log.info("Attempting login with invalid credentials.");
		loginPage.doLogin("john", "wrongpass123");

		String errorMsg = loginPage.getErrorMessageText();
		Log.info("Captured error message from UI: " + errorMsg);

		Assert.assertTrue(errorMsg.contains("internal error") || errorMsg.contains("could not be verified"),
				"Expected error message was not displayed for invalid login.");

		Log.info("----- Test Passed: testInvalidPasswordLogin -----");
	}
}