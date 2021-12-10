package com.sap.objects.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class ePayablesHomePage {
    WebDriver driver;

    public static final Logger LOG = LogManager.getLogger(ePayablesHomePage.class);

    //***  Page Constructor
    public ePayablesHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public final String pageTitle = "ePayables Request Portal - SAP NetWeaver Portal";

    public final By usernameInput = By.xpath("//input[@id='sap-user']");
    public final By passwordInput = By.xpath("//input[@id='sap-password']");
    public final By logonBtn = By.xpath("//a[@id='LOGON_BUTTON']/span[@class='urBtnCnt']");

    public final By requestStatus = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]/span[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[5]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/div[1]/span[1]/span[1]/span[1]/span[2]/span[1]");



    // Accrual Forms
    private final String po_accrual_request_locator = "//td[@smartid='WDF']/a[@innertext=' PO Accrual Request']";
    public final By po_accrual_request = By.xpath(po_accrual_request_locator);

    @FindBy(xpath = "//a[@title='Submit Accrual Request' and contains(text(),'Non-PO Accrual Request')]")
    public static WebElement nonPoAccrualRequest;

    private final String non_po_accrual_request_locator = "//a[@title='Submit Accrual Request' and contains(text(),'Non-PO Accrual Request')]";
    public final By non_po_accrual_request = By.xpath(non_po_accrual_request_locator);
}
