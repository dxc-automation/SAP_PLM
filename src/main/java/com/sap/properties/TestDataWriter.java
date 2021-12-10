package com.sap.properties;

import java.io.*;

import com.sap.config.GeneralTestConfig;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static com.sap.properties.FilePaths.testDataFile;
import static com.sap.properties.TestData.*;


import java.util.*;

public class TestDataWriter extends GeneralTestConfig {

    public static void addToTemplate(String scenarioType, int testCaseNumber, String status) throws Exception {
        try {
            //Get the excel file.
            FileInputStream file = new FileInputStream(new File(testDataFile));

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet1 = workbook.getSheet(scenarioType);

            XSSFRow row = sheet1.getRow(testCaseNumber);

            String requestId = TEST_DATA.getRequestID();
            XSSFCell requestNumber = row.getCell(18);
            requestNumber.setCellValue(requestId);

            XSSFCell requestStatus = row.getCell(19);
            requestStatus.setCellValue(TEST_DATA.getRequestStatusFromWeb());

            XSSFCell overallStatus = row.getCell(20);
            overallStatus.setCellValue(status.toUpperCase());

            file.close();
            //Where you want to save the updated sheet.
            FileOutputStream out = new FileOutputStream(new File(testDataFile));
            workbook.write(out);
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dataWriter() throws IOException {
        // workbook object
        XSSFWorkbook workbook = new XSSFWorkbook();

        // spreadsheet object
        XSSFSheet spreadsheet = workbook.createSheet(" Student Data ");

        // creating a row object
        XSSFRow row;

        // This data needs to be written (Object[])
        Map<String, Object[]> studentData = new TreeMap<String, Object[]>();

        studentData.put("1", new Object[] { "Roll No", "NAME", "Year" });
        studentData.put("2", new Object[] { "128", "Aditya", "2nd year" });
        studentData.put("3", new Object[] { "129", "Narayana", "2nd year" });
        studentData.put("4", new Object[] { "130", "Mohan", "2nd year" });
        studentData.put("5", new Object[] { "131", "Radha", "2nd year" });
        studentData.put("6", new Object[] { "132", "Gopal", "2nd year" });

        Set<String> keyid = studentData.keySet();

        int rowid = 0;

        // writing the data into the sheets...

        for (String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = studentData.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }

        // .xlsx is the format for Excel Sheets...
        // writing the workbook into the file...
        FileOutputStream out = new FileOutputStream(new File("C:/savedexcel/GFGsheet.xlsx"));
        workbook.write(out);
        out.close();
    }
}