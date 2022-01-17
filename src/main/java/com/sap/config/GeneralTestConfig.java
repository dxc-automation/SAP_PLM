package com.sap.config;

import autoitx4java.AutoItX;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.LibraryLoader;
import com.jacob.com.Variant;
import com.sap.properties.TestDataReader;
import com.sap.properties.TestData;
import com.sap.utilities.Assertions;
import com.sap.utilities.Commons;
import com.sap.utilities.VideoRecord;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.sap.properties.FilePaths.*;
import static com.sap.config.ExtentReport.*;
import static com.sap.properties.DataReader.getPropertiesFile;
import static com.sap.utilities.FileUtility.createDateFolder;
import static com.sap.utilities.FileUtility.*;


/**
 *                          This class contains all methods for taking screenshots,
 *                          browser initialization and generateReport generation.
 *          List:
 *   [1]    takeScreenshot  Capture screenshot and save the file with PNG extension.
 *                          Example:            takeScreenshot(driver, "FileName");
 *   [2]    browserConfig           Launch web browser. Value must be setted in testng.xml
 *   [3]    generateReport          Describes the result of a scripts and print result values.
 *   [4]    finishReport    Writes scripts information from the started reporters to
 *                          their output view.
 *   [5]    tearDown        Stop web driver and close the browser.
 */

@Listeners(TestNGListener.class)
public abstract class GeneralTestConfig {

    public GeneralTestConfig()  {
        super();
    }

    public static WebDriver    driver;
    public WiniumDriverService service;
    public static WiniumDriver winiumDriver;

    public static TestDataReader testDataReader = new TestDataReader();
    public ExtentReport EXTENT_REPORT = new ExtentReport();
    public Assertions  assertions = new Assertions();
    public static Commons commons = new Commons();
    public static VideoRecord    VIDEO_RECORD = new VideoRecord();
    public static TestData       TEST_DATA    = new TestData();

    public int     delay = 0;
    public static  String concate = ".";
    private static String imgPath;
    public static  AutoItX autoItX = new AutoItX();
    static final   Logger LOG = LogManager.getLogger(GeneralTestConfig.class);


    public ActiveXComponent SapROTWr, GUIApp, Connection, Session, Obj;
    public Dispatch ROTEntry;
    public Variant ScriptEngine;


    /**
     * Used for screenshot generating
     * @throws Exception
     */
    public static String getScreenshot (String imageName, String driverType) throws IOException {
        String imageFilePath = getScreenshotPath();

        if (driverType.equalsIgnoreCase("desktop")) {
            File source = ((TakesScreenshot) winiumDriver).getScreenshotAs(OutputType.FILE);
            imgPath = imageFilePath + imageName + ".png";
            File path = new File(imgPath);
            FileUtils.copyFile(source, path);
            return imgPath;

        } else if (driverType.equalsIgnoreCase("web")) {
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            imgPath = imageFilePath + imageName + ".png";
            File path = new File(imgPath);
            FileUtils.copyFile(source, path);
            return imgPath;
        }
        return imgPath;
    }


    public static Object screenCapture(String imgDetails, String imgName, String imgType) throws IOException {
        if (imgType.equalsIgnoreCase("fail")) {
            test.log(Status.FAIL, imgDetails, MediaEntityBuilder.createScreenCaptureFromPath(imgName).build());
        } else if (imgType.equalsIgnoreCase("normal")) {
            test.log(Status.INFO, imgDetails, MediaEntityBuilder.createScreenCaptureFromPath(imgName).build());
        }
        return test;
    }


    public static void passFailScreenshot(String imgDetails, String imgName, String imgType, String driverType) throws IOException {
        String screenshotName = concate + getScreenshot(imgName, driverType);
        screenCapture(imgDetails, screenshotName, imgType);
    }


    /**
     * Delete all reporting files from previous tests
     */
        @BeforeSuite
        public static void cleanReportData() throws Exception {
            // Get number of iterations
            deleteFolderContent(reportJsonFolder);
            deleteFolderContent(screenshotsFailedFolder);
            deleteFolderContent(screenshotsActualFolder);
            deleteFolderContent(screenshotsBufferFolder);
            deleteFolderContent(videoFiles);
            deleteFolderContent(tempFolder);

            File winiumDriverLogFile = new File(winiumLog);
            File exceptionLogFile    = new File(exceptionLog);

            if (winiumDriverLogFile.exists()) {
                FileUtils.forceDelete(winiumDriverLogFile);
            } else {
                System.out.println("Winium log file is not found"); }
            if (exceptionLogFile.exists()) {
                FileUtils.forceDelete(exceptionLogFile);
            } else {
                System.out.println("Previous stack trace log file is not found");
            }
            VIDEO_RECORD.startRecording();
        }



