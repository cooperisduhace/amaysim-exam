package com.amaysim.exam.library;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import static com.amaysim.exam.data.SiteVariables.*;

public class FunctionFactory {

    private static WebDriver getWebDriver() {
        return DriverFactory.getDriver();
    }

    private static WebDriverWait getWebDriverWait() {
        return new WebDriverWait(getWebDriver(), DEFAULT_TIMEOUT);
    }

    public static void type(By locator, String text) {
        getWebDriver().findElement(locator).clear();
        getWebDriver().findElement(locator).sendKeys(text);
        System.out.println("[INFO] : Typed " + text);
    }

    public static void click(By locator) {
        waitForElementToBeVisible(locator);
        getWebDriver().findElement(locator).click();
        System.out.println("[INFO] : Clicked " + locator);
    }

    public static void waitForElementToBeVisible(By locator) {
        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForElementToBeInvisible(By locator) {
        getWebDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static String getElementText(By locator) {
        waitForElementToBeVisible(locator);
        String text = getWebDriver().findElement(locator).getText();
        System.out.println("[INFO] : Element Text " + text);
        return text;
    }

    public static void verifyElementText(By locator, String expected, SoftAssert softAssert) {
        waitForElementToBeVisible(locator);
        System.out.println("[ASSERTION] : Expected Text (" + expected + ") VS Actual Text (" + getElementText(locator) + ")");
        softAssert.assertEquals(getElementText(locator), expected);
    }

    public static void goToUrl(String url) {
        DriverFactory.getDriver().get(url);
    }
}
