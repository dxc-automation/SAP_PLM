package com.sap.test_cases.non_po_accrual_request;

import com.sap.config.BrowserManager;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.test_scripts.desktop.commonly_used.Login;
import com.sap.test_scripts.desktop.commonly_used.Logoff;
import com.sap.test_scripts.desktop.opt_vim.VIM_VA2;
import com.sap.test_scripts.desktop.opt_vim.VIM_WP;
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
public class TS_32_Create_NPO_Accrual_Request_Excel_Upload extends GeneralTestConfig {
    private Login login = new Login();
    private Logoff logoff = new Logoff();
    private VIM_VA2 vim_va2 = new VIM_VA2();
    private VIM_WP vim_wp = new VIM_WP();
    private ePayablesRequestPortal ePayablesRequestPortal = new ePayablesRequestPortal();
    private NonPOAccrualRequest nonPOAccrualRequest = new NonPOAccrualRequest();
    private BrowserManager browserManager = new BrowserManager();
    private General general = new General();

    public int detectedNumberOfTests;
    private String scenarioType = "TS_32_Create_NPO_Accrual_Excel";

    @BeforeClass
    public void countTestDataRows() throws Exception {
        detectedNumberOfTests = getNumberOfRows(scenarioType);
        TEST_DATA.setDetectedNumberOfTests(detectedNumberOfTests);
        System.out.println(detectedNumberOfTests + " # Tests are detected");
    }


    @Test
    public void createNpoAccrualRequestExcelUpload() throws Exception {
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

                testName = "<b>Create NPO Accrual - Excel Upload</b>";
                testDescription =
                        "<b><i class=\"fa fa-desktop blue-color \"></i> Search by Document Type</b><br><br>" +
                                "Validate that the user can create NPO Accrual Request (CO/HQ) from ePayables Request Portal using excel file.<br>" +
                                "[1] Open ePayables Request Portal in browser.<br>" +
                                "[2] Submit a Non-PO Accrual Request (CO/HQ)." +
                                "[3] Enter the transaction code from the SAP Easy Access World Bank.<br>" +
                                "[4] Enter specific Document Process Number.<br>" +
                                "[5] Verify that specific document pending for approval.<br>" +
                                "[5] Export search results to HTML file.";

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
                nonPOAccrualRequest.uploadSplitCharges();
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
                vim_va2.openTransaction(testCaseNumber);
                vim_va2.searchByDocumentProcessingNumber();
                vim_va2.checkSearchResults();
                vim_va2.exportResults();
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
