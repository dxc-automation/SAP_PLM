package com.sap.objects.desktop.documents;

import com.jacob.activeX.ActiveXComponent;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.properties.TestDataReader;
import com.sap.test_scripts.desktop.commonly_used.CommandField;
import org.testng.annotations.Listeners;

import java.io.IOException;

@Listeners(TestNGListener.class)
public class ProcessNonPOAccuralRequest extends GeneralTestConfig {

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


    // Set focus
    public void setFocus() {
        autoItX.winWait("[CLASS:SAP_FRONTEND_SESSION]", "", 5);
        autoItX.winActivate("[CLASS:SAP_FRONTEND_SESSION]");
    }

    // Get property values from Basic Data tab
    public String getBasicData(String property) throws IOException, InterruptedException {
        getSession();
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
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();

        return value;
    }


    // Get properties from Line Items tab
    public String getLineItems(String property) {
        // Open line items tab
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB2").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
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
    public String getOtherData(String property) {
        // Open process tab
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
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
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();

        return value;
    }


    // Check review completed checkbox
    public void reviewCompleted() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/chkGH_IDX_APPLICATION->MS_IDX_HEADER-CUSTOM_FIELD7").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.setProperty("selected", -1);
    }


    // Get property values from Process tab
    public String getProcess(String property) {
        // Open process tab
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1001/subSUB_TAB_STRIP:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB5").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
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



}