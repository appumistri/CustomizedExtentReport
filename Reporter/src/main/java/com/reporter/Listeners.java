package com.reporter;

import java.io.IOException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.internal.IResultListener;

public class Listeners implements IResultListener, ISuiteListener {

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.startTest(result.getName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.testPassLog(result.getName());

	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			Reporter.testFailedLog(result.getName(), result);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.testSkippedLog(result.getName());

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConfigurationSuccess(ITestResult itr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConfigurationFailure(ITestResult itr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConfigurationSkip(ITestResult itr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ISuite suite) {
		try {
			Reporter.initReporter();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onFinish(ISuite suite) {
		Reporter.closeReporter();

	}

}
