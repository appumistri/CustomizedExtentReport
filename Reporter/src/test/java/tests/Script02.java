package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.reporter.BaseTest;
import com.reporter.DriverManager;
import com.reporter.ExtentReport;

public class Script02 extends BaseTest {

	@Test(description="This is a bing test.", groups= {"bing"})
	public void test5() {
		DriverManager.getDriver().get("http://bing.com");
		Assert.assertTrue(true);
	}

	@Test(description="This is a google test.", groups= {"google"})
	public void test6() {
		DriverManager.getDriver().get("http://google.co.in");
		ExtentReport.logWarning("Formal warning");
		Assert.assertTrue(true);
	}

	@Test(description="This is again a bing test.", groups= {"bing"})
	public void test7() {
		DriverManager.getDriver().get("http://bing.com");
		Assert.assertTrue(false);
	}

	@Test(description="This is again a google test.", groups= {"google"})
	public void test8() {
		DriverManager.getDriver().get("http://google.co.in");
		ExtentReport.logInfo("info message.");
		Assert.assertTrue(true);
	}

}
