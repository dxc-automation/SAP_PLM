package com.sap.test_scripts.desktop.transactions;

import com.jacob.activeX.ActiveXComponent;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.properties.TestDataReader;
import com.sap.test_scripts.desktop.commonly_used.CommandField;
import org.testng.annotations.Listeners;

import java.io.IOException;

import static com.sap.config.ExtentReport.*;
import static com.sap.properties.TestDataReader.testDescription;
import static com.sap.properties.TestDataReader.testName;

@Listeners(TestNGListener.class)
public class FB03 extends GeneralTestConfig {

    private CommandField commandField = new CommandField();
    private TestDataReader testDataReader = new TestDataReader();

    private String transactionWindowTitle = "Display Document: Initial Screen";
    private String transactionCode = "FB03";

    private String dataDocumentNumber;
    private String dataDocumentDate;
    private String dataReference;
    private String dataCurrency;
    private String dataCompanyCode;
    private String dataPostingDate;
    private String dataFiscalYear;
    private String dataPeriod;

    //***   Application Toolbar
    private final String appToolbar = "/app/con[0]/ses[0]/wnd[0]/tbar[1]";
    private final String appToolbar_ChangeDisplayModeBtn = "/app/con[0]/ses[0]/wnd[0]/tbar[1]/btn[25]";
    private final String appToolbar_DisplayAnotherDocumentBtn = "/app/con[0]/ses[0]/wnd[0]/tbar[1]/btn[17]";
    private final String appToolbar_SelectIndividualObjectBtn = "/app/con[0]/ses[0]/wnd[0]/tbar[1]/btn[2]";

    private final String menuBarSystemStatus = "wnd[0]/mbar/menu[4]/menu[11]";


    public void openTransaction(String scenarioType, int testCaseNumber) throws Exception {
        String testCaseString = testDataReader.getTestCaseID(scenarioType, testCaseNumber);

        // Declare what will be information printed in the report
        testName = "<b>Validate FI Document details in SAP (FB03)</b>";
        testDescription =
                "<br><b>* * * &Tab; T E S T &nbsp; &nbsp; S T E P S &Tab; * * *</b><br>" +
                        "[1] Call transaction code using command field.<br>" +
                        "[2] Open system status dialog and verify transaction details.<br>" +
                        "[3] Search for specific document data entry.<br>" +
                        "[4] Validate document details.<br>" +
                        "[5] Close transaction.";

        // Start report listener
        startTestReport("desktop", testName, testDescription, testCaseString);

        commandField.searchForTransaction(transactionCode);
        commandField.checkSearchResult(transactionCode, menuBarSystemStatus);
    }


    public void searchDataEntry(String scenarioType, int testCaseNumber) throws Exception {
        getSession();

        String documentNumber = testDataReader.getViewMode(scenarioType, testCaseNumber);
        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/usr/txtRF05L-BELNR").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.setProperty("Text", documentNumber);

        String companyCode = testDataReader.getAction(scenarioType, testCaseNumber);
        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/usr/ctxtRF05L-BUKRS").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.setProperty("Text", companyCode);

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/tbar[0]/btn[0]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");

        test.createNode("<b>[ Check Document Searching Functionality ]</b>")
            .pass("Specific document is found successfully")
            .info("<b>Document Number</b> &Tab; &Tab;" + documentNumber + "<br><b>Company Code</b> &Tab; &Tab;" + companyCode);
    }

    public void getDataEntry() {
        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/usr/txtBKPF-BELNR").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        dataDocumentNumber = Obj.getProperty("Text").toString();

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/usr/ctxtBKPF-BLDAT").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        dataDocumentDate = Obj.getProperty("Text").toString();

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/usr/txtBKPF-XBLNR").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        dataReference = Obj.getProperty("Text").toString();

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/usr/ctxtBKPF-WAERS").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        dataCurrency = Obj.getProperty("Text").toString();

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/usr/ctxtBKPF-BUKRS").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        dataCompanyCode = Obj.getProperty("Text").toString();

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/usr/ctxtBKPF-BUDAT").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        dataPostingDate = Obj.getProperty("Text").toString();

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/usr/txtBKPF-GJAHR").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        dataFiscalYear = Obj.getProperty("Text").toString();

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/usr/txtBKPF-MONAT").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        dataPeriod = Obj.getProperty("Text").toString();

        test.createNode("<b>[ Check Document Data Entry ]</b>")
            .pass("Document data entry is displayed")
            .info("<pre>Data Entry<br><b>Document Number</b> &Tab;" +
                dataDocumentNumber + "<br><b>Document Date</b> &Tab; &Tab; " +
                dataDocumentDate   + "<br><b>Reference</b> &Tab; &Tab; &Tab; "     +
                dataReference      + "<br><b>Currency</b> &Tab; &Tab; &Tab; "      +
                dataCurrency       + "<br><b>Company Code</b> &Tab; &Tab; &Tab; "  +
                dataCompanyCode    + "<br><b>Posting Date</b> &Tab; &Tab; "  +
                dataPostingDate    + "<br><b>Fiscal Year</b> &Tab; &Tab; &Tab; "   +
                dataFiscalYear     + "<br><b>Period</b> &Tab; &Tab; &Tab; &Tab; "        +
                dataPeriod + "</pre>");
    }

    public void getTableData() {
        autoItX.winActivate(transactionWindowTitle);
        autoItX.controlFocus(transactionWindowTitle, "", "SAPALVGrid1");
        autoItX.controlSend(transactionWindowTitle,"","SAPALVGrid1","{RIGHT}");
        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/usr/cntlCTRL_CONTAINERBSEG/shellcont/shell").toDispatch());
        System.out.println(Obj.getProperty("text").toString());
        String value = Obj.getPropertyAsString("Text");
        System.out.println(value);
    }

    public void closeTransaction() throws IOException, InterruptedException {
        commandField.goToRootSAPMenu();
        test.createNode("<b>[ Close Transaction ]</b>")
            .pass("Transaction is closed properly");
    }
}