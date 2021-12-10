package com.sap.test_scripts.desktop.opt_vim;

import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.objects.desktop.opt_vim.VIM_WP_Obj;
import com.sap.properties.TestDataReader;
import com.sap.test_scripts.desktop.commonly_used.CommandField;
import com.sap.test_scripts.desktop.commonly_used.Dialogs;
import org.testng.annotations.Listeners;

import java.io.IOException;

import static com.sap.config.ExtentReport.*;
import static com.sap.properties.TestDataReader.testDescription;
import static com.sap.properties.TestDataReader.testName;
import static com.sap.utilities.Assertions.amountValidation;
import static com.sap.utilities.Assertions.valuesValidation;

@Listeners(TestNGListener.class)
public class VIM_WP extends GeneralTestConfig {

    private CommandField commandField = new CommandField();
    private TestDataReader testDataReader = new TestDataReader();
    private VIM_WP_Obj   vim_wp_obj   = new VIM_WP_Obj();
    private Dialogs      dialogs      = new Dialogs();

    public void openTransaction(int testCaseNumber) throws Exception {
        String testCaseString  = "SAP GUI Windows";
        String transactionCode = "/OPT/VIM_WP";

        commandField.searchForTransaction(vim_wp_obj.transactionCodeSearch);
        dialogs.cancelDownloadFile();

        commandField.checkSearchResult(vim_wp_obj.transactionCode, vim_wp_obj.menuBarSystemStatus);
        addTestReport(
                "desktop",
                "Access SAP VIM Workplace",
                "Access transaction with code <b>" + vim_wp_obj.transactionCode + "</b>");
    }


    // Change work view mode
    public void setWorkViewMode(String scenarioType, int testCaseNumber) throws Exception {
        String workView = testDataReader.getViewMode(scenarioType, testCaseNumber);
        vim_wp_obj.switchWorkView(workView);
        dialogs.cancelDownloadFile();
        vim_wp_obj.validateWorkView(workView);
        addTestReport(
                "desktop",
                "VIM: Out of Workflow Approval",
                "Work view mode is successfully changed to " + workView);
    }


    public void searchDocumentId() throws Exception {
        vim_wp_obj.searchDocumentAllInbox();
        dialogs.cancelDownloadFile();

        vim_wp_obj.openSearchResultDocument();
        dialogs.cancelDownloadFile();

        addTestReport(
                "desktop",
                "VIM: Out of Workflow Approval",
                "Search for document ID " + TEST_DATA.getRequestID());
    }


