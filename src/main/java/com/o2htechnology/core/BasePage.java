package com.o2htechnology.core;

import com.o2htechnology.utils.common_utils.CommonUtils;
import com.o2htechnology.utils.common_utils.PageData;
import com.o2htechnology.utils.load_strategies.LoadStrategies;
import com.o2htechnology.utils.logging.Logs;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * BasePage class holds all the common methods and actions needed across the pages. All the page objects extends BasePage in order to pass the instance of the driver.
 */
public class BasePage {
    protected static WebDriver driver;
    private Boolean isPageLoaded;
    private Boolean isElementTypeSelect;
    private List<String> menuOptions = new ArrayList<String>();
    private List<String> tableHeaders = new ArrayList<String>();
    private List<String> tableRows = new ArrayList<String>();

    /**
     * Constructs BasePage class and sets the driver instance for sharing across page objects and helper methods.
     *
     * @param driver Takes WebDriver as a parameter.
     */
    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }

    /**
     * This helper method enters the value inside the textfield.
     *
     * @param textElement Takes WebElement as a parameter.
     * @param text        Takes String text as a parameter.
     */
    protected void setTextInField(WebElement textElement, String text) {
        LoadStrategies.waitTillElementIsInteractable(driver, textElement);
        textElement.clear();
        textElement.sendKeys(text);
    }

    /**
     * This helper method fetches the text from the textfield.
     *
     * @param textElement Takes WebElement as a parameter.
     * @return The message from the field.
     */
    protected String getTextFromField(WebElement textElement) {
        LoadStrategies.waitTillElementIsVisible(driver, textElement);
        return textElement.getText();
    }

    /**
     * This helper method clears the data present inside the field.
     *
     * @param textElement Takes WebElement as a parameter.
     */
    protected void removeTextFromField(WebElement textElement) {
        LoadStrategies.waitTillElementIsInteractable(driver, textElement);
        textElement.clear();
        Logs.logDebugMessage(textElement + PageData.Messages.Info.FIELD_CLEARED);
    }

    /**
     * This helper method clicks on the button.
     *
     * @param buttonElement Takes WebElement as a parameter.
     */
    protected void clickButton(WebElement buttonElement) {
        LoadStrategies.waitTillElementIsClickable(driver, buttonElement);
        buttonElement.click();
    }

    /**
     * This helper method fetches all the options present in the dropdown list.
     *
     * @param selectElement Takes WebElement as a parameter.
     * @return List of WebElement.
     */
    protected List<WebElement> getAllOptionsFromDropdown(WebElement selectElement) {
        checkElementTypeIsSelect(selectElement);
        if (isElementTypeSelect) {
            Select dropdown = new Select(selectElement);
            Logs.logDebugMessage(PageData.Messages.Info.OPTIONS_IN_DROPDOWN + dropdown.getOptions());
            return dropdown.getOptions();
        } else {
            return null;
        }
    }

    /**
     * This helper method gets all the selected options from the dropdown list.
     *
     * @param selectElement Takes WebElement as the parameter.
     * @return List of WebElement.
     */
    protected List<WebElement> getAllSelectedOptionsFromDropdown(WebElement selectElement) {
        checkElementTypeIsSelect(selectElement);
        if (isElementTypeSelect) {
            Select dropdown = new Select(selectElement);
            Logs.logDebugMessage(PageData.Messages.Info.SELECTED_OPTIONS_IN_DROPDOWN + dropdown.getAllSelectedOptions());
            return dropdown.getAllSelectedOptions();
        } else {
            return null;
        }
    }

    /**
     * This helper method fetches the src of the image.
     *
     * @param imageElement Takes WebElement as the parameter.
     * @return Image source.
     */
    protected String getImageSrc(WebElement imageElement) {
        return imageElement.getAttribute("src");
    }

    /**
     * This method checks if the provided element is of select type or not.
     *
     * @param selectElement Takes WebElement as a parameter.
     * @return Returns true if the provided element is of select type.
     */
    private boolean checkElementTypeIsSelect(WebElement selectElement) {
        LoadStrategies.waitTillElementIsInteractable(driver, selectElement);
        String selectElementXpath = selectElement.toString();
        if (!(selectElementXpath.endsWith("/select")) || !(selectElementXpath.endsWith("/select[]")) || !(selectElementXpath.endsWith("/option")) || !(selectElementXpath.endsWith("/option[]"))) {
            Logs.logErrorMessage(PageData.Messages.Error.SELECT_ELEMENT_NOT_PROVIDED);
            isElementTypeSelect = false;
        } else {
            isElementTypeSelect = true;
        }
        return isElementTypeSelect;
    }

    /**
     * This helper method fetches all the options from an unordered list.
     *
     * @param menuElement Takes WebElement as the parameter.
     * @return Returns list of menus from the unordered list.
     */
    protected List<String> getMenuOptions(WebElement menuElement) {
        //TODO consider also when the menuElement does not have ul as its locator
        List<WebElement> options = menuElement.findElements(By.tagName("li"));
        for (WebElement option : options) {
            if (!(option.getText().isBlank())) {
                setMenuOptions(option);
            }
        }
        Logs.logInfoMessage(menuOptions.toString());
        return menuOptions;
    }

    /**
     * This method adds the menu to the list of menus.
     *
     * @param listElement Takes WebElement as the parameter.
     */
    private void setMenuOptions(WebElement listElement) {
        menuOptions.add(listElement.getText());
    }

    /**
     * This helper methods fetches all the headers from the table.
     *
     * @param tableElement Takes WebElement as the parameter.
     * @return Returns list of headers from the table.
     */
    protected List<String> getTableHeaders(WebElement tableElement) {
        List<WebElement> headers = tableElement.findElements(By.tagName("th"));
        for (WebElement header : headers) {
            setTableHeaders(header);
        }
        Logs.logInfoMessage(tableHeaders.toString());
        return tableHeaders;
    }

    /**
     * This method adds the header to the list of headers.
     *
     * @param tableHeaderElement Takes WebElement as the parameter.
     */
    private void setTableHeaders(WebElement tableHeaderElement) {
        tableHeaders.add(tableHeaderElement.getText());
    }

    /**
     * This helper method fetches all the rows from the table.
     *
     * @param rowElement Takes WebElement as the parameter.
     * @return Returns list of rows from the table.
     */
    protected List<String> getTableRows(WebElement rowElement) {
        List<WebElement> rows = rowElement.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            setTableRows(row);
        }
        Logs.logInfoMessage(tableRows.toString());
        return tableRows;
    }

    /**
     * This method adds the row to the list of rows.
     *
     * @param rowElement Takes WebElement as the parameter.
     */
    private void setTableRows(WebElement rowElement) {
        tableRows.add(rowElement.getText());
    }

    /**
     * This helper method initializes the current loaded page and checks if the page's elements are ready for interaction.
     *
     * @param pageElements Takes WebElement as parameter. Multiple WebElement are allowed.
     */
    protected void initPage(WebElement... pageElements) {
        Boolean isThePageLoaded = isPageLoaded(pageElements);
        refreshPageIfElementsNotLoaded(isThePageLoaded);
    }

    /**
     * This method refreshes the page if the elements have not been loaded.
     *
     * @param isPageLoaded
     */
    private void refreshPageIfElementsNotLoaded(Boolean isPageLoaded) {
        if (!isPageLoaded) {
            Logs.logInfoMessage(PageData.Messages.Info.PAGE_REFRESHING);
            driver.navigate().refresh();
        }
    }

    /**
     * This method checks if the page's elements have been loaded completely.
     *
     * @param pageElements Takes WebElement as parameter.
     * @return
     */
    private Boolean isPageLoaded(WebElement... pageElements) {
        List<WebElement> listOfElements = Arrays.asList(pageElements);
        List<Boolean> comparisonList = new ArrayList<>();
        isPageLoaded = true;
        for (WebElement element : listOfElements) {
            comparisonList.add(element.isEnabled());
        }
//        if (comparisonList.contains(false)){ //another way to compare the values in comparisonList
//            result = false;
//        }
        for (Boolean value : comparisonList) {
            isPageLoaded = isPageLoaded && value;
            if (!isPageLoaded) {
                break;
            }
        }
        return isPageLoaded;
    }

    /**
     * This helper method will capture a screenshot of the currently focused tab in the AUT.
     *
     * @param result Takes the result of the test case.
     * @return Returns a file object of the captured screenshot.
     */
    public static File takeScreenshot(ITestResult result) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File directory = new File(CommonUtils.getScreenshotPath());
        String imageFormat = CommonUtils.ImageFormat.PNG.getImageFormat();
        String fileName = CommonUtils.generateFilename(result, imageFormat);
        File screenshot = new File(directory, fileName);
        try {
            FileUtils.copyFile(file, screenshot);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        if (file.exists()) {
            file.delete();
        }
        return screenshot;
    }
}