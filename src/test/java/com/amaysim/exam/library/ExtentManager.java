package com.amaysim.exam.library;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.amaysim.exam.data.SiteVariables.*;

public class ExtentManager extends FunctionFactory {
    static ExtentHtmlReporter htmlReporter;
    static ExtentReports extent;
    static ExtentTest test;
    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
    static String testName;
    public static Double countPassed = (double) 0;
    public static Double countFailed = (double) 0;
    public static Double countErrored = (double) 0;
    public static Double countSkipped = (double) 0;
    public static Double countAllTests = (double) 0;

    public static synchronized ExtentReports getReporter() {
        if (extent == null) {
            extent = new ExtentReports();
        }
        return extent;
    }

    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized ExtentTest startTest(String testName) {
        test = extent.createTest(testName);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }

    @BeforeSuite
    public static void createReport(ITestContext context) {
        String suiteName = context.getCurrentXmlTest().getSuite().getName();
        File file = new File(TEST_RESULTS_PATH + suiteName + "_Result.html");

        //Create the file.
        try {
            if (file.exists()) {
                file.createNewFile();
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        htmlReporter = new ExtentHtmlReporter(TEST_RESULTS_PATH + suiteName + "_Result.html");
        htmlReporter.config().setDocumentTitle(suiteName);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Environment", TEST_URL);
        extent.setSystemInfo("User", USERNAME);
    }

    @BeforeMethod
    public void getTestName(Method method) {
        testName = method.getAnnotation(Test.class).description();
        test = startTest(testName);
    }

    public void generateReport(ITestResult testResult) {
        try {
            getTest().assignCategory(this.getClass().getSimpleName());
            switch (testResult.getStatus()) {
                case ITestResult.SUCCESS:
                    countAllTests++;
                    countPassed++;
                    getTest().log(Status.PASS, "<b><font size='+1' color='#00af00'>[PASS] </font></b>Test has passed.");
                    break;
                case ITestResult.FAILURE:
                    String stackTrace = testResult.getThrowable().toString();
                    countAllTests++;
                    if (stackTrace.contains("NullPointerException") || stackTrace.contains("ArrayIndexOutOfBound")
                            || stackTrace.contains("IllegalArgumentException") || stackTrace.contains("IllegalStateException")
                            || stackTrace.contains("ArithmaticException")) {
                        countErrored++;
                        getTest().error("<b><font size='+1' color='#ff6347'>[ERROR] Exception has occured.</font></b>")
                                .error(testResult.getThrowable());
                    } else {
                        countFailed++;
                        getTest().fail("<b><font size='+1' color='#F7464A'>[FAIL] Assertion has failed.</font></b>")
                                .fail(testResult.getThrowable());
                    }
                    break;
                case ITestResult.SKIP:
                    getTest().skip("<b><font size='+1' color='darkorange'>[SKIP] </font></b>Test was skipped.")
                            .skip("<b><font size='+1' color='darkorange'>[REASON] </font></b>"
                                    + testResult.getThrowable().getMessage());
                    countSkipped++;
                    break;
                default:
                    getTest().log(Status.WARNING, "Unknown Result");
                    break;
            }
        } catch (Exception e) {
            throw e;
        }

    }

    @AfterSuite
    public void finishReport(ITestContext context) {
        getReporter().flush();
    }

}
