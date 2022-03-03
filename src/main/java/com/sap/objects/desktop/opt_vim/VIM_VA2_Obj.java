package com.sap.objects.desktop.opt_vim;

import com.jacob.activeX.ActiveXComponent;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.properties.TestDataReader;
import com.sap.test_scripts.desktop.commonly_used.CommandField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.io.IOException;

import static com.sap.properties.FilePaths.reportFolder;
import static com.sap.properties.FilePaths.tempFolder;

@Listeners(TestNGListener.class)
public class VIM_VA2_Obj extends GeneralTestConfig {

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



    // Add request id into document processing number field
    public void enterDocumentProcessingNumber(String documentProcessingNumber) throws IOException, InterruptedException {
        getSession();
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/ctxtS_DOCID-LOW").toDispatch());
        Obj.invoke("setFocus");
        Obj.setProperty("text", documentProcessingNumber);
        autoItX.sleep(delay);
    }


    // Open table list view
    public void openListOutputView() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlCCTRL_MAIN/shellcont/shell/shellcont[0]/shell").toDispatch());
        Obj.invoke("pressToolbarContextButton", "&MB_VIEW");
        autoItX.sleep(500);
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlCCTRL_MAIN/shellcont/shell/shellcont[0]/shell").toDispatch());
        Obj.invoke("selectContextMenuItem", "&PRINT_BACK_PREVIEW");

