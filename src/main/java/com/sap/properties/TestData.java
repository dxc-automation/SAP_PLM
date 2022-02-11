package com.sap.properties;

import com.sap.config.GeneralTestConfig;

public class TestData extends GeneralTestConfig {

    //  * * * *    TEST VARIABLES
    private String requestID;
    private String companyCode;
    private String businessArea;
    private String amount;
    private String currency;
    private String upiTtl;
    private String cardCyclePeriod;
    private String cardLastDigits;
    private String cardHolderUpi;
    private String accountGL;
    private String approver;
    private String chargeType;
    private String chargeCode;
    private String serviceFrom;
    private String serviceTo;
    private String requestType;
    private String dpDocumentType;

    private String reimbursementFor;
    private String invoiceDate;
    private String invoiceReceiptNumber;

    private int detectedNumberOfTests;

    public static boolean  isRequestDuplicated;
    private String   requestStatusFromWeb;
    public static boolean  scenarioOverallStatus;
    public static String   actionType;



    //***   Get & Set Values
    public int  getDetectedNumberOfTests() { return  detectedNumberOfTests; }
    public void setDetectedNumberOfTests(int newDetectedNumberOfTests) { this.detectedNumberOfTests = newDetectedNumberOfTests; }

    public String getDpDocumentType() { return dpDocumentType; }
    public void   setDpDocumentType(String newDpDocumentType) { this.dpDocumentType = newDpDocumentType; }

    public String getInvoiceReceiptNumber() { return invoiceReceiptNumber; }
    public void   setInvoiceReceiptNumber(String newInvoiceReceiptNumber) { this.invoiceReceiptNumber = newInvoiceReceiptNumber; }

    public String getInvoiceDate() { return invoiceDate; }
    public void   setInvoiceDate(String newInvoiceDate) { this.invoiceDate = newInvoiceDate; }

    public String getReimbursementFor() { return reimbursementFor; }
    public void   setReimbursementFor(String newReimbursementFor) { this.reimbursementFor = newReimbursementFor; }

    public String getRequestStatusFromWeb() { return  requestStatusFromWeb; }
    public void   setRequestStatusFromWeb(String newRequestStatusFromWeb) { this.requestStatusFromWeb = newRequestStatusFromWeb; }

    public String getRequestType() { return requestType; }
    public void   setRequestType(String newRequestType) { this.requestType = newRequestType; }

    public String getCardCyclePeriod() { return cardCyclePeriod; }
    public void   setCardCyclePeriod(String newCardCyclePeriod) { this.cardCyclePeriod = newCardCyclePeriod; }

    public String getServiceFrom() { return serviceFrom; }
    public void   setServiceFrom(String newServiceFrom) { this.serviceFrom = newServiceFrom; }

    public String getServiceTo() { return serviceTo; }
    public void   setServiceTo(String newServiceTo) { this.serviceTo = newServiceTo; }

    public String getChargeCode() { return chargeCode; }
    public void   setChargeCode(String newChargeCode) { this.chargeCode = newChargeCode; }

    public String getChargeType() { return chargeType; }
    public void   setChargeType(String newChargeType) { this.chargeType = newChargeType; }

    public String getApprover() { return  approver; }
    public void   setApprover(String newApprover) { this.approver = newApprover; }

    public String getAccountGL() { return accountGL; }
    public void   setAccountGL(String newAccountGL) { this.accountGL = newAccountGL; }

    public String getCardHolderUpi() { return cardHolderUpi; }
    public void   setCardHolderUpi(String newCardHolderUpi) { this.cardHolderUpi = newCardHolderUpi; }

    public String getRequestID() {
        return requestID;
    }
    public void   setRequestID(String newRequestId) {
        this.requestID = newRequestId;
    }

    public String getCompanyCode() { return companyCode; }
    public void   setCompanyCode(String newCompanyCode) { this.companyCode = newCompanyCode; }

