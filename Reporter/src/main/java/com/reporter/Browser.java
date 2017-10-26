package com.reporter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Browser {

	static WebDriver driver;

	public static void launchIEBrowser() {
		System.setProperty("webdriver.ie.driver", "./Drivers/IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		ThreadManager.setDriver(driver);
		ThreadManager.getDriver().manage().window().maximize();
		driver.manage().window().maximize();
		
	}

	public static void launchFirefoxBrowser() {
		driver = new FirefoxDriver();
		ThreadManager.setDriver(driver);
		ThreadManager.getDriver().manage().window().maximize();
		driver.manage().window().maximize();

	}

	public static void quitBrowser() {
		ThreadManager.getDriver().quit();
		driver.quit();
	}

}
