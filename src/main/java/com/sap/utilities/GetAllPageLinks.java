package com.sap.utilities;

import com.sap.config.GeneralTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.sap.config.ExtentReport.*;


public class GetAllPageLinks extends GeneralTestConfig {


    private static final Logger LOG  = LogManager.getLogger(GetAllPageLinks.class);

    public static List<WebElement> hyperlinks;

    public static void allLinks() throws Exception {
        for (WebElement url : hyperlinks) {
            if ((url.getAttribute("href").trim().length() != 0) && (url.getText().trim().length() != 0)) {
                test.info(url.getAttribute("aria-label").trim() + "   ===>   " + url.getAttribute("href").trim());
            }
        }
    }
}

