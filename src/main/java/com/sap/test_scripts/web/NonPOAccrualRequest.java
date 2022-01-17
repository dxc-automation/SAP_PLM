package com.sap.test_scripts.web;

import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.objects.web.NonPoAccrualRequestPage;
import com.sap.objects.web.ePayablesHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.awt.*;
import java.io.IOException;

import static com.sap.config.BrowserManager.wait;
import static com.sap.properties.TestData.*;
import static com.sap.config.ExtentReport.*;
import static com.sap.properties.FilePaths.*;
import static com.sap.utilities.Assertions.verifyString;
import static com.sap.utilities.Commons.delay;
import static com.sap.utilities.FileUtility.*;

@Listeners(TestNGListener.class)
public class NonPOAccrualRequest extends GeneralTestConfig {

    private ePayablesHomePage ePayablesHomePage             = new ePayablesHomePage(driver);
    private ePayablesRequestPortal ePayablesRequestPortal   = new ePayablesRequestPortal();
    private NonPoAccrualRequestPage nonPoAccrualRequestPage = new NonPoAccrualRequestPage(driver);

    private String cardCyclePeriod;


    public void proceedNonPoAccrualRequest() throws Exception {
        ePayablesRequestPortal.openSubmitRequestPage("npr");
    }


    public void clickContinueButton() throws Exception {
            wait.until(ExpectedConditions.elementToBeClickable(nonPoAccrualRequestPage.continueBtn));
            commons.clickElement(nonPoAccrualRequestPage.continueBtn);
            delay(500);
            System.out.println("Continue to the next step......");
    }


    public void companyCode() throws Exception {
        String companyCodeValue = TEST_DATA.getCompanyCode();

        wait.until(ExpectedConditions.elementToBeClickable(nonPoAccrualRequestPage.companyCodeInputFld));
        String selectedCompanyCode = driver.findElement(nonPoAccrualRequestPage.companyCodeInputFld).getAttribute("value");

        switch (selectedCompanyCode) {
            case "IBRD":
                System.out.println("Selected company code is " + selectedCompanyCode);
                break;
            case "IFC":
                driver.findElement(nonPoAccrualRequestPage.companyCodeInputFld);
                commons.clickElement(nonPoAccrualRequestPage.companyCodeInputFld);
                delay(1000);

                driver.findElement(nonPoAccrualRequestPage.companyCodeInputFld).sendKeys(Keys.ARROW_UP);
                delay(1000);
                driver.findElement(nonPoAccrualRequestPage.companyCodeInputFld).sendKeys(Keys.ENTER);
                delay(500);
                break;
            case "MIGA":
                driver.findElement(nonPoAccrualRequestPage.companyCodeInputFld);
                commons.clickElement(nonPoAccrualRequestPage.companyCodeInputFld);
                delay(1000);

                driver.findElement(nonPoAccrualRequestPage.companyCodeInputFld).sendKeys(Keys.ARROW_UP);
                delay(1000);
                driver.findElement(nonPoAccrualRequestPage.companyCodeInputFld).sendKeys(Keys.ARROW_UP);
                delay(500);
                driver.findElement(nonPoAccrualRequestPage.companyCodeInputFld).sendKeys(Keys.ENTER);
                delay(500);
                break;
        }
        String companyCode = driver.findElement(nonPoAccrualRequestPage.companyCodeInputFld).getAttribute("value");
        Assert.assertEquals(companyCode, companyCodeValue);
        System.out.println("Company code " + companyCodeValue);
    }


    public void businessArea() throws Exception {
        String businessAreaValue = TEST_DATA.getBusinessArea();

        wait.until(ExpectedConditions.presenceOfElementLocated(nonPoAccrualRequestPage.businessAreaInputFld));
        commons.clickElement(nonPoAccrualRequestPage.businessAreaInputFld);
        delay(1000);

        driver.findElement(nonPoAccrualRequestPage.businessAreaInputFld).sendKeys(Keys.ARROW_DOWN);
        delay(1000);

        driver.findElement(nonPoAccrualRequestPage.businessAreaInputFld).sendKeys(Keys.ENTER);
        delay(500);

        String businessArea = driver.findElement(nonPoAccrualRequestPage.businessAreaInputFld).getAttribute("value");
        Assert.assertTrue(businessArea.contains(businessAreaValue), "Selected Business Area seems to be invalid");
        System.out.println("Business area " + businessArea);
    }


