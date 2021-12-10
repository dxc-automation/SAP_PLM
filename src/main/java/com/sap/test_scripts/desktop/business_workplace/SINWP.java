package com.sap.test_scripts.desktop.business_workplace;

import com.jacob.activeX.ActiveXComponent;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.properties.TestDataReader;
import com.sap.test_scripts.desktop.commonly_used.CommandField;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.io.IOException;

import static com.sap.config.ExtentReport.*;
import static com.sap.properties.TestDataReader.testDescription;
import static com.sap.properties.TestDataReader.testName;

@Listeners(TestNGListener.class)
public class SINWP extends GeneralTestConfig {

    private CommandField commandField = new CommandField();
    private TestDataReader testDataReader = new TestDataReader();

    private String transactionWindowTitle = "Business Workplace of Venugopal Suddala";
    private String transactionCode = "SBWP";

    //***   Find Dialog
    private String applicationToolbar        = "wnd[0]/usr/cntlSINWP_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[0]/shell";
    private String findDialogCancelBtn       = "123";
    private String findDialogSearchTermField = "wnd[1]/usr/txtGS_SEARCH-VALUE";
    private String checkMarkBtn              = "wnd[1]/tbar[0]/btn[0]";

    private final String systemFunctionBarBackBtn         = "wnd[0]/tbar[0]/btn[3]";
    private final String filterDialogStatusIndicatorField = "wnd[1]/usr/ssub%_SUBSCREEN_FREESEL:SAPLSSEL:1105/ctxt%%DYN009-LOW";

    private final String advancedTreeList = "wnd[0]/usr/cntlSINWP_CONTAINER/shellcont/shell/shellcont[0]/shell";
    private final String approveBtn = "wnd[0]/tbar[1]/btn[16]";
    private final String rejectBtn  = "wnd[0]/tbar[1]/btn[14]";

    private final String menuBarSystem = "wnd[0]/mbar/menu[6]/menu[11]";


    public void openTransaction(String scenarioType, int testCaseNumber) throws Exception {
        String testCaseString = testDataReader.getTestCaseID(scenarioType, testCaseNumber);

        // Declare what will be information printed in the report
        testName = "<b>Approve/Reject Approvals in SAP</b>";
        testDescription =
                "<br><b>* * * &Tab; T E S T &nbsp; &nbsp; S T E P S &Tab; * * *</b><br>" +
                        "[1] Open Business Workplace.<br>" +
                        "[2] Navigate to Inbox/Workflow folder.<br>" +
                        "[3] Set status filter.<br>" +
                        "[4] Search for specific id.<br>" +
                        "[5] Approve trip request.<br>" +
                        "[6] Reject trip request.<br>" +
                        "[7] Close transaction.";

        // Start report listener
        startTestReport("desktop", testName, testDescription, testCaseString);

        commandField.searchForTransaction(transactionCode);
        commandField.checkSearchResult(transactionCode, menuBarSystem);
        test.pass("Verify that the user can open Business Workplace");
    }

    public void openInbox() throws IOException, InterruptedException {
        getSession();
        Obj = new ActiveXComponent(Session.invoke("FindById", advancedTreeList).toDispatch());
        Obj.invoke("selectNode", "          2");

        Obj = new ActiveXComponent(Session.invoke("FindById", advancedTreeList).toDispatch());
        Obj.invoke("pressKey", "Enter");

        Obj = new ActiveXComponent(Session.invoke("FindById", advancedTreeList).toDispatch());
        Obj.invoke("selectNode", "          5");
        Obj.invoke("pressKey", "Enter");

        test.createNode("<b>[ Check Business Workplace Inbox ]</b>")
            .pass("Business workplace is opened")
            .pass("Workplace inbox folder is opened");
    }


    public void searchRequest(int testCase) throws Exception {
        Obj = new ActiveXComponent(Session.invoke("FindById", applicationToolbar).toDispatch());
        Obj.invoke("pressToolbarButton", "&FIND");

        String searchItem = TEST_DATA.getRequestID();
        Obj = new ActiveXComponent(Session.invoke("FindById", findDialogSearchTermField).toDispatch());
        Obj.setProperty("text", searchItem);

        Obj = new ActiveXComponent(Session.invoke("FindById", checkMarkBtn).toDispatch());
        Obj.invoke("press");

        autoItX.sleep(3000);
        String result = autoItX.controlGetText(transactionWindowTitle,"","59393");
        Assert.assertNotEquals("No Hit Found", result, "No search results");

        test.createNode("<b>[ Check Request Searching Functionality ]</b>")
            .pass("Specific trip request has been found")
            .info("Search for <b>" + searchItem + "</b><br>Search status is <b>" + result + "</b>");
    }

