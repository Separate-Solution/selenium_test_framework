package com.o2htechnology.rerun_test;

import com.o2htechnology.test_core.BaseTest;
import com.o2htechnology.listeners.Listener;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RerunTest extends BaseTest implements IRetryAnalyzer {
    private int firstRun = 0;
    Listener listener = new Listener();
    private int maximumRunsAllowed = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (!(result.isSuccess())) {
//            if (ConfigurationReader.getBooleanValue(CommonUtils.BROWSER_PROPERTIES_FILE_PATH, "retry_test_on_failure")) {
//                maximumRunsAllowed = ConfigurationReader.getIntValue(CommonUtils.BROWSER_PROPERTIES_FILE_PATH, "rerun_test_time");
//            }
            if (firstRun < maximumRunsAllowed) {
                firstRun++;
                result.setStatus(ITestResult.FAILURE);
                listener.onTestFailure(result);
                return true;
            } else {
                result.setStatus(ITestResult.SUCCESS);
                return false;
            }
        } else {
            result.setStatus(ITestResult.SUCCESS);
            return false;
        }
    }
}
