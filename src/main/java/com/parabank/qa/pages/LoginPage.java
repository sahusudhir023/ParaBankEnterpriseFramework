package com.parabank.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.parabank.qa.base.BasePage;

public class LoginPage extends BasePage {

	// ====Locators====
	private By usernameInput = By.name("username");
	private By passwordInput = By.name("password");
	private By loginBtn = By.xpath("//input[@value='Log In']");
	private By errorMessage = By.xpath("//p[@class='error']");
	private By registerLink = By.linkText("Register");
	private By forgotPasswordLink = By.linkText("Forgot login info?");
	private By welcomHeader = By.cssSelector("h1.title");

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// ==== PAGE ACTIONS ====
	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	public AccountsOverviewPage doLogin(String username, String password) {
		doSendKeys(usernameInput, username);
		doSendKeys(passwordInput, password);
		doClick(loginBtn);

		return new AccountsOverviewPage(driver);
	}

	public String getErrorMessageText() {
		return doGetElementText(errorMessage);
	}

	public RegisterPage clickRegisterLink() {
		doClick(registerLink);

		return new RegisterPage(driver);
	}

	public void clickForgotPasswordLink() {
		doClick(forgotPasswordLink);
	}

	public String getWelcomeHeaderText() {
		return doGetElementText(welcomHeader);
	}

}
