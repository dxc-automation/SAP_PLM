package com.sap.test_scripts.desktop.opt_vim;

import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.objects.desktop.documents.ProcessNonPOAccuralRequest;
import com.sap.objects.desktop.opt_vim.ZWBREP_NPOACR_RPT_Obj;
import com.sap.objects.desktop.opt_vim.ZWFINVAPP_Obj;
import com.sap.properties.TestDataReader;
import com.sap.test_scripts.desktop.commonly_used.CommandField;
import com.sap.test_scripts.desktop.commonly_used.Dialogs;
import com.sap.test_scripts.desktop.commonly_used.DocumentActions;
import com.sap.test_scripts.desktop.commonly_used.DocumentDetails;
import org.apache.commons.math3.analysis.function.Add;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.io.IOException;

import static com.sap.config.ExtentReport.addTestReport;
import static com.sap.config.ExtentReport.test;
import static com.sap.properties.TestDataReader.getAction;

@Listeners(TestNGListener.class)
public class ZWBREP_NPOACR_RPT extends GeneralTestConfig {

    private CommandField commandField = new CommandField();
    private ZWBREP_NPOACR_RPT_Obj zwbrep_npoacr_rpt_obj = new ZWBREP_NPOACR_RPT_Obj();
    private DocumentActions documentActions = new DocumentActions();
    private Dialogs dialogs = new Dialogs();
    private TestDataReader testDataReader = new TestDataReader();


    private String menuBarSystemStatus = "wnd[0]/mbar/menu[3]/menu[11]";
    private String tCode = "ZWBREP_NPOACR_RPT";



    public void openTransaction() throws Exception {
        addTestReport(
                  "desktop",
                  "VIM Non-PO Accrual Report",
                "Open transaction with code <b>" + tCode + "</b>");

        commandField.searchForTransaction(tCode);
        commandField.checkSearchResult(tCode, menuBarSystemStatus);
    }


    public void searchDocument() throws IOException, InterruptedException {
        zwbrep_npoacr_rpt_obj.selectDocumentType();
        zwbrep_npoacr_rpt_obj.enterDocumentCreateDate();
        zwbrep_npoacr_rpt_obj.enterDocumentNumber();
        zwbrep_npoacr_rpt_obj.clickExecute();

        addTestReport(
                  "desktop",
                  "Search Non-PO Accrual Report",
                "Document search criteria:<br><p>" +
                        "Request ID &Tab; <b>"    + TEST_DATA.getRequestID()  + "</b><br>" +
                        "Create Date &Tab; <b>"   + commons.getCurrentDate()  + "</b><br>" +
                        "DP Type &Tab; &Tab; <b>" + TEST_DATA.getDpDocumentType() + "</b>");
    }


    public void validateSearchResult() throws IOException, InterruptedException {
        zwbrep_npoacr_rpt_obj.displayDocumentDetails();
        dialogs.cancelDownloadFile();

        DocumentDetails documentDetails = new DocumentDetails();
        String originalAmount = documentDetails.getOtherData("amount");
        String subAmount = documentDetails.getOtherData("subAmount");
        String currency = documentDetails.getOtherData("currency");
        documentDetails.closeDocumentDetailsWindow();


        addTestReport(
                "desktop",
                 "Validate Search Result Details",
                "Original submitted amount <b>" + originalAmount + " " + currency + "</b><br>" +
                        "USD equivalent amount <b>" + subAmount + "</b>"
        );
    }

}