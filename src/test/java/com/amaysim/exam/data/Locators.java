package com.amaysim.exam.data;

import org.openqa.selenium.By;

public class Locators {

    // Login locators
    public static final By LOGIN_LOGIN_MAIN = By.xpath("(//a[contains(@href,'login')])[1]");
    public static final By LOGIN_USERNAME = By.xpath("//input[@id='username']");
    public static final By LOGIN_PASSWORD = By.xpath("//input[@id='password']");
    public static final By LOGIN_LOGIN_BUTTON = By.xpath("//button[@class='arrow-next']");

    // Home locators
    public static final By HOME_WELCOME = By.xpath("//span[@id='WelcomeMessage']");
    public static final By HOME_MANAGE_PLAN = By.xpath("//a[text()='Manage plan']");

    // Plan Locators
    public static final By SIDEBAR_SETTINGS = By.xpath("//li[contains(@class,'ama-submenu-item')]/a[contains(@href,'settings')]");
    public static final By SETTINGS_LOADER = By.xpath("//div[@class='loading-spinner']");
    public static final By SETTINGS_EDIT_NICKNAME = By.xpath("//a[contains(@id,'edit_settings_phone_label')]");
    public static final By SETTINGS_EDIT_NICKNAME_FIELD = By.xpath("//input[@id='my_amaysim2_setting_phone_label']");
    public static final By SETTINGS_CALL_FORWARDING_TEXT_VALUE = By.xpath("//div[@id='settings_call_forwarding']//div[contains(@class,'setting-option-details-text')]");
    public static final By SETTINGS_CALL_FORWARDING_OPTION_VALUE = By.xpath("//div[@id='settings_call_forwarding']//div[contains(@class,'setting-option-value-text')]");
    public static final By SETTINGS_CALL_FORWARDING_EDIT_BUTTON = By.xpath("//a[contains(@id,'edit_settings_call_forwarding')]");
    public static final By SETTINGS_CALL_FORWARDING_MODAL_CONFIRM_BUTTON = By.xpath("//a[contains(@class,'confirm_popup_confirm')]");
    public static final By SETTINGS_CALL_FORWARDING_MODAL_CANCEL_LINK = By.xpath("//a[contains(@class,'confirm_popup_cancel')]");
    public static final By SETTINGS_CALL_FORWARDING_EDIT_YES_OPTION = By.xpath("//input[@id='my_amaysim2_setting_call_divert_true']/following-sibling::span");
    public static final By SETTINGS_CALL_FORWARDING_EDIT_NO_OPTION = By.xpath("//input[@id='my_amaysim2_setting_call_divert_false']/following-sibling::span");
    public static final By SETTINGS_CALL_FORWARDING_EDIT_NUMBER_FIELD = By.xpath("//input[@id='my_amaysim2_setting_call_divert_number']");
    public static final By SETTINGS_CALL_FORWARDING_EDIT_SAVE_BUTTON = By.xpath("//form[@id='update_call_forwarding_form']//input[@name='commit']");
    public static final By SETTINGS_CALL_FORWARDING_EDIT_CANCEL_LINK = By.xpath("//form[@id='update_call_forwarding_form']//a[@id='cancel_settings_call_forwarding']");
    public static final By SETTINGS_CALL_FORWARDING_ERROR_MESSAGE = By.xpath("//span[contains(@class,'error')]");
    public static final By SETTINGS_CALL_FORWARDING_SUCCESS_MODAL = By.xpath("//div[@data-popup-type='success']/p");
    public static final By SETTINGS_CALL_FORWARDING_SUCCESS_MODAL_CLOSE = By.xpath("//div[@data-popup-type='success']/a[@class='close-reveal-modal']");
}
