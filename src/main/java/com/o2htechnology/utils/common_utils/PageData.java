package com.o2htechnology.utils.common_utils;

/**
 * All the data such as log messages, driver and system properties, constants, file paths, etc. will be added here.
 */
public class PageData {
    public static class SystemProperties {
        public final static String HTTP_FACTORY = "webdriver.http.factory";
        public final static String HTTP_CLIENT = "jdk-http-client";
        public final static String CHROME_WEB_DRIVER = "webdriver.chrome.driver";
        public final static String FIREFOX_WEB_DRIVER = "webdriver.gecko.driver";
        public final static String EDGE_WEB_DRIVER = "webdriver.edge.driver";
        public final static String USER_DIR = "user.dir";
    }

    public static class DriverArguments {
        public final static String INCOGNITO = "--incognito";
        public final static String MAXIMIZED = "start-maximized";
        public final static String HEADLESS = "--headless";
        public final static String WINDOW_SIZE = "--window-size=1440,900";
    }

    public static class Keywords {
        public final static String AUT = "aut";
        public final static String PROJECT_URL = "url";
        public final static String USERNAME = "username";
        public final static String PASSWORD = "password";
        public final static String ENVIRONMENT = "environment";
        public static final String BROWSER = "browser";
        public static final String INCOGNITO = "incognito";
        public static final String HEADLESS = "headless";
        public static final String MAXIMIZED = "maximized";
        public static final String QUIT_BROWSER = "quit_browser_after_test_execution";
        public static final String WINDOW_SIZE = "window_size";
        public final static String LOG_LEVEL = "log_level";
        public final static String DRIVER_VERSION = "driver_version";
    }

    public static class Messages {
        public static class Error {
            public final static String DRIVER_SETUP = "An error occurred while setting up the WebDriver";
            public final static String BROWSER_SETUP = "Error occurred while setting up ";
            public final static String DRIVER_INITIALIZE = "There was an error initializing the driver. Please check the browser name set in the properties file";
            public final static String WINDOWS_CLOSED = "Error occurred while tearing down - ";
            public final static String LOAD_STRATEGIES_INSTANCE = "An error occurred while creating an instance of LoadStrategies...";
            public final static String BROWSER_NAME_INCORRECT = "Browser mentioned does not exist. Please check and enter correct browser name";
            public final static String DRIVER_PATH_NOT_SET = "Driver path could not be set up";
            public final static String PROPERTIES_FILE_NOT_FOUND = " could not be read. check the file path";
            public final static String CONFIGURATION_READER_NOT_SET = "Error occurred while creating an instance of ConfigurationReader - ";
            public final static String KEY_NOT_EXISTS = "Provided key does not exist in the properties file. Please check the spelling of the key";
            public final static String ELEMENT_NOT_FOUND = "Error occurred while waiting for the element - ";
            public final static String FILE_READ_ERROR = "Error occurred while reading the file - ";
            public final static String DRIVER_VERSION_NOT_FOUND = "Could not find the driver version. Please check the path of driver and OS";
            public final static String OS_NOT_FOUND = "Could not find the OS from the driver path. Please check the path of driver";
            public final static String SELECT_ELEMENT_NOT_PROVIDED = "Provided element is not of select type. Please provide a select element";
        }

        public static class Success {

        }

        public static class Info {
            public final static String CURRENT_DRIVER = "Current driver - ";
            public final static String CLOSED_WINDOWS = "Closed windows - ";
            public final static String SESSION_STARTING = "Starting session...";
            public final static String BROWSER_LAUNCHED = "Browser launched!";
            public final static String SESSION_STARTED = "Session started!";
            public final static String SESSION_ENDING = "Ending session...";
            public final static String SESSION_ENDED = "Session ended!";
            public final static String PAGE_REFRESHING = "Elements not completely loaded. Refreshing the page...";
            public final static String LATEST_CHROMEDRIVER_NOT_FOUND = "The latest version of ChromeDriver does not exist. Downloading the latest ChromeDriver version...";
            public final static String LATEST_CHROMEDRIVER_FOUND = "Found the latest ChromeDriver executable in local system. Setting the ChromeDriver path...";
            public final static String LATEST_FIREFOXDRIVER_NOT_FOUND = "The latest version of FirefoxDriver does not exist. Downloading the latest FirefoxDriver version...";
            public final static String LATEST_FIREFOXDRIVER_FOUND = "Found the latest FirefoxDriver executable in local system. Setting the FirefoxDriver path...";
            public final static String LATEST_EDGEDRIVER_NOT_FOUND = "The latest version of EdgeDriver does not exist. Downloading the latest EdgeDriver version...";
            public final static String LATEST_EDGEDRIVER_FOUND = "Found the latest EdgeDriver executable in local system. Setting the EdgeDriver path...";
            public final static String SCREENSHOT_CAPTURED = "Test failed. Screenshot captured - ";
            public final static String WAIT_ELEMENT_VISIBLE = "Waiting for element to be visible - ";
            public final static String WAIT_ELEMENT_CLICKABLE = "Waiting for element to be clickable - ";
            public final static String WAIT_ELEMENT_INTERACTABLE = "Waiting for element to be visible - ";
            public final static String ELEMENT_FOUND = "Element found - ";
            public final static String FIELD_CLEARED = "Field has been cleared";
            public final static String OPTIONS_IN_DROPDOWN = "Options present in the dropdown received : ";
            public final static String SELECTED_OPTIONS_IN_DROPDOWN = "Options selected in the dropdown are : ";
            public final static String TEST_EXECUTION_START = "----------------------------------------------------[ Starting Test Execution ]----------------------------------------------------";
            public final static String TEST_EXECUTION_END = "----------------------------------------------------[ Ending Test Execution ]----------------------------------------------------";
        }

        public static class StepDetail {
        }
    }

    public static class ExtentReportData {
        public final static String OS = "Ubuntu Linux 22.04";
        public final static String TEST_AUTHOR = "o2h tech qa team";
        public final static String REPORT_NAME = "Automation Framework Test Report";
    }
}
