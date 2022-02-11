package com.sap.objects.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class LeaseRequestPage {
    public WebDriver driver;

    public static final Logger LOG = LogManager.getLogger(LeaseRequestPage.class);

    //***  Page Constructor
    public LeaseRequestPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public final By continueBtn   = By.xpath("//table[@class='urFontStd']//td[4]//a[1]");

    public final By companyCodeDropdown    = By.xpath("//table[@class='urMatrixLayout']//input[@style='width: 6ex;']");
    public final By vendorAddressTextarea  = By.xpath("//table[@class='urMatrixLayout']//textarea[@xpath='1']");
    public final By serviceDropdown = By.xpath("//table[@class='urMatrixLayout']//input[@style='width: 39ex;']");
    public final By vendorIdInput   = By.xpath("//table[@class='urMatrixLayout']//input[@type='Text']");
}
