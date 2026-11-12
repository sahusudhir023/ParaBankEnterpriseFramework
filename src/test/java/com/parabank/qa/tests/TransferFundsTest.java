package com.parabank.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.parabank.qa.base.BaseTest;
import com.parabank.qa.dataprovider.TestDataProvider;
import com.parabank.qa.pages.LoginPage;
import com.parabank.qa.pages.TransferFundsPage;
import com.parabank.qa.utils.Log;

public class TransferFundsTest extends BaseTest {
	
	private LoginPage loginPage;
	private TransferFundsPage transferFundsPage;
	
	@BeforeMethod
	public void pageSetup() {
		Log.info("Initializing pages and performing pre-requisite login for TransferFundsTest.");
		loginPage = new LoginPage(BaseTest.getDriver());
		transferFundsPage = new TransferFundsPage(BaseTest.getDriver());
		
		// 1. Pre-requisite: Login with valid credential
		loginPage.doLogin("john", "demo");
		Log.info("Pre-test login successful for TransferFundsTest.");
	}
	
	@Test(priority = 1, dataProvider = "transferFundsTestData", dataProviderClass = TestDataProvider.class, 
			description = "TC_TRF_001: Transfer funds between internal accounts using Excel data")
	public void testFundTransfer(String amount, String fromAccountStr, String toAccountStr) throws InterruptedException {
		
		// Convert to int from String
		int fromAccount = Integer.parseInt(fromAccountStr);
		int toAccount = Integer.parseInt(toAccountStr);
		
		Log.info("\n----- Starting Data-Driven Test: testFundTransfer -----");
		Log.info("Fund Transfer Parameters -> Amount: " + amount + " | From Account: " + fromAccount + " | To Account: " + toAccount);
		
		// 2. Click transfer funds link
		Log.info("Clicking on 'Transfer Funds' link.");
		transferFundsPage.clickTransferFundsLink();
		
		// 3. Short wait for AJAX account dropdown to populate.
		Log.info("Waiting briefly for AJAX account dropdowns to load...");
		Thread.sleep(1500);
		
		// 4. Enter amount and submit transfer
		Log.info("Selecting source account: " + fromAccount);
		transferFundsPage.selectFromAccount(fromAccount);
		
		Log.info("Selecting target account: " + toAccount);
		transferFundsPage.selectToAccount(toAccount);
		
		Log.info("Entering transfer amount: " + amount);
		transferFundsPage.enterTransferAmount(amount);
		
		Log.info("Clicking transfer button to submit transaction.");
		transferFundsPage.clickTransferButton();
		
		// 5. Verify Success Message
		String successMsg = transferFundsPage.getTransferSuccessMessage();
		Log.info("Captured Transfer Success Message from UI: " + successMsg);
		
		boolean isTransferComplete = successMsg != null && successMsg.toLowerCase().contains("transfer complete");
		
		if (isTransferComplete) {
			Log.info("Fund transfer completed successfully from account " + fromAccount + " to " + toAccount);
		} else {
			Log.error("Fund transfer failed or success message not displayed! Received message: " + successMsg);
		}
		
		Assert.assertTrue(isTransferComplete, "Fund transfer failed or success message not displayed!");
		
		Log.info("----- Test Passed: testFundTransfer -----");
	}

}