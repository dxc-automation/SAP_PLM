package com.sap.test_scripts.desktop.commonly_used;

import com.jacob.activeX.ActiveXComponent;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import org.testng.annotations.Listeners;

import java.io.IOException;

import static com.sap.properties.FilePaths.tempFolder;

@Listeners(TestNGListener.class)
public class DocumentDetails extends GeneralTestConfig {

    private String path;
    private String value;


    //  Close document details window
    public void closeDocumentDetailsWindow() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]").toDispatch());
        Obj.invoke("close");
    }


    /**
     *  Returns property values from Basic Data tab
     *      Options:
     *  1.  Reference Number
     *  2.  Company Code
     *  3.  Gross Amount
     *  4.  Currency
     *  5.  Expense Type
     *  6.  Document Date
     */
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
        value = Obj.getProperty("text").toString();

        return value;
    }


    /**
     *  Returns properties from Line Items tab
     *      Options:
     *  1.  Amount
     *  2.  Account GL
     *  3.  Company Code
     *  4.  Cost Center
     */
    public String getLineItems(String property) {
        // Open line items tab
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB2").toDispatch());
        Obj.invoke("setFocus");
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
        value = Obj.getProperty("text").toString();

        return value;
    }


    /**
     *  Returns properties from Other Data tab
     *      Options:
     *  1.  Approver
     *  2.  Index User
     *  3.  Service From
     *  4.  Service To
     *  5.  UPI of TTL
     *  6.  Card Last 4 Digits
     *  7.  Card Holder UPI
     */
    public String getOtherData(String property) throws IOException, InterruptedException {
        getSession();
        // Open other data tab
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6").toDispatch());
        Obj.invoke("setFocus");
        Obj.invoke("select");

        switch (property) {
            case "approver":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/cmbGV_APPROVER_LIST";
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
            case "amount":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/txtGH_IDX_APPLICATION->MS_IDX_HEADER-ZZNPO_REQ_AMT";
                break;
            case "subAmount":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/txtGV_ORIG_USD";
                break;
            case "currency":
                path = "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB6/ssubTAB_MAIN_SUBSCREEN:SAPLZFI_VIM75_IDX_UI_NPO:1903/txtGH_IDX_APPLICATION->MS_IDX_HEADER-WAERS";
                break;
        }

        Obj = new ActiveXComponent(Session.invoke("findById", path).toDispatch());
        Obj.invoke("setFocus");
        value = Obj.getProperty("text").toString();

        return value;
    }


    /**
     *  Returns properties from Process tab
     *      Options:
     *  1.  Document ID
     *  2.  Document Type
     *  3.  Document Status
     *  4.  Process Type
     */
    public String getProcess(String property) {
        // Open process tab
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/usr/subSUB_MAIN:/OPT/SAPLVIM_IDX_UI:1002/tabsTAB_MAIN/tabpTAB5").toDispatch());
        Obj.invoke("setFocus");
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
        value = Obj.getProperty("text").toString();

        return value;
    }
}

