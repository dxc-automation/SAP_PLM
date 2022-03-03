package com.sap.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.sap.properties.FilePaths.firefox_driver_file;

public class BrowserManager extends GeneralTestConfig {
    public static WebDriverWait wait = null;

    public void browserConfig(@Optional("chrome") String browser) throws Exception {
        DesiredCapabilities capability = new DesiredCapabilities();

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("--disable-search-geolocation-disclosure");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);
            LOG.info("| Chrome browser launched successfully |");

        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", firefox_driver_file);
            FirefoxProfile profile = new FirefoxProfile();
            profile.setAcceptUntrustedCertificates(true);
            profile.setAssumeUntrustedCertificateIssuer(true);

            FirefoxOptions options = new FirefoxOptions();
            options.setLogLevel(FirefoxDriverLogLevel.TRACE);
            driver = new FirefoxDriver();
            LOG.info("| Firefox browser launched successfully |");

        } else if (browser.equalsIgnoreCase("iexplorer")) {
            WebDriverManager.iedriver().setup();

            SafariOptions sOptions = new SafariOptions();
            SafariOptions.fromCapabilities(capability);
            driver = new InternetExplorerDriver();
            LOG.info("| Safari browser launched successfully |");
        }
        wait = new WebDriverWait(driver, 30);
    }

    // Minimize browser window
    public void minimizeBrowserWindow() throws AWTException {
        driver.manage().window().setPosition(new Point(0, -1000));
    }

    // Maximize browser window
    public void maximizeBrowserWindow() {
        driver.manage().window().maximize();
    }


    public void tearDownDriver() {
        try {
            driver.close();
        } catch (Exception e) {
            System.out.println("Failed to close WebDriver");
        }
    }
}
