package com.amaysim.exam.library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.amaysim.exam.data.SiteVariables.*;

public class DriverFactory extends ExtentManager {

    protected static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    @BeforeSuite
    public void createDriver() {
        DriverFactory.setDriver(DriverFactory.createWebDriver(BROWSER));
        DriverFactory.getDriver().get(TEST_URL);
    }

    @AfterSuite
    public void closeDriver() {
        getDriver().close();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult testResult) {
        generateReport(testResult);
    }

    public static WebDriver createWebDriver(String browser) {
        WebDriver driver = null;

        // Add browser options here

        // Chrome options
        ChromeOptions options = new ChromeOptions();
        if (System.getProperties().getProperty("os.name").toLowerCase().contains("windows")) {
            System.setProperty("webdriver.chrome.driver",
                    BINARY_ROOT_FOLDER + "windows//googlechrome//chromedriver.exe");
        } else if (System.getProperties().getProperty("os.name").toLowerCase().contains("mac")) {
            System.setProperty("webdriver.chrome.driver", BINARY_ROOT_FOLDER + "osx//chromedriver");
        } else if (System.getProperties().getProperty("os.name").toLowerCase().contains("linux")) {
            System.setProperty("webdriver.chrome.driver", BINARY_ROOT_FOLDER + "linux//googlechrome//chromedriver");
        }

        options.addArguments("disable-infobars");
        options.addArguments("--disable-infobars");
        options.addArguments("disable-extensions");
        options.addArguments("no-sandbox");
        options.addArguments("--no-sandbox");
        options.addArguments("disable-plugins");
        options.addArguments("--start-maximized");
        options.addArguments("window-size=1920,1080");
        options.addArguments("start-maximized");
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
    }

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    public static void setDriver(WebDriver driver) {
        driverThread.set(driver);
        driver.manage().window().maximize();
    }
}
