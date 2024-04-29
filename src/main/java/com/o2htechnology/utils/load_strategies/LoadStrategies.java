package com.o2htechnology.utils.load_strategies;

import com.o2htechnology.utils.common_utils.PageData;
import com.o2htechnology.utils.logging.Logs;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

/**
 * LoadStrategies class provides different waiting and loading strategies depending on the scenario.
 *
 * @extends BasePage
 */
public class LoadStrategies {
    private static final Duration TIMEOUT = Duration.ofSeconds(10);
    private static final Duration POLLING = Duration.ofSeconds(1);
    private static FluentWait<WebDriver> fluentWait;
    private static WebDriver driver;

    private static WebDriver setDriver(WebDriver driver) {
        LoadStrategies.driver = driver;
        return driver;
    }

    /**
     * This method will implicitly wait based on the timeout provided.
     */
    public static void implicitWait() {
        driver.manage().timeouts().implicitlyWait(TIMEOUT);
    }

    /**
     * This method will wait fluently until the element becomes visible.
     *
     * @param driver,element Takes WebDriver and WebElement as parameters.
     * @throws NoSuchElementException Ignores NoSuchElementException while waiting.
     */
    public static void waitTillElementIsVisible(WebDriver driver, WebElement element) {
        setDriver(driver);
        try {
            fluentWait = new FluentWait<>(driver);
            Logs.logWarningMessage(PageData.Messages.Info.WAIT_ELEMENT_VISIBLE + element);
            fluentWait.withTimeout(TIMEOUT).pollingEvery(POLLING).ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(element));
            Logs.logInfoMessage(PageData.Messages.Info.ELEMENT_FOUND + element);
        } catch (NoSuchElementException e) {
            Logs.logErrorMessage(PageData.Messages.Error.ELEMENT_NOT_FOUND + e.getMessage());
        }
    }

    /**
     * This method will wait fluently until the element becomes clickable.
     *
     * @param driver,element Takes WebDriver and WebElement as parameters.
     * @throws ElementClickInterceptedException Ignores ElementClickInterceptedException while waiting.
     */
    public static void waitTillElementIsClickable(WebDriver driver, WebElement element) {
        setDriver(driver);
        try {
            fluentWait = new FluentWait<>(driver);
            Logs.logWarningMessage(PageData.Messages.Info.WAIT_ELEMENT_CLICKABLE + element);
            fluentWait.withTimeout(TIMEOUT).pollingEvery(POLLING).ignoring(ElementClickInterceptedException.class).until(ExpectedConditions.elementToBeClickable(element));
            Logs.logInfoMessage(PageData.Messages.Info.ELEMENT_FOUND + element);
        } catch (ElementClickInterceptedException e) {
            Logs.logErrorMessage(PageData.Messages.Error.ELEMENT_NOT_FOUND + e.getMessage());
        }
    }

    /**
     * This method will wait fluently until the element becomes interactable.
     *
     * @param driver,element Takes WebDriver and WebElement as parameters.
     * @throws ElementNotInteractableException Ignores ElementNotInteractableException while waiting.
     */
    public static void waitTillElementIsInteractable(WebDriver driver, WebElement element) {
        setDriver(driver);
        try {
            fluentWait = new FluentWait<>(driver);
            Logs.logWarningMessage(PageData.Messages.Info.WAIT_ELEMENT_INTERACTABLE + element);
            fluentWait.withTimeout(TIMEOUT).pollingEvery(POLLING).ignoring(ElementNotInteractableException.class).until(ExpectedConditions.elementToBeClickable(element));
            Logs.logInfoMessage(PageData.Messages.Info.ELEMENT_FOUND + element);
        } catch (ElementNotInteractableException e) {
            Logs.logErrorMessage(PageData.Messages.Error.ELEMENT_NOT_FOUND + e.getMessage());
        }
    }
}
