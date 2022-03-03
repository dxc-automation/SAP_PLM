package com.sap.test_cases.non_po_accrual_request;

import com.sap.config.BrowserManager;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.test_scripts.desktop.commonly_used.Login;
import com.sap.test_scripts.desktop.commonly_used.Logoff;
import com.sap.test_scripts.desktop.opt_vim.VIM_VA2;
import com.sap.test_scripts.desktop.opt_vim.VIM_WP;
import com.sap.test_scripts.desktop.opt_vim.ZWBREP_NPOACR_RPT;
import com.sap.test_scripts.web.General;
import com.sap.test_scripts.web.NonPOAccrualRequest;
import com.sap.test_scripts.web.ePayablesRequestPortal;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.sap.config.ExtentReport.startTestReport;
import static com.sap.config.TestNGListener.failedTests;
import static com.sap.config.TestNGListener.passedTests;
import static com.sap.properties.DataReader.getNumberOfRows;
import static com.sap.properties.TestData.getAllTestData;
import static com.sap.properties.TestDataReader.*;
import static com.sap.properties.TestDataWriter.addToTemplate;
import static com.sap.utilities.Commons.checkTestStatus;
import static com.sap.utilities.FileUtility.saveStackTrace;

@Listeners(TestNGListener.class)
public class TS_46_47_Create_NPO_Accrual_Request_Currency extends GeneralTestConfig {
    private Login login = new Login();
    private Logoff logoff = new Logoff();
    private ZWBREP_NPOACR_RPT zwbrep_npoacr_rpt = new ZWBREP_NPOACR_RPT();
    private ePayablesRequestPortal ePayablesRequestPortal = new ePayablesRequestPortal();
    private NonPOAccrualRequest nonPOAccrualRequest = new NonPOAccrualRequest();
    private BrowserManager browserManager = new BrowserManager();
    private General general = new General();

    public int detectedNumberOfTests;
    private String scenarioType = "TS_46_&_47_NPO_Local_Currency";

    @BeforeClass
    public void countTestDataRows() throws Exception {
        detectedNumberOfTests = getNumberOfRows(scenarioType);
        System.out.println(detectedNumberOfTests + " # Tests are detected");
    }


    @Test
    public void npoRequestWithLocalCurrency() throws Exception {
        for (int testCaseNumber = 1; true; testCaseNumber++) {
            if (testCaseNumber > detectedNumberOfTests) {
                break;
            }
            String testEnabledDisabled = checkTestStatus(scenarioType, testCaseNumber);

            if (testEnabledDisabled.equals("disabled")) {
                System.out.println("\nTest Case No " + testCaseNumber + " is disabled");
                continue;
            }
            try {
                System.out.println("\nTest No " + testCaseNumber + " is running.......");
                String testCaseString = getTestCaseID(scenarioType, testCaseNumber);
                getAllTestData(scenarioType, testCaseNumber);

                testName = "<b>Create NPO Accrual - Local Currency</b>";
                testDescription =
                        "<b><i class=\"fa fa-desktop blue-color \"></i> Submit NPO accrual request in local currency.</b><br><br>" +
                                "Validate that the user can submit NPO Accrual Request (CO/HQ) from ePayables Request Portal with local currency. This request should go for final review/posting in SAP and VIM reflects equivalent USD value.<br>" +
                                "[1] Open ePayables Request Portal in browser.<br>" +
                                "[2] Submit a Non-PO Accrual Request (CO/HQ).<br>" +
                                "[3] Enter the transaction code from the SAP Easy Access World Bank.<br>" +
                                "[4] Enter search filter criteria.<br>" +
                                "[5] Verify search result details.<br>";

                // Start report listener
                startTestReport("desktop", testName, testDescription, testCaseString);

                browserManager.browserConfig("chrome");

                nonPOAccrualRequest.proceedNonPoAccrualRequest();
                general.logon();
                nonPOAccrualRequest.clickContinueButton();

                // Company code & Business Area
                nonPOAccrualRequest.companyCode();
                nonPOAccrualRequest.businessArea();
                nonPOAccrualRequest.clickContinueButton();

                nonPOAccrualRequest.requestFor();
                nonPOAccrualRequest.eventNaturePurchase(scenarioType, testCaseNumber);
                nonPOAccrualRequest.serviceFrom();
                nonPOAccrualRequest.serviceTo();
                nonPOAccrualRequest.ttlUPI();
                nonPOAccrualRequest.accrualCurrency();
                nonPOAccrualRequest.paymentExpectedMode();

                nonPOAccrualRequest.clickContinueButton();

                // Accrual/Charge Code Information
                nonPOAccrualRequest.amount();
                nonPOAccrualRequest.chargeType();
                nonPOAccrualRequest.chargeCode();
                nonPOAccrualRequest.glCategory();
                nonPOAccrualRequest.addText(scenarioType, testCaseNumber);
                nonPOAccrualRequest.addProformaInvoiceAttachment();
                nonPOAccrualRequest.addTttlReviewersApproval();
                nonPOAccrualRequest.selectApprover();
                nonPOAccrualRequest.clickContinueButton();

                // Submit request
                nonPOAccrualRequest.confirmationMsg();
                nonPOAccrualRequest.submitRequest();

                // SAP reviewer account login
                login.setAccount("reviewer");
                login.logonServer();

                // Transaction actions
                zwbrep_npoacr_rpt.openTransaction();
                zwbrep_npoacr_rpt.searchDocument();
                zwbrep_npoacr_rpt.validateSearchResult();
                logoff.logOff();

                general.checkRequestStatus(scenarioType, testCaseNumber);
                addToTemplate(scenarioType, testCaseNumber, "pass");
                browserManager.tearDownDriver();
                passedTests++;
            } catch (Throwable throwable) {
                // Print stack trace into console
                throwable.printStackTrace();
                String exception = throwable.fillInStackTrace().toString();
                saveStackTrace(throwable.getMessage());

                addToTemplate(scenarioType, testCaseNumber, "fail");
                passFailScreenshot("<b>Reason for failure: </b>" + throwable.getMessage() + "<center><br><b>Failed on screen</b></center>", scenarioType, "fail", "desktop");

                failedTests++;
                logoff.logOff();
            }
        }
    }
}
