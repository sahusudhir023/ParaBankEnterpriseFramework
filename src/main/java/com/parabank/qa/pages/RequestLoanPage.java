package com.parabank.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.parabank.qa.base.BasePage;

public class RequestLoanPage extends BasePage {
	
	//.Locator for Resquest Loan Page
	private By requestLoanLink = By.xpath("//a[text()='Request Loan']");
	private By loanAmountField = By.id("amount");
	private By downPaymentField = By.id("downPayment");
	private By fromAccountDropdown = By.id("fromAccountId");
	private By applyNowBtn = By.xpath("//input[@value='Apply Now']");
	private By loanStatusResult = By.id("loanStatus");
	private By newAccountIdResult = By.id("newAccountId");
	
	public RequestLoanPage(WebDriver driver) {
		super(driver);
	}
	
	//.Page Actions
	public void clickRequestLoanLink() {
		doClick(requestLoanLink);
	}
	
	public void enterLoanDetails(String loanAmount, String downPayment) {
		doSendKeys(loanAmountField, loanAmount);
		doSendKeys(downPaymentField, downPayment);
	}
	
	public void selectFromAccount(String accountNumber) {
		doSelectByVisibleText(fromAccountDropdown, accountNumber);
	}
	
	public void clickApplyNowButton() {
		doClick(applyNowBtn);
	}
	
	public String getLoanStatusMessage() {
		return doGetElementText(loanStatusResult);
	}
	
	
	public String getNewLoanAccountNumber() {
		return doGetElementText(newAccountIdResult);
	}

}
