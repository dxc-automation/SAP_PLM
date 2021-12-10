package com.sap.test_scripts.desktop.commonly_used;

import com.jacob.activeX.ActiveXComponent;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import org.testng.annotations.Listeners;

@Listeners(TestNGListener.class)
public class Logoff extends GeneralTestConfig {

    private CommandField commandField = new CommandField();


    public void logOff() throws Exception {
        getSession();
        commandField.goToRootSAPMenu();

        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/tbar[0]/btn[15]").toDispatch());
        Obj.invoke("setFocus");
        Obj.invoke("press");

        autoItX.winWait("Log Off", "", 3);
        autoItX.winActivate("Log Off");

        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/usr/btnSPOP-OPTION1").toDispatch());
        Obj.invoke("setFocus");
        Obj.invoke("press");
    }
}

