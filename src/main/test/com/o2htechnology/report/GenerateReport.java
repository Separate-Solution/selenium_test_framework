package com.o2htechnology.report;

import com.o2htechnology.utils.common_utils.PageData;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class GenerateReport {
    public static ExtentReports extentReports = new ExtentReports();
    //TODO improve the report such that it will display more details about the current test being executed, its processes etc. also find a way to attach the screenshot in the report in case of test failure

    public static synchronized ExtentReports generateExtentReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(new File("./src/test/resources/reports/Spark.html"));
        reporter.config().setReportName(PageData.ExtentReportData.REPORT_NAME);
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("OS", PageData.ExtentReportData.OS);
        extentReports.setSystemInfo("Author", PageData.ExtentReportData.TEST_AUTHOR);
        return extentReports;
    }
}
