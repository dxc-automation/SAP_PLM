package com.sap.test_scripts.desktop.opt_vim;

import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.objects.desktop.documents.ProcessNonPOAccuralRequest;
import com.sap.objects.desktop.opt_vim.ZWFINVAPP_Obj;
import com.sap.properties.TestDataReader;
import com.sap.test_scripts.desktop.commonly_used.CommandField;
import com.sap.test_scripts.desktop.commonly_used.Dialogs;
import com.sap.test_scripts.desktop.commonly_used.DocumentActions;
import org.testng.annotations.Listeners;

import java.io.IOException;

import static com.sap.config.ExtentReport.addTestReport;
import static com.sap.config.ExtentReport.test;
import static com.sap.properties.TestDataReader.getAction;

@Listeners(TestNGListener.class)
public class ZWBREP_NPOACR_RPT extends GeneralTestConfig {

    private CommandField commandField = new CommandField();
    private DocumentActions documentActions = new DocumentActions();
    private Dialogs dialogs = new Dialogs();
    private ZWFINVAPP_Obj zwfinvapp_obj = new ZWFINVAPP_Obj();
    private ProcessNonPOAccuralRequest processNonPOAccuralRequest = new ProcessNonPOAccuralRequest();
    private TestDataReader testDataReader = new TestDataReader();


    private String menuBarSystemStatus = "wnd[0]/mbar/menu[3]/menu[11]";
    private String tCode = "ZWBREP_NPOACR_RPT";



    public void openTransaction(int testCaseNumber) throws Exception {
        addTestReport(
                "desktop",
                "Access SAP Approve ERR and Non-PO VIM Request",
                "Open transaction with code <b>" + zwfinvapp_obj.transactionCode + "</b>");

        commandField.searchForTransaction(tCode);
        commandField.checkSearchResult(tCode, menuBarSystemStatus);
    }


    public void searchByDocumentCreatedDate() {

    }

}