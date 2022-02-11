package com.sap.config;

import com.jacob.com.Variant;
import com.sap.test_scripts.desktop.commonly_used.CommandField;
import lombok.SneakyThrows;
import org.apache.commons.lang.time.StopWatch;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.io.IOException;
import java.util.ArrayList;

import static com.sap.config.ExtentReport.*;
import static com.sap.properties.FilePaths.exceptionLog;
import static com.sap.properties.TestDataWriter.addToTemplate;
import static com.sap.utilities.Commons.printExecutionTime;
import static com.sap.utilities.FileUtility.*;

public class TestNGListener extends GeneralTestConfig implements ITestListener {

    public static int passedTests = 0;
    public static int failedTests = 0;
    public static String failedTestName;
    public static long startTime;
    public static long endTime;

    private CommandField commandField = new CommandField();

    @Override
    public void onStart(ITestContext arg0) {
        System.out.println("\nEXECUTION STARTING.....");
        startTime = System.nanoTime();
    }


    @Override
    public void onTestStart(ITestResult arg0) {
        // Print test status in the console
        System.out.println("\nTEST STARTED: [ " +arg0.getName() + " ]");

    }


    @Override
    public void onTestSuccess(ITestResult arg0) {
        // Print test status in the console
        System.out.println("\nTEST PASSED: [ " +arg0.getName() + " ]");
    }


    @Override
    public void onTestFailure(ITestResult arg0) {
        // Print test status in the console
        System.out.println("\nTEST FAILED: [ " +arg0.getName() + " ]");
    }



    @Override
    public void onTestSkipped(ITestResult arg0) {
        System.out.println("\nTEST SKIPPED: [ " + arg0.getName() + " ]");
    }


    @Override
    public void onFinish(ITestContext context) {
        System.out.println("\nEXECUTION FINISH......");
        endTime = System.nanoTime();

        System.out.println("***** Passed  Tests: [ " + passedTests + " ]");
        System.out.println("***** Failed  Tests: [ " + failedTests    + " ]");
        System.out.println("***** Skipped Tests: [ " + context.getSkippedTests().size()   + " ]");

        printExecutionTime(startTime, endTime);
    }



    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub

    }
}