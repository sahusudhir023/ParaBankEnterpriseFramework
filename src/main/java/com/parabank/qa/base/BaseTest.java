package com.parabank.qa.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.parabank.qa.utils.ConfigReader;
import com.parabank.qa.utils.Log;

public class BaseTest {
	
	public static WebDriver driverInstance = null;
		
	private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
	
	public static WebDriver getDriver() {
		return driverThread.get();
	}
	
	public static void setDriver(WebDriver driverInstance) {
		driverThread.set(driverInstance);
	}
	
	@BeforeMethod
	public void setUp() {
		// CDP aur Selenium DevTools warnings ko silent karne ke liye
		System.setProperty("webdriver.chrome.silentOutput", "true");
		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(java.util.logging.Level.SEVERE);
			
		Log.info("\n===========================================================");
		Log.info("Starting Test Setup in BaseTest...");
		
		String browserName = ConfigReader.getProperty("browser");
		String appUrl = ConfigReader.getProperty("url");
		int timeout = ConfigReader.getIntProperty("timeout", 10);
		String isHeadless = ConfigReader.getProperty("headless");
		
		if(browserName != null) {
			Log.info("Configured Browser from properties: " + browserName);
			
			if(browserName.equalsIgnoreCase("chrome")) {
				Log.info("Launching Chrome Browser with anti-bot configuration...");
				
				ChromeOptions options = new ChromeOptions();
				
				if(isHeadless != null && isHeadless.equalsIgnoreCase("true")) {
					Log.info("Executing Chrome in Headless mode.");
					
					options.addArguments("--headless=new");
				}
				
				//--- START: Anti-bot and Cloudflare ByPass Setting ----	
				options.setExperimentalOption("useAutomationExtension", false);
				options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});	
				options.addArguments("--disable-blink-features=AutomationControlled");	
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--remote-allow-origins=*");
				//--- END: Anti-bot setting
				
				driverInstance = new ChromeDriver(options);
				
			}else if(browserName.equalsIgnoreCase("firefox")) {
				Log.info("Launching Firfox Browser...");	
				driverInstance = new FirefoxDriver();
			}else if(browserName.equalsIgnoreCase("edge")) {
				Log.info("Launching Edge Browser...");
				driverInstance = new EdgeDriver();
			}else {
				Log.info("Unsupported browser provided in config: " + browserName + ". Defaulting to chrome");
				
				ChromeOptions defaultOptions = new ChromeOptions();
				
				defaultOptions.setExperimentalOption("useAutomationExtension", false);
				defaultOptions.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
				defaultOptions.addArguments("--disable-blink-features=AutomationControlled");
				driverInstance = new ChromeDriver(defaultOptions);
			}
			
		}else {
			Log.info("No browser specified in config.properties. Launching chrome as default.");
			
			ChromeOptions defaultOptions = new ChromeOptions();
			
			defaultOptions.setExperimentalOption("useAutomationExtension", false);
			defaultOptions.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
			defaultOptions.addArguments("--disable-blink-features=AutomationControlled");
			driverInstance = new ChromeDriver(defaultOptions);
		}
		
		setDriver(driverInstance);
		Log.info("WebDriver instance bound to current thread successfully.");
		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
		Log.info("Window maximized, cookies cleared, implicit wait set to: " + timeout + " second.");
		
		if(appUrl != null) {
			Log.info("Navigate to Application URL: " + appUrl);
			getDriver().get(appUrl);
		}else {
			Log.error("Application URL is missing in config.properties!");
		}
	}
	
	@AfterMethod
	public void tearDown() {
		if(getDriver() != null) {
			Log.info("Closing Browser and quiting ThreadLocal WebDriver instance...");
			getDriver().quit();
			
			driverThread.remove();
			Log.info("ThreadLocal driver instance removed successfully.");
			Log.info("===========================================================");
		}
	}

}
