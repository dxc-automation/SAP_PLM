package com.sap.test_scripts.desktop.transactions;

import com.jacob.activeX.ActiveXComponent;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.objects.desktop.transactions.ZFTPR_CBK_CONFIG_DS_Obj;
import com.sap.properties.TestDataReader;
import com.sap.test_scripts.desktop.commonly_used.CommandField;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.io.IOException;

import static com.sap.config.ExtentReport.startTestReport;
import static com.sap.config.ExtentReport.test;
import static com.sap.properties.TestDataReader.*;

@Listeners(TestNGListener.class)
public class ZFTPR_CBK_CONFIG_DS extends GeneralTestConfig {

    private CommandField commandField = new CommandField();
    private TestDataReader testDataReader = new TestDataReader();

    private String transactionCode = "ZFTPR_CBK_CONFIG_DS";
    private String transactionWindowTitle = "Maintain DocuSign Email Id cost object mapping";

    private ZFTPR_CBK_CONFIG_DS_Obj zftpr_cbk_config_ds = new ZFTPR_CBK_CONFIG_DS_Obj();

    private final String menuBarSystemStatus = "wnd[0]/mbar/menu[6]/menu[11]";


    public void openTransaction(String scenarioType, int testCaseNumber) throws Exception {
        String testCaseString = testDataReader.getTestCaseID(scenarioType, testCaseNumber);

        // Declare what will be information printed in the report
        testName = "<b>Validate Docusign CostObject mapping</b>";
        testDescription =
                "<br><b>* * * &Tab; T E S T &nbsp; &nbsp; S T E P S &Tab; * * *</b><br>" +
                        "[1] Call transaction code using command field.<br>" +
                        "[2] Open system status dialog and verify transaction details.<br>" +
                        "[3] Set payment type filter.<br>" +
                        "[4] Search for specific email id.<br>" +
                        "[5] Save transaction with incorrect data.<br>" +
                        "[6] Save transaction with correct data.<br>" +
                        "[7] Close transaction.";

        // Start report listener
        startTestReport("desktop", testName, testDescription, testCaseString);

        // Search for transaction
        commandField.searchForTransaction(transactionCode);
        commandField.checkSearchResult(transactionCode, menuBarSystemStatus);
    }

    public void exportTable(String scenarioType, int testCaseNumber) throws Exception {
        zftpr_cbk_config_ds.clickExportButton();
        zftpr_cbk_config_ds.selectExportSpreadsheetDropdownOption();
        zftpr_cbk_config_ds.clickEnterButton();
        zftpr_cbk_config_ds.selectExportProcessingMode(scenarioType, testCaseNumber);
        zftpr_cbk_config_ds.clickEnterButton();
        zftpr_cbk_config_ds.clickEnterButton();
    }


    public void searchForEmailID(String scenarioType, int testCaseNumber) throws Exception {
        getSession();
        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/usr/cntlALV_CONTAINER/shellcont/shell").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("pressToolbarButton", "&FIND");

        String emailID = testDataReader.getViewMode(scenarioType, testCaseNumber);
        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[1]/usr/txtGS_SEARCH-VALUE").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.setProperty("Text", emailID);

        autoItX.sleep(delay);
        autoItX.winActivate("Find");
        autoItX.controlFocus("Find", "", "Button2");
        autoItX.sleep(delay);
        autoItX.controlClick("Find", "", "Button2");

        autoItX.sleep(3000);
        String result = autoItX.controlGetText(transactionWindowTitle,"","59393");
        Assert.assertNotEquals(result, "No Hit Found", "No search results");
        test.pass("Verify that the user can use SAP search to find specified transaction<br>Search for <b>" + emailID + "</b><br>Search status is <b>" + result + "</b>");
    }


