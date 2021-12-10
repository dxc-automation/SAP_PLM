package com.sap.utilities;

import com.sap.config.GeneralTestConfig;
import org.openqa.selenium.WebElement;


public class GetElementText extends GeneralTestConfig {

    public static String getElementText(WebElement element) {
        String elementText = element.getText();
        return elementText;
    }
}