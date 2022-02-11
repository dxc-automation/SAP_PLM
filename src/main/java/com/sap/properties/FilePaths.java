package com.sap.properties;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.sap.properties.DataReader.getPropertiesFile;
import static com.sap.properties.TestDataReader.environment;

public class FilePaths {

    //  Get project main dir
    public static Path path = Paths.get(new File(System.getProperty("user.dir")).toPath().toString());


    //  * * * *    F I L E   P A T H S

    public final static String appPath             = "C:\\Program Files (x86)\\SAP\\FrontEnd\\SAPgui\\saplogon.exe";

    public final static String attachment          = path + "/src/main/resources/TestAttachment.xlsx";
    public final static String testDataFile        = path + "/src/main/resources/TestData.xlsm";
    public final static String reportJsonFolder    = path + "/report/JSON/";
    public final static String reportFolder        = path + "/report/";
    public final static String reportHtmlFile      = path + "/report/TestReport.html";
    public final static String reportConfigXmlFile = path + "/src/main/resources/extent-config.xml";
    public final static String xmlFilesFolder      = path + "/src/main/resources/";
    public final static String reportArchiveFolder = path + "/report_archive/";

    public final static String screenshotsFailedLocal    = "./report/Screenshots/Failed/";
    public final static String screenshotsFailedFolder   = path + "\\report\\Screenshots\\Failed\\";
    public final static String screenshotsActualFolder   = path + "\\report\\Screenshots\\Actual\\";
    public final static String screenshotsBufferFolder   = path + "\\report\\Screenshots\\Buffer\\";
    public final static String screenshotsExpectedFolder = path + "\\report\\Screenshots\\Expected\\";
    public final static String videoFiles = path + "\\report\\video\\";

    public final static String winiumLog = path + "\\report\\Log.log";
    public final static String exceptionLog                = path + "\\report\\StackTrace.log";
    public final static String config_properties_file      = path + "\\src\\main\\resources\\config.properties";
    public final static String winium_driver_file          = path + "\\src\\main\\resources\\Winium.Desktop.Driver.exe";
    public final static String firefox_driver_file         = path + "\\src\\main\\resources\\drivers\\geckodriver.exe";

    public final static String tempFolder                     = path + "/report/temp/";
    public final static String proformaInvoiceAttachment      = path + "\\src\\main\\resources\\attachments\\Proforma_Invoice_Attachment.pdf";
    public final static String ttlReviewersApprovalAttachment = path + "\\src\\main\\resources\\attachments\\TTL_Reviewers_Approval.pdf";
    public final static String splitChargesAttachment         = path + "\\src\\main\\resources\\attachments\\TEMPLATE.XLS";


    public static String getScreenshotPath() throws IOException {
        getPropertiesFile("env");
        String path = "";
        switch (environment) {
            case "local":
                path = screenshotsFailedLocal;
                break;

            case "remote":
                path = "./report/Screenshots/Failed/";
                break;
        }
        return path;
    }
}


