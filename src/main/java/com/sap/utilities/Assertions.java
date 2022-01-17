package com.sap.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.io.IOException;

import static com.sap.config.ExtentReport.test;
import static com.sap.config.GeneralTestConfig.*;

public class Assertions {


    public static boolean verifyString(String expected, String actual) {
        boolean contains = expected.contains(actual);
        return contains;
    }


    public static boolean elementExistsAssertion(WebElement element) {
        if (element.getSize() != null) {
            return true;
        } else {
            return false;
        }
    }

    public static void amountValidation(String actual, String expected) throws IOException {
        String actualAmount = actual.replace(",", "").replace(".", "");
        String expectedAmount = expected.replace(",", "").replace(".", "");
        if (actualAmount.equals(expectedAmount)) {
            System.out.println("Amount is checked");
        } else {
            passFailScreenshot("<b>Found difference between expected and actual amount !</b><br>Expected amount is " + expected + " but found "+ actual, actualAmount, "fail", "desktop");
        }
    }


    public static void valuesValidation(String property, String actual, String expected) throws IOException {
        if (actual.equalsIgnoreCase(expected)) {
            System.out.println(actual + " is validated");
        } else {
            passFailScreenshot("<b>Found difference between " + property + " !</b><br>Expected " + expected + " but found " + actual, actual, "fail", "desktop");
        }
    }


    public static ExpectedCondition<Boolean> textToBePresentInElement(By locator, String text) {

        return new ExpectedCondition<Boolean>() {
            WebElement element = driver.findElement(locator);
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    String elementText = element.getText();
                    return elementText.contains(text);
                } catch (StaleElementReferenceException e) {
                    return false;
                }
            }
            @Override
            public String toString() {
                return String.format("text ('%s') to be present in element %s", text, element);
            }
        };
    }



    public boolean checkPageTitle(String expectedPageTitle) {
        String pageTitle   = driver.getTitle();
        if (pageTitle.equals(expectedPageTitle)) {
            return true;
        } else {
            return false;
        }
    }
}
