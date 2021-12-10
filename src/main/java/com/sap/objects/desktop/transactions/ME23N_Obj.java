package com.sap.objects.desktop.transactions;

import com.jacob.activeX.ActiveXComponent;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.test_scripts.desktop.commonly_used.CommandField;
import org.testng.annotations.Listeners;

import java.io.IOException;

@Listeners(TestNGListener.class)
public class ME23N_Obj extends GeneralTestConfig {

    private CommandField commandField = new CommandField();

    public String transactionWindowTitle = "Consultant (FIRM) PO 7198959 Created by Renu Bansilal LNU";
    public String transactionCode = "ME23N";

    public final String menuBarSystemStatus    = "wnd[0]/mbar/menu[4]/menu[11]";


    public void clickBackButton() throws IOException, InterruptedException {
        getSession();
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/tbar[0]/btn[3]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");
    }

    public void clickOtherPurchaseOrderButton() throws IOException, InterruptedException {
        getSession();
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/tbar[1]/btn[17]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");
    }

    public void setDocumentPO(String documentPO) {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[1]/usr/subSUB0:SAPLMEGUI:0003/ctxtMEPO_SELECT-EBELN").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.setProperty("text", "");
        autoItX.sleep(300);
        Obj.setProperty("text", documentPO);
    }

    public void clickOtherDocumentButton() {
        String windowTitle = "Select Document";
        autoItX.winWaitActive(windowTitle);
        autoItX.winActivate(windowTitle);
        autoItX.controlFocus(windowTitle, "", "111");
        autoItX.sleep(delay);
        autoItX.controlClick(windowTitle, "", "111");
    }

    public void clickHeaderDeliveryInvoiceTab() throws IOException, InterruptedException {
        autoItX.sleep(2000);
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT1").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("select");
    }

