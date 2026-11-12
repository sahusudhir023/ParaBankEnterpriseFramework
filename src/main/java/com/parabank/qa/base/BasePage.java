package com.parabank.qa.base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.parabank.qa.utils.ConfigReader;

public class BasePage {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	//Constructor for BasePage to intialize driver
	public BasePage(WebDriver driver) {
		this.driver = driver;
		int explicitTimeout = ConfigReader.getIntProperty("explicitWait", 15);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitTimeout));
	}
	
	
	//======= REUSABLE ELEMENT ACTION METHODS =======
	
	public void doClick(By locator) {
		try {
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			element.click();
		} catch (Exception e) {
			System.err.println("Failed to click on element located by: " + locator);
			e.printStackTrace();
		}
	}
	
	public void doSendKeys(By locator, String value) {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			System.err.println("Faied to enter text '" + value + "' into element located by: " + locator);
			e.printStackTrace();
		}
	}
	
	public String doGetElementText(By locator) {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return element.getText().trim();
		} catch (Exception e) {
			System.err.println("Failed to get text from element located by: " + locator);
			return "";
		}
	}
	
	public boolean isElementDisplayed(By locator) {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
		} catch (Exception e) {
			System.err.println("Element not displayed: " + locator);
			return false;
		}
	}
	
	public void doSelectByVisibleText(By locator, String text) {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			Select select = new Select(element);
			select.selectByVisibleText(text);
		} catch (Exception e) {
			System.err.println("Failed to select dropdown option: " + text + " for locator: " + locator);
			e.printStackTrace();
		}
	}
	
	public void doSelectByIndex(By locator, int index) {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			Select select = new Select(element);
			select.selectByIndex(index);
		} catch (Exception e) {
			System.err.println("Failed to select dropdown option: " + index + " for locator: " + locator);
			e.printStackTrace();
		}
	}
	
	public List<WebElement> getElements(By locator){
		try {
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		} catch (Exception e) {
			System.err.print("Failed to get elements located by: " + locator);
			return null;
		}
	}
	
}
