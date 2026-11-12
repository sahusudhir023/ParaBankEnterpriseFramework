package com.parabank.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.parabank.qa.base.BasePage;

public class LoginPage extends BasePage {
	
	//1. Loactors
	private By usernameInput = By.name("username");
	private By passwordInput = By.name("password");
	private By loginBtn = By.xpath("//input[@value='Log In']");
//	private By errorMessage = By.cssSelector("p.error");
	private By errorMessage = By.xpath("//p[@class='error']");
	private By registerLink = By.linkText("Register");
	private By forgotPasswordLink = By.linkText("Forgot login info?");
	private By welcomHeader = By.cssSelector("h1.title");
	
	//2. Constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	//3. ==== PAGE ACTIONS ====
	
	//1. Page Title fetch
	public String getLoginPageTitle() {
		return driver.getTitle();
	}
	
	//2. User credential enter and login button click
	public AccountsOverviewPage doLogin(String username, String password) {
		doSendKeys(usernameInput, username);
		doSendKeys(passwordInput, password);
		doClick(loginBtn);
		
		return new AccountsOverviewPage(driver);
	}
	
	//3. Error message text fetch
	public String getErrorMessageText() {
		return doGetElementText(errorMessage);
	}
	
	//4. Registration click
	public RegisterPage clickRegisterLink() {
		doClick(registerLink);
		
		return new RegisterPage(driver);
	}
	
	//5. Forgot password click
	public void clickForgotPasswordLink() {
		doClick(forgotPasswordLink);
	}
	
	//6. Successful login after welcome header text verify
	public String getWelcomeHeaderText() {
		return doGetElementText(welcomHeader);
	}

}
