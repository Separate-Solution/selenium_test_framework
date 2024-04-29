package com.o2htechnology.test_core;

import com.o2htechnology.utils.common_utils.CommonUtils;
import com.o2htechnology.utils.common_utils.PageData;
import com.o2htechnology.utils.common_utils.TestData;
import com.o2htechnology.utils.logging.Logs;
import com.o2htechnology.utils.read_files.ConfigurationReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.manager.SeleniumManager;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;
import java.util.List;

import static com.o2htechnology.utils.common_utils.CommonUtils.*;

/**
 * BaseTest class will initialize the WebDriver and then destroy the session at the end of the execution of the test suite. All the page test classes extends this class.
 */
public class BaseTest {
    private enum OS {
        LINUX("Linux"), WINDOWS("Windows"), MAC("MacOS");
        private final String osType;
        private static String currentURL;


        OS(String osType) {
            this.osType = osType;
        }

        public String getOsType() {
            return osType;
        }
    }

    public static String url;
    protected WebDriver driver;
    protected SoftAssert softAssert;
    private String currentURL;

    /**
     * This method will call all the methods required to start the automation session with the URL of the AUT. It will be called before any class during the test execution to setup the WebDriver.
     */
    @BeforeMethod
    public void setUp() {
//        TODO have the global properties called and laoded here itself and check if the user wants the custom values
        Logs.logInfoMessage(PageData.Messages.Info.SESSION_STARTING);
        String environment = System.getProperty("environment", "global").toLowerCase();
        setEnvironment(environment);
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        setBrowser(browser);
        Boolean headless = Boolean.valueOf(System.getProperty("headless", "true").toLowerCase());
        setHeadless(headless);
        url = ConfigurationReader.getStringValue(PageData.Keywords.PROJECT_URL);
        setHttpClient();
        setDriver();
        Logs.logInfoMessage(PageData.Messages.Info.SESSION_STARTED);
        if (driver != null) {
//            BasePage.waitImplicitly();
            driver.get(url);
        } else {
            Logs.logErrorMessage(PageData.Messages.Error.DRIVER_SETUP);
        }
    }

    /**
     * This method will set the http-client property, useful in scenarios when the automation session won't launch.
     */
    private void setHttpClient() {
        System.setProperty(PageData.SystemProperties.HTTP_FACTORY, PageData.SystemProperties.HTTP_CLIENT);
    }

    /**
     * This method will initialize the WebDriver based on the browser. WebDriverManager will automatically fetch the latest version of the browser and install it in the system.
     *
     * @return Returns the instance of the WebDriver.
     * @throws Exception Catches exception occurred while setting up the WebDriver.
     */
    private WebDriver setDriver() {
        String browser = ConfigurationReader.getStringValue(PageData.Keywords.BROWSER);
        switch (browser) {
            case "chrome" -> {
                try {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    setDriverOptions(chromeOptions);
//                    TODO to add a way to check if a driver version is provided in the properties file, then use that instead of the latest driver version available
                    driver = new ChromeDriver(chromeOptions);
                    Logs.logInfoMessage(driver + " initialized");
//                    TODO get the current driver version
                    Logs.logInfoMessage(PageData.Messages.Info.BROWSER_LAUNCHED);
                    return driver;
                } catch (Exception e) {
                    Logs.logErrorMessage(PageData.Messages.Error.BROWSER_SETUP + e.getMessage());
                    return driver = null;
                }
            }
            case "firefox" -> {
                try {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    setDriverOptions(firefoxOptions);
                    driver = new FirefoxDriver(firefoxOptions);
                    Logs.logInfoMessage(driver + " initialized");
                    Logs.logInfoMessage(PageData.Messages.Info.BROWSER_LAUNCHED);
                    return driver;
                } catch (Exception e) {
                    Logs.logErrorMessage(PageData.Messages.Error.BROWSER_SETUP + e.getMessage());
                    return driver = null;
                }
            }
            case "edge" -> {
                try {
                    EdgeOptions edgeOptions = new EdgeOptions();
                    setDriverOptions(edgeOptions);
                    driver = new EdgeDriver(edgeOptions);
                    Logs.logInfoMessage(driver + " initialized");
                    Logs.logInfoMessage(PageData.Messages.Info.BROWSER_LAUNCHED);
                    return driver;
                } catch (Exception e) {
                    Logs.logErrorMessage(PageData.Messages.Error.BROWSER_SETUP + e.getMessage());
                    return driver = null;
                }
            }
            default -> {
                Logs.logErrorMessage(PageData.Messages.Error.DRIVER_INITIALIZE);
                return driver = null;
            }
        }
    }

    /**
     * This method will provide the browserOptions for setting up the WebDriver. It will set the driver to launch the browser in incognito and maximized mode.
     *
     * @param chromeOptions Takes ChromeOptions as a parameter.
     */
    private void setDriverOptions(ChromeOptions chromeOptions) {
        if (ConfigurationReader.getBooleanValue(PageData.Keywords.INCOGNITO)) {
            chromeOptions.addArguments(PageData.DriverArguments.INCOGNITO);
        }
        if (ConfigurationReader.getBooleanValue(PageData.Keywords.MAXIMIZED)) {
            chromeOptions.addArguments(PageData.DriverArguments.MAXIMIZED);
        }
        if (ConfigurationReader.getBooleanValue(PageData.Keywords.HEADLESS)) {
            chromeOptions.addArguments(PageData.DriverArguments.HEADLESS);
        }
//        if (ConfigurationReader.getFloatValue(CommonUtils.BROWSER_PROPERTIES_FILE_PATH, PageData.Keywords.WINDOW_SIZE) > 0) {
            chromeOptions.addArguments(PageData.DriverArguments.WINDOW_SIZE);
//        }
    }

