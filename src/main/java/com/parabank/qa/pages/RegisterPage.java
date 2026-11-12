package com.parabank.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.parabank.qa.base.BasePage;

public class RegisterPage extends BasePage {
	
	//1. Registration Locators
	private By firstNameInput = By.id("customer.firstName");
	private By lastNameInput = By.id("customer.lastName");
	private By streetInput = By.id("customer.address.street");
	private By cityInput = By.id("customer.address.city");
	private By stateInput = By.id("customer.address.state");
	private By zipCodeInput = By.id("customer.address.zipCode");
	private By phoneNumberInput = By.id("customer.phoneNumber");
	private By ssnInput = By.id("customer.ssn");
	private By usernameInput = By.id("customer.username");
	private By passwordInput = By.id("customer.password");
	private By confirmPasswordInput = By.id("repeatedPassword");
	private By registerBtn = By.xpath("//input[@value='Register']");
	
	// Success Heading Locator
	private By successHeading = By.xpath("//div[@id='rightPanel']/h1");
	private By successParagraph = By.xpath("//div[@id='rightPanel']/p");
    
	// Logout link
	private By logoutLink = By.xpath("//a[text()='Log Out']");
	
	//2. Constructor
	public RegisterPage(WebDriver driver) {
		super(driver);
	}
	
	
    //3. ==== PAGE ACTIONS ====
	
	//1. Full user registration form fill and sumbit
	public void registerNewUser(String fName, String lName, String street, String city, String state, 
			String zip, String phone, String ssn, String username, String password, String confirmPassword) {
		
		doSendKeys(firstNameInput, fName);
		doSendKeys(lastNameInput, lName);
		doSendKeys(streetInput, street);
		doSendKeys(cityInput, city);
		doSendKeys(stateInput, state);
		doSendKeys(zipCodeInput, zip);
		doSendKeys(phoneNumberInput, phone);
		doSendKeys(ssnInput, ssn);
		doSendKeys(usernameInput, username);
		doSendKeys(passwordInput, password);
		doSendKeys(confirmPasswordInput, confirmPassword);
	}
	
	//2. Regiter Button submit
	public void clickRegisterButton() {
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		doClick(registerBtn);
	}
	
	//3. Registration success message fetch
	public String getSuccessMessageText() {
		String headingText = doGetElementText(successHeading);
		String paragraphText = doGetElementText(successParagraph);
		
		return headingText + " " + paragraphText;
	}
	
	//4. Logout link isDisplayed
	public boolean isUserRegisteredAndLoggedIn() {
		return isElementDisplayed(logoutLink);
	}
}
