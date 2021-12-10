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

import static com.sap.config.ExtentReport.*;
import static com.sap.properties.TestData.*;
import static com.sap.properties.TestDataReader.*;

@Listeners(TestNGListener.class)
public class ZWFINVAPP extends GeneralTestConfig {

    private CommandField commandField = new CommandField();
    private DocumentActions documentActions = new DocumentActions();
    private Dialogs dialogs = new Dialogs();
    private ZWFINVAPP_Obj zwfinvapp_obj = new ZWFINVAPP_Obj();
    private ProcessNonPOAccuralRequest processNonPOAccuralRequest = new ProcessNonPOAccuralRequest();
    private TestDataReader testDataReader = new TestDataReader();

    private String managerDocumentId;

    private String menuBarSystemStatus = "wnd[0]/mbar/menu[3]/menu[11]";



    public void openTransaction(int testCaseNumber) throws Exception {
        addTestReport(
                "desktop",
                "Access SAP Approve ERR and Non-PO VIM Request",
                "Open transaction with code <b>" + zwfinvapp_obj.transactionCode + "</b>");

        commandField.searchForTransaction(zwfinvapp_obj.transactionCodeSearch);
        commandField.checkSearchResult(zwfinvapp_obj.transactionCode, menuBarSystemStatus);
    }


    public void searchForDocument() throws IOException, InterruptedException {
        zwfinvapp_obj.setDocumentLedgerNumber();
        documentActions.clickExecute();
    }


    public void displayDP(int testCaseNumber) throws Exception {
        zwfinvapp_obj.clickDisplayDP();
        dialogs.cancelDownloadFile();

        String managerCurrency    = zwfinvapp_obj.getManagerBasicData("currency");
        String managerCompanyCode = zwfinvapp_obj.getManagerBasicData("companyCode");

        String managerAccountGL  = zwfinvapp_obj.getManagerLineItems("accountGL");
        String managerAmount     = zwfinvapp_obj.getManagerLineItems("amount");
        String managerCostCenter = zwfinvapp_obj.getManagerLineItems("costCenter");

        managerDocumentId   = zwfinvapp_obj.getManagerProcess("documentId");
        String managerDocumentType = zwfinvapp_obj.getManagerProcess("documentType");

        String managerIndexUser      = zwfinvapp_obj.getManagerOtherData("indexUser");
        String managerApprover       = zwfinvapp_obj.getManagerOtherData("approver");
        String managerServiceFrom    = zwfinvapp_obj.getManagerOtherData("serviceFrom");
        String managerServiceTo      = zwfinvapp_obj.getManagerOtherData("serviceTo");
        String managerRequestType    = zwfinvapp_obj.getManagerOtherData("requestType");
        String managerCardLastDigits = zwfinvapp_obj.getManagerOtherData("cardLastDigits");
        String managerTtlUPI         = zwfinvapp_obj.getManagerOtherData("ttlUPI");
        String managerCardHolderUPI  = zwfinvapp_obj.getManagerOtherData("cardHolderUPI");

        addTestReport(
                "desktop",
                "Non-PO Accrual Request Details",
                "Document ID &Tab; &Tab; <b>"             + managerDocumentId     + "</b><br>"
                        + "Document Type &Tab; <b>"              + managerDocumentType   + "</b><br>"
                        + "Index User &Tab; &Tab; <b>"           + managerIndexUser      + "</b><br>"
                        + "Approver &Tab; &Tab; <b>"             + managerApprover       + "</b><br>"
                        + "Service From &Tab; &Tab; <b>"         + managerServiceFrom    + "</b><br>"
                        + "Service To &Tab; &Tab; <b>"           + managerServiceTo      + "</b><br>"
                        + "Request Type &Tab; &Tab; <b>"         + managerRequestType    + "</b><br>"
                        + "UPI of TTL &Tab; &Tab; <b>"           + managerTtlUPI         + "</b><br>"
                        + "Card Holder UPI &Tab; <b>"            + managerCardHolderUPI  + "</b><br>"
                        + "Card Last Digits &Tab; <b>"           + managerCardLastDigits + "</b><br>"
                        + "G/L Account &Tab; &Tab; <b>"          + managerAccountGL      + "</b><br>"
                        + "Amount &Tab; &Tab; &Tab; <b>"         + managerAmount + " "   + managerCurrency + "</b><br>"
                        + "Cost Center &Tab; &Tab; <b>"          + managerCostCenter     + "</b><br>"
                        + "Company Code &Tab; <b>"               + managerCompanyCode    + "</b>");

        zwfinvapp_obj.closeDisplayedDP();
    }


    public void reassignRequest() {
        String userUPI = "WB350333";
        zwfinvapp_obj.reassign(userUPI);
        addTestReport(
                "desktop",
                "Reassign Work Item",
                "Work item re-assigned successfully to <b>" + userUPI + "</b>");
    }


    public void takeAction(String scenarioType, int testCaseNumber) throws Exception {
        String action = getAction(scenarioType, testCaseNumber);
        zwfinvapp_obj.approveReject(action);
        switch (action) {
            case "approve":
                addTestReport(
                        "desktop",
                        "Approve Non-PO Accrual Request",
                        "Non-PO Accrual Request with ID <b>" + managerDocumentId + "</b> is approved");
                break;
            case "reject":
                addTestReport(
                        "desktop",
                        "Reject Non-PO Accrual Request",
                        "Non-PO Accrual Request with ID <b>" + managerDocumentId + "</b> is rejected");
                break;
        }
    }





    public void closeTransaction() throws IOException, InterruptedException {
        commandField.goToRootSAPMenu();
        test.createNode("<b>[ Close Transaction ]</b>").pass("Transaction is closed properly");
    }
}