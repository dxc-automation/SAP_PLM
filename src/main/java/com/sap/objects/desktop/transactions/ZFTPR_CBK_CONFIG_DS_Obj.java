package com.sap.objects.desktop.transactions;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.properties.TestDataReader;
import com.sap.test_scripts.desktop.commonly_used.CommandField;
import org.testng.annotations.Listeners;

import java.io.IOException;

@Listeners(TestNGListener.class)
public class ZFTPR_CBK_CONFIG_DS_Obj extends GeneralTestConfig {

    private CommandField commandField = new CommandField();
    private TestDataReader testDataReader = new TestDataReader();
    private Variant[] arg = new Variant[2];

    private String transactionCode = "ZFTPR_CBK_CONFIG_DS";
    private String transactionWindowTitle = "Maintain DocuSign Email Id cost object mapping";

    public void clickEnterButton() {
        String messageWindow = "[CLASS:#32770]";
        autoItX.winWait(messageWindow, "", 3);

        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/tbar[0]/btn[0]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");
    }


    //  EXPORT TO EXCEL
    public void clickExportButton() throws IOException, InterruptedException {
        getSession();
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlALV_CONTAINER/shellcont/shell").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("pressToolbarContextButton", "&MB_EXPORT");
    }

    public void selectExportSpreadsheetDropdownOption() {
        autoItX.sleep(1500);
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlALV_CONTAINER/shellcont/shell").toDispatch());
        Obj.invoke("selectContextMenuItem", "&XXL");
    }


    public void selectExportProcessingMode(String scenarioType, int testCaseNumber) throws Exception {
        String processingMode = testDataReader.getChargeCode(scenarioType, testCaseNumber);
        switch (processingMode) {
            case "Table":
                Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/usr/subSUBSCREEN_STEPLOOP:SAPLSPO5:0150/sub:SAPLSPO5:0150/radSPOPLI-SELFLAG[0,0]").toDispatch());
                Obj.invoke("setFocus");
                autoItX.sleep(delay);
                Obj.invoke("select");
                break;
            case "Pivot Table":
                Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/usr/subSUBSCREEN_STEPLOOP:SAPLSPO5:0150/sub:SAPLSPO5:0150/radSPOPLI-SELFLAG[1,0]").toDispatch());
                Obj.invoke("setFocus");

                Obj.invoke("select");
                break;
        }

        // Click continue button
        clickEnterButton();
    }


    public void openListOutputView() throws IOException, InterruptedException {
        autoItX.sleep(1500);
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlALV_CONTAINER/shellcont/shell").toDispatch());
        Obj.invoke("pressToolbarContextButton", "&MB_VIEW");
        autoItX.sleep(500);
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/cntlALV_CONTAINER/shellcont/shell").toDispatch());
        Obj.invoke("selectContextMenuItem", "&PRINT_BACK_PREVIEW");
        autoItX.winActivate("[CLASS:AfxWnd140]");
        autoItX.controlClick("[CLASS:AfxWnd140]", "", "AfxWnd1404");
    }

    @SuppressWarnings("deprecation")
    public ActiveXComponent SAPGUITableControlGetCellObject(String lbl, int rowid, String ColumnName )
    {
        String str = null;
        ActiveXComponent currentObj=null;
        arg[0] = new Variant(lbl);
        arg[1] = new Variant("Afx:52A30000:1008");
        Obj = new ActiveXComponent(Session.invoke("FindByName",  arg).toDispatch());
        int Columnindex = SAPGUITableControlGetColumnIndex(lbl, ColumnName);
        currentObj = new ActiveXComponent(Session.invoke("GetCell", rowid, Columnindex ).toDispatch());
        return currentObj;

    }

    @SuppressWarnings("deprecation")
    public int SAPGUITableControlGetColumnIndex(String lbl, String ColumName )
    {
        String str = null;
        ActiveXComponent currentObj;
        arg[0] = new Variant(lbl);
        arg[1] = new Variant("Afx:52A30000:1008");
        Obj = new ActiveXComponent(Session.invoke("FindByName",  arg).toDispatch());
        Dispatch dis = Dispatch.call(Session, "Columns").toDispatch();
        int count = Dispatch.call(dis, "count").toInt();
        Dispatch[] arr = new Dispatch[count];
        for(int i=0;i<count;i++)
        {
            currentObj = new ActiveXComponent(Session.invoke("Columns", i).toDispatch());
            if( currentObj.getProperty("title").toString().trim().equals(ColumName))
            {
                return i;
            }
        }

        return -1;

    }

}

