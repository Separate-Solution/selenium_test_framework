package com.o2htechnology.listeners;

import com.o2htechnology.test_core.BaseTest;
import com.o2htechnology.report.GenerateReport;
import com.o2htechnology.report.ReportManager;
import com.o2htechnology.core.BasePage;
import com.o2htechnology.utils.common_utils.CommonUtils;
import com.o2htechnology.utils.common_utils.TestData;
import com.o2htechnology.utils.logging.Logs;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class Listener extends BaseTest implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        ReportManager.startTest(getTestCaseName(result), getTestCaseDescription(result));
        Logs.logInfoMessage(TestData.TestCase.InfoMessages.CURRENTLY_EXECUTING_TEST + getTestCaseName(result) + "\n" + TestData.TestCase.InfoMessages.TEST_DESCRIPTION + getTestCaseDescription(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Logs.logInfoMessage(TestData.TestCase.InfoMessages.TEST_PASSED + getTestCaseName(result));
        ReportManager.getTest().log(Status.PASS, TestData.TestCase.InfoMessages.TEST_PASSED);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Logs.logErrorMessage(TestData.TestCase.InfoMessages.TEST_FAILED + getTestCaseName(result));
        BasePage.takeScreenshot(result);
        /*
        uncomment below code when you want to affix base64string screenshots in your report
         */
//        Object testClass = result.getInstance();
//        WebDriver newDriverInstance = ((BaseTest) testClass).getDriver();
//        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(newDriverInstance)).getScreenshotAs(OutputType.BASE64);
//        ReportManager.getTest().log(Status.FAIL, TestData.TestCase.InfoMessages.TEST_FAILED, ReportManager.getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
        ReportManager.getTest().log(Status.FAIL, TestData.TestCase.InfoMessages.TEST_FAILED, MediaEntityBuilder.createScreenCaptureFromPath("screenshots" + File.separator + CommonUtils.generateFilename(result, CommonUtils.ImageFormat.PNG.getImageFormat())).build());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Logs.logInfoMessage(TestData.TestCase.InfoMessages.TEST_SKIPPED + getTestCaseName(result));
        ReportManager.getTest().log(Status.SKIP, TestData.TestCase.InfoMessages.TEST_SKIPPED);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Logs.logInfoMessage(TestData.TestCase.InfoMessages.TEST_PASSED_WITH_EXCEPTION + getTestCaseName(result));
    }

    @Override
    public void onStart(ITestContext context) {
        Logs.logInfoMessage(TestData.TestCase.InfoMessages.TEST_SUITE_INSTANTIATED + context.getName());
        context.setAttribute("Webdriver", driver);
    }

    @Override
    public void onFinish(ITestContext context) {
        Logs.logInfoMessage(TestData.TestCase.InfoMessages.TEST_SUITE_FINISHED + context.getName());
        GenerateReport.extentReports.flush();
    }

    private static String getTestCaseName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }

    private static String getTestCaseDescription(ITestResult result) {
        return result.getMethod().getDescription();
    }
}
