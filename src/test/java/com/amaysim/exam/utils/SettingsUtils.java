package com.amaysim.exam.utils;

import com.amaysim.exam.library.FunctionFactory;

import static com.amaysim.exam.data.Locators.*;
import static com.amaysim.exam.data.SiteVariables.*;
import static com.amaysim.exam.utils.CommonUtils.waitForLoading;

public class SettingsUtils extends FunctionFactory {
    private static String defaultNumber = "";

    public static void editCallForwarding(Options options, String number) {
        click(SETTINGS_CALL_FORWARDING_EDIT_BUTTON);
        waitForLoading();
        click(SETTINGS_CALL_FORWARDING_MODAL_CONFIRM_BUTTON);
        waitForElementToBeInvisible(SETTINGS_CALL_FORWARDING_MODAL_CONFIRM_BUTTON);
        switch (options) {
            case YES:
                click(SETTINGS_CALL_FORWARDING_EDIT_YES_OPTION);
                type(SETTINGS_CALL_FORWARDING_EDIT_NUMBER_FIELD, number);
                break;
            case NO:
                click(SETTINGS_CALL_FORWARDING_EDIT_NO_OPTION);
                break;
            default:
                click(SETTINGS_CALL_FORWARDING_EDIT_NO_OPTION);
                break;
        }
    }

    public static void editCallForwarding(Options options) {
        editCallForwarding(options, defaultNumber);
    }

    public static void saveCallForwarding(Options options) {
        switch (options) {
            case YES:
                click(SETTINGS_CALL_FORWARDING_EDIT_SAVE_BUTTON);
                break;
            case NO:
                click(SETTINGS_CALL_FORWARDING_EDIT_CANCEL_LINK);
                break;
            default:
                click(SETTINGS_CALL_FORWARDING_EDIT_SAVE_BUTTON);
                break;
        }
        waitForLoading();
    }
}
