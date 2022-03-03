package com.sap.objects.desktop.opt_vim;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.properties.TestDataReader;
import com.sap.test_scripts.desktop.commonly_used.CommandField;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.io.IOException;

@Listeners(TestNGListener.class)
public class ZWBREP_NPOACR_RPT_Obj extends GeneralTestConfig {

    private CommandField commandField = new CommandField();
    private TestDataReader testDataReader = new TestDataReader();

    private String transactionWindowTitle = "VIM Analytics";
    private String transactionCodeSearch  = "/N/OPT/VIM_VA2";
    private String transactionCode        = "/OPT/VIM_VA2";
    private String transactionProgram     = "/OPT/VIM_ANALYTICS_750";

    private String path;
    private String value;
    private String menuBarSystemStatus = "wnd[0]/mbar/menu[3]/menu[11]";
    private String executeBtnId = "119";


    //  Returns selected dp document type
    public String getCurrentDPtype() {
        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/usr/cmbPS_DOCTY").toDispatch());
        String dpDocumentType = Obj.getProperty("text").toString();
        return dpDocumentType;
    }


    //  Select document type
    public void selectDocumentType() throws IOException, InterruptedException {
        getSession();
        switch (TEST_DATA.getDpDocumentType()) {
            case "CO":
                Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cmbPS_DOCTY").toDispatch());
                Obj.invoke("setFocus");
                Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cmbPS_DOCTY").toDispatch());
                Obj.setProperty("key", "CO");
                break;
            case "HQ":
                Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cmbPS_DOCTY").toDispatch());
                Obj.invoke("setFocus");
                Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cmbPS_DOCTY").toDispatch());
                Obj.setProperty("key", "HQ");
                break;
        }
    }


    //  Enter document create date
    public void enterDocumentCreateDate() {
        String date = commons.getCurrentDate().replaceAll("-", "/");

        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/ctxtS_DATE-LOW").toDispatch());
        Obj.setProperty("text", date);
    }


    //  Enter document number
    public void enterDocumentNumber() {
        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/usr/ctxtS_DOCID-LOW").toDispatch());
        Obj.setProperty("text", TEST_DATA.getRequestID());
    }


    //  Click execute  button
    public void clickExecute() {
        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/tbar[1]/btn[8]").toDispatch());
        Obj.invoke("press");
    }


    //  Open document details
    public void openDetails() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/tbar[1]/btn[39]").toDispatch());
        Obj.invoke("press");
    }


    //  Open document details in separated window
    public void displayDocumentDetails() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlGRID1/shellcont/shell").toDispatch());
        Obj.setProperty("currentCellColumn", "DOCID");

        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlGRID1/shellcont/shell").toDispatch());
        Obj.invoke("clickCurrentCell");
    }


    //  Returns amount in USD
    public String getAmountInUSD() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/usr/cntlGRID/shellcont/shell").toDispatch());
        Variant[] arg = new Variant[2];
        arg[0] = new Variant(13);
        arg[1] = new Variant("VALUE");
        Obj.invoke("setCurrentCell", arg);
        Obj.invoke("setFocus");
        value = Obj.getProperty("text").toString();
        return value;
    }


    //  Close details
    public void closeDetails() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/tbar[0]/btn[0]").toDispatch());
        Obj.invoke("press");
    }
}