    public void clickHeaderConditionsTab() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT2").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("select");
    }

    public void clickHeaderAddressTab() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0018/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT4").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("select");
    }

    public void clickHeaderCommunicationTab() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT5").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");
    }

    public void clickHeaderAdditionalTab() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT7").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("select");
    }

    public void clickHeaderOrganizationDataTab() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT9").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("select");
    }

    public void clickHeaderStatusTab() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT10").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("select");
    }

    public void clickHeaderReleaseStrategyTab() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT12").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("select");
    }

    public void clickHeaderVersionsTab() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT13").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("select");
    }


    //***   Delivery/Invoice Tab
    public String getPaymentTermsValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT1/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1226/ctxtMEPO1226-ZTERM").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getPaymentInDaysValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT1/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1226/txtMEPO1226-ZBD1T").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getIncotermsValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT1/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1226/ctxtMEPO1226-INCO1").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getCurrencyValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT1/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1226/ctxtMEPO1226-WAERS").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getExchangeRateValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT1/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1226/ctxtMEPO1226-WKURS").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }


    //***   Conditions Tab
    public void clickConditionsDetailsButton() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0018/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT2/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1236/subSUB0:SAPLV69A:6201/subSUBSCREEN_PUSHBUTTONS:SAPLV69A:1000/btnBT_KODE").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");
    }

    public String getApplicationValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/ctxtKOMV-KAPPL").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getConditionPriceDateValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/ctxtKOMV-KDATU").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getAmountValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/txtKOMV-KBETR").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getConditionBaseValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/txtKOMV-KAWRT").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getConditionValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/txtKOMV-KWERT").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }



    //***   Address Tab
    public String getStreetHouseNumberValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT4/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1223/ssubVENDOR_ADDRESS:SAPLSZA1:0300/subCOUNTRY_SCREEN:SAPLSZA1:0301/txtADDR1_DATA-STREET").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getPostalCodeCityValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT4/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1223/ssubVENDOR_ADDRESS:SAPLSZA1:0300/subCOUNTRY_SCREEN:SAPLSZA1:0301/txtADDR1_DATA-POST_CODE1").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getCountryValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT4/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1223/ssubVENDOR_ADDRESS:SAPLSZA1:0300/subCOUNTRY_SCREEN:SAPLSZA1:0301/ctxtADDR1_DATA-COUNTRY").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getTelephoneValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT4/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1223/ssubVENDOR_ADDRESS:SAPLSZA1:0300/subCOUNTRY_SCREEN:SAPLSZA1:0301/txtSZA1_D0100-TEL_NUMBER").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getFaxValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT4/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1223/ssubVENDOR_ADDRESS:SAPLSZA1:0300/subCOUNTRY_SCREEN:SAPLSZA1:0301/txtSZA1_D0100-FAX_NUMBER").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }




    //***   Additional Data Tab
    public String getValidityStartDateValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT7/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1229/ctxtMEPO1229-KDATB").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getValidityEndDateValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT7/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1229/ctxtMEPO1229-KDATE").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }



    //***   Organization Data Tab
    public String getPurchasingOrganizationValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT9/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1221/ctxtMEPO1222-EKORG").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getPurchasingGroupValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT9/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1221/ctxtMEPO1222-EKGRP").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getCompanyCodeValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT9/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1221/ctxtMEPO1222-BUKRS").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }



    //***   Status Tab
    public String getOrderedValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT10/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1232/ssubHEADER_CUM_1:SAPLMEGUI:1235/txtMEPO1235-VALUE01").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getDeliveredValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT10/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1232/ssubHEADER_CUM_1:SAPLMEGUI:1235/txtMEPO1235-VALUE02").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getStillToDeliverValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT10/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1232/ssubHEADER_CUM_1:SAPLMEGUI:1235/txtMEPO1235-VALUE03").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getInvoicedValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT10/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1232/ssubHEADER_CUM_1:SAPLMEGUI:1235/txtMEPO1235-VALUE04").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getDownPaymentsValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT10/ssubTABSTRIPCONTROL2SUB:SAPLMEGUI:1232/ssubHEADER_CUM_1:SAPLMEGUI:1235/txtMEPO1235-VALUE05").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }



    //***   Release Strategy Tab
    public String getReleaseGroupValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT12/ssubTABSTRIPCONTROL2SUB:SAPLMERELVI:1100/txtMEPO_REL_GENERAL-FRGGR").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getReleaseStrategyValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT12/ssubTABSTRIPCONTROL2SUB:SAPLMERELVI:1100/txtMEPO_REL_GENERAL-FRGSX").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getReleaseIndicatorValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0013/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT12/ssubTABSTRIPCONTROL2SUB:SAPLMERELVI:1100/txtMEPO_REL_GENERAL-FRGKX").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }




    //      Click on header button
    public void clickHeaderExpandButton() throws IOException, InterruptedException {
        getSession();
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0016/subSUB1:SAPLMEVIEWS:1100/subSUB1:SAPLMEVIEWS:4000/btnDYN_4000-BUTTON").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");
    }

    public void clickHeaderCollapseButton() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0018/subSUB1:SAPLMEVIEWS:1100/subSUB1:SAPLMEVIEWS:4000/btnDYN_4000-BUTTON").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");
    }



    //***   Versions Tab

    public void openVersionPrintPreview() throws IOException, InterruptedException {
        autoItX.sleep(1500);
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0018/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT13/ssubTABSTRIPCONTROL2SUB:SAPLMEDCMV:0100/cntlDCMGRIDCONTROL1/shellcont/shell").toDispatch());
        Obj.invoke("pressToolbarContextButton", "&MB_VIEW");
        autoItX.sleep(1500);
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0018/subSUB1:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1102/tabsHEADER_DETAIL/tabpTABHDT13/ssubTABSTRIPCONTROL2SUB:SAPLMEDCMV:0100/cntlDCMGRIDCONTROL1/shellcont/shell").toDispatch());
        Obj.invoke("selectContextMenuItem", "&PRINT_BACK_PREVIEW");
        autoItX.winActivate("[CLASS:AfxWnd140]");
        autoItX.controlClick("[CLASS:AfxWnd140]", "", "Afx:52A30000:10081");
    }

    public String getVersionValue() throws IOException, InterruptedException {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/sub/1[0,0]/sub/1/2[0,0]/sub/1/2/3[0,3]/lbl[8,3]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getReasonForChangeValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/sub/1[0,0]/sub/1/2[0,0]/sub/1/2/3[0,3]/lbl[23,3]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getTextValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/sub/1[0,0]/sub/1/2[0,0]/sub/1/2/3[0,3]/lbl[29,3]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getRequestedByValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/sub/1[0,0]/sub/1/2[0,0]/sub/1/2/3[0,3]/lbl[60,3]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getNetOrderValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/sub/1[0,0]/sub/1/2[0,0]/sub/1/2/3[0,3]/lbl[70,3]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getValueChange() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/sub/1[0,0]/sub/1/2[0,0]/sub/1/2/3[0,3]/lbl[82,3]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getVersionCurrencyValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/sub/1[0,0]/sub/1/2[0,0]/sub/1/2/3[0,3]/lbl[94,3]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getCreatedByValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/sub/1[0,0]/sub/1/2[0,0]/sub/1/2/3[0,3]/lbl[99,3]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getCreatedOnValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/sub/1[0,0]/sub/1/2[0,0]/sub/1/2/3[0,3]/lbl[108,3]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getTimeValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/sub/1[0,0]/sub/1/2[0,0]/sub/1/2/3[0,3]/lbl[119,3]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getReleasedByValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/sub/1[0,0]/sub/1/2[0,0]/sub/1/2/3[0,3]/lbl[128,3]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getReleasedOnValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/sub/1[0,0]/sub/1/2[0,0]/sub/1/2/3[0,3]/lbl[137,3]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getTimeOfReleaseValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/sub/1[0,0]/sub/1/2[0,0]/sub/1/2/3[0,3]/lbl[148,3]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }




    //***   ITEM OVERVIEW

    public void collapseItemOverview() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0016/subSUB2:SAPLMEVIEWS:1100/subSUB1:SAPLMEVIEWS:4001/btnDYN_4000-BUTTON").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");
    }

    public void expandItemOverview() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0016/subSUB2:SAPLMEVIEWS:1100/subSUB1:SAPLMEVIEWS:4001/btnDYN_4000-BUTTON").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");
    }

    public String getItemOverviewNetPriceValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0016/subSUB2:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1211/tblSAPLMEGUITC_1211/txtMEPO1211-NETPR[8,0]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getItemOverviewCurrencyValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0016/subSUB2:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1211/tblSAPLMEGUITC_1211/txtMEPO1211-WAERS[9,0]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getItemOverviewDeliveryDateValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0016/subSUB2:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1211/tblSAPLMEGUITC_1211/ctxtMEPO1211-EEIND[10,0]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getItemOverviewQuantityValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0016/subSUB2:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1211/tblSAPLMEGUITC_1211/txtMEPO1211-MENGE[6,0]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }



    //***   ITEM

    public void expandItem() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0016/subSUB3:SAPLMEVIEWS:1100/subSUB1:SAPLMEVIEWS:4002/btnDYN_4000-BUTTON").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");
        autoItX.sleep(500);
    }

    public void collapseItem() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0021/subSUB3:SAPLMEVIEWS:1100/subSUB1:SAPLMEVIEWS:4002/btnDYN_4000-BUTTON").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");
    }

    public void openItemDeliveryScheduleTab() {
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0019/subSUB3:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1301/subSUB2:SAPLMEGUI:1303/tabsITEM_DETAIL/tabpTABIDT5").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("select");
    }

    public void openItemConditionsTab() throws IOException, InterruptedException {
        getSession();
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0019/subSUB3:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1301/subSUB2:SAPLMEGUI:1303/tabsITEM_DETAIL/tabpTABIDT8").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("select");
    }

    public String getItemConditionNetPriceValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/sub/1[0,0]/sub/1/3[0,1]/sub/1/3/4[0,4]/lbl[62,4]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getItemConditionCurrencyValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/ctxtRV61A-KOEIN").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getItemConditionQuantityValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/txtKOMV-KPEIN").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public String getItemDeliveryDateValue() {
        String value;
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0015/subSUB3:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1301/subSUB2:SAPLMEGUI:1303/tabsITEM_DETAIL/tabpTABIDT5/ssubTABSTRIPCONTROL1SUB:SAPLMEGUI:1320/tblSAPLMEGUITC_1320/ctxtMEPO1320-EEIND[2,0]").toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        value = Obj.getProperty("text").toString();
        return value;
    }

    public void openItemConditionDetails() {
        autoItX.sleep(3000);
        Obj = new ActiveXComponent(Session.invoke("findById", "wnd[0]/usr/subSUB0:SAPLMEGUI:0019/subSUB3:SAPLMEVIEWS:1100/subSUB2:SAPLMEVIEWS:1200/subSUB1:SAPLMEGUI:1301/subSUB2:SAPLMEGUI:1303/tabsITEM_DETAIL/tabpTABIDT1/ssubTABSTRIPCONTROL1SUB:SAPLMEGUI:1328/subSUB0:SAPLMLSP:0400/btnDETAIL").toDispatch());
        Obj.invoke("press");
    }
}