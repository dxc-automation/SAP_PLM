package com.sap.config;

import autoitx4java.AutoItX;
import com.jacob.com.LibraryLoader;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.sap.properties.FilePaths.*;
import static com.sap.config.GeneralTestConfig.*;


/**
 *  This class is used as an main class and point out TestNG
 *  framework  with  XML file.  The  original  XML  file  is
 *  located in project resources folder and after start scripts
 *  execution we copy it to the target folder.
 */

public class ConsoleRunner {

    protected static File xmlFile;
    public static String testXml;


    public static void main(String[] args) throws Exception {
        // Source file
        String xml = testXml + ".xml";

        // Original folder + source file
        File xmlFile = new File(xmlFilesFolder + xml);

        try {
            TestNG testng = new TestNG();
            List<String> suites = new ArrayList<String>();
            suites.add(path + "/src/main/resources/testng.xml");
            testng.setTestSuites(suites);
            testng.setOutputDirectory(reportFolder);
            testng.run();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}