    @BeforeClass
    public void initEnvironment() throws Exception {
        // Check the OS architecture and set correct dll version
        String jacobDllVersionToUse;
        if (System.getProperty("os.arch").contains("64")){
            jacobDllVersionToUse = "jacob-1.20-x64.dll";
        }
        else {
            jacobDllVersionToUse = "jacob-1.20-x86.dll";
        }
        System.out.println("\nJacob " + jacobDllVersionToUse + " library is loaded");

        // Create file variable and use it to load the library in the system
        File file = new File("lib", jacobDllVersionToUse);
        System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());

        DesktopOptions options = new DesktopOptions(); //Initiate Winium Desktop Options
        options.setApplicationPath(appPath); //Set application path

        service = new WiniumDriverService.Builder()
                .usingDriverExecutable(new File(winium_driver_file))
                .usingPort(9999)
                .withVerbose(true)
                .withSilent(false)
                .withLogFile(new File(winiumLog))
                .buildDesktopService();

        service.start(); //Build and Start a Winium Driver service
        System.out.println("Winium service started successfully");
        Thread.sleep(5000);
        winiumDriver = new WiniumDriver(service, options); //Start a winium driver
        Thread.sleep(3000);

        autoItX = new AutoItX();
    }

    public void getSession() throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "startup.bat").start();
        Thread.sleep(7000);

        try {
            ComThread.InitSTA();
            SapROTWr     = new ActiveXComponent("SapROTWr.SapROTWrapper");
            ROTEntry     = SapROTWr.invoke("GetROTEntry", "SAPGUI").toDispatch();
            ScriptEngine = Dispatch.call(ROTEntry, "GetScriptingEngine");
            GUIApp     = new ActiveXComponent(ScriptEngine.toDispatch());
            Connection = new ActiveXComponent(GUIApp.invoke("Children", 0).toDispatch());
            Session    = new ActiveXComponent(Connection.invoke("Children", 0).toDispatch());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * Collect the results from every test
     */
    @AfterMethod(alwaysRun = true)
    public void generateReport(ITestResult result) throws Exception {
        String fileName   = result.getMethod().getMethodName();
        Path   file       = Paths.get(reportJsonFolder + fileName);
        String methodName = String.format("%s", result.getMethod().getRealClass().getCanonicalName());

        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                // TODO
                break;
            case ITestResult.SKIP:
                // TODO
                break;
            case ITestResult.FAILURE:
                break;
        }
    }


    @AfterSuite(alwaysRun = true)
    public void report() throws Exception {
        extent.flush();

        try {
            driver.quit();
        } catch (Exception e) {
            System.out.println("Driver process is killed already");
        }

        // Kill application process
        autoItX.processClose("saplogon.exe");
        autoItX.processClose("Winium.Desktop.Driver.exe");

        // Save previous reports
        String reportDateFolder = reportArchiveFolder + getDate();

        String reportDir = path + "/report_archive";
        File reportFolderPath = new File(reportDir);

        createDateFolder(reportDateFolder);

        String zipName     = commons.getRandomGeneratedString();
        String folderName  = reportArchiveFolder + getDate();
        String zipFileName = folderName + "/TestReport_" + zipName + ".zip";

        zipReport(zipFileName);

        try {
            String emailOnOff = getPropertiesFile("email");
            if(emailOnOff.equalsIgnoreCase("on")) {
                // Send via mail and open the report
                Email.sendEmail(zipFileName);
            } else {
                System.out.println("Email functionality is disabled");
            }
        } catch (Exception e) {
            System.out.println("\nEmail was not send......");
            saveInputInFile(e.fillInStackTrace().toString(), "Fail_email_send");
        }
        File htmlFile = new File(reportHtmlFile);
        java.awt.Desktop.getDesktop().browse(htmlFile.toURI());
        VIDEO_RECORD.stopRecording();

        setFolderMaxSize(reportFolderPath, getFolderSize(reportFolderPath));

        // Disconnect
        try {
            service.stop();
        } catch (Exception e) {
            //TODO
        }
        ComThread.Release();
        ComThread.quitMainSTA();
    }
}

