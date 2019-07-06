package com.amaysim.exam.tests;

import com.amaysim.exam.library.DriverFactory;
import com.amaysim.exam.utils.CommonUtils;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

import static com.amaysim.exam.data.Locators.*;
import static com.amaysim.exam.data.SiteVariables.*;
import static com.amaysim.exam.data.SiteVariables.Options.*;
import static com.amaysim.exam.utils.CommonUtils.*;
import static com.amaysim.exam.utils.SettingsUtils.*;

public class EditSettingsTest extends DriverFactory {
    SoftAssert softAssert;

    @BeforeSuite()
    public void goToPlan() {
        login(USERNAME, PASSWORD);
        click(HOME_MANAGE_PLAN);
    }

    @BeforeMethod
    public void goToSettings() {
        click(SIDEBAR_SETTINGS);
        waitForLoading();
        softAssert = new SoftAssert();
    }

    @DataProvider(name = "ValidNumberFormats")
    public static Object[] createValidNumberFormats() throws IOException {
        List<String> list = CommonUtils.readFile("./src/test/resources/numbers");
        return list.toArray(new Object[list.size()]);
    }

    @DataProvider(name = "InvalidNumberFormats")
    public static Object[] createInvalidNumberFormats() throws IOException {
        List<String> list = CommonUtils.readFile("./src/test/resources/invalid_numbers");
        return list.toArray(new Object[list.size()]);
    }

    @Test(description = "Call Forwarding Option Value should be NO")
    public void testChangeToNo() {
        editCallForwarding(NO);
        saveCallForwarding(YES);
        verifyElementText(SETTINGS_CALL_FORWARDING_SUCCESS_MODAL, SUCCESS_MESSAGE, softAssert);
        click(SETTINGS_CALL_FORWARDING_SUCCESS_MODAL_CLOSE);
        verifyElementText(SETTINGS_CALL_FORWARDING_OPTION_VALUE, "No", softAssert);
        softAssert.assertAll();
    }

    @Test(description = "Call Forwarding Details Value should be YES with valid formats", dataProvider = "ValidNumberFormats")
    public void testChangeToYesValidFormats(String number) {
        editCallForwarding(YES, number);
        saveCallForwarding(YES);
        verifyElementText(SETTINGS_CALL_FORWARDING_SUCCESS_MODAL, SUCCESS_MESSAGE, softAssert);
        click(SETTINGS_CALL_FORWARDING_SUCCESS_MODAL_CLOSE);
        verifyElementText(SETTINGS_CALL_FORWARDING_TEXT_VALUE, setString(CALL_FORWARDING_NUMBER, CALL_FORWARDING_DEFAULT), softAssert);
        verifyElementText(SETTINGS_CALL_FORWARDING_OPTION_VALUE, "Yes", softAssert);
        softAssert.assertAll();
    }

    @Test(description = "Call Forwarding Details Value should not be saved with invalid number formats", dataProvider = "InvalidNumberFormats")
    public void testChangeToYesInvalidFormats(String number) {
        editCallForwarding(YES, number);
        saveCallForwarding(YES);
        waitForElementToBeVisible(SETTINGS_CALL_FORWARDING_ERROR_MESSAGE);
    }

    @Test(description = "Call Forwarding Details Value should be retained upon cancelled")
    public void testCancel() {
        //Setup
        editCallForwarding(YES, CALL_FORWARDING_244_FORMAT);
        saveCallForwarding(YES);
        click(SETTINGS_CALL_FORWARDING_SUCCESS_MODAL_CLOSE);

        //Test
        editCallForwarding(YES, CALL_FORWARDING_433_FORMAT_2);
        saveCallForwarding(NO);
        verifyElementText(SETTINGS_CALL_FORWARDING_TEXT_VALUE, setString(CALL_FORWARDING_NUMBER, CALL_FORWARDING_DEFAULT), softAssert);
        verifyElementText(SETTINGS_CALL_FORWARDING_OPTION_VALUE, "Yes", softAssert);
        softAssert.assertAll();
    }
}
