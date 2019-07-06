package com.amaysim.exam.data;

import java.util.ResourceBundle;

public class SiteVariables {
    //Generic Variables
    public static final String RUN_MODE = System.getenv("RUN_MODE") != null ? System.getenv("RUN_MODE") : "local";
    public static final ResourceBundle PROPERTIES = ResourceBundle.getBundle(RUN_MODE);
    public static final String BINARY_ROOT_FOLDER = PROPERTIES.getString("binaryRootFolder");
    public static final String BROWSER = PROPERTIES.getString("browser");
    public static final String TEST_RESULTS_PATH = PROPERTIES.getString("resultspath");
    public static final String DEFAULT_TIMEOUT_STRING = System.getenv("TIMEOUT") != null ?
            System.getenv("TIMEOUT") : PROPERTIES.getString("timeout");
    public static final Integer DEFAULT_TIMEOUT = Integer.parseInt(DEFAULT_TIMEOUT_STRING);

    // Basic Variables
    public static final String TEST_URL = System.getenv("URL") != null ? System.getenv("URL") : PROPERTIES.getString("url");
    public static final String TEST_SETTINGS_URL = System.getenv("URL_SETTINGS") != null ? System.getenv("URL_SETTINGS") : PROPERTIES.getString("url_settings");
    public static final String USERNAME = System.getenv("USER_NAME") != null ? System.getenv("USER_NAME") : PROPERTIES.getString("user_name");
    public static final String PASSWORD = System.getenv("PASSWORD") != null ? System.getenv("PASSWORD") : PROPERTIES.getString("password");

    // Test Data
    public static final String CALL_FORWARDING_DEFAULT = "0412345671";
    public static final String CALL_FORWARDING_433_FORMAT = "0412 345 671";
    public static final String CALL_FORWARDING_433_FORMAT_2 = "0412 345 672";
    public static final String CALL_FORWARDING_244_FORMAT = "04 1234 5671";
    public static final String SUCCESS_MESSAGE = "We have successfully updated your SIM settings.";
    public static final String CALL_FORWARDING_NUMBER = "Forward calls to %s";

    public enum Options {
        YES, NO
    }

}
