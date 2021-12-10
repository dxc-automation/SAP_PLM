package com.sap.test_cases.templates;

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

@Listeners(TestNGListener.class)
public class TestTemplate extends GeneralTestConfig {
    private Login  login  = new Login();
    private Logoff logoff = new Logoff();
    private VIM_WP vim_wp = new VIM_WP();
    private ePayablesRequestPortal ePayablesRequestPortal = new ePayablesRequestPortal();
    private NonPOAccrualRequest    nonPOAccrualRequest    = new NonPOAccrualRequest();
    private BrowserManager browserManager = new BrowserManager();
    private General general = new General();

    private int testCaseNumber;
    private int detectedNumberOfTests;
    // Excel sheet name with test data
    private String scenarioType = "";

    @BeforeClass
    public void countTestDataRows() throws Exception {
        detectedNumberOfTests = getNumberOfRows(scenarioType);
        System.out.println("# of tests " + detectedNumberOfTests);
    }

    @Test
    public void template() throws Exception {
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

                // Load test data from the excel sheet
                getAllTestData(scenarioType, testCaseNumber);

                // Launch browser
                browserManager.browserConfig("chrome");


                // SAP reviewer account login
                login.setAccount("reviewer");
                login.logonServer();

                general.checkRequestStatus(scenarioType, testCaseNumber);

                addToTemplate(scenarioType, testCaseNumber, "pass");
                testCaseNumber++;
                passedTests++;
                driver.close();

            } catch (Throwable throwable) {
                // Print stack trace into console
                throwable.printStackTrace();

                addToTemplate(scenarioType, testCaseNumber, "fail");

                String exception = throwable.fillInStackTrace().toString();
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
