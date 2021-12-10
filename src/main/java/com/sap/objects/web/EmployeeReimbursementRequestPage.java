package com.sap.objects.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class EmployeeReimbursementRequestPage {
    WebDriver driver;

    public static final Logger LOG = LogManager.getLogger(EmployeeReimbursementRequestPage.class);

    //***  Page Constructor
    public EmployeeReimbursementRequestPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public final By nextBtn = By.xpath("//a[@class='urBtnStd' and contains(text(),' Next')]");

    // Staff Details
    public final By reimbursementForInput = By.xpath("//input[@type='Text']");
    public final By businessAreaInput = By.xpath("//input[@type='text'][3]");

    // Invoice Details
    public final By invoiceDateInput = By.xpath("//tr[2]/td[2]//input[@type='text'][1]");
    public final By invoiceNumberInput = By.xpath("//tr[5]/td[2]//input[@type='Text']");
    public final By currencyInput = By.xpath("//tr[8]/td[2]//input[@type='Text']");
    public final By expenseTypeInput = By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]/span[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[5]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/table[1]/tbody[1]/tr[5]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/div[1]/span[1]/span[1]/div[1]/table[1]/tbody[1]/tr[8]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/span[1]/input[1]");
}
