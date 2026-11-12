package com.parabank.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.parabank.qa.base.BasePage;

public class BillPayPage extends BasePage {
	
	//.Locator for Bill pay page
	private By billPayLink = By.xpath("//a[text()='Bill Pay']");
	private By payeeNameField = By.name("payee.name");
	private By addressField = By.name("payee.address.street");
	private By cityField = By.name("payee.address.city");
	private By stateField = By.name("payee.address.state");
	private By zipCodeField = By.name("payee.address.zipCode");
	private By phoneField = By.name("payee.phoneNumber");
	private By accountNumberField = By.name("payee.accountNumber");
	private By verifyAccountField = By.name("verifyAccount");
	private By amountField = By.name("amount");
	private By fromAccountDropdown = By.name("fromAccountId");
	private By sendPaymentBtn = By.xpath("//input[@value='Send Payment']");
	private By successMessage = By.xpath("//div[@id='billpayResult']/h1");
	
	public BillPayPage(WebDriver driver) {
		super(driver);
	}
	
	//.Page Actions
	public void clickBillPayLink() {
		doClick(billPayLink);
	}
	
	public void enterPayeeDetails(String name, String address, String city, String state, 
			String zip, String phone, String accNo, String amount, String fromAccountNumber) {
		

        doSendKeys(payeeNameField, name);
		doSendKeys(addressField, address);
		doSendKeys(cityField, city);
		doSendKeys(stateField, state);
		doSendKeys(zipCodeField, zip);
		doSendKeys(phoneField, phone);
		doSendKeys(accountNumberField, accNo);
		doSendKeys(verifyAccountField, accNo);
		doSendKeys(amountField, amount);
		
		doSelectByIndex(fromAccountDropdown, 0);
	}
	
	public void clickSendPaymentButton() {
		doClick(sendPaymentBtn);
	}
	
	public String getBillPaySuccessMessage() {
		return doGetElementText(successMessage);
	}

}