    public void setFilter(String scenarioType, int testCaseNumber) throws Exception {
        Obj = new ActiveXComponent(Session.invoke("FindById", applicationToolbar).toDispatch());
        Obj.invoke("selectAll");

        Obj = new ActiveXComponent(Session.invoke("FindById", applicationToolbar).toDispatch());
        Obj.invoke("pressToolbarButton", "&MB_FILTER");

        Obj = new ActiveXComponent(Session.invoke("FindById", filterDialogStatusIndicatorField).toDispatch());
        Obj.invoke("setFocus");

        String filterByStatusIndicator = testDataReader.getAction(scenarioType, testCaseNumber);
        switch (filterByStatusIndicator) {
            case "READY":
                Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[1]/usr/ssub%_SUBSCREEN_FREESEL:SAPLSSEL:1105/ctxt%%DYN009-LOW").toDispatch());
                Obj.setProperty("text", "@CS\\QReady@");
                break;
            case "IN PROGRESS":
                Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[1]/usr/ssub%_SUBSCREEN_FREESEL:SAPLSSEL:1105/ctxt%%DYN009-LOW").toDispatch());
                Obj.setProperty("text", "@CU\\QIn Process@");
                break;
        }

        Obj = new ActiveXComponent(Session.invoke("FindById", checkMarkBtn).toDispatch());
        Obj.invoke("press");

        test.createNode("<b>[ Check Inbox Filtering Functionality ]</b>")
                .pass("Filtering has been applied successfully");
    }

    public void acceptRequest() throws IOException, InterruptedException {
        autoItX.winWaitActive(transactionWindowTitle);
        autoItX.winActivate(transactionWindowTitle);
        autoItX.controlSend(transactionWindowTitle,"","SAPALVGrid1","{DOWN}");
        autoItX.sleep(5000);

        Obj = new ActiveXComponent(Session.invoke("FindById", applicationToolbar).toDispatch());
        Obj.invoke("pressToolbarButton", "APRO");

        Obj = new ActiveXComponent(Session.invoke("FindById", approveBtn).toDispatch());
        Obj.invoke("press");

        autoItX.sleep(2000);
        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[1]/usr/tblSAPLZ_WFR_TRTC_410_COMMENTS/txtST_APP_COMMENTS-LINE[0,0]").toDispatch());
        Obj.setProperty("text", "Automation Test");

        autoItX.sleep(2000);
        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[1]/usr/chkCHK_COVID").toDispatch());
        Obj.setProperty("selected", "true");

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[1]/tbar[0]/btn[5]").toDispatch());
        Obj.invoke("press");

        autoItX.sleep(3000);
        String result = autoItX.controlGetText(transactionWindowTitle,"","59393");
        boolean status = result.contains("approved");
        Assert.assertTrue(status, "Trip request was not approved");

        test.createNode("<b>[ Check Approving Trip Request ]</b>")
                .pass("Request has been approved successfully");
    }

    public void rejectRequest() {
        autoItX.winWaitActive(transactionWindowTitle);
        autoItX.winActivate(transactionWindowTitle);
        autoItX.controlSend(transactionWindowTitle,"","SAPALVGrid1","{DOWN}");
        autoItX.sleep(5000);

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[0]/usr/cntlSINWP_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[0]/shell").toDispatch());
        Obj.invoke("pressToolbarButton", "APRO");

        Obj = new ActiveXComponent(Session.invoke("FindById", rejectBtn).toDispatch());
        Obj.invoke("press");

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[1]/usr/tblSAPLZ_WFR_BOTC_110_APP_TXT/txtI_APP_TXT-LINE[0,0]").toDispatch());
        Obj.setProperty("text", "Automation Test");

        Obj = new ActiveXComponent(Session.invoke("FindById", "wnd[1]/usr/btnBTNOK").toDispatch());
        Obj.invoke("press");

        test.createNode("<b>[ Check Rejecting Trip Request ]</b>")
                .pass("Request has been rejected successfully");
    }

    public void closeTransaction() throws IOException, InterruptedException {
        commandField.goToRootSAPMenu();
        test.pass("Check that transaction can be closed properly");
    }
}