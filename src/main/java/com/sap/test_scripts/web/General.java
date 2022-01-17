package com.sap.test_scripts.web;

import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.objects.web.NonPoAccrualRequestPage;
import com.sap.objects.web.ePayablesHomePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;

import static com.sap.config.BrowserManager.wait;
import static com.sap.config.ExtentReport.addTestReport;
import static com.sap.properties.TestData.getNPRRequestURL;
import static com.sap.utilities.Commons.delay;
import static com.sap.utilities.Commons.passwordEncryption;

@Listeners(TestNGListener.class)
public class General extends GeneralTestConfig {

    private com.sap.objects.web.ePayablesHomePage ePayablesHomePage = new ePayablesHomePage(driver);
    private NonPoAccrualRequestPage     nonPoAccrualRequestPage     = new NonPoAccrualRequestPage(driver);

    public void logon() throws Exception {
        String user = testDataReader.getRequesterUser();
        String pass = testDataReader.getRequesterPass();

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(ePayablesHomePage.usernameInput)).sendKeys(user);

            String decrypted = passwordEncryption(pass);
            wait.until(ExpectedConditions.presenceOfElementLocated(ePayablesHomePage.passwordInput)).sendKeys(decrypted);

            wait.until(ExpectedConditions.elementToBeClickable(ePayablesHomePage.logonBtn)).click();
            delay(1000);

            try {
                commons.clickAction(nonPoAccrualRequestPage.duplicatedSessionContinueBtn);
                delay(500);
            } catch (Exception e) {
                System.out.println("Duplicated session has been terminated !");
            }
        } catch (Exception e) {
            System.out.println("Requester account is already logged in");
        }

        addTestReport(
                "web",
                "Log in ePayables Request Portal",
                "Log into web ePayables request portal with UPI <b>" + user + "</b>");
    }


    public void checkRequestStatus(String scenarioType, int testCaseNumber) throws Exception {
        String requestId = TEST_DATA.getRequestID();
        String url = getNPRRequestURL(requestId);
        delay(5000);
        driver.get(url);

        wait.until(ExpectedConditions.presenceOfElementLocated(ePayablesHomePage.requestStatus));
        passFailScreenshot("", scenarioType, "normal", "web");

        String requestStatusFromWeb = driver.findElement(ePayablesHomePage.requestStatus).getText();
        TEST_DATA.setRequestStatusFromWeb(requestStatusFromWeb);
        System.out.println(requestStatusFromWeb);

        addTestReport(
                "web",
                "Validate Request Status",
                "Request Status <b>" + requestStatusFromWeb + "</b>" +
                        "<br>Request ID <b>" + TEST_DATA.getRequestID() + "</b>" +
                        "<br><a href='" + url + "'>Link</a>");
    }
}