package com.sap.test_scripts.desktop.commonly_used;

import com.jacob.activeX.ActiveXComponent;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;

import static com.sap.config.ExtentReport.*;
import static com.sap.properties.TestDataReader.*;
import static com.sap.utilities.Commons.passwordEncryption;

@Listeners(TestNGListener.class)
public class Login extends GeneralTestConfig {


    // ***  Element Locators
    private String windowTitle  = "SAP Logon 760";
    private String logonBtnId   = "1068";
    private String logonBtnText = "&Log On";
    private final String usernameTxtField = "wnd[0]/usr/txtRSYST-BNAME";
    private final String passwordTxtField = "wnd[0]/usr/pwdRSYST-BCODE";
    private final String enterButton  = "wnd[0]/tbar[0]/btn[0]";


    public void setAccount(String accountType) throws Exception {
        switch (accountType) {
            case "reviewer":
                username = TEST_DATA_READER.getAccountingReviewerUser();
                password = TEST_DATA_READER.getAccountingReviewerPass();
                break;
            case "approver":
                username = TEST_DATA_READER.getManagerApproveUser();
                password = TEST_DATA_READER.getManagerApprovePass();
                break;
            case "requester":
                username = TEST_DATA_READER.getRequesterUser();
                password = TEST_DATA_READER.getRequesterPass();
                break;
            case "manager":
                username = TEST_DATA_READER.getManagerReAssignUser();
                password = TEST_DATA_READER.getManagerReAssignPass();
                break;
        }
    }

    public void logonServer() throws Exception {
        // SAP Logon 760 - Click Log on button
        autoItX.winWait(windowTitle);
        autoItX.winActivate(windowTitle);
        autoItX.controlFocus(windowTitle, logonBtnText, logonBtnId);
        autoItX.controlClick(windowTitle, logonBtnText, logonBtnId);

        // Enter username
        Obj = new ActiveXComponent(Session.invoke("FindById", usernameTxtField).toDispatch());
        Obj.invoke("setFocus");
        Obj.setProperty("Text", username);

        // Enter password
        Obj = new ActiveXComponent(Session.invoke("FindById", passwordTxtField).toDispatch());
        Obj.invoke("setFocus");
        Obj.setProperty("Text", passwordEncryption(password));

        // Click OK button
        Obj = new ActiveXComponent(Session.invoke("FindById", enterButton).toDispatch());
        Obj.invoke("setFocus");
        Obj.invoke("press");

        addTestReport(
                "desktop",
                "Log in SAP NetWear",
                "Log into desktop SAP GUI for Windows with UPI <b>" + username + "</b>");
    }
}

