package com.sap.test_cases.non_po_accrual_request;

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
public class TC_Web extends GeneralTestConfig {
    private Login  login  = new Login();
    private Logoff logoff = new Logoff();
    private VIM_WP vim_wp = new VIM_WP();
    private ePayablesRequestPortal ePayablesRequestPortal = new ePayablesRequestPortal();
    private NonPOAccrualRequest    nonPOAccrualRequest    = new NonPOAccrualRequest();
    private BrowserManager browserManager = new BrowserManager();
    private General general = new General();

    private int testCaseNumber;
    private int detectedNumberOfTests;
    private String scenarioType = "NPOARC_Obsolete";

    @BeforeClass
    public void countTestDataRows() throws Exception {
        detectedNumberOfTests = getNumberOfRows(scenarioType);
        System.out.println("# of tests " + detectedNumberOfTests);
    }

    @Test
    public void Scenario_Web() throws Exception {
        testCaseNumber = 1;
        do {
            try {
                if (!checkTestStatus(scenarioType, testCaseNumber)) {
                    System.out.println("\nTest Case No " + testCaseNumber + " is disabled");
                    testCaseNumber++;
                }

                String testCaseString = getTestCaseID(scenarioType, testCaseNumber);

                // Declare what will be information printed in the report
                testName = "<b>Non-PO Accrual Request</b>";
                testDescription =
                        "<b>Send Non-PO Accrual Request</b><br><br>" +
                                "Validate that the user can send Non-PO Accrual Request from web portal.<br>" +
                                "[1] Login SAP Web portal.<br>" +
                                "[2] Read and save test data from excel spreadsheet.<br>" +
                                "[3] Send Non-PO Accrual Request.";

                // Start report listener
                startTestReport("web", testName, testDescription, testCaseString);

                getAllTestData(scenarioType, testCaseNumber);
                browserManager.browserConfig("chrome");

                nonPOAccrualRequest.proceedNonPoAccrualRequest();
                general.logon();
                nonPOAccrualRequest.clickContinueButton();

                // Company code & Business Area
                nonPOAccrualRequest.companyCode();
                nonPOAccrualRequest.businessArea();
                nonPOAccrualRequest.clickContinueButton();

                nonPOAccrualRequest.lastCardDigits();
                nonPOAccrualRequest.serviceFrom();
                nonPOAccrualRequest.serviceTo();
                nonPOAccrualRequest.ttlUPI();
                nonPOAccrualRequest.cardHolderUPI();
                nonPOAccrualRequest.cardCyclePeriod();
                nonPOAccrualRequest.accrualCurrency();
                nonPOAccrualRequest.eventNaturePurchase(scenarioType, testCaseNumber);
                nonPOAccrualRequest.lastCardDigits();
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

                addToTemplate(scenarioType, testCaseNumber, "pass");
                testCaseNumber++;
                passedTests++;
                driver.close();

            } catch (Throwable throwable) {
                // Print stack trace into console
                throwable.printStackTrace();
                String exception = throwable.fillInStackTrace().toString();
                saveStackTrace(exception);
                addToTemplate(scenarioType, testCaseNumber, "fail");

                if (exception.contains("jacob")) {
                    passFailScreenshot("Failed on screen: ", scenarioType, "fail", "desktop");
                }

                passFailScreenshot("Failed on screen: ", scenarioType, "fail", "web");
                testCaseNumber++;
                failedTests++;
                driver.quit();
            }
        }
        while (testCaseNumber == detectedNumberOfTests);
    }
}
