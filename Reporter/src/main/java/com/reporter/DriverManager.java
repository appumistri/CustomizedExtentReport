package com.reporter;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public static void setDriver(WebDriver Driver) {
		driver.set(Driver);
	}

	public static WebDriver getDriver() {
		return driver.get();
	}
}
