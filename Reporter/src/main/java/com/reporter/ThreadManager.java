package com.reporter;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

public class ThreadManager {

	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public static void setReporterTest(ExtentTest Test) {
		test.set(Test);
	}

	public static ExtentTest getReporterTest() {
		return test.get();
	}

	public static void setDriver(WebDriver Driver) {
		driver.set(Driver);
	}

	public static WebDriver getDriver() {
		return driver.get();
	}
}
