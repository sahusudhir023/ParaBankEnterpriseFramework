package com.parabank.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.parabank.qa.base.BaseTest;
import com.parabank.qa.dataprovider.TestDataProvider;
import com.parabank.qa.pages.BillPayPage;
import com.parabank.qa.pages.LoginPage;
import com.parabank.qa.utils.Log;

public class BillPayTest extends BaseTest {
	
	private LoginPage loginPage;
	private BillPayPage billPayPage;
	
	@BeforeMethod
	public void pageSetup() {
		Log.info("Initializing LoginPage and BillPayPage objects, and logging in with default user.");
		loginPage = new LoginPage(BaseTest.getDriver());
		billPayPage = new BillPayPage(BaseTest.getDriver());
		
		loginPage.doLogin("john", "demo");
		Log.info("Pre-test login successful for BillPayTest.");
	}
	
	@Test(priority = 1, dataProvider = "billPayTestData", dataProviderClass = TestDataProvider.class, 
			description = "TC_PAY_001: Successful Bill Payment with valid payee details")
	public void testSuccessfulBillPayment(String payeeName, String address, String city, String state,
			String zip, String phone, String accountNo, String amount, String fromAccountNo) throws InterruptedException {
		
		Log.info("\n----- Starting Data-Driven Test: testSuccessfulBillPayment for Payee: " + payeeName + " | Amount: " + amount + " -----");
		
		Log.info("Clicking on 'Bill Pay' link from navigation menu.");
		billPayPage.clickBillPayLink();
		
		Log.info("Waiting briefly for bill pay form to load...");
		Thread.sleep(1000);
		
		Log.info("Entering payee details and payment amount...");
		billPayPage.enterPayeeDetails(payeeName, address, city, state, zip, phone, accountNo, amount, fromAccountNo);
		
		Log.info("Clicking 'Send Payment' button.");
		billPayPage.clickSendPaymentButton();
		
		String successMsg = billPayPage.getBillPaySuccessMessage();
		Log.info("Captured Bill Pay Success Message from UI: " + successMsg);
		
		boolean isPaymentComplete = successMsg != null && successMsg.toLowerCase().contains("complete");
		
		if (isPaymentComplete) {
			Log.info("Bill payment completed successfully for payee: " + payeeName);
		} else {
			Log.error("Bill payment failed or unexpected message received for payee: " + payeeName + ". Message: " + successMsg);
		}
		
		Assert.assertTrue(isPaymentComplete, "Bill payment failed or success message not displayed!");
		
		Log.info("----- Test Completed Successfully: testSuccessfulBillPayment for " + payeeName + " -----");
	}

}