    private void setDriverOptions(FirefoxOptions firefoxOptions) {
        if (ConfigurationReader.getBooleanValue(PageData.Keywords.INCOGNITO)) {
            firefoxOptions.addArguments(PageData.DriverArguments.INCOGNITO);
        }
        if (ConfigurationReader.getBooleanValue(PageData.Keywords.MAXIMIZED)) {
            firefoxOptions.addArguments(PageData.DriverArguments.MAXIMIZED);
        }
        if (ConfigurationReader.getBooleanValue(PageData.Keywords.HEADLESS)) {
            firefoxOptions.addArguments(PageData.DriverArguments.HEADLESS);
        }
//        if (ConfigurationReader.getFloatValue(CommonUtils.BROWSER_PROPERTIES_FILE_PATH, PageData.Keywords.WINDOW_SIZE) > 0) {
//            chromeOptions.addArguments(PageData.DriverArguments.WINDOW_SIZE + ConfigurationReader.getFloatValue(CommonUtils.BROWSER_PROPERTIES_FILE_PATH, "window_size_height") + "," + ConfigurationReader.getFloatValue(CommonUtils.BROWSER_PROPERTIES_FILE_PATH, "window_size_width"));
//        }
    }

    private void setDriverOptions(EdgeOptions edgeOptions) {
        if (ConfigurationReader.getBooleanValue(PageData.Keywords.INCOGNITO)) {
            edgeOptions.addArguments(PageData.DriverArguments.INCOGNITO);
        }
        if (ConfigurationReader.getBooleanValue(PageData.Keywords.MAXIMIZED)) {
            edgeOptions.addArguments(PageData.DriverArguments.MAXIMIZED);
        }
        if (ConfigurationReader.getBooleanValue(PageData.Keywords.HEADLESS)) {
            edgeOptions.addArguments(PageData.DriverArguments.HEADLESS);
        }
//        if (ConfigurationReader.getFloatValue(CommonUtils.BROWSER_PROPERTIES_FILE_PATH, PageData.Keywords.WINDOW_SIZE) > 0) {
//            chromeOptions.addArguments(PageData.DriverArguments.WINDOW_SIZE + ConfigurationReader.getFloatValue(CommonUtils.BROWSER_PROPERTIES_FILE_PATH, "window_size_height") + "," + ConfigurationReader.getFloatValue(CommonUtils.BROWSER_PROPERTIES_FILE_PATH, "window_size_width"));
//        }
    }

    /**
     * This method will destroy the current session of the WebDriver by going through every opened tabs and closing them. It will be called after every class have been executed during the test execution.
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ConfigurationReader.getBooleanValue(PageData.Keywords.QUIT_BROWSER)) {
            try {
                for (String eachWindowHandle : driver.getWindowHandles()) {
                    Logs.logInfoMessage(PageData.Messages.Info.SESSION_ENDING);
                    Logs.logInfoMessage(PageData.Messages.Info.CURRENT_DRIVER + driver);
                    Logs.logInfoMessage(PageData.Messages.Info.CLOSED_WINDOWS + driver.getWindowHandles().size());
                    driver.switchTo().window(eachWindowHandle);
                    driver.close();
                }
            } catch (Exception e) {
                Logs.logErrorMessage(PageData.Messages.Error.WINDOWS_CLOSED + e.getMessage());
            } finally {
                Logs.logInfoMessage(PageData.Messages.Info.SESSION_ENDED);
            }
        }
    }


    /**
     * This method will check if the user is on mentioned page. Compares the expected URL with the current URL.
     *
     * @param possibleURL Takes the URL of the expected page.
     */
    public void testUserIsOnThePage(List<String> possibleURL) {
        SoftAssert softAssert = new SoftAssert();
        currentURL = CommonUtils.getCurrentURL(driver);
        softAssert.assertTrue(possibleURL.contains(currentURL), TestData.TestCase.FailMessages.USER_NOT_ON_EXPECTED_PAGE);
    }

    /**
     * This method will create an object of SoftAssert for utilizing in test cases.
     *
     * @return Returns an object of SoftAssert.
     */
    protected SoftAssert getSoftAssert() {
        softAssert = new SoftAssert();
        return softAssert;
    }

    /**
     * This method will be called at the start of the test suite execution. It will log test execution message in the console.
     */
    @BeforeSuite
    private void startingTestCase() {
        Logs.logInfoMessage(PageData.Messages.Info.TEST_EXECUTION_START);
    }

    /**
     * This method will be called at the end of the test suite execution. It will log test execution message in the console.
     */
    @AfterSuite
    private void endingTestCase() {
        Logs.logInfoMessage(PageData.Messages.Info.TEST_EXECUTION_END);
    }
}