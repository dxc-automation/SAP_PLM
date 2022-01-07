package com.sap.test_cases.non_po_accrual_request;

import com.sap.config.BrowserManager;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.test_scripts.desktop.commonly_used.Login;
import com.sap.test_scripts.desktop.commonly_used.Logoff;
import com.sap.test_scripts.desktop.opt_vim.VIM_WP;
import com.sap.test_scripts.desktop.opt_vim.ZWFINVAPP;
import com.sap.test_scripts.web.General;
import com.sap.test_scripts.web.NonPOAccrualRequest;
import com.sap.test_scripts.web.ePayablesRequestPortal;
import org.testng.annotations.*;

import static com.sap.config.ExtentReport.startTestReport;
import static com.sap.config.ExtentReport.test;
import static com.sap.config.TestNGListener.failedTests;
import static com.sap.config.TestNGListener.passedTests;
import static com.sap.properties.DataReader.getNumberOfRows;
import static com.sap.properties.TestData.getAllTestData;
import static com.sap.properties.TestDataReader.*;
import static com.sap.properties.TestDataWriter.addToTemplate;
import static com.sap.utilities.Commons.checkTestStatus;
import static com.sap.utilities.FileUtility.saveStackTrace;

@Listeners(TestNGListener.class)
public class TC_Approve_Reject extends GeneralTestConfig {
    private Login  login  = new Login();
    private Logoff logoff = new Logoff();

    private ePayablesRequestPortal ePayablesRequestPortal = new ePayablesRequestPortal();
    private NonPOAccrualRequest    nonPOAccrualRequest    = new NonPOAccrualRequest();
    private BrowserManager browserManager = new BrowserManager();
    private General general = new General();

    private String scenarioType = "NPOARC_Approve_Reject";
    private int detectedNumberOfTests;
    private int testCaseNumber;


    @BeforeClass
    public void countTestDataRows() throws Exception {
        detectedNumberOfTests = getNumberOfRows(scenarioType);
        System.out.println("# of tests " + detectedNumberOfTests);
    }


    @Test
    public void Scenario_1() throws Exception {
            testCaseNumber = 1;
            do {
                try {
                    if (!checkTestStatus(scenarioType, testCaseNumber)) {
                        System.out.println("\nTest Case No " + testCaseNumber + " is disabled");
                        testCaseNumber++;
                    }

                    String testCaseString = getTestCaseID(scenarioType, testCaseNumber);

                    // Declare what will be information printed in the report
                    testName = "<b>Approve/Reject Non-PO Accrual Request</b>";
                    testDescription =
                                    "<b>Summary of Test</b>" +
                                            "<br>The test scope includes submit non-PO payment request from ePayables web portal and approve or reject non-PO payment request." +
                                            "<br><br><b>Test Steps</b>" +
                                            "<br>[1] Log in ePayables request portal." +
                                            "<br>[2] Submit non-PO accrual payment request." +
                                            "<br>[3] Log in SAP NetWear." +
                                            "<br>[4] Take action on the work item.<br>";

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

                    // SAP reviewer account login
                    login.setAccount("reviewer");
                    login.logonServer();

                    // Transaction actions
                    VIM_WP vim_wp = new VIM_WP();
                    vim_wp.openTransaction(testCaseNumber);
                    vim_wp.setWorkViewMode(scenarioType, testCaseNumber);
                    vim_wp.searchDocumentId();
                    vim_wp.validateReviewerDocumentDetails(scenarioType, testCaseNumber);
                    vim_wp.save();
                    vim_wp.submit(scenarioType, testCaseNumber);
                    vim_wp.closeTransaction();
                    logoff.logOff();

                    // SAP manager account login
                    login.setAccount("manager");
                    login.logonServer();

                    // Transaction actions
                    ZWFINVAPP zwfinvapp = new ZWFINVAPP();
                    zwfinvapp.openTransaction(testCaseNumber);
                    zwfinvapp.searchForDocument();
                    zwfinvapp.reassignRequest();
                    logoff.logOff();

                    // SAP manager account login
                    login.setAccount("approver");
                    login.logonServer();

                    // Transaction actions
                    zwfinvapp.openTransaction(testCaseNumber);
                    zwfinvapp.searchForDocument();
                    zwfinvapp.displayDP(testCaseNumber);
                    zwfinvapp.takeAction(scenarioType, testCaseNumber);
                    logoff.logOff();

                    general.checkRequestStatus(scenarioType, testCaseNumber);

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