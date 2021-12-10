package com.sap.objects.desktop.opt_vim;

import com.jacob.activeX.ActiveXComponent;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.properties.TestDataReader;
import com.sap.test_scripts.desktop.commonly_used.CommandField;
import org.testng.annotations.Listeners;

import java.io.IOException;


@Listeners(TestNGListener.class)
public class ZWFINVAPP_Obj extends GeneralTestConfig {

    private CommandField commandField = new CommandField();
    private TestDataReader testDataReader = new TestDataReader();

    public String transactionCodeSearch  = "ZWFINVAPP";
    public String transactionCode        = "ZWFINVAPP";
    public String transactionProgram     = "ZWF_VIM_DOC_WORKITEM_REPT";

    private String path;
    private String value;
    private String menuBarSystemStatus = "wnd[0]/mbar/menu[3]/menu[11]";
    private String mainWindowClass   = "[CLASS:SAP_FRONTEND_SESSION]";
    private String displayDpButtonId = "118";


    public void setDocumentLedgerNumber() throws IOException, InterruptedException {
        getSession();
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/ctxtP_INV_NO-LOW").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.setProperty("text", TEST_DATA.getRequestID());
    }

    public void clickDisplayDP() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/tbar[1]/btn[7]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");
        autoItX.sleep(500);
    }

    public void closeDisplayedDP() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]").toDispatch());
        Obj.invoke("close");
    }

    // Get property values from Basic Data tab
    public String getManagerBasicData(String property) {
        switch (property) {
            case "referenceNumber":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB1/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1100/txtGH_IDX_APPLICATION->MS_IDX_HEADER-XBLNR";
                break;
            case "companyCode":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB1/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1100/ctxtGH_IDX_APPLICATION->MS_IDX_HEADER-BUKRS";
                break;
            case "grossAmount":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB1/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1100/txtGH_IDX_APPLICATION->MS_IDX_HEADER-GROSS_AMOUNT";
                break;
            case "currency":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB1/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1100/ctxtGH_IDX_APPLICATION->MS_IDX_HEADER-WAERS";
                break;
            case "expenseType":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB1/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1100/ctxtGH_IDX_APPLICATION->MS_IDX_HEADER-EXPENSE_TYPE";
                break;
            case "documentDate":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB1/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1100/ctxtGH_IDX_APPLICATION->MS_IDX_HEADER-BLDAT";
                break;
        }
        Obj = new ActiveXComponent(Session.invoke("findById", path).toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();

        return value;
    }


    // Get properties from Line Items tab
    public String getManagerLineItems(String property) {
        // Open line items tab
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB2").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("select");

        switch (property) {
            case "amount":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB2/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1200/tabsTAB_SUB/tabpSUB_TAB2/ssubTAB_SUB_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1210/tblSAPLZFI_VIM75_IDX_UI_NPOTCTRL_ITEM_1210/txt/OPT/CIDX_ITEMS-WRBTR[4,0]";
                break;
            case "accountGL":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB2/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1200/tabsTAB_SUB/tabpSUB_TAB2/ssubTAB_SUB_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1210/tblSAPLZFI_VIM75_IDX_UI_NPOTCTRL_ITEM_1210/ctxt/OPT/CIDX_ITEMS-HKONT[1,0]";
                break;
            case "companyCode":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB2/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1200/tabsTAB_SUB/tabpSUB_TAB2/ssubTAB_SUB_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1210/tblSAPLZFI_VIM75_IDX_UI_NPOTCTRL_ITEM_1210/ctxt/OPT/CIDX_ITEMS-BUKRS[7,0]";
                break;
            case "costCenter":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB2/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1200/tabsTAB_SUB/tabpSUB_TAB2/ssubTAB_SUB_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1210/tblSAPLZFI_VIM75_IDX_UI_NPOTCTRL_ITEM_1210/ctxt/OPT/CIDX_ITEMS-KOSTL[9,0]";
                break;
        }
        Obj = new ActiveXComponent(Session.invoke("findById", path).toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();

        return value;
    }


    // Get property values from Other Data tab
    public String getManagerOtherData(String property) {
        // Open process tab
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("select");

        switch (property) {
            case "approver":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/txtGH_IDX_APPLICATION->MS_IDX_HEADER-ZAPPROVER";
                break;
            case "indexUser":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/txtGH_IDX_APPLICATION->MS_IDX_HEADER-INDEX_USER";
                break;
            case "serviceFrom":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/txtGH_IDX_APPLICATION->MS_IDX_HEADER-ZSERVFROMDATE";
                break;
            case "serviceTo":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/txtGH_IDX_APPLICATION->MS_IDX_HEADER-ZSERVTODATE";
                break;
            case "requestType":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/txtGV_CARD";
                break;
            case "ttlUPI":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/txtGH_IDX_APPLICATION->MS_IDX_HEADER-ZZTTL_UPI";
                break;
            case "cardLastDigits":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/txtGH_IDX_APPLICATION->MS_IDX_HEADER-ZZCRAD_NO";
                break;
            case "cardHolderUPI":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/txtGH_IDX_APPLICATION->MS_IDX_HEADER-ZZCARD_UPI";
                break;
        }

        Obj = new ActiveXComponent(Session.invoke("findById", path).toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();

        return value;
    }

    // Get property values from Process tab
    public String getManagerProcess(String property) {
        // Open process tab
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB5").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("select");

        switch (property) {
            case "documentId":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB5/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1500/ctxtGH_IDX_APPLICATION->MS_IDX_HEADER-DOCID";
                break;
            case "documentType":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB5/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1500/ctxtGH_IDX_APPLICATION->MS_IDX_HEADER-DOCTYPE";
                break;
            case "documentStatus":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB5/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1500/cmbGH_IDX_APPLICATION->MS_IDX_HEADER-STATUS";
                break;
            case "processType":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB5/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1500/cmbGH_IDX_APPLICATION->MS_IDX_FIELDS-PROC_TYPE";
                break;
        }
        Obj = new ActiveXComponent(Session.invoke("findById", path).toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();

        return value;
    }


    public void approveReject(String action) {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlGRID1/shellcont/shell").toDispatch());
        Obj.setProperty("currentCellColumn", "EXECUTE");
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlGRID1/shellcont/shell").toDispatch());
        Obj.setProperty("selectedRows", "0");
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlGRID1/shellcont/shell").toDispatch());
        Obj.invoke("clickCurrentCell");
        autoItX.sleep(1000);

        switch (action) {
            case "approve":
                Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/btnGV_APPROVE").toDispatch());
                Obj.invoke("setFocus");
                Obj.invoke("press");
                autoItX.sleep(1000);

                Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlTEXTEDIT/shellcont/shell").toDispatch());
                Obj.invoke("setFocus");
                Obj.setProperty("text", "Test");

                Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/btnAPPR_BUTTON").toDispatch());
                Obj.invoke("press");
                break;

            case "reject":
                Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/btnGV_REJECT").toDispatch());
                Obj.invoke("setFocus");
                Obj.invoke("press");
                autoItX.sleep(1000);

                Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlTEXTEDIT/shellcont/shell").toDispatch());
                Obj.invoke("setFocus");
                Obj.setProperty("text", "Test");

                Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/btnREJECT_BUTTON").toDispatch());
                Obj.invoke("press");
                break;
        }
    }


    public void reassign(String UPI) {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlGRID1/shellcont/shell").toDispatch());
        Obj.setProperty("currentCellColumn", "FORWARD");
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlGRID1/shellcont/shell").toDispatch());
        Obj.setProperty("selectedRows", "0");
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlGRID1/shellcont/shell").toDispatch());
        Obj.invoke("clickCurrentCell");
        autoItX.sleep(500);

        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/usr/ctxtNEW_OWNER").toDispatch());
        Obj.invoke("setFocus");
        Obj.setProperty("text", UPI);
        autoItX.sleep(500);

        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/tbar[0]/btn[8]").toDispatch());
        Obj.invoke("press");
        autoItX.sleep(500);
    }

}