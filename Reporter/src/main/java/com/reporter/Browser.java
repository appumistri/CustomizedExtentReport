package com.reporter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser {

	public static void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		DriverManager.setDriver(driver);
	}

	public static void quitBrowser() {
		DriverManager.getDriver().quit();
	}
}
