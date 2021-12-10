package com.sap.test_scripts.web;

import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.objects.web.EmployeeReimbursementRequestPage;
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
import static com.sap.config.ExtentReport.addTestReport;
import static com.sap.config.ExtentReport.test;
import static com.sap.properties.FilePaths.proformaInvoiceAttachment;
import static com.sap.properties.FilePaths.splitChargesAttachment;
import static com.sap.properties.TestData.isRequestDuplicated;
import static com.sap.utilities.Commons.delay;
import static com.sap.utilities.FileUtility.saveInputInFile;

@Listeners(TestNGListener.class)
public class EmployeeReimbursmentRequest extends GeneralTestConfig {

    private ePayablesRequestPortal ePayablesRequestPortal   = new ePayablesRequestPortal();
    private EmployeeReimbursementRequestPage employeeReimbursementRequestPage = new EmployeeReimbursementRequestPage(driver);

    private String cardCyclePeriod;


    public void proceedRequest() throws Exception {
        ePayablesRequestPortal.openSubmitRequestPage("err");
    }

    public void clickNextButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(employeeReimbursementRequestPage.nextBtn));
        driver.findElement(employeeReimbursementRequestPage.nextBtn).click();
        System.out.println("Continue to the next step......");
    }

    public void reimbursementFor() {
        wait.until(ExpectedConditions.presenceOfElementLocated(employeeReimbursementRequestPage.reimbursementForInput));
        WebElement element = driver.findElement(employeeReimbursementRequestPage.reimbursementForInput);
        element.clear();
        element.sendKeys(TEST_DATA.getReimbursementFor());

        String value = element.getAttribute("value");
        System.out.println("Reimbursement For " + value);
    }

    public void businessArea() {
        wait.until(ExpectedConditions.presenceOfElementLocated(employeeReimbursementRequestPage.businessAreaInput));
        WebElement element = driver.findElement(employeeReimbursementRequestPage.businessAreaInput);

        String value = element.getAttribute("value");
        System.out.println("Business Area " + value);
    }

    public void invoiceDate() {
        wait.until(ExpectedConditions.presenceOfElementLocated(employeeReimbursementRequestPage.invoiceDateInput));
        WebElement element = driver.findElement(employeeReimbursementRequestPage.invoiceDateInput);
        element.sendKeys();
    }



}