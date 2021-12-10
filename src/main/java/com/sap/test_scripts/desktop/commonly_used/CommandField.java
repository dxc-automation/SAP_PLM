package com.sap.test_scripts.desktop.commonly_used;

import com.jacob.activeX.ActiveXComponent;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.io.IOException;

import static com.sap.config.ExtentReport.test;

@Listeners(TestNGListener.class)
public class CommandField extends GeneralTestConfig {

    /*
     * This class contains reusable commands. Every method
     * execute different command in  Command Field. System
     * actions that are used for quicker navigating.
     */


    private final String applicationName = "SAP Easy Access";
    private final String commandField    = "1001";
    private final String enterButton     = "wnd[0]/tbar[0]/btn[0]";
    private final String exitButton      = "wnd[0]/tbar[0]/btn[15]";
    private final String commandFieldId  = "//*[@AutomationId='1001']";


    public void exit() throws IOException, InterruptedException {
        getSession();
        Obj = new ActiveXComponent(Session.invoke("FindById", exitButton).toDispatch());
        Obj.invoke("setFocus");
        Obj.invoke("press");
    }

    //  Search for specified transaction code
    public void searchForTransaction(String searchItem) throws Exception {
        getSession();

        autoItX.winActivate(applicationName);
        autoItX.winWaitActive(applicationName);
        autoItX.controlFocus(applicationName, "", commandField);
        autoItX.sleep(delay);
        autoItX.ControlSetText(applicationName,"", commandField, searchItem);
        autoItX.sleep(delay);

        Obj = new ActiveXComponent(Session.invoke("FindById", enterButton).toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(500);
        Obj.invoke("press");
    }


    //  Validation for transaction search
    public void checkSearchResult(String transactionName, String menuBarSystemStatus) throws IOException, InterruptedException {
        Obj = new ActiveXComponent(Session.invoke("FindById", menuBarSystemStatus).toDispatch());
        Obj.invoke("select");
        autoItX.sleep(500);

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[1]/usr/txtSHKONTEXT-TCODE").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(500);
        String transactionCode = Obj.getProperty("Text").toString();
        Assert.assertEquals(transactionCode, transactionName, "Transaction code is incorrect");

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[1]/tbar[0]/btn[0]").toDispatch());
        Obj.invoke("setFocus");
        Obj.invoke("press");
    }


    //  Takes the user to the root SAP Easy Access menu from anywhere
    public void goToRootSAPMenu() throws IOException, InterruptedException {
        getSession();

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/tbar[0]/okcd").toDispatch());
        Obj.invoke("setFocus");
        Obj.setProperty("Text", "/n");

        Obj = new ActiveXComponent(Session.invoke("FindById", enterButton).toDispatch());
        Obj.invoke("press");
    }


    //  Closes all windows for the current system/client and logs off of SAP
    public void closeAllWindows() throws IOException, InterruptedException {
        getSession();
        autoItX.winActivate(applicationName);
        autoItX.winWaitActive(applicationName);
        autoItX.ControlSetText(applicationName,"", commandField, "/nex");

        Obj = new ActiveXComponent(Session.invoke("FindById", enterButton).toDispatch());
        Obj.invoke("press");
        test.pass("SAP log off successfully");
    }


    //  It allows you to open a new session (leaving anyother sessions open on your desktop)
    public void openNewSession() throws IOException, InterruptedException {
        getSession();
        autoItX.winActivate(applicationName);
        autoItX.winWaitActive(applicationName);
        autoItX.ControlSetText(applicationName,"", commandField, "/o");

        Obj = new ActiveXComponent(Session.invoke("FindById", enterButton).toDispatch());
        Obj.invoke("press");
        test.pass("New session dialog is displayed");
    }


    //  Grab several lines of text or several cells of values in a table
    public void getSeveralLines() throws IOException, InterruptedException {
        getSession();
        autoItX.winActivate(applicationName);
        autoItX.winWaitActive(applicationName);
        autoItX.ControlSetText(applicationName,"", commandField, "Ctrl-Y");

        Obj = new ActiveXComponent(Session.invoke("FindById", enterButton).toDispatch());
        Obj.invoke("press");
    }
}

