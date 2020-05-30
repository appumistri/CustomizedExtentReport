package com.reporter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class BaseTest {
	@BeforeSuite(alwaysRun = true)
	public void globalSetup() {
		Browser.launchBrowser();
		ExtentReport.initializeReport();
	}

	@BeforeMethod(alwaysRun = true)
	public void globalTestSetup(Method method) {

		if (method.isAnnotationPresent(Test.class)) {
			Test testAnnotation = method.getAnnotation(Test.class);
			String testDescription = testAnnotation.description();
			if (testDescription != null && !testDescription.isEmpty()) {
				ExtentReport.startTest(method.getName(), testDescription);
			} else {
				ExtentReport.startTest(method.getName());
			}

			String[] categories = testAnnotation.groups();
			if (categories != null && categories.length > 0) {
				ExtentReport.setTestCategory(categories);
			}
		}
	}

	@AfterMethod(alwaysRun = true)
	public void globalTestTeardown(Method method, ITestResult result) {
		String testName = result.getName();
		int testResult = result.getStatus();
		switch (testResult) {
		case ITestResult.SUCCESS:
			ExtentReport.logPassedTest("Test passed: " + testName);
			break;
		case ITestResult.SKIP:
			ExtentReport.logSkippedTest("Test skipped: " + testName);
			break;
		default:
			StringWriter swDefault = new StringWriter();
			result.getThrowable().printStackTrace(new PrintWriter(swDefault));
			String stacktraceDefault = swDefault.toString();
			ExtentReport.logError(stacktraceDefault);
			ExtentReport.logFailedTest("Test failed: " + testName);
		}
	}

	@AfterSuite(alwaysRun = true)
	public void globalTeardown() {
		ExtentReport.closeReporter();
		Browser.quitBrowser();
	}
}
