package com.sap.test_cases.employee_reimbursment_request;

import com.sap.config.BrowserManager;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.test_scripts.desktop.commonly_used.Login;
import com.sap.test_scripts.desktop.commonly_used.Logoff;
import com.sap.test_scripts.desktop.opt_vim.VIM_WP;
import com.sap.test_scripts.web.General;
import com.sap.test_scripts.web.NonPOAccrualRequest;
import com.sap.test_scripts.web.ePayablesRequestPortal;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import static com.sap.properties.DataReader.getNumberOfRows;

@Listeners(TestNGListener.class)
public class Test extends GeneralTestConfig {
    private Login login = new Login();
    private Logoff logoff = new Logoff();
    private VIM_WP vim_wp = new VIM_WP();
    private ePayablesRequestPortal ePayablesRequestPortal = new ePayablesRequestPortal();
    private NonPOAccrualRequest nonPOAccrualRequest = new NonPOAccrualRequest();
    private BrowserManager browserManager = new BrowserManager();
    private General general = new General();

    private int testCaseNumber;
    private int detectedNumberOfTests;
    private String scenarioType = "NPO_Accrual_Request_Obsolete";

    @BeforeClass
    public void initBrowser() throws Exception {
        detectedNumberOfTests = getNumberOfRows(scenarioType);
        System.out.println("# of tests " + detectedNumberOfTests);
    }

    @org.testng.annotations.Test
    public void Scenario_3_Obsolete() throws Exception {
        testCaseNumber = 1;
        TEST_DATA.getAllTestData(scenarioType, testCaseNumber);
        browserManager.browserConfig("chrome");

        nonPOAccrualRequest.proceedNonPoAccrualRequest();
        general.logon();
    }
}