package com.sap.objects.desktop.opt_vim;

import com.jacob.activeX.ActiveXComponent;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.properties.TestDataReader;
import com.sap.test_scripts.desktop.commonly_used.CommandField;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.io.IOException;

import static com.sap.properties.TestData.*;
import static org.testng.Assert.assertTrue;

@Listeners(TestNGListener.class)
public class VIM_WP_Obj extends GeneralTestConfig {

    private CommandField commandField = new CommandField();
    private TestDataReader testDataReader = new TestDataReader();

    public String transactionWindowTitle;
    public String transactionCodeSearch  = "/N/OPT/VIM_WP";
    public String transactionCode        = "/OPT/VIM_WP";
    public String transactionProgram     = "/OPT/SAPLVIM_PMC_UI_COMP";

    private String path;
    private String value;
    private String workViewTitle;
    public String  menuBarSystemStatus = "wnd[0]/mbar/menu[2]/menu[11]";



    // Select and confirm work view
    public void switchWorkView(String workView) throws IOException, InterruptedException {
        // Open work view options dialog
        getSession();
        System.out.println("====> " + workView);
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/tbar[1]/btn[18]").toDispatch());
        Obj.invoke("press");
        autoItX.sleep(500);

        switch (workView) {
            case "personal":
                path = "wnd[1]/usr/subSUBSCREEN_STEPLOOP:SAPLSPO5:0150/sub:SAPLSPO5:0150/radSPOPLI-SELFLAG[0,0]";
                break;
            case "team":
                path = "wnd[1]/usr/subSUBSCREEN_STEPLOOP:SAPLSPO5:0150/sub:SAPLSPO5:0150/radSPOPLI-SELFLAG[2,0]";
                break;
            case "all users":
                path = "wnd[1]/usr/subSUBSCREEN_STEPLOOP:SAPLSPO5:0150/sub:SAPLSPO5:0150/radSPOPLI-SELFLAG[3,0]";
                break;
        }
        System.out.println("==== " + path);
        // Select work view option
        Obj = new ActiveXComponent(Session.invoke("findById", path).toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(500);
        Obj.invoke("select");

        // Confirm
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/tbar[0]/btn[0]").toDispatch());
        Obj.invoke("setFocus");
        Obj.invoke("press");
        autoItX.sleep(500);
    }

    public void validateWorkView(String expectedWorkView) {
        // Get text from title bar
        autoItX.winWait("titl", "", 3);
        autoItX.winActivate("titl");
        Obj = new ActiveXComponent(Session.invoke("findById", "/app/con[0]/ses[0]/wnd[0]/titl").toDispatch());
        String actualWorkView = Obj.getProperty("Text").toString();

        switch (expectedWorkView) {
            case "personal":
                workViewTitle = "Personal View";
                break;
            case "team":
                workViewTitle = "Team View";
                break;
            case "all users":
                workViewTitle = "All Users View";
                break;
        }
        boolean contains = actualWorkView.contains(workViewTitle);
        Assert.assertTrue(contains, "Work view screen title is different from expected !");
        transactionWindowTitle = workViewTitle;
    }


    public void findDocument(String documentId) {
        // Open Search dialog
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/shellcont[1]/shell/shellcont[0]/shell").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("pressToolbarButton", "&FIND");

        // Add search item
        autoItX.winWait("Find", "", 3);
        autoItX.winActivate("Find");
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/usr/txtGS_SEARCH-VALUE").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.setProperty("text", documentId);

        // Confirm
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/tbar[0]/btn[0]").toDispatch());
        Obj.invoke("press");

        // Verify search result exists
        autoItX.sleep(2000);
        String result = autoItX.controlGetText(transactionWindowTitle,"","59393");
        Assert.assertNotEquals(result, "No Hit Found", "No search results");
    }

    public void searchDocumentAllInbox() {
        // Add text
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/tabsTAB_MAIN/tabpTAB1/ssubTAB_MAIN_SUBSCREEN:/OPT/SAPLVIM_PMC_UI_COMP:1001/subSELECT_SUBSCRN_MIDDLE:/OPT/SAPLVIM_PMC_UI_COMP:1200/ctxtH1_DOID-LOW").toDispatch());
        Obj.invoke("setFocus");
        Obj.setProperty("Text", TEST_DATA.getRequestID());
        autoItX.sleep(500);

        // Press apply
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/tabsTAB_MAIN/tabpTAB1/ssubTAB_MAIN_SUBSCREEN:/OPT/SAPLVIM_PMC_UI_COMP:1001/btnBT_H_APPLY").toDispatch());
        Obj.invoke("setFocus");
        Obj.invoke("press");
        autoItX.sleep(1000);
    }



    public void openSearchResultDocument() {
        // Locate execution cell
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/shellcont[1]/shell/shellcont[0]/shell").toDispatch());
        Obj.setProperty("currentCellColumn", "ICON_EXEC");
        Obj.invoke("setFocus");

        // Click execute
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/shellcont[1]/shell/shellcont[0]/shell").toDispatch());
        Obj.invoke("clickCurrentCell");
    }


    // Save
    public void saveChanges() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/tbar[0]/btn[11]").toDispatch());
        Obj.invoke("press");
        autoItX.sleep(500);
    }


