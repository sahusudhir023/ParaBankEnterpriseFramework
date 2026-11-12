package com.parabank.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.parabank.qa.base.BaseTest;
import com.parabank.qa.pages.LoginPage;
import com.parabank.qa.pages.UpdateProfilePage;
import com.parabank.qa.utils.Log;

public class UpdateProfileTest extends BaseTest {
	
	private LoginPage loginPage;
	private UpdateProfilePage updateProfilePage;
	
	@BeforeMethod
	public void pageSetup() {
		Log.info("Initializing pages and performing pre-requisite login for UpdateProfileTest.");
		loginPage = new LoginPage(BaseTest.getDriver());
		updateProfilePage = new UpdateProfilePage(BaseTest.getDriver());
		
		// 1. Pre-requisite: Login with valid credentials
		loginPage.doLogin("john", "demo");
		Log.info("Pre-test login successful for UpdateProfileTest.");
	}
	
	@Test(priority = 1, description = "TC_PRF_001: Update user contact information successfully.")
	public void testUpdateProfile() throws InterruptedException {
		Log.info("\n----- Starting Test: testUpdateProfile -----");
		
		Log.info("Clicking on 'Update Contact Info' link.");
		updateProfilePage.clickUpdateContactInfoLink();
		
		Log.info("Waiting briefly for profile form fields to render...");
		Thread.sleep(1000);
		
		Log.info("Updating profile details with new address and contact information...");
		updateProfilePage.updateDetails("John", "Smith", "456 Tech Park", 
				"Hyderabad", "Telangana", "500081", "9988776655");
		
		Log.info("Clicking 'Update Profile' button.");
		updateProfilePage.clickUpdateProfileButton();
		
		String successMsg = updateProfilePage.getUpdateSuccessMessage();
		Log.info("Captured Profile Update Success Message from UI: " + successMsg);
		
		boolean isUpdated = successMsg != null && successMsg.toLowerCase().contains("profile updated");
		
		if (isUpdated) {
			Log.info("Profile details successfully updated and verified.");
		} else {
			Log.error("Profile update failed or expected message not displayed! Captured text: " + successMsg);
		}
		
		Assert.assertTrue(isUpdated, "Profile update failed or success message not displayed!");
		
		Log.info("----- Test Passed: testUpdateProfile -----");
	}

}