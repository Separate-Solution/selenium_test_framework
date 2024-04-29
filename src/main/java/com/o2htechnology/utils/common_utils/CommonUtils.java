package com.o2htechnology.utils.common_utils;

import com.o2htechnology.utils.logging.Logs;
import com.o2htechnology.utils.read_files.ConfigurationReader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.util.Date;

public class CommonUtils {
    private static String pageTitle, currentURL;
    private final static String CSV_FILE_PATH = System.getProperty(PageData.SystemProperties.USER_DIR) + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "project_data" + File.separator + "projectData.csv";
    private final static String SCREENSHOT_PATH = System.getProperty(PageData.SystemProperties.USER_DIR) + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "reports" + File.separator + "screenshots";
    private final static String PROPERTIES_DIRECTORY_PATH = System.getProperty(PageData.SystemProperties.USER_DIR) + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "properties" + File.separator;
    private static String environment, browser;
    private static Boolean headless;

    public enum ImageFormat {
        PNG("png"),
        JPG("jpg"),
        JPEG("jpeg"),
        SVG("svg");
        private final String imageFormat;

        ImageFormat(String imageFormat) {
            this.imageFormat = imageFormat;
        }

        public String getImageFormat() {
            return imageFormat;
        }
    }

    /**
     * This method will take the instance of the driver and get the URL of the opened tab.
     *
     * @param driver Takes WebDriver instance as a parameter.
     * @return URL of the current tab.
     */
    public static String getCurrentURL(WebDriver driver) {
        currentURL = driver.getCurrentUrl();
        return currentURL;
    }

    /**
     * This method will fetch the current page's title from the properties file based on the environment set in the loadConfigFile() method.
     *
     * @param driver Takes WebDriver instance as a parameter.
     * @return Title of the page.
     */
    public static String getPageTitle(WebDriver driver) {
        pageTitle = driver.getTitle();
        return pageTitle;
    }

    /**
     * This method will generate the file name for the screenshot.
     *
     * @param result      Takes result of the test case as a parameter.
     * @param imageFormat Takes image format as a parameter.
     * @return Returns file name of the captured screenshot.
     */
    public static String generateFilename(ITestResult result, String imageFormat) {
        Date currentTime = new Date();
        String aut = ConfigurationReader.getStringValue(PageData.Keywords.AUT);
        String dateString = currentTime.toString().replace(" ", "_");
        String testCase = result.getName();
        String fileName = aut + "_" + testCase + "_" + dateString + "." + imageFormat;
        Logs.logErrorMessage(PageData.Messages.Info.SCREENSHOT_CAPTURED + fileName);
        return fileName;
    }

    /**
     * This helper method sets the custom environment.
     *
     * @param currentEnvironment Takes String as the parameter.
     */
    public static void setEnvironment(String currentEnvironment) {
        environment = currentEnvironment;
    }

    /**
     * This helper method sets the custom browser.
     *
     * @param currentBrowser Takes String as the parameter.
     */
    public static void setBrowser(String currentBrowser) {
        browser = currentBrowser;
    }

    /**
     * This helper method sets the custom headless value.
     *
     * @param currentHeadless Takes Boolean as the parameter.
     */
    public static void setHeadless(Boolean currentHeadless) {
        headless = currentHeadless;
    }

    /**
     * This helper method fetches the environment variable value.
     *
     * @return Returns the current value of environment.
     */
    public static String getEnvironment() {
        return environment;
    }

    /**
     * This helper method fetches the browser variable value.
     *
     * @return Returns the current value of browser.
     */
    public static String getBrowser() {
        return browser;
    }

    /**
     * This helper method fetches the headless variable value.
     *
     * @return Returns the current value of headless.
     */
    public static Boolean getHeadless() {
        return headless;
    }

    public static String getCsvFilePath() {
        return CSV_FILE_PATH;
    }

    public static String getScreenshotPath() {
        return SCREENSHOT_PATH;
    }

    public static String getPropertiesDirectoryPath() {
        return PROPERTIES_DIRECTORY_PATH;
    }
}