    // Submit
    public void submitDocument(String scenarioType, int testCaseNumber) throws Exception {
        actionType = testDataReader.getAction(scenarioType, testCaseNumber);

        if (!isRequestDuplicated) {
            Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_PROC_OPTIONS:/OPT/SAPLVIM_IDX_UI:1003/cntlCC_PROCESS_OPTIONS/shellcont/shell").toDispatch());
            Obj.setProperty("currentCellRow", 2);
            Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_PROC_OPTIONS:/OPT/SAPLVIM_IDX_UI:1003/cntlCC_PROCESS_OPTIONS/shellcont/shell").toDispatch());
            Obj.setProperty("selectedRows", "2");
            Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_PROC_OPTIONS:/OPT/SAPLVIM_IDX_UI:1003/cntlCC_PROCESS_OPTIONS/shellcont/shell").toDispatch());
            Obj.invoke("pressButtonCurrentCell");

            autoItX.sleep(3000);
            String result = autoItX.controlGetText("[CLASS:SAP_FRONTEND_SESSION]","","59393");
            Assert.assertEquals(result, "Changes saved successfully", "No search results");

        } else {
            Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/tbar[1]/btn[22]").toDispatch());
            Obj.invoke("press");
            autoItX.sleep(1000);
            Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/usr/subSS_CURRENT_COMMENTS:/OPT/SAPLCP_FG9C1:0011/cntlCC_CURRENT_COMMENTS/shellcont/shell").toDispatch());
            Obj.setProperty("text", "Test");
            autoItX.sleep(500);
            Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/tbar[0]/btn[11]").toDispatch());
            Obj.invoke("press");
            autoItX.sleep(1000);
        }
    }



    // Get property values from Basic Data tab
    public String getReviewerBasicData(String property) throws IOException, InterruptedException {
        switch (property) {
            case "referenceNumber":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB1/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1100/txtGH_IDX_APPLICATION->MS_IDX_HEADER-XBLNR";
                break;
            case "companyCode":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB1/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1100/ctxtGH_IDX_APPLICATION->MS_IDX_HEADER-BUKRS";
                break;
            case "grossAmount":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB1/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1100/txtGH_IDX_APPLICATION->MS_IDX_HEADER-GROSS_AMOUNT";
                break;
            case "currency":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB1/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1100/ctxtGH_IDX_APPLICATION->MS_IDX_HEADER-WAERS";
                break;
            case "expenseType":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB1/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1100/ctxtGH_IDX_APPLICATION->MS_IDX_HEADER-EXPENSE_TYPE";
                break;
            case "documentDate":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB1/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1100/ctxtGH_IDX_APPLICATION->MS_IDX_HEADER-BLDAT";
                break;
        }
        Obj = new ActiveXComponent(Session.invoke("findById", path).toDispatch());
        Obj.invoke("setFocus");
        value = Obj.getProperty("text").toString();

        return value;
    }


