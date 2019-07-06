package com.amaysim.exam.utils;

import com.amaysim.exam.library.FunctionFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.amaysim.exam.data.Locators.*;

public class CommonUtils extends FunctionFactory {

    public static void login(String username, String password) {
        click(LOGIN_LOGIN_MAIN);
        waitForElementToBeVisible(LOGIN_LOGIN_BUTTON);
        type(LOGIN_USERNAME, username);
        type(LOGIN_PASSWORD, password);
        click(LOGIN_LOGIN_BUTTON);
        waitForElementToBeVisible(HOME_WELCOME);
    }

    public static void waitForLoading() {
        waitForElementToBeVisible(SETTINGS_LOADER);
        waitForElementToBeInvisible(SETTINGS_LOADER);
    }

    public static String setString(String firstText, String secondText) {
        return String.format(firstText, secondText);
    }

    public static List<String> readFile(String fileName) throws IOException {
        List<String> items = new ArrayList<>();
        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line = bufferedReader.readLine()) != null) {
            items.add(line);
        }

        return items;
    }
}
