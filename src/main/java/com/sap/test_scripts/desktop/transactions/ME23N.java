package com.sap.test_scripts.desktop.transactions;

import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.objects.desktop.transactions.ME23N_Obj;
import com.sap.test_scripts.desktop.commonly_used.CommandField;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.io.IOException;

import static com.sap.config.ExtentReport.*;
import static com.sap.properties.TestDataReader.*;

@Listeners(TestNGListener.class)
public class ME23N extends GeneralTestConfig {

    private CommandField commandField = new CommandField();
    private ME23N_Obj me23N       = new ME23N_Obj();

    private String documentCurrency;
    private String documentNetOrderValue;
    private String documentCreatedBy;

    public void openTransaction(String scenarioType, int testCaseNumber) throws Exception {
        String testCaseString = testDataReader.getTestCaseID(scenarioType, testCaseNumber);

        // Declare what will be information printed in the report
        testName        = "<b>Validate PO details in SAP (ME23N)</b>";
        testDescription =
                "<br><b>* * * &Tab; T E S T &nbsp; &nbsp; S T E P S &Tab; * * *</b><br>" +
                        "[1] Call transaction code using command field.<br>" +
                        "[2] Open system status dialog and verify transaction details.<br>" +
                        "[3] Open other purchase order by using specific document number and company code.<br>" +
                        "[4] Validate order values from document header section.<br>" +
                        "[5] Validate item details from document item overview section.<br>" +
                        "[6] Validate item details from document item section.<br>" +
                        "[7] Close transaction.";

        // Start report listener
        startTestReport("desktop", testName, testDescription, testCaseString);

        // Call search method
        commandField.searchForTransaction(me23N.transactionCode);

        // Call searching functionality validation method
        commandField.checkSearchResult(me23N.transactionCode, me23N.menuBarSystemStatus);
    }

    public void otherPurchaseOrder(String scenarioType, int testCaseNumber) throws Exception {
        me23N.clickOtherPurchaseOrderButton();

        // Get test data
        String documentPO = testDataReader.getViewMode(scenarioType, testCaseNumber);
        me23N.setDocumentPO(documentPO);

        me23N.clickOtherDocumentButton();
        test.pass("Verify that the user can create other purchase order.");
    }

    public void headerDeliveryInvoiceDetails() throws IOException, InterruptedException {
        // Expand header
        me23N.clickHeaderExpandButton();

        // Navigate to Delivery/Invoice tab
        me23N.clickHeaderDeliveryInvoiceTab();

        // Get values
        String paymentTerms  = me23N.getPaymentTermsValue();
        String paymentInDays = me23N.getPaymentInDaysValue();
        String incoterms     = me23N.getIncotermsValue();
        String currency      = me23N.getCurrencyValue();
        String exchangeRate  = me23N.getExchangeRateValue();

        test.pass("<br><b><center>Document Header Delivery Invoice Tab Details</center></b><br>" +
                "<pre>Payment Terms  &Tab; " + "<b>" + paymentTerms   + "</b><br>" +
                "Payment In Days  &Tab; "    + "<b>" + paymentInDays  + "</b><br>" +
                "Incoterms  &Tab; &Tab; "    + "<b>" + incoterms      + "</b><br>" +
                "Currency  &Tab; &Tab; "     + "<b>" + currency       + "</b><br>" +
                "Exchange Rate &Tab; "       + "<b>" + exchangeRate   + "</b></pre>");
    }


    public void headerConditionDetails() throws IOException, InterruptedException {
        // Navigate to Condition tab
        me23N.clickHeaderConditionsTab();

        // Open header condition details
        me23N.clickConditionsDetailsButton();

        // Get values
        String application = me23N.getApplicationValue();
        String conditionPricingDate = me23N.getConditionPriceDateValue();
        String amount = me23N.getAmountValue();
        String conditionBaseValue = me23N.getConditionBaseValue();
        String conditionValue = me23N.getConditionValue();

        // Click back button
        me23N.clickBackButton();

        test.pass("<br><b><center>Document Header Condition Tab Details</center></b><br>"              +
                "<pre>Application &Tab; &Tab; &Tab; " + "<b>" + application           + "</b><br>" +
                "Condition Pricing Date &nbsp;&nbsp;" + "<b>" + conditionPricingDate  + "</b><br>" +
                "Amount &Tab; &Tab; &Tab; &Tab; "     + "<b>" + amount                + "</b><br>" +
                "Condition Base Value &Tab; "         + "<b>" + conditionBaseValue    + "</b><br>" +
                "Condition Value &Tab; &Tab; "        + "<b>" + conditionValue        + "</b></pre>");
    }


