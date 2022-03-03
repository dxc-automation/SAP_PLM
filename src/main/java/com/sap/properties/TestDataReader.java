package com.sap.properties;

import static com.sap.properties.DataReader.getTestData;

public class TestDataReader {

    public static String environment;
    public static String emailOnOff;
    public static String username;
    public static String password;
    public static String nprURL;
    public static String errURL;
    private String testStatus;

    //  * * * *    TEST INFO
    public static String testName;
    public static String testCase = "";
    public static String testDescription;
    public static String transactionPackage;
    public static String transactionProgram;
    public static String transactionCode;
    public static String transactionName;

    public static String accountingReviewerUser;
    public static String accountingReviewerPass;
    public static String managerApproveUser;
    public static String managerApprovePass;
    public static String managerReAssignUser;
    public static String managerReAssignPass;
    public static String requesterUser;
    public static String requesterPass;


    //***   SAP ACCOUNTS   ***//

    public String getAccountingReviewerUser() throws Exception {
        accountingReviewerUser = getTestData("Account", "User", 1);
        return accountingReviewerUser;
    }

    public String getAccountingReviewerPass() throws Exception {
        accountingReviewerPass = getTestData("Account", "Password", 1);
        return accountingReviewerPass;
    }

    public String getManagerApproveUser() throws Exception {
        managerApproveUser = getTestData("Account", "User", 2);
        return managerApproveUser;
    }

    public String getManagerApprovePass() throws Exception {
        managerApprovePass = getTestData("Account", "Password", 2);
        return managerApprovePass;
    }

    public String getRequesterUser() throws Exception {
        requesterUser = getTestData("Account", "User", 3);
        return requesterUser;
    }

    public String getRequesterPass() throws Exception {
        requesterPass = getTestData("Account", "Password", 3);
        return requesterPass;
    }

    public String getManagerReAssignUser() throws Exception {
        managerReAssignUser = getTestData("Account", "User", 4);
        return managerReAssignUser;
    }

    public String getManagerReAssignPass() throws Exception {
        managerReAssignPass = getTestData("Account", "Password", 4);
        return managerReAssignPass;
    }




    //***   TEST GENERAL INFO   ***//

    public String getTestStatus(String scenarioType, int testCaseNumber) throws Exception {
        testStatus = getTestData(scenarioType, "On/Off", testCaseNumber);
        return testStatus;
    }

    public static String getTestCaseID(String scenarioType, int testCaseNumber) throws Exception {
        testCase = getTestData(scenarioType, "Test Case ID", testCaseNumber);
        return testCase;
    }



    //***   TRANSACTION INFORMATION   ***//

    public static String getTransactionPackage(String scenarioType, int testCaseNumber) throws Exception {
        return transactionPackage = getTestData(scenarioType, "Package", testCaseNumber);
    }

    public static String getTransactionProgram(String scenarioType, int testCaseNumber) throws Exception {
        return transactionProgram = getTestData(scenarioType, "Program", testCaseNumber);
    }

    public static String getTransactionCode(String scenarioType, int testCaseNumber) throws Exception {
        return transactionCode = getTestData(scenarioType, "Transaction Code", testCaseNumber);
    }

    public String getTransactionName(String scenarioType, int testCaseNumber) throws Exception {
        return transactionName = getTestData(scenarioType, "Transaction Text", testCaseNumber);
    }



    //***   TEST DATA   ***//

    public String getPaymentModeValue(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "Payment Mode", testCaseNumber);
    }

    public static String getViewMode(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "Work View Mode", testCaseNumber);
    }

    public static String getAction(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "Action", testCaseNumber);
    }

    public static String getAmount(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "Amount", testCaseNumber);
    }

    public static String getChargeType(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "Charge Type", testCaseNumber);
    }

    public static String getChargeCode(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "Charge Code", testCaseNumber);
    }

    public static String getGLCategory(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "GL Category", testCaseNumber);
    }

    public String getEventNaturePurchaseValue(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "Event Nature Purchase", testCaseNumber);
    }

    public static String getApprover(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "Approver", testCaseNumber);
    }

    public String getRequestTypeValue(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "Request Type", testCaseNumber);
    }

    public static String getUPIofTTL(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "UPI of TTL", testCaseNumber);
    }

    public static String getUPIofCardHolder(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "UPI of Card Holder", testCaseNumber);
    }

    public static String getLastCardDigits(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "Last 4 Digits of Card", testCaseNumber);
    }

    public static String getCompanyCode(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "Company Code", testCaseNumber);
    }

    public static String getBusinessArea(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "Business Area", testCaseNumber);
    }

    public String getCurrency(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "Accrual Currency", testCaseNumber);
    }

    public String getServiceDateFrom(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "Service Date From", testCaseNumber);
    }

    public String getServiceDateTo(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "Service Date To", testCaseNumber);
    }

    public String getReimbursementForValue(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "Reimbursement For", testCaseNumber);
    }

    public String getInvoiceDateValue(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "Invoice Date", testCaseNumber);
    }

    public String getInvoiceReceiptNumber(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "Invoice Receipt No", testCaseNumber);
    }

    public String getDocumentTypeValue(String scenarioType, int testCaseNumber) throws Exception {
        return getTestData(scenarioType, "DP Document Type", testCaseNumber);
    }
}