    public void validateReviewerDocumentDetails(String scenarioType, int testCaseNumber) throws Exception {
        String reviewerCurrency    = vim_wp_obj.getReviewerBasicData("currency");
        String reviewerCompanyCode = vim_wp_obj.getReviewerBasicData("companyCode");

        String reviewerAccountGL  = vim_wp_obj.getReviewerLineItems("accountGL");
        String reviewerAmount     = vim_wp_obj.getReviewerLineItems("amount");
        String reviewerCostCenter = vim_wp_obj.getReviewerLineItems("costCenter");

        String reviewerDocumentId   = vim_wp_obj.getReviewerProcess("documentId");
        String reviewerDocumentType = vim_wp_obj.getReviewerProcess("documentType");

        String reviewerIndexUser      = vim_wp_obj.getReviewerOtherData("indexUser");
        String reviewerApprover       = vim_wp_obj.getReviewerOtherData("approver");
        String reviewerServiceFrom    = vim_wp_obj.getReviewerOtherData("serviceFrom");
        String reviewerServiceTo      = vim_wp_obj.getReviewerOtherData("serviceTo");
        String reviewerRequestType    = vim_wp_obj.getReviewerOtherData("requestType");
        String reviewerCardLastDigits = vim_wp_obj.getReviewerOtherData("cardLastDigits");
        String reviewerTtlUPI         = vim_wp_obj.getReviewerOtherData("ttlUPI");
        String reviewerCardHolderUPI  = vim_wp_obj.getReviewerOtherData("cardHolderUPI");

        valuesValidation("Currency", reviewerCurrency, TEST_DATA.getCurrency());
        valuesValidation("Company Code", reviewerCompanyCode, TEST_DATA.getCompanyCode());
        valuesValidation("GL Category", reviewerAccountGL, TEST_DATA.getAccountGL());

        String failCheck = testDataReader.getTestCaseID(scenarioType, testCaseNumber);
        if (failCheck.contains("Failed")) {
            amountValidation(reviewerAmount, "6,250.00");
        }

        amountValidation(reviewerAmount, TEST_DATA.getAmount());
        valuesValidation("Amount", reviewerAmount, TEST_DATA.getAmount());
        valuesValidation("Request ID", reviewerDocumentId, TEST_DATA.getRequestID());
        valuesValidation("Service From", reviewerServiceFrom, TEST_DATA.getServiceFrom());
        valuesValidation("Service To", reviewerServiceTo, TEST_DATA.getServiceTo());
        valuesValidation("Card last 4 digits", reviewerCardLastDigits, TEST_DATA.getCardLastDigits());
        valuesValidation("UPI of card holder", reviewerCardHolderUPI, "WB" + TEST_DATA.getCardHolderUpi());
        valuesValidation("UPI of TTL", reviewerTtlUPI, "WB" + TEST_DATA.getUpiTtl());

        // Click checkbox
        vim_wp_obj.reviewCompleted();

        addTestReport(
                "desktop",
                "Non-PO Accrual Request Details",
                "Document ID &Tab; &Tab; <b>" + reviewerDocumentId     + "</b><br>"
                        + "Document Type &Tab; <b>"              + reviewerDocumentType   + "</b><br>"
                        + "Index User &Tab; &Tab; <b>"           + reviewerIndexUser      + "</b><br>"
                        + "Approver &Tab; &Tab; <b>"             + reviewerApprover       + "</b><br>"
                        + "Service From &Tab; &Tab; <b>"         + reviewerServiceFrom    + "</b><br>"
                        + "Service To &Tab; &Tab; <b>"           + reviewerServiceTo      + "</b><br>"
                        + "Request Type &Tab; &Tab; <b>"         + reviewerRequestType    + "</b><br>"
                        + "UPI of TTL &Tab; &Tab; <b>"           + reviewerTtlUPI         + "</b><br>"
                        + "Card Holder UPI &Tab; <b>"            + reviewerCardHolderUPI  + "</b><br>"
                        + "Card Last Digits &Tab; <b>"           + reviewerCardLastDigits + "</b><br>"
                        + "G/L Account &Tab; &Tab; <b>"          + reviewerAccountGL      + "</b><br>"
                        + "Amount &Tab; &Tab; &Tab; <b>"         + reviewerAmount + " "   + reviewerCurrency + "</b><br>"
                        + "Cost Center &Tab; &Tab; <b>"          + reviewerCostCenter     + "</b><br>"
                        + "Company Code &Tab; <b>"               + reviewerCompanyCode    + "</b>");
    }


    public void save() {
        vim_wp_obj.saveChanges();
    }


    public void submit(String scenarioType, int testCaseNumber) throws Exception {
        vim_wp_obj.submitDocument(scenarioType, testCaseNumber);
        addTestReport(
                "desktop",
                "Submit Document Work Item",
                "Successfully submit the document");
    }


    public void obsolete() throws Exception {
        vim_wp_obj.setToObsolete();
        addTestReport(
                "desktop",
                "Obsolete Document Work Item",
                "Successfully set document with ID <b>" + TEST_DATA.getRequestID() + "</b> to obsolete");
    }


    public void closeTransaction() throws IOException, InterruptedException {
        commandField.goToRootSAPMenu();
    }
}