    public void headerAddressDetails() {
        // Navigate to Address tab
        me23N.clickHeaderAddressTab();

        // Get values
        String streetHouseNumber = me23N.getStreetHouseNumberValue();
        String postalCodeCity = me23N.getPostalCodeCityValue();
        String country = me23N.getCountryValue();
        String telephone = me23N.getTelephoneValue();
        String fax = me23N.getFaxValue();

        test.pass("<br><b><center>Document Header Address Tab Details</center></b><br>"              +
                "<pre>Street House Number &Tab; "    + "<b>" + streetHouseNumber  + "</b><br>" +
                "Postal Code/City &Tab; &Tab; "      + "<b>"  + postalCodeCity    + "</b><br>" +
                "Country &Tab; &Tab; &Tab; &Tab; "   + "<b>" + country            + "</b><br>" +
                "Telephone &Tab; &Tab; &Tab; "       + "<b>" + telephone          + "</b><br>" +
                "Fax &Tab; &Tab; &Tab; &Tab; &Tab; " + "<b>" + fax                + "</b></pre>");
    }

    public void headerAdditionalDataDetails() {
        // Navigate to Additional Data tab
        me23N.clickHeaderAdditionalTab();

        // Get values
        String validityStart = me23N.getValidityStartDateValue();
        String validityEnd   = me23N.getValidityEndDateValue();

        test.pass("<br><b><center>Document Header Additional Data Tab Details</center></b><br>"              +
                "<pre>Validity Start Date &Tab; " + "<b>" + validityStart  + "</b><br>" +
                "Validity End Date &Tab; "        + "<b>" + validityEnd    + "</b></pre>");
    }


    public void headerOrganizationDataDetails() {
        // Navigate to Organization Data tab
        me23N.clickHeaderOrganizationDataTab();

        // Get value
        String purchasingOrganization = me23N.getPurchasingOrganizationValue();
        String purchasingGroup = me23N.getPurchasingGroupValue();
        String companyCode = me23N.getCompanyCodeValue();

        test.pass("<br><b><center>Document Header Organization Data Tab Details</center></b><br>"              +
                "<pre>Purchasing Organization &Tab; " + "<b>" + purchasingOrganization + "</b><br>" +
                "Purchasing Group &Tab; &Tab; &Tab; " + "<b>" + purchasingGroup        + "</b><br>" +
                "Company Code &Tab; &Tab; &Tab; "     + "<b>" + companyCode            + "</b></pre>");
    }


    public void headerStatusDetails() {
        // Navigate to Status tab
        me23N.clickHeaderStatusTab();

        // Get values
        String ordered = me23N.getOrderedValue();
        String delivered = me23N.getDeliveredValue();
        String stillToDeliver = me23N.getStillToDeliverValue();
        String invoiced = me23N.getInvoicedValue();
        String downPayments = me23N.getDownPaymentsValue();

        test.pass("<br><b><center>Document Header Status Tab Details</center></b><br>"              +
                "<pre>Ordered &Tab; &Tab; &Tab; " + "<b>" + ordered        + "</b><br>" +
                "Delivered &Tab; &Tab; "          + "<b>" + delivered      + "</b><br>" +
                "Still To Delivery &Tab; "        + "<b>" + stillToDeliver + "</b><br>" +
                "Invoiced &Tab; &Tab; &Tab; "     + "<b>" + invoiced       + "</b><br>" +
                "Down Payments &Tab; "            + "<b>" + downPayments   + "</b></pre>");
    }


    public void headerReleaseStrategyDetails() {
        // Navigate to Release Strategy tab
        me23N.clickHeaderReleaseStrategyTab();

        // Get values
        String releaseGroup = me23N.getReleaseGroupValue();
        String releaseStrategy = me23N.getReleaseStrategyValue();
        String releaseIndicator = me23N.getReleaseIndicatorValue();

        test.pass("<br><b><center>Header Release Strategy Details</center></b><br>"              +
                "<pre>Release Group &Tab; " + "<b>" + releaseGroup     + "</b><br>" +
                "Release Strategy &Tab; "   + "<b>" + releaseStrategy  + "</b><br>" +
                "Release Indicator &Tab; "  + "<b>" + releaseIndicator + "</b></pre>");
    }