        autoItX.winActivate("[CLASS:AfxWnd140]");
        autoItX.controlClick("[CLASS:AfxWnd140]", "", "500");
    }

    // Return the number of results
    public String getResultsNumber() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/titl").toDispatch());
        String value = Obj.getProperty("text").toString();
        return value;
    }


    // Get document details
    public String getDetails(String property) {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[1,4]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(500);

        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/tbar[1]/btn[39]").toDispatch());
        Obj.invoke("press");
        autoItX.sleep(500);

        switch (property) {
            case "documentId":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/2[0,0]/sub/1/2/3[0,3]/lbl[27,3]";
                break;
            case "invoiceType":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/2[0,0]/sub/1/2/4[0,4]/lbl[27,4]";
                break;
            case "dpDocumentType":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/2[0,0]/sub/1/2/27[0,27]/lbl[27,27]";
                break;
        }
        Obj = new ActiveXComponent(Session.invoke("findById", path).toDispatch());
        Obj.invoke("setFocus");
        value = Obj.getProperty("text").toString();

        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/tbar[0]/btn[3]").toDispatch());
        Obj.invoke("press");
        autoItX.sleep(500);

        return value;
    }


    // Get specific value from table
    public String getRequestValue(String property) {
        switch (property) {
            case "result":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/2[0,0]/lbl[0,0]";
                break;
            case "documentId":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[1,4]";
                break;
            case "businessArea":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[9,4]";
                break;
            case "companyCode":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[14,4]";
                break;
            case "status":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[60,4]";
                break;
            case "processStatus":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[67,4]";
                break;
            case "dpDocumentType":
                if (TEST_DATA.getDpDocumentType().equalsIgnoreCase("CO")) {
                    path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[106,4]";
                } else {
                    path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[89,4]";
                }
                break;
            case "processType":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[89,4]";
                break;
            case "exceptionReason":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[100,4]";
                break;
            case "grossAmount":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[160,4]";
                break;
            case "currency":
                Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr").toDispatch());
                Obj = new ActiveXComponent(Obj.getProperty("horizontalScrollbar").toDispatch());
                Obj.setProperty("position", 54);
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[117,4]";
                break;
            case "amount":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[127,4]";
                break;
            case "enterOn":
                Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr").toDispatch());
                Obj = new ActiveXComponent(Obj.getProperty("horizontalScrollbar").toDispatch());
                Obj.setProperty("position", 82);
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[145,4]";
                break;
            case "postingDate":
                path = "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[156,4]";
                break;
        }
        Obj = new ActiveXComponent(Session.invoke("findById", path).toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();

        return value;
    }


    // Click the Back button
    public void clickBackButton() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/tbar[0]/btn[3]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");
    }


    // Select process row
    public void selectFirstProcessRow() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlCCTRL_MAIN/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.setProperty("selectedRows", "0");
    }


    // Process workflow
    public void clickDisplayWorkflowLog() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlCCTRL_MAIN/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("pressToolbarButton", "WFLOG");
    }


    // Click the Process Document button
    public void clickProcessDocument() {
        autoItX.winWait("Workflow Log", "", 3);
        autoItX.winActivate("Workflow Log");
        autoItX.controlFocus("Workflow Log", "", "SAPTreeList2");
        autoItX.controlSend("Workflow Log", "", "SAPTreeList2", "{DOWN}");
        autoItX.controlSend("Workflow Log", "", "SAPTreeList2", "{DOWN}");
        System.out.println(autoItX.controlGetHandle("Workflow Log", "", "SAPTreeList2"));
    }


    // Click on the execute button
    public void clickExecute() {
        autoItX.winWait("[CLASS:SAP_FRONTEND_SESSION]", "", 3);
        autoItX.winActivate("[CLASS:SAP_FRONTEND_SESSION]");
        autoItX.controlFocus("[CLASS:SAP_FRONTEND_SESSION]", "", executeBtnId);
        autoItX.sleep(delay);
        autoItX.controlClick("[CLASS:SAP_FRONTEND_SESSION]", "", executeBtnId);
    }


    // Submit
    public void submit() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_PROC_OPTIONS:/OPT/SAPLVIM_IDX_UI:1003/cntlCC_PROCESS_OPTIONS/shellcont/shell").toDispatch());
        Obj.setProperty("currentCellRow", 2);
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_PROC_OPTIONS:/OPT/SAPLVIM_IDX_UI:1003/cntlCC_PROCESS_OPTIONS/shellcont/shell").toDispatch());
        Obj.setProperty("selectedRows", "2");
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_PROC_OPTIONS:/OPT/SAPLVIM_IDX_UI:1003/cntlCC_PROCESS_OPTIONS/shellcont/shell").toDispatch());
        Obj.invoke("pressButtonCurrentCell");

        autoItX.sleep(3000);
        String result = autoItX.controlGetText("[CLASS:SAP_FRONTEND_SESSION]","","59393");
        Assert.assertNotEquals(result, "Changes saved successfully", "No search results");
    }


    // Enter text in DP Document Type field
    public void enterDpDocumentType(String documentType) throws IOException, InterruptedException {
        getSession();
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/ctxtS_DOCTYP-LOW").toDispatch());
        Obj.invoke("setFocus");
        Obj.setProperty("text", documentType);
    }


    // Click the Local File button
    public void openSaveListInFile() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/tbar[1]/btn[45]").toDispatch());
        Obj.invoke("press");
        autoItX.sleep(500);
    }


    // Select HTML file format
    public void selectFileFormat() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/usr/subSUBSCREEN_STEPLOOP:SAPLSPO5:0150/sub:SAPLSPO5:0150/radSPOPLI-SELFLAG[3,0]").toDispatch());
        Obj.invoke("select");
        autoItX.sleep(500);

        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/tbar[0]/btn[0]").toDispatch());
        Obj.invoke("press");
        autoItX.sleep(500);
    }


    public void generateHtmlFile(String fileName) {
        // Path
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/usr/ctxtDY_PATH").toDispatch());
        Obj.setProperty("text", tempFolder);
        autoItX.sleep(500);

        // File
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/usr/ctxtDY_FILENAME").toDispatch());
        Obj.setProperty("text", fileName + ".html");
        autoItX.sleep(500);

        // Generate
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/tbar[0]/btn[0]").toDispatch());
        Obj.invoke("press");
    }
}