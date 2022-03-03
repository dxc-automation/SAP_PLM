package com.sap.test_scripts.desktop.commonly_used;

import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;

@Listeners(TestNGListener.class)
public class DocumentActions extends GeneralTestConfig {

    /*
     * This class contains reusable commands. Every method
     * execute different command in  Command Field. System
     * actions that are used for quicker navigating.
     */


    private final String mainWindowClass = "[CLASS:SAP_FRONTEND_SESSION]";
    private final String executeButtonId = "119";
    private final String enterButton     = "wnd[0]/tbar[0]/btn[0]";
    private final String exitButton      = "wnd[0]/tbar[0]/btn[15]";
    private final String commandFieldId  = "//*[@AutomationId='1001']";


    //  Click execute button
    public void clickExecute() {
        autoItX.winWait("[CLASS:SAP_FRONTEND_SESSION]", "", 5);
        autoItX.winActivate("[CLASS:SAP_FRONTEND_SESSION]");
        autoItX.controlFocus("[CLASS:SAP_FRONTEND_SESSION]", "", executeButtonId);
        autoItX.controlClick("[CLASS:SAP_FRONTEND_SESSION]", "", executeButtonId);
    }


    //  Returns system message
    public String getSystemMsg() {
        autoItX.sleep(3000);
        String result = autoItX.controlGetText(mainWindowClass,"","59393");
        return result;
    }


    public void maximizeWindow() {
        autoItX.winMinimizeAllUndo();
    }

    public void minimizeWindow() {
        autoItX.winMinimizeAll();
    }

}