    public void headerVersionsDetails() throws IOException, InterruptedException {
        // Navigate to Versions tab
        me23N.clickHeaderVersionsTab();

        // Click on Details button
        me23N.openVersionPrintPreview();

        // Get values
        String version         = me23N.getVersionValue();
        String reasonForChange = me23N.getReasonForChangeValue();
        String text            = me23N.getTextValue();
        String requestedBy     = me23N.getRequestedByValue();
        documentNetOrderValue  = me23N.getNetOrderValue();
        String valueChange     = me23N.getValueChange();
        documentCurrency       = me23N.getVersionCurrencyValue();
        documentCreatedBy      = me23N.getCreatedByValue();
        String createdOn       = me23N.getCreatedOnValue();
        String time            = me23N.getTimeValue();
        String releasedBy      = me23N.getReleasedByValue();
        String releasedOn      = me23N.getReleasedOnValue();
        String timeOfRelease   = me23N.getTimeOfReleaseValue();

        me23N.clickBackButton();
        // Click header collapse button
        me23N.clickHeaderCollapseButton();

        test.pass("<br><b><center>Header Version Details</center></b><br>"              +
                "<pre>Version &Tab; &Tab; &Tab;" + "<b>" + version               + "</b><br>" +
                "Reason For Change "             + "<b>" + reasonForChange       + "</b><br>" +
                "Text &Tab; &Tab; &Tab; "        + "<b>" + text                  + "</b><br>" +
                "Requested By &Tab; &Tab; "      + "<b>" + requestedBy           + "</b><br>" +
                "Net Order Value &Tab; "         + "<b>" + documentNetOrderValue + "</b><br>" +
                "Value Change &Tab; &Tab; "      + "<b>" + valueChange           + "</b><br>" +
                "Currency &Tab; &Tab; "          + "<b>" + documentCurrency      + "</b><br>" +
                "Created By &Tab; &Tab; "        + "<b>" + documentCreatedBy     + "</b><br>" +
                "Created On &Tab; &Tab; "        + "<b>" + createdOn             + "</b><br>" +
                "Time &Tab; &Tab; &Tab; "        + "<b>" + time                  + "</b><br>" +
                "Released By &Tab; &Tab; "       + "<b>" + releasedBy            + "</b><br>" +
                "Released On &Tab; &Tab; "       + "<b>" + releasedOn            + "</b><br>" +
                "Time Of Release &Tab; "         + "<b>" + timeOfRelease         + "</b></pre>");
    }


    public void itemOverview() {
        // Expand item overview section
        me23N.expandItemOverview();

        // Get values
        String itemOverviewNetPrice = me23N.getItemOverviewNetPriceValue();
        String itemOverviewCurrency = me23N.getItemOverviewCurrencyValue();
        String itemOverviewQuantity = me23N.getItemOverviewQuantityValue();
        String itemOverviewDeliveryDate = me23N.getItemOverviewDeliveryDateValue();

        test.pass("<br><b><center>Document Item Overview Details</center></b><br>"  +
                "<pre>Currency &Tab; "       + "<b>" + itemOverviewCurrency     + "</b><br>" +
                "Net Price &Tab; "           + "<b>" + itemOverviewNetPrice     + "</b><br>" +
                "Quantity  &Tab; "           + "<b>" + itemOverviewQuantity     + "</b><br>" +
                "Delivery Date &nbsp;&nbsp;" + "<b>" + itemOverviewDeliveryDate + "</b></pre>");

        // Collapse item overview section
        me23N.collapseItemOverview();
    }


    public void item(String scenarioType, int testCaseNumber) throws Exception {
        // Expand item section
        me23N.expandItem();

        // Get test data
        String currency = testDataReader.getAction(scenarioType, testCaseNumber);
        String netOrderValue = testDataReader.getChargeType(scenarioType, testCaseNumber);
        String createdBy = testDataReader.getAmount(scenarioType, testCaseNumber);

        Assert.assertEquals(currency, documentCurrency, "Item currency is not correct");
        Assert.assertEquals(netOrderValue, documentNetOrderValue, "Item net price is not correct");
        Assert.assertEquals(createdBy, documentCreatedBy, "Document creator is not correct");

        test.pass("<b>Order details are verified</b>");

        // Collapse item section
        me23N.collapseItem();
    }

    public void closeTransaction() throws IOException, InterruptedException {
        commandField.goToRootSAPMenu();
        test.pass("Check that transaction can be closed properly");
    }
}