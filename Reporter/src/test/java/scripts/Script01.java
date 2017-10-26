package scripts;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.reporter.Reporter;
import com.reporter.ThreadManager;
import com.reporter.Browser;

public class Script01 {

	@BeforeClass
	public void launchBrowser() {
		Browser.launchFirefoxBrowser();
	}

	@AfterClass
	public void quitBrowser() {
		Browser.quitBrowser();
	}

	@Test
	public void test1() {
		ThreadManager.getDriver().get("http://bing.com");
		Assert.assertTrue(true);
	}

	@Test
	public void test2() {
		ThreadManager.getDriver().get("http://google.co.in");
		Reporter.testErrorLog("test2", "Formal warning");
		Assert.assertTrue(true);
	}
	
	@Test
	public void test3() {
		ThreadManager.getDriver().get("http://bing.com");
		Assert.assertTrue(false);
	}

	@Test
	public void test4() {
		ThreadManager.getDriver().get("http://google.co.in");
		Assert.assertTrue(false);
	}

}
