package com.sap.test_scripts.web;

import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.objects.web.NonPoAccrualRequestPage;
import com.sap.objects.web.ePayablesHomePage;
import org.testng.annotations.Listeners;

import static com.sap.properties.DataReader.getPropertiesFile;

@Listeners(TestNGListener.class)
public class ePayablesRequestPortal extends GeneralTestConfig {

    private ePayablesHomePage ePayablesHomePage = new ePayablesHomePage(driver);
    private NonPoAccrualRequestPage nonPoAccrualRequestPage = new NonPoAccrualRequestPage(driver);

    // Open ePayables Request Portal
    public void openSubmitRequestPage(String requestName) throws Exception {
        String url;

        switch (requestName) {
            case "npr":
                url = getPropertiesFile("nprUrl");
                driver.get(url);
                break;
            case "err":
                url = getPropertiesFile("errUrl");
                driver.get(url);
                break;
            case "lease":
                url = getPropertiesFile("leaseUrl");
                driver.get(url);
                break;
        }
    }
}