package com.reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.commons.compress.utils.CharsetNames;
//import org.apache.commons.compress.utils.CharsetNames;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport {

	private static ExtentReports extent = null;
	private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<ExtentTest>();

	public static void initializeReport() {
		String reportDir;
		ExtentHtmlReporter htmlReporter = null;

		reportDir = Paths.get(".", "Reports").toString();

		File log = new File(reportDir);
		if (!log.exists()) {
			log.mkdir();
		}

		String reportFile = new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss").format(new Date()).concat(".html");
		String reportName = Paths.get(reportDir, reportFile).toString();
		System.setProperty("report.path", reportName);
		htmlReporter = new ExtentHtmlReporter(reportName);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		InetAddress localhost = null;
		try {
			localhost = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		extent.setSystemInfo("Host", localhost.getHostAddress());
		extent.setSystemInfo("Environment", System.getProperty("os.name"));
		extent.setSystemInfo("UserName", System.getProperty("user.name"));

		htmlReporter.loadXMLConfig(new File("./extentReportConfig.xml"));
	}

	public static void closeReporter() {
		try {
			extent.flush();
			File testReport = new File(System.getProperty("report.path"));
			Document doc = Jsoup.parse(testReport, CharsetNames.UTF_8);
			doc.head().prepend(
					"<script src='https://www.amcharts.com/lib/4/themes/animated.js' type='text/javascript'></script>");
			doc.head()
					.prepend("<script src='https://www.amcharts.com/lib/4/charts.js' type='text/javascript'></script>");
			doc.head().prepend("<script src='https://www.amcharts.com/lib/4/core.js' type='text/javascript'></script>");
			doc.head().prepend("<script src='https://d3js.org/d3.v3.min.js' language='JavaScript'></script>");

			PrintWriter writer = new PrintWriter(testReport, "UTF-8");
			writer.write(doc.html());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void logPassedTest(String logMessage) {
		getTestThread().log(Status.PASS, MarkupHelper.createLabel(logMessage, ExtentColor.GREEN));
	}

	public static void logFailedTest(String logMessage) {
		try {
			TakesScreenshot scrShot = ((TakesScreenshot) DriverManager.getDriver());
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

			FileInputStream fileInputStreamReader = new FileInputStream(SrcFile);
			byte[] bytes = new byte[(int) SrcFile.length()];
			fileInputStreamReader.read(bytes);
			String encodedfile = Base64.getEncoder().encodeToString(bytes);
			MediaEntityModelProvider mediaProvider;
			mediaProvider = MediaEntityBuilder.createScreenCaptureFromBase64String(encodedfile).build();
			getTestThread().log(Status.FAIL, MarkupHelper.createLabel(logMessage, ExtentColor.RED));
			getTestThread().log(Status.FAIL, "", mediaProvider);
			fileInputStreamReader.close();

		} catch (Exception e) {
			getTestThread().log(Status.FAIL, MarkupHelper.createLabel(logMessage, ExtentColor.RED));
		}
	}

	public static void logSkippedTest(String logMessage) {
		getTestThread().log(Status.SKIP, logMessage);
	}

	public static void logInfo(String logMessage) {
		getTestThread().log(Status.INFO, logMessage);
	}

	public static void logWarning(String logMessage) {
		getTestThread().log(Status.WARNING, logMessage);
	}

	public static void logError(String logMessage) {
		getTestThread().log(Status.ERROR, logMessage);
	}

	public static void logFatal(String logMessage) {
		getTestThread().log(Status.FATAL, logMessage);
	}

	public static void startTest(String testName) {
		setTestThread(extent.createTest(testName));
	}

	public static void startTest(String testName, String description) {
		setTestThread(extent.createTest(testName, description));
	}

	public static void endTest() {
		// TODO auto generated stub
	}

	public static void setTestCategory(String[] category) {
		getTestThread().assignCategory(category);
	}

	private static void setTestThread(ExtentTest test) {
		testThread.set(test);
	}

	private static ExtentTest getTestThread() {
		return testThread.get();
	}

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		// TODO Auto-generated method stub

	}
}
