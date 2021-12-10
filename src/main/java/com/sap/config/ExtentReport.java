package com.sap.config;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.IOException;
import java.net.InetAddress;

import static com.sap.properties.FilePaths.reportConfigXmlFile;
import static com.sap.properties.FilePaths.reportHtmlFile;
import static com.sap.config.GeneralTestConfig.*;


/**
 *                              This class contains methods for HTML generateReport generation.
 *          List:
 *   [1]    ExtentReports       Print information about machine.
 *   [2]    ExtentHtmlReporter  HTML file design configuration.
 *   [3]    ExtentTest          Create a new scripts object.
 */

public class ExtentReport {

    public static  ExtentTest    test;
    public static  ExtentReports extent;
    private static ExtentSparkReporter htmlReporter;

    private static final String osName      = System.getProperty("os.name");
    private static final String osVersion   = System.getProperty("os.version");
    private static final String osArch      = System.getProperty("os.arch");
    private static final String javaVersion = System.getProperty("java.version");


    public static ExtentReports GetExtent() throws IOException {
        if (extent != null)
            return extent;
        extent = new ExtentReports();
        extent.attachReporter(getHtmlReporter());
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);

        InetAddress localHost = InetAddress.getLocalHost();
        String hostname = localHost.getHostName();

        extent.setSystemInfo("Local Host", localHost.getHostAddress());
        extent.setSystemInfo("Host Name",  hostname);
        extent.setSystemInfo("Java",       javaVersion);
        extent.setSystemInfo("OS",         osName);
        extent.setSystemInfo("OS Version", osVersion);
        extent.setSystemInfo("OS Arch",    osArch);

        return extent;
    }

    private static ExtentSparkReporter getHtmlReporter() throws IOException {
        htmlReporter = new ExtentSparkReporter(reportHtmlFile);
        htmlReporter.config().setReportName("Automation Test Report");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setTimelineEnabled(true);

        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setCss("pre,.code-block {font-family: serif !important; border: 1px groove black;} " +
                "table {font-family: serif;} " +
                "p {font-family: serif;} " +
                ".badge-primary {background-color: transparent; color: black;} " +
                ".header {background-color: white;}" +
                ".pt-0,.py-0 {font-size: xx-small;}");
        htmlReporter.config().setEncoding("UTF-8");
        htmlReporter.config().setJs("$('#charts-row .panel-name').eq(3).text('Tests')");
        htmlReporter.loadXMLConfig(reportConfigXmlFile);
        return htmlReporter;
    }


    // Start report listener
    public static void startTestReport(String system, String testName, String testDescription, String testCase) throws Exception {
        extent = GetExtent();
        test = extent.createTest(
                "<b>[ " + testCase + " </b>]  " + testName,
                "<pre>"
                        + "<p align=justify>"
                        + testDescription
                        + "</p>"
                        + "</pre>");
    }


    public static void addTestReport(String type, String title, String details) {
        switch (type) {
            case "desktop":
                test.createNode("<b><i class=\"fa fa-desktop blue-color \"></i> [ " + title + " ]</b>").pass("<pre>" + details + "</pre>");
                break;
            case "web":
                test.createNode("<b><i class=\"fa fa-chrome blue-color \"></i> [ " + title + " ]</b>").pass("<pre>" + details + "</pre>");
                break;
        }
    }
}