    public String getBusinessArea() { return businessArea; }
    public void   setBusinessArea(String newBusinessArea) { this.businessArea = newBusinessArea; }

    public String getAmount() { return amount; }
    public void   setAmount(String newAmount) { this.amount = newAmount; }

    public String getCurrency() { return currency; }
    public void   setCurrency(String newCurrency) { this.currency = newCurrency; }

    public String getUpiTtl() { return upiTtl; }
    public void   setUpiTtl(String newUpiTtl) { this.upiTtl = newUpiTtl; }

    public String getCardLastDigits() { return cardLastDigits; }
    public void   setCardLastDigits(String newCardLastDigits) { this.cardLastDigits = newCardLastDigits; }



    public static void getAllTestData(String scenarioType, int testCaseNumber) throws Exception {
        String documentType = testDataReader.getDocumentTypeValue(scenarioType, testCaseNumber);
        TEST_DATA.setDpDocumentType(documentType);

        String invoiceReceiptNumber = testDataReader.getInvoiceReceiptNumber(scenarioType, testCaseNumber);
        TEST_DATA.setInvoiceReceiptNumber(invoiceReceiptNumber);

        String invoiceDate = testDataReader.getInvoiceDateValue(scenarioType, testCaseNumber);
        TEST_DATA.setInvoiceDate(invoiceDate);

        String reimbursementFor = testDataReader.getReimbursementForValue(scenarioType, testCaseNumber);
        TEST_DATA.setReimbursementFor(reimbursementFor);

        String requestType = testDataReader.getRequestType(scenarioType, testCaseNumber);
        TEST_DATA.setRequestType(requestType);

        String serviceFrom = testDataReader.getServiceDateFrom(scenarioType, testCaseNumber);
        TEST_DATA.setServiceFrom(serviceFrom);

        String serviceTo = testDataReader.getServiceDateTo(scenarioType, testCaseNumber);
        TEST_DATA.setServiceTo(serviceTo);

        String chargeType = testDataReader.getChargeType(scenarioType, testCaseNumber);
        TEST_DATA.setChargeType(chargeType);

        String chargeCode = testDataReader.getChargeCode(scenarioType, testCaseNumber);
        TEST_DATA.setChargeCode(chargeCode);

        String approver = testDataReader.getApprover(scenarioType, testCaseNumber);
        TEST_DATA.setApprover(approver);

        String cardHolderUPI = testDataReader.getUPIofCardHolder(scenarioType, testCaseNumber);
        TEST_DATA.setCardHolderUpi(cardHolderUPI);

        String companyCode = testDataReader.getCompanyCode(scenarioType, testCaseNumber);
        TEST_DATA.setCompanyCode(companyCode);

        String businessArea = testDataReader.getBusinessArea(scenarioType, testCaseNumber);
        TEST_DATA.setBusinessArea(businessArea);

        String amount = testDataReader.getAmount(scenarioType, testCaseNumber);
        TEST_DATA.setAmount(amount);

        String currency = testDataReader.getCurrency(scenarioType, testCaseNumber);
        TEST_DATA.setCurrency(currency);

        String upiOfTTL = testDataReader.getUPIofTTL(scenarioType, testCaseNumber);
        TEST_DATA.setUpiTtl(upiOfTTL);

        String cardLastDigits = testDataReader.getLastCardDigits(scenarioType, testCaseNumber);
        TEST_DATA.setCardLastDigits(cardLastDigits);

        String glCategory = testDataReader.getGLCategory(scenarioType, testCaseNumber);
        TEST_DATA.setAccountGL(glCategory);
    }

    public static String getNPRRequestURL(String requestId) {
        String url = "https://tstlbssl.worldbank.org/sap/bc/webdynpro/sap/zvim_non_po_accrual?DISPLAY=&DOCTYPE=ZVIMNPO_CO&DOCID=" + requestId + "#";
        System.out.println(url);
        return url;
    }
}