    public void requestFor() throws Exception {
        switch (TEST_DATA.getRequestType()) {
            case "Event card":
                wait.until(ExpectedConditions.presenceOfElementLocated(nonPoAccrualRequestPage.eventCardCheckbox)).click();
                break;
            case "PCard":
                wait.until(ExpectedConditions.presenceOfElementLocated(nonPoAccrualRequestPage.pCardCheckbox)).click();
                break;
            case "Other":
                wait.until(ExpectedConditions.presenceOfElementLocated(nonPoAccrualRequestPage.nonPurchaseOrderCheckbox)).click();
                break;
        }
        test.pass("Expense type <b>" + TEST_DATA.getRequestType() + "</b> is selected");
    }


    public void accrualCurrency() throws Exception {
        String currencyValue = TEST_DATA.getCurrency();

        wait.until(ExpectedConditions.presenceOfElementLocated(nonPoAccrualRequestPage.accrualCurrencyFld));
        driver.findElement(nonPoAccrualRequestPage.accrualCurrencyFld).sendKeys(currencyValue);
        System.out.println("Currency " + currencyValue);
    }


    public void areYouTtl() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(nonPoAccrualRequestPage.areYouTtl_No_Checkbox));
        if (!commons.isCheckboxSelected(nonPoAccrualRequestPage.areYouTtl_No_Checkbox)) {
            driver.findElement(nonPoAccrualRequestPage.areYouTtl_No_Checkbox).click();
        } else {
            System.out.println("No - Are you TTL is selected");
        }
        test.pass("TTL owner is selected properly");
    }


    public void areYouCardHolder() {
        wait.until(ExpectedConditions.elementToBeClickable(nonPoAccrualRequestPage.areYouCardHolder_No_Checkbox));
        if (!commons.isCheckboxSelected(nonPoAccrualRequestPage.areYouCardHolder_No_Checkbox)) {
            driver.findElement(nonPoAccrualRequestPage.areYouCardHolder_No_Checkbox).click();
        } else {
            System.out.println("No - Are you card holder is selected");
        }
        test.pass("Credit card holder is selected");
    }


    public void eventNaturePurchase(String scenarioType, int testCaseNumber) throws Exception {
        String eventNaturePurchase = testDataReader.getEventNaturePurchase(scenarioType, testCaseNumber);

        wait.until(ExpectedConditions.presenceOfElementLocated(nonPoAccrualRequestPage.eventNaturePurchaseFld));
        driver.findElement(nonPoAccrualRequestPage.eventNaturePurchaseFld).sendKeys(eventNaturePurchase);
        System.out.println("Event/Nature purchase " + eventNaturePurchase);
    }


    public void lastCardDigits() throws Exception {
        String requesterCardLastDigits = TEST_DATA.getCardLastDigits();

        wait.until(ExpectedConditions.presenceOfElementLocated(nonPoAccrualRequestPage.lastCardDigitsFld));
        driver.findElement(nonPoAccrualRequestPage.lastCardDigitsFld).sendKeys(requesterCardLastDigits);
        System.out.println("Credit card last 4 digits " + requesterCardLastDigits);
    }


    public void serviceFrom() throws Exception {
        String serviceFromValue = TEST_DATA.getServiceFrom();

        wait.until(ExpectedConditions.presenceOfElementLocated(nonPoAccrualRequestPage.serviceFromField)).sendKeys(serviceFromValue);
        System.out.println("Service from " + serviceFromValue);
    }


    public void serviceTo() throws Exception {
        String serviceToValue = TEST_DATA.getServiceTo();

        wait.until(ExpectedConditions.presenceOfElementLocated(nonPoAccrualRequestPage.serviceToField)).sendKeys(serviceToValue);
        System.out.println("Service to " + serviceToValue);
    }


    public void ttlUPI() throws Exception {
        String requesterTtlUPI = TEST_DATA.getUpiTtl();

        wait.until(ExpectedConditions.presenceOfElementLocated(nonPoAccrualRequestPage.ttlUpiField)).sendKeys(requesterTtlUPI);
        System.out.println("UPI of TTL: " + requesterTtlUPI);
    }


    public void cardHolderUPI() throws Exception {
        String requesterCardHolderUPI = TEST_DATA.getCardHolderUpi();

        wait.until(ExpectedConditions.presenceOfElementLocated(nonPoAccrualRequestPage.cardHolderUpiField)).sendKeys(requesterCardHolderUPI);
        System.out.println("Card holder UPI: " + requesterCardHolderUPI);
    }


    public void cardCyclePeriod() throws Exception {
        String month = commons.getMonthFromDate(TEST_DATA.getServiceFrom());
        switch (month) {
            case "1":
                cardCyclePeriod = "FY23 - JAN";
                break;
            case "2":
                cardCyclePeriod = "FY23 - FEB";
                break;
            case "3":
                cardCyclePeriod = "FY23 - MAR";
                break;
            case "4":
                cardCyclePeriod = "FY23 - APR";
                break;
            case "5":
                cardCyclePeriod = "FY23 - MAY";
                break;
            case "6":
                cardCyclePeriod = "FY23 - JUN";
                break;
            case "7":
                cardCyclePeriod = "FY23 - JUL";
                break;
            case "8":
                cardCyclePeriod = "FY23 - AUG";
                break;
            case "9":
                cardCyclePeriod = "FY22 - SEP";
                break;
            case "10":
                cardCyclePeriod = "FY22 - OCT";
                break;
            case "11":
                cardCyclePeriod = "FY22 - NOV";
                break;
            case "12":
                cardCyclePeriod = "FY22 - DEC";
                break;
        }
        TEST_DATA.setCardCyclePeriod(cardCyclePeriod);
        wait.until(ExpectedConditions.presenceOfElementLocated(nonPoAccrualRequestPage.cardCyclePeriodField)).sendKeys(cardCyclePeriod);
        System.out.println("Card cycle period: " + cardCyclePeriod);
    }


    public void amount() throws Exception {
        String amountValue = TEST_DATA.getAmount();

        delay(2000);
        driver.findElement(nonPoAccrualRequestPage.amountInput).clear();
        delay(500);
        driver.findElement(nonPoAccrualRequestPage.amountInput).sendKeys(amountValue);
        delay(500);
        System.out.println("Amount: " + amountValue);
    }


    public void chargeType() throws Exception {
        String chargeTypeValue = TEST_DATA.getChargeType();

        wait.until(ExpectedConditions.presenceOfElementLocated(nonPoAccrualRequestPage.chargeTypeInput));
        driver.findElement(nonPoAccrualRequestPage.chargeTypeInput).click();
        delay(500);
        driver.findElement(nonPoAccrualRequestPage.chargeTypeInput).sendKeys(Keys.ARROW_DOWN);
        delay(500);
        driver.findElement(nonPoAccrualRequestPage.chargeTypeInput).sendKeys(Keys.ENTER);
        delay(1500);
        System.out.println("Charge Type: " + chargeTypeValue);
    }


    public void chargeCode() throws Exception {
        String chargeCodeValue = TEST_DATA.getChargeCode();

        delay(1000);
        wait.until(ExpectedConditions.elementToBeClickable(nonPoAccrualRequestPage.chargeCodeInput));
        commons.clickAction(nonPoAccrualRequestPage.chargeCodeInput);
        delay(500);
        driver.findElement(nonPoAccrualRequestPage.chargeCodeInput).sendKeys(chargeCodeValue);
        delay(500);
        System.out.println("Charge Code: " + chargeCodeValue);
    }


    public void glCategory() throws Exception {
        String requesterAccountGL = TEST_DATA.getAccountGL();

        WebElement gl_category_input = driver.findElement(nonPoAccrualRequestPage.glCategoryInput);
        delay(500);
        gl_category_input.sendKeys(requesterAccountGL);
        delay(500);
        gl_category_input.sendKeys(Keys.ENTER);
        delay(500);
        System.out.println("GL Category: " + requesterAccountGL);
    }


    public void addProformaInvoiceAttachment() throws InterruptedException, IOException {
        wait.until(ExpectedConditions.presenceOfElementLocated(nonPoAccrualRequestPage.proformaInvoiceAttachmentBtn));
        commons.clickElement(nonPoAccrualRequestPage.proformaInvoiceAttachmentBtn);
        delay(2000);
        commons.clickElement(nonPoAccrualRequestPage.proformaInvoiceAttachmentBtn);
        delay(2000);

        commons.iframesPrint();
        driver.switchTo().frame(1);
        delay(1000);

        commons.clickAction(nonPoAccrualRequestPage.proformaInvoiceAttachmentInput);
        delay(1000);

        winiumDriver.findElementById("1148").sendKeys(proformaInvoiceAttachment);
        delay(1000);
        autoItX.winWaitActive("[CLASS:#32770]", "", 3);
        autoItX.controlClick("[CLASS:#32770]", "&Open", "Button1");
        delay(2000);

        wait.until(ExpectedConditions.presenceOfElementLocated(nonPoAccrualRequestPage.proformaInvoiceAttachmentUploadBtn));
        commons.clickElement(nonPoAccrualRequestPage.proformaInvoiceAttachmentUploadBtn);
        delay(3500);

        commons.clickElement(nonPoAccrualRequestPage.proformaInvoiceAttachmentOkBtn);
        delay(2000);
        System.out.println("Proforma invoice attachment is uploaded");
        driver.switchTo().defaultContent();
    }


    public void addTttlReviewersApproval() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(nonPoAccrualRequestPage.ttlReviewersAttachmentBtn));
        commons.clickElement(nonPoAccrualRequestPage.ttlReviewersAttachmentBtn);
        delay(2000);
        commons.clickElement(nonPoAccrualRequestPage.ttlReviewersAttachmentBtn);
        delay(2000);

        commons.iframesPrint();
        driver.switchTo().frame(1);
        delay(1000);

        commons.clickAction(nonPoAccrualRequestPage.ttlReviewersAttachmentInput);
        delay(1500);

        winiumDriver.findElementByName("File name:").sendKeys(proformaInvoiceAttachment);
        delay(1000);

        autoItX.winWaitActive("[CLASS:#32770]", "", 10);
        autoItX.controlClick("[CLASS:#32770]", "&Open", "Button1");
        delay(2000);

        wait.until(ExpectedConditions.presenceOfElementLocated(nonPoAccrualRequestPage.ttlReviewersAttachmentUploadBtn));
        WebElement upload = driver.findElement(nonPoAccrualRequestPage.ttlReviewersAttachmentUploadBtn);
        commons.jsClickElement(upload);
        delay(3500);

        wait.until(ExpectedConditions.presenceOfElementLocated(nonPoAccrualRequestPage.ttlReviewersAttachmentOkBtn));
        commons.clickElement(nonPoAccrualRequestPage.ttlReviewersAttachmentOkBtn);
        delay(2000);
        System.out.println("TTL/Reviewers approval attachment is uploaded");
        driver.switchTo().defaultContent();
    }


    public void selectApprover() throws Exception {
        String approverValue = TEST_DATA.getApprover();

        wait.until(ExpectedConditions.elementToBeClickable(nonPoAccrualRequestPage.selectApproverDropdown));
        commons.clickElement(nonPoAccrualRequestPage.selectApproverDropdown);
        delay(1000);

        driver.findElement(nonPoAccrualRequestPage.selectApproverDropdown).sendKeys(Keys.UP);
        delay(500);
        driver.findElement(nonPoAccrualRequestPage.selectApproverDropdown).sendKeys(Keys.UP);
        delay(500);
        driver.findElement(nonPoAccrualRequestPage.selectApproverDropdown).sendKeys(Keys.UP);
        delay(500);
        driver.findElement(nonPoAccrualRequestPage.selectApproverDropdown).sendKeys(Keys.ENTER);
        delay(500);
        System.out.println("Approver is selected");
    }


    public void addText(String scenarioType, int testCaseNumber) throws Exception {
        String text = testDataReader.getEventNaturePurchase(scenarioType, testCaseNumber);
        wait.until(ExpectedConditions.elementToBeClickable(nonPoAccrualRequestPage.textInput));
        delay(500);
        commons.clickAction(nonPoAccrualRequestPage.textInput);
        delay(500);
        driver.findElement(nonPoAccrualRequestPage.textInput).sendKeys(text);
        delay(500);
        driver.findElement(nonPoAccrualRequestPage.textInput).sendKeys(Keys.ENTER);
        delay(500);
        System.out.println("Text: " + text);
    }


    public void confirmationMsg() throws InterruptedException, IOException {
        delay(2000);

        driver.switchTo().frame("URLS-PopupWindow-0");
        saveInputInFile(driver.getPageSource(), "Page_Source");
        delay(1000);
        commons.clickElement(nonPoAccrualRequestPage.confirmMsgContinueBtn);
        delay(2000);
        driver.switchTo().defaultContent();
        System.out.println("Non-PO request details are confirmed");
    }


    public void submitRequest() throws Exception {
        commons.clickAction(nonPoAccrualRequestPage.submitButton);
        delay(2000);

        isRequestDuplicated = handleDuplicatedRequests();
        System.out.println("Duplicated = " + isRequestDuplicated);

        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.presenceOfElementLocated(nonPoAccrualRequestPage.successfullyRequestSend));

        String successfullySend = driver.findElement(nonPoAccrualRequestPage.successfullyRequestSend).getText();
        String requestNumber = successfullySend.replace("Your request has been Submitted. The request # is ", "");

        Assert.assertFalse(verifyString(requestNumber, "Enter the accrual amount along with the charge code and General Ledger (GL) category in the below table."));
        TEST_DATA.setRequestID(requestNumber);

        addTestReport(
                "web",
                "Submit Non-PO Accrual Request",
                "Request Type &Tab; &Tab; &Tab; <b>" + TEST_DATA.getRequestType()  + "</b><br>"
                + "Business Area &Tab; &Tab; <b>"          + TEST_DATA.getBusinessArea() + "</b><br>"
                + "UPI of TTL &Tab; &Tab; &Tab; <b>"       + TEST_DATA.getUpiTtl()       + "</b><br>"
                + "Currency &Tab; &Tab; &Tab; <b>"         + TEST_DATA.getCurrency()     + "</b><br>"
                + "Amount &Tab; &Tab; &Tab; &Tab; <b>"     + TEST_DATA.getAmount()       + "</b><br>"
                + "GL Category &Tab; &Tab; &Tab; <b>"      + TEST_DATA.getAccountGL()    + "</b><br>"
                + "Charge Type &Tab; &Tab; &Tab; <b>"      + TEST_DATA.getChargeType()   + "</b><br>"
                + "Charge Code &Tab; &Tab; &Tab; <b>"      + TEST_DATA.getChargeCode()   + "</b><br>"
                + "<center><b>Request Card Holder Details</b></center><br>"
                + "UPI of card holder &Tab; &Tab; <b>"     + TEST_DATA.getCardHolderUpi()   + "</b><br>"
                + "Last 4 digits of the card &Tab; <b>"    + TEST_DATA.getCardLastDigits()  + "</b><br>"
                + "Approver &Tab; &Tab; &Tab; <b>"         + TEST_DATA.getApprover()        + "</b><br>"
                + "Card cycle period &Tab; &Tab; <b>"      + TEST_DATA.getCardCyclePeriod() + "</b><br>"
                + "Service From &Tab; &Tab; &Tab; <b>"     + TEST_DATA.getServiceFrom()     + "</b><br>"
                + "Service To &Tab; &Tab; &Tab; <b>"       + TEST_DATA.getServiceTo()       + "</b><br>"
                + "Request ID &Tab;  &Tab; &Tab; <b>"      + requestNumber            + "</b></pre>");
    }


    public void uploadSplitCharges() throws InterruptedException, IOException, AWTException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nonPoAccrualRequestPage.splitChargeAttachmentInput));
        WebElement uploadFile = driver.findElement(nonPoAccrualRequestPage.splitChargeAttachmentInput);
        commons.jsClickElement(uploadFile);
        delay(2000);

        winiumDriver.findElement(By.id("1148")).sendKeys(splitChargesAttachment);
        delay(1000);
        autoItX.winWaitActive("[CLASS:#32770]", "", 3);
        autoItX.controlClick("[CLASS:#32770]", "&Open", "Button1");
        delay(2000);

        WebElement uploadBtn = driver.findElement(nonPoAccrualRequestPage.splitChargeAttachmentUploadBtn);
        commons.jsClickElement(uploadBtn);
        delay(3000);
        System.out.println("Split Charges attachment is uploaded");
    }


    public boolean handleDuplicatedRequests() throws InterruptedException {
        try {
            delay(1000);
            driver.switchTo().frame(0);
            delay(500);

            driver.findElement(nonPoAccrualRequestPage.duplicatedMsgTextArea).sendKeys("Test");
            delay(500);

            commons.clickAction(nonPoAccrualRequestPage.duplicatedMsgConfirmBtn);
            delay(1000);
            System.out.println("Request is duplicated");
            return true;
        } catch (Throwable e) {
            System.out.println("Request is not duplicated");
            return false;
        }
    }
}