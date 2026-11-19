package com.parabank.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.parabank.qa.base.BasePage;

public class AccountsOverviewPage extends BasePage {

	// ====Locators====
	private By accountsOverviewHeader = By.cssSelector("h1.title");
	private By totalBalance = By.xpath("//b[text()='Total']/parent::td/following-sibling::td//b");
	private By logoutLink = By.linkText("Log Out");

	public AccountsOverviewPage(WebDriver driver) {
		super(driver);
	}

	// ==== PAGE ACTIONS ====
	public String getAccountsOverviewHeader() {
		return doGetElementText(accountsOverviewHeader);
	}

	public String getTotalBalanceAmount() {
		return doGetElementText(totalBalance);
	}

	public boolean isLogOutLinkExist() {
		return isElementDisplayed(logoutLink);
	}

	public LoginPage clickLogOutLink() {
		doClick(logoutLink);
		return new LoginPage(driver);
	}

}
