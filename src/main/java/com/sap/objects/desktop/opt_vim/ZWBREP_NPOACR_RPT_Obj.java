package com.sap.objects.desktop.opt_vim;

import com.jacob.activeX.ActiveXComponent;
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


    public void enterFromDate() {

    }

}