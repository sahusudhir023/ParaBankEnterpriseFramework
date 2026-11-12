package com.parabank.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.parabank.qa.base.BasePage;

public class OpenAccountPage extends BasePage {
	
	//.Locators for Open New Account page
	private By openNewAccountLink = By.xpath("//a[text()='Open New Account']");
	private By accountTypeDropdown = By.id("type");
	private By fromAccountDropdown = By.id("fromAccountId");
	private By openNewAccountBtn = By.xpath("//input[@value='Open New Account']");
	private By successMessage = By.xpath("//div[@id='openAccountResult']/h1");
	private By newAccountNumber = By.id("newAccountId");
	
	//.Constructor
	public OpenAccountPage(WebDriver driver) {
		super(driver);
	}
	
	//.Page Actions
	public void clickOpenNewAccountLink() {
		doClick(openNewAccountLink);
	}
	
	public void selectAccountType(String accountType) {
		doSelectByVisibleText(accountTypeDropdown, accountType);
	}
	
	public void selectFromAccountId(String fromAccountNumber) {
		try {
			doSelectByVisibleText(fromAccountDropdown, fromAccountNumber);
		} catch (Exception e) {
			System.out.println("Account Number (" + fromAccountNumber + ") not found in dropdown. Selecting first avilable account.");
			doSelectByIndex(fromAccountDropdown, 0);
		}
	}
	
	public void clickSubmitOpenAccount() {
		doClick(openNewAccountBtn);
	}
	
	public String getSuccessMessageText() {
		return doGetElementText(successMessage);
	}
	
	public String getNewlyCreatedAccountNumber() {
		return doGetElementText(newAccountNumber);
	}

}
