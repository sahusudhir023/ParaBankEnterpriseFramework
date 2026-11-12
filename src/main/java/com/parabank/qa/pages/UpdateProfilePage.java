package com.parabank.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.parabank.qa.base.BasePage;

public class UpdateProfilePage extends BasePage {
	
	//.Locator for Update Contact info
	private By updateContactInfoLink = By.xpath("//a[text()='Update Contact Info']");
	private By firstNameField = By.id("customer.firstName");
	private By lastNameField = By.id("customer.lastName");
	private By addressField = By.id("customer.address.street");
	private By cityField = By.id("customer.address.city");
	private By stateField = By.id("customer.address.state");
	private By zipCodeField = By.id("customer.address.zipCode");
	private By phoneField = By.id("customer.phoneNumber");
	private By updateProfileBtn = By.xpath("//input[@value='Update Profile']");
	private By successMessage = By.xpath("//div[@id='updateProfileResult']/h1");
	
	public UpdateProfilePage(WebDriver driver) {
		super(driver);
	}
	
	//.Page Actions
	public void clickUpdateContactInfoLink() {
		doClick(updateContactInfoLink);
	}
	
	public void updateDetails(String firstName, String lastName, 
			String address, String city, String state, String zip, String phone) {
		
		doSendKeys(firstNameField, firstName);
		doSendKeys(lastNameField, lastName);
		doSendKeys(addressField, address);
		doSendKeys(cityField, city);
		doSendKeys(stateField, state);
		doSendKeys(zipCodeField, zip);
		doSendKeys(phoneField, phone);		
	}
	
	public void clickUpdateProfileButton() {
		doClick(updateProfileBtn);
	}
	
	public String getUpdateSuccessMessage() {
		return doGetElementText(successMessage);
	}

}
