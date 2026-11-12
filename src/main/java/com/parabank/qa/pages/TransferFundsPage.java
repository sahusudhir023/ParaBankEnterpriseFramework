package com.parabank.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.parabank.qa.base.BasePage;


public class TransferFundsPage extends BasePage {
	
	//.Locator for Transfer Funds page
	private By transferFundsLink = By.xpath("//a[text()='Transfer Funds']");
	private By amountField = By.id("amount");
	private By fromAccountDropdown = By.id("fromAccountId");
	private By toAccountDropdown = By.id("toAccountId");
	private By transferBtn = By.xpath("//input[@value='Transfer']");
	private By successMessage = By.xpath("//div[@id='showResult']/h1");
	
	public TransferFundsPage(WebDriver driver) {
		super(driver);
	}
	
	//.Page Actions
	public void clickTransferFundsLink() {
		doClick(transferFundsLink);
	}
	
	public void enterTransferAmount(String amount) {
		doSendKeys(amountField, amount);
	}
	
	public void selectFromAccount(int index) {
		doSelectByIndex(fromAccountDropdown, index);
	}
	
	public void selectToAccount(int index) {
		doSelectByIndex(toAccountDropdown, index);
	}
	
	public void clickTransferButton() {
		doClick(transferBtn);
	}
	
	public String getTransferSuccessMessage() {
		return doGetElementText(successMessage);
	}

}
