package com.parabank.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.parabank.qa.base.BaseTest;
import com.parabank.qa.pages.LoginPage;
import com.parabank.qa.pages.OpenAccountPage;
import com.parabank.qa.utils.Log;

public class AccountManagementTest extends BaseTest {

	private LoginPage loginPage;
	private OpenAccountPage openAccountPage;

	@BeforeMethod
	public void pageSetup() {
		Log.info("Initializing pages and performing pre-requisite login for AccountManagementTest.");
		loginPage = new LoginPage(BaseTest.getDriver());
		openAccountPage = new OpenAccountPage(BaseTest.getDriver());

		loginPage.doLogin("john", "demo");
		Log.info("Pre-test login successful with default user credentials.");
	}

	@Test(priority = 1, description = "TC_ACC_001: open new CHECKING account and verify generation")
	public void testOpenCheckingAccount() throws InterruptedException {
		Log.info("\n----- Starting Test: testOpenCheckingAccount -----");

		Log.info("Clicking on 'Open New Account' link.");
		openAccountPage.clickOpenNewAccountLink();

		Log.info("Waiting briefly for DOM to sync account options...");
		Thread.sleep(1000);

		Log.info("Selecting account type as CHECKING.");
		openAccountPage.selectAccountType("CHECKING");

		Log.info("Submitting account opening request.");
		openAccountPage.clickSubmitOpenAccount();

		String successMsg = openAccountPage.getSuccessMessageText();
		Log.info("Captured Account Opening success message from UI: " + successMsg);

		boolean isMsgValid = successMsg.contains("Account Opened");
		if (isMsgValid) {
			Log.info("Success message validated successfully.");
		} else {
			Log.error("Account opening success message mismatch! Captured text: " + successMsg);
		}

		Assert.assertTrue(isMsgValid, "New Checking account creation failed!");

		String newAccId = openAccountPage.getNewlyCreatedAccountNumber();
		Log.info("Generated New Checking Account ID: " + newAccId);

		Assert.assertNotNull(newAccId, "Generated Account ID is null");
		Log.info("New Account ID successfully verified as non-null.");

		Log.info("----- Test Passed: testOpenCheckingAccount -----");
	}

}