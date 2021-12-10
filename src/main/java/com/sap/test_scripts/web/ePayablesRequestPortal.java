package com.sap.test_scripts.web;

import com.sap.config.ExtentLink;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.objects.web.NonPoAccrualRequestPage;
import com.sap.objects.web.ePayablesHomePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;

import static com.sap.config.BrowserManager.wait;
import static com.sap.config.ExtentReport.*;
import static com.sap.properties.DataReader.getProperties;
import static com.sap.properties.TestDataReader.*;
import static com.sap.properties.TestData.*;
import static com.sap.properties.TestDataWriter.addToTemplate;
import static com.sap.utilities.Commons.delay;
import static com.sap.utilities.Commons.passwordEncryption;

@Listeners(TestNGListener.class)
public class ePayablesRequestPortal extends GeneralTestConfig {

    private ePayablesHomePage ePayablesHomePage = new ePayablesHomePage(driver);
    private NonPoAccrualRequestPage nonPoAccrualRequestPage = new NonPoAccrualRequestPage(driver);

    // Open ePayables Request Portal
    public void openSubmitRequestPage(String requestName) throws Exception {
        String url;

        switch (requestName) {
            case "npr":
                url = getProperties("nprUrl");
                driver.get(url);
                break;
            case "err":
                url = getProperties("errUrl");
                driver.get(url);
                break;
        }
    }
}