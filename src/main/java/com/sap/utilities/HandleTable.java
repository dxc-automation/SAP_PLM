package com.sap.utilities;

import com.sap.config.GeneralTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class HandleTable extends GeneralTestConfig {


    private static final Logger LOG = LogManager.getLogger(HandleTable.class);


    public static void handleWebTable(WebElement table) throws Exception {
        //Get all rows
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        //Print data from each row
        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            for (WebElement col : cols) {
                System.out.print(col.getText() + "\t");
            }
            System.out.println();
        }
    }
}