    // Get properties from Line Items tab
    public String getReviewerLineItems(String property) {
        // Open line items tab
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB2").toDispatch());
        Obj.invoke("setFocus");
        Obj.invoke("select");

        switch (property) {
            case "amount":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB2/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1200/tabsTAB_SUB/tabpSUB_TAB2/ssubTAB_SUB_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1210/tblSAPLZFI_VIM75_IDX_UI_NPOTCTRL_ITEM_1210/txt/OPT/CIDX_ITEMS-WRBTR[4,0]";
                break;
            case "accountGL":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB2/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1200/tabsTAB_SUB/tabpSUB_TAB2/ssubTAB_SUB_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1210/tblSAPLZFI_VIM75_IDX_UI_NPOTCTRL_ITEM_1210/ctxt/OPT/CIDX_ITEMS-HKONT[1,0]";
                break;
            case "companyCode":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB2/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1200/tabsTAB_SUB/tabpSUB_TAB2/ssubTAB_SUB_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1210/tblSAPLZFI_VIM75_IDX_UI_NPOTCTRL_ITEM_1210/ctxt/OPT/CIDX_ITEMS-BUKRS[7,0]";
                break;
            case "costCenter":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB2/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1200/tabsTAB_SUB/tabpSUB_TAB2/ssubTAB_SUB_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1210/tblSAPLZFI_VIM75_IDX_UI_NPOTCTRL_ITEM_1210/ctxt/OPT/CIDX_ITEMS-KOSTL[9,0]";
                break;
        }
        Obj = new ActiveXComponent(Session.invoke("findById", path).toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();

        return value;
    }


    // Get property values from Other Data tab
    public String getReviewerOtherData(String property) {
        // Open process tab
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6").toDispatch());
        Obj.invoke("setFocus");
        Obj.invoke("select");

        switch (property) {
            case "approver":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/txtGH_IDX_APPLICATION->MS_IDX_HEADER-ZAPPROVER";
                break;
            case "indexUser":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/txtGH_IDX_APPLICATION->MS_IDX_HEADER-INDEX_USER";
                break;
            case "serviceFrom":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/txtGH_IDX_APPLICATION->MS_IDX_HEADER-ZSERVFROMDATE";
                break;
            case "serviceTo":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/txtGH_IDX_APPLICATION->MS_IDX_HEADER-ZSERVTODATE";
                break;
            case "requestType":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/txtGV_CARD";
                break;
            case "ttlUPI":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/txtGH_IDX_APPLICATION->MS_IDX_HEADER-ZZTTL_UPI";
                break;
            case "cardLastDigits":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/txtGH_IDX_APPLICATION->MS_IDX_HEADER-ZZCRAD_NO";
                break;
            case "cardHolderUPI":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/txtGH_IDX_APPLICATION->MS_IDX_HEADER-ZZCARD_UPI";
                break;
        }

        Obj = new ActiveXComponent(Session.invoke("findById", path).toDispatch());
        Obj.invoke("setFocus");
        value = Obj.getProperty("text").toString();

        return value;
    }

    // Check review completed checkbox
    public void reviewCompleted() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/chkGH_IDX_APPLICATION->MS_IDX_HEADER-CUSTOM_FIELD7").toDispatch());
        Obj.invoke("setFocus");
        Obj.setProperty("selected", -1);
    }


    // Get property values from Process tab
    public String getReviewerProcess(String property) {
        // Open process tab
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB5").toDispatch());
        Obj.invoke("setFocus");
        Obj.invoke("select");

        switch (property) {
            case "documentId":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB5/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1500/ctxtGH_IDX_APPLICATION->MS_IDX_HEADER-DOCID";
                break;
            case "documentType":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB5/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1500/ctxtGH_IDX_APPLICATION->MS_IDX_HEADER-DOCTYPE";
                break;
            case "documentStatus":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB5/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1500/cmbGH_IDX_APPLICATION->MS_IDX_HEADER-STATUS";
                break;
            case "processType":
                path = "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB5/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1500/cmbGH_IDX_APPLICATION->MS_IDX_FIELDS-PROC_TYPE";
                break;
        }
        Obj = new ActiveXComponent(Session.invoke("findById", path).toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();

        return value;
    }


    public void setToObsolete() throws Exception {
        if (isRequestDuplicated) {
            Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/tbar[1]/btn[20]").toDispatch());
            Obj.invoke("press");
            autoItX.sleep(500);

            Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/usr/cmbG_DELREASON").toDispatch());
            Obj.setProperty("key", "01");
            autoItX.sleep(500);

            Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/usr/cntlG_COMMCONT/shellcont/shell").toDispatch());
            Obj.setProperty("text", "Test");
            autoItX.sleep(500);

            Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/tbar[0]/btn[5]").toDispatch());
            Obj.invoke("press");
            autoItX.sleep(500);
        } else {
            System.out.println("Request must be duplicated for this action !");
        }
    }

}