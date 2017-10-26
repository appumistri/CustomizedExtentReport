package com.reporter;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class Reporter {

	private static ExtentReports report;

	public static void initReporter() throws IOException {
		report = new ExtentReports("./Reports/MyReport.html", true);
		report.loadConfig(new File("./Reports/config/extentReportConfig.xml"));
	}

	public static void startTest(String testName) {
		ThreadManager.setReporterTest(report.startTest(testName));
		testInfoLog(testName, "Starting Test : \"" + testName + "\"");
	}

	public static void endTest(String testName) {
		testInfoLog(testName, "Ending Test : \"" + testName + "\"");
		report.endTest(ThreadManager.getReporterTest());
	}

	public static void closeReporter() {
		report.flush();
	}

	public static void testPassLog(String testName) {
		ThreadManager.getReporterTest().log(LogStatus.PASS, testName);
		endTest(testName);
	}

	public static void testFailedLog(String testName, ITestResult result) throws IOException {
		ThreadManager.getReporterTest().log(LogStatus.FAIL,
				testName + ThreadManager.getReporterTest().addScreenCapture(getScreenshotPath(testName))
						+ result.getThrowable());
		endTest(testName);
	}

	public static void testSkippedLog(String testName) {
		ThreadManager.getReporterTest().log(LogStatus.FAIL, "Skipped Test : \"" + testName + "\"");
		endTest(testName);
	}

	public static void testWarningLog(String testName, String log) {
		ThreadManager.getReporterTest().log(LogStatus.WARNING, testName, log);
	}

	public static void testErrorLog(String testName, String log) {
		ThreadManager.getReporterTest().log(LogStatus.ERROR, testName, log);
	}

	public static void testInfoLog(String testName, String log) {
		ThreadManager.getReporterTest().log(LogStatus.INFO, testName, log);
	}

	public static void testUnknownLog(String testName, String log) {
		ThreadManager.getReporterTest().log(LogStatus.UNKNOWN, testName, log);
	}

	private static String getScreenshotPath(String testName) throws IOException {
		try {
			TakesScreenshot scrShot = ((TakesScreenshot) ThreadManager.getDriver());
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			String imagePath = "ScreenShots/" + testName + ".png";
			File DestFile = new File("./Reports/" + imagePath);
			FileUtils.copyFile(SrcFile, DestFile);
			return imagePath;
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
		return "";
	}

}
