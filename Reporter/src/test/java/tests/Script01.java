package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.reporter.BaseTest;
import com.reporter.DriverManager;
import com.reporter.ExtentReport;

public class Script01 extends BaseTest {

	@Test(description = "This is a bing test.", groups = { "bing" })
	public void test1() {
		DriverManager.getDriver().get("http://bing.com");
		Assert.assertTrue(true);
	}

	@Test(description = "This is a google test.", groups = { "google" })
	public void test2() {
		DriverManager.getDriver().get("http://google.co.in");
		ExtentReport.logError("Formal warning");
		Assert.assertTrue(true);
	}

	@Test(description = "This is again a bing test.", groups = { "bing" })
	public void test3() {
		DriverManager.getDriver().get("http://bing.com");
		Assert.assertTrue(false);
	}

	@Test(description = "This is again a google test.", groups = { "bing" })
	public void test4() {
		DriverManager.getDriver().get("http://google.co.in");
		Assert.assertTrue(false);
	}

}
