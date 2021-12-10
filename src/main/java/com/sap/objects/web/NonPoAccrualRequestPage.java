package com.sap.objects.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class NonPoAccrualRequestPage {
    public WebDriver driver;

    public static final Logger LOG = LogManager.getLogger(NonPoAccrualRequestPage.class);

    //***  Page Constructor
    public NonPoAccrualRequestPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public  final By continueBtn   = By.xpath("//table[@class='urFontStd']//td[4]//a[1]");



    //***   Company Code & Business Area
    public final By companyCodeInputFld = By.xpath("//div/table[@class='urMatrixLayout']//input[1]");
    public final By businessAreaDropdown = By.xpath("//input[@id='WDAB-btn']");
    public final By businessAreaInputFld = By.xpath("(//input[@ct='CB'])[2]");
    public final By eventCardCheckbox = By.xpath("//input[@name='WD0170_RB'][1]");
    public final By pCardCheckbox = By.xpath("//tr/td[2]/span[@class='urCWhlEmph']/input[@name='WD0170_RB']");
    public final By nonPurchaseOrderCheckbox = By.xpath("//tr/td[3]/span[@class='urCWhlEmph']/input[@name='WD0170_RB']");
    public final By accrualCurrencyFld = By.xpath("(//label[text()='Accrual Currency:']/following::input)[1]");
    public final By areYouTtl_No_Checkbox = By.xpath("(//span[text()=' No'])[1]");
    public final By areYouCardHolder_No_Checkbox = By.xpath("(//span[text()=' No'])[2]");
    public final By eventNaturePurchaseFld = By.xpath("(//label[text()='Event/Nature of Purchase:']/following::input)[1]");
    public final By lastCardDigitsFld = By.xpath("(//label[text()='Last 4 digits of the Card:']/following::input)[1]");
    public final By serviceFromField = By.xpath("(//label[text()='Service period:']/following::input)[1]");
    public final By serviceToField = By.xpath("(//label[text()='Service period:']/following::input)[3]");
    public final By ttlUpiField = By.xpath("(//label[text()='UPI of TTL:']/following::input)[1]");
    public final By cardHolderUpiField = By.xpath("(//label[text()='UPI of card holder:']/following::input)[1]");
    public final By cardCyclePeriodField = By.xpath("//table[@class='urMatrixLayout']//span[@class='urCoB2Whl']/input[1]");


    //***   Accrual/Charge Code Information
    public final By amountInput = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]/span[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[5]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/table[1]/tbody[1]/tr[3]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/div[1]/div[1]/span[1]/span[2]/div[1]/table[1]/tbody[1]/tr[8]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/table[1]/tbody[1]/tr[1]/td[1]/span[1]/table[1]/tbody[1]/tr[2]/td[3]/table[1]/tbody[1]/tr[1]/td[1]/input[1]");
    public final By chargeTypeInput = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]/span[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[5]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/table[1]/tbody[1]/tr[3]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/div[1]/div[1]/span[1]/span[2]/div[1]/table[1]/tbody[1]/tr[8]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/table[1]/tbody[1]/tr[1]/td[1]/span[1]/table[1]/tbody[1]/tr[2]/td[4]/table[1]/tbody[1]/tr[1]/td[1]/input[1]");
    public final By chargeCodeInput = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]/span[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[5]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/table[1]/tbody[1]/tr[3]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/div[1]/div[1]/span[1]/span[2]/div[1]/table[1]/tbody[1]/tr[8]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/table[1]/tbody[1]/tr[1]/td[1]/span[1]/table[1]/tbody[1]/tr[2]/td[5]/table[1]/tbody[1]/tr[1]/td[1]/input[1]");
    public final By glCategoryInput = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]/span[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[5]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/table[1]/tbody[1]/tr[3]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/div[1]/div[1]/span[1]/span[2]/div[1]/table[1]/tbody[1]/tr[8]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/table[1]/tbody[1]/tr[1]/td[1]/span[1]/table[1]/tbody[1]/tr[2]/td[6]/table[1]/tbody[1]/tr[1]/td[1]/input[1]");
    public final By selectApproverDropdown = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]/span[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[5]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/table[1]/tbody[1]/tr[3]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/div[1]/div[1]/span[1]/span[3]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/div[1]/span[1]/span[1]/span[1]/span[2]/span[1]/input[2]");
    public final By textInput = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]/span[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[5]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/table[1]/tbody[1]/tr[3]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/div[1]/div[1]/span[1]/span[2]/div[1]/table[1]/tbody[1]/tr[8]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/table[1]/tbody[1]/tr[1]/td[1]/span[1]/table[1]/tbody[1]/tr[2]/td[9]/table[1]/tbody[1]/tr[1]/td[1]/input[1]");
    public final By confirmMsgContinueBtn = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/table[1]/tbody[1]/tr[1]/td[3]/table[1]/tbody[1]/tr[1]/td[1]/a[1]");
    public final By submitButton = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]/span[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[5]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/span[1]/span[2]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[1]/td[10]");
    public final By successfullyRequestSend = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]/span[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[5]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/table[1]/tbody[1]/tr[3]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/div[1]/div[1]/span[1]/span[1]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/span[1]");

    //***   Split Charge Attachment
    public final By splitChargeAttachmentInput = By.xpath("//input[@ct='FU']");
    public final By splitChargeAttachmentBrowseBtn = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]/span[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[5]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/table[1]/tbody[1]/tr[3]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/div[1]/div[1]/span[1]/span[2]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/span[1]/span[1]/form[1]/input[1]");
    public final By splitChargeAttachmentUploadBtn = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]/span[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[5]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/table[1]/tbody[1]/tr[3]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/div[1]/div[1]/span[1]/span[2]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/span[1]/span[2]/a[1]");

    //***   Proforma Invoice Attachment
    public final By proformaInvoiceAttachmentBtn   = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]/span[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[5]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/table[1]/tbody[1]/tr[3]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/div[1]/div[1]/span[1]/span[6]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/a[1]");
    public final By proformaInvoiceAttachmentInput = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[2]/div[1]/span[1]/span[1]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/form[1]");
    public final By proformaInvoiceAttachmentOkBtn = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/table[1]/tbody[1]/tr[1]/td[3]/table[1]/tbody[1]/tr[1]/td[1]/a[1]");
    public final By proformaInvoiceAttachmentUploadBtn = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[2]/div[1]/span[1]/span[1]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/table[1]/tbody[1]/tr[5]/td[1]/a[1]");

    //***   TTL/Reviewers Approval Attachment
    public final By ttlReviewersAttachmentBtn   = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]/span[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[5]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/table[1]/tbody[1]/tr[3]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/div[1]/div[1]/span[1]/span[6]/div[1]/table[1]/tbody[1]/tr[5]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/a[1]");
    public final By ttlReviewersAttachmentInput = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[2]/div[1]/span[1]/span[1]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/form[1]/input[1]");
    public final By ttlReviewersAttachmentOkBtn = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/table[1]/tbody[1]/tr[1]/td[3]/table[1]/tbody[1]/tr[1]/td[1]/a[1]");
    public final By ttlReviewersAttachmentUploadBtn = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[2]/div[1]/span[1]/span[1]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/table[1]/tbody[1]/tr[5]/td[1]/a[1]");


    //***   Duplicated Message
    public final By duplicatedMsgTextArea   = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[2]/div[1]/span[1]/span[1]/div[1]/span[1]/span[2]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/textarea[1]");
    public final By duplicatedMsgConfirmBtn = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[2]/div[1]/span[1]/span[1]/div[1]/span[1]/span[3]/table[1]/tbody[1]/tr[1]/td[1]/span[1]/a[1]");
    public final By duplicatedSessionContinueBtn = By.xpath("//a[@id='SESSION_QUERY_CONTINUE_BUTTON']");
}
