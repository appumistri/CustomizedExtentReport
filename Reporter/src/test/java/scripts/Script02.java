package scripts;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.reporter.Reporter;
import com.reporter.ThreadManager;
import com.reporter.Browser;

public class Script02 {

	@BeforeClass
	public void launchBrowser() {
		Browser.launchFirefoxBrowser();
	}

	@AfterClass
	public void quitBrowser() {
		Browser.quitBrowser();
	}

	@Test
	public void test5() {
		ThreadManager.getDriver().get("http://bing.com");
		Assert.assertTrue(true);
	}

	@Test
	public void test6() {
		ThreadManager.getDriver().get("http://google.co.in");
		Reporter.testWarningLog("test5", "Formal warning");
		Assert.assertTrue(true);
	}
	
	@Test
	public void test7() {
		ThreadManager.getDriver().get("http://bing.com");
		Assert.assertTrue(false);
	}

	@Test
	public void test8() {
		ThreadManager.getDriver().get("http://google.co.in");
		Reporter.testUnknownLog("test8", "unknown");
		Assert.assertTrue(true);
	}

}
