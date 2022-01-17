package com.sap.test_scripts.desktop.transactions;

import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.test_scripts.desktop.commonly_used.CommandField;
import org.testng.annotations.Listeners;

import static com.sap.config.ExtentReport.startTestReport;
import static com.sap.properties.TestDataReader.testDescription;
import static com.sap.properties.TestDataReader.testName;

@Listeners(TestNGListener.class)
public class ZFTPR_CBK_RM_REP extends GeneralTestConfig {

    private CommandField commandField = new CommandField();

    private String transactionWindowTitle = "Chargeback details report";
    private String transactionCode = "ZFTPR_CBK_RM_REP";

    private final String executeBtn = "119";
    private final String menuBarSystemStatus = "wnd[0]/mbar/menu[6]/menu[11]";


    public void openTransaction(String scenarioType, int testCaseNumber) throws Exception {
        String testCaseString = testDataReader.getTestCaseID(scenarioType, testCaseNumber);

        // Declare what will be information printed in the report
        testName = "<b>Chargeback details report</b>";
        testDescription =
                "<br><b>* * * &Tab; T E S T &nbsp; &nbsp; S T E P S &Tab; * * *</b><br>" +
                        "[1] Expand <i>Institutional Chargeback</i> folder.<br>" +
                        "[2] Expand <i>Config</i> folder.<br>" +
                        "[3] Open <i>DocuSign Chargeback Configuration</i> transaction.<br>" +
                        "[4] Check search for transaction functionality.<br>" +
                        "[5] Check filtering functionality.<br>" +
                        "[6] Enter valid password.<br>" +
                        "[7] Click on Execute button.";

        // Start report listener
        startTestReport("desktop", testName, testDescription, testCaseString);

        commandField.searchForTransaction(transactionCode);
        commandField.checkSearchResult(transactionCode, menuBarSystemStatus);
    }
}