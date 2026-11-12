package com.parabank.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.parabank.qa.base.BaseTest;
import com.parabank.qa.pages.LoginPage;
import com.parabank.qa.pages.RequestLoanPage;
import com.parabank.qa.utils.Log;

public class RequestLoanTest extends BaseTest {
	
	private LoginPage loginPage;
	private RequestLoanPage requestLoanPage;
	
	@BeforeMethod
	public void pageSetup() {
		Log.info("Initializing pages and performing pre-requisite login for RequestLoanTest.");
		loginPage = new LoginPage(BaseTest.getDriver());
		requestLoanPage = new RequestLoanPage(BaseTest.getDriver());
		
		loginPage.doLogin("john", "demo");
		Log.info("Pre-test login successful for RequestLoanTest.");
	}
	
	@Test(priority = 1, description = "TC_LON_001: Request loan successfully and verify approval status")
	public void testRequestLoan() throws InterruptedException {
		Log.info("\n----- Starting Test: testRequestLoan -----");
		
		Log.info("Clicking on 'Request Loan' link.");
		requestLoanPage.clickRequestLoanLink();
		
		Log.info("Waiting briefly for loan application form to load...");
		Thread.sleep(1500);
		
		Log.info("Entering loan details - Amount: 100, Down Payment: 10");
		requestLoanPage.enterLoanDetails("100", "10");
		
		Log.info("Clicking 'Apply Now' button for loan request.");
		requestLoanPage.clickApplyNowButton();
		
		Log.info("Waiting for loan request response from server...");
		Thread.sleep(2500);
		
		String loanStatus = requestLoanPage.getLoanStatusMessage();
		Log.info("Captured Loan Application Status message from UI: " + loanStatus);
		
		boolean isApproved = loanStatus != null && loanStatus.toLowerCase().contains("approved");
		if (isApproved) {
			Log.info("Loan application successfully approved.");
		} else {
			Log.error("Loan request was not approved or failed! Status received: " + loanStatus);
		}
		
		Assert.assertTrue(isApproved, "Loan request was not approved or failed!");
		
		String newLoanAccId = requestLoanPage.getNewLoanAccountNumber();
		Log.info("Generated New Loan Account ID: " + newLoanAccId);
		
		Assert.assertFalse(newLoanAccId.isEmpty(), "New Loan Account ID is empty!");
		Log.info("New Loan Account ID successfully verified as non-empty.");
		
		Log.info("----- Test Passed: testRequestLoan -----");
	}

}