    public void setFilter(String scenarioType, int testCaseNumber) throws Exception {
        getSession();
        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/usr/cntlALV_CONTAINER/shellcont/shell").toDispatch());
        Obj.invoke("selectAll");

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/usr/cntlALV_CONTAINER/shellcont/shell").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("pressToolbarButton", "&MB_FILTER");

        String paymentTypeFilter = testDataReader.getAction(scenarioType, testCaseNumber);
        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[1]/usr/ctxt%%DYN004-LOW").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.setProperty("Text", paymentTypeFilter);
        autoItX.sleep(delay);

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[1]/tbar[0]/btn[0]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");
        test.pass("Verify that the user can set filter<br>Filter by <b>Payment Type - " + paymentTypeFilter + "</b>");
    }


    public void verifyErrorMessage() throws IOException {
        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/usr/cntlALV_CONTAINER/shellcont/shell").toDispatch());
        Obj.setProperty("selectedRows", "0");

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/usr/cntlALV_CONTAINER/shellcont/shell").toDispatch());
        Obj.invoke("doubleClickCurrentCell");

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/tbar[1]/btn[17]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");

        autoItX.winActivate(transactionWindowTitle);
        autoItX.controlSend(transactionWindowTitle,"","SAPALVGrid1","{RIGHT}");
        autoItX.controlSend(transactionWindowTitle,"","SAPALVGrid1","{RIGHT}");
        autoItX.controlSend(transactionWindowTitle,"","SAPALVGrid1","{RIGHT}");
        autoItX.controlSend(transactionWindowTitle,"","SAPALVGrid1","{RIGHT}");
        autoItX.controlSend(transactionWindowTitle,"","SAPALVGrid1","{UP}");
        autoItX.controlFocus(transactionWindowTitle, "", "SAPALVGrid1");
        autoItX.sleep(delay);
        autoItX.controlSend(transactionWindowTitle,"","SAPALVGrid1","{SPACE}");
        autoItX.controlSend(transactionWindowTitle,"","SAPALVGrid1","{ENTER}");
        autoItX.sleep(delay);

        autoItX.winWaitActive("Error Log");
        Assert.assertTrue(autoItX.winExists("Error Log"));
        test.pass("Verify that the error message appear if the user try to save transaction with empty required field");

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/shellcont").toDispatch());
        Obj.invoke("close");
    }


    public void saveRecordChanges(String scenarioType, int testCaseNumber) throws Exception {
        String chargeCode = testDataReader.getAmount(scenarioType, testCaseNumber);
        autoItX.winWaitActive(transactionWindowTitle);
        autoItX.winActivate(transactionWindowTitle);
        autoItX.controlFocus(transactionWindowTitle, "", "SAPALVGrid1");
        autoItX.sleep(delay);
        autoItX.controlSend(transactionWindowTitle,"","SAPALVGrid1", chargeCode);
        autoItX.sleep(delay);
        autoItX.controlSend(transactionWindowTitle,"","SAPALVGrid1","{ENTER}");
        autoItX.sleep(delay);

        autoItX.winWaitActive("Error Log");
        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/shellcont").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("close");

        String remarks = testDataReader.getChargeType(scenarioType, testCaseNumber);
        autoItX.winActivate(transactionWindowTitle);
        autoItX.controlSend(transactionWindowTitle,"","SAPALVGrid1","{RIGHT}");
        autoItX.controlSend(transactionWindowTitle,"","SAPALVGrid1","{RIGHT}");
        autoItX.controlFocus(transactionWindowTitle, "", "SAPALVGrid1");
        autoItX.sleep(delay);
        autoItX.controlSend(transactionWindowTitle,"","SAPALVGrid1", remarks);
        autoItX.sleep(delay);
        autoItX.controlSend(transactionWindowTitle,"","SAPALVGrid1","{ENTER}");
        autoItX.controlFocus(transactionWindowTitle, "", "SAPALVGrid1");
        autoItX.sleep(delay);

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/tbar[0]/btn[11]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");

        autoItX.winActivate(transactionWindowTitle);
        Assert.assertFalse(autoItX.winExists("Error Log"));
        test.pass("Verify that the user can enter correct data and save transaction successfully");
    }

    public void closeTransaction() throws IOException, InterruptedException {
        commandField.goToRootSAPMenu();
        test.pass("Check that transaction can be closed properly");
    }
}

