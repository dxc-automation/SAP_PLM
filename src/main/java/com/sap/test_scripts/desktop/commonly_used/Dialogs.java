package com.sap.test_scripts.desktop.commonly_used;

import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import org.testng.annotations.Listeners;

import java.io.IOException;

import static com.sap.properties.FilePaths.tempFolder;

@Listeners(TestNGListener.class)
public class Dialogs extends GeneralTestConfig {


   public void cancelDownloadFile() throws IOException, InterruptedException {
       autoItX.winWaitActive("File Download", "", 10);
       if (autoItX.winExists("File Download")) {
           System.out.println("File Download dialog is detected");
           try {
               autoItX.winActivate("File Download");
               autoItX.controlFocus("File Download", "", "Button3");
               autoItX.sleep(500);
               autoItX.controlClick("File Download", "", "Button3");
               autoItX.sleep(500);
               System.out.println("File download has been canceled");
           } catch (Exception e) {
               //TODO
           }
       } else {
           System.out.println("File download dialog not found");
       }
   }

   public void saveDownloadFile(String tempFileName) throws IOException, InterruptedException {
       getSession();
       String dialogTitle = "[CLASS:#32770]";
       String dialogSaveBtn = "4427";
       String dialogConfirmSaveBtn = "Button2";
       String dialogAddressBarTxtField = "1001";
       String dialogFileNameField = "Edit1";
       String file = tempFolder + tempFileName + ".pdf";

       // Click save button

       autoItX.winWait("File Download");
       autoItX.winActivate("File Download");
       autoItX.controlFocus("File Download", "", dialogSaveBtn);
       autoItX.sleep(delay);
       autoItX.controlClick("File Download", "", dialogSaveBtn);

       // Add file path and name
       autoItX.winWait("Save As");
       autoItX.winActivate("Save As");
       autoItX.controlFocus("Save As", "", dialogFileNameField);
       autoItX.sleep(delay);
       autoItX.controlSend("Save As", "", dialogFileNameField, "");
       autoItX.sleep(500);
       autoItX.controlSend("Save As", "", dialogFileNameField, file);
       autoItX.sleep(500);

       // Confirm save
       autoItX.controlFocus("Save As", "", dialogConfirmSaveBtn);
       autoItX.sleep(500);
       autoItX.sleep(delay);
       autoItX.controlClick("Save As", "", dialogConfirmSaveBtn);
   }


   public void uploadFile(String filePath) throws IOException, InterruptedException {
       getSession();

       String windowClass = "[CLASS:#32770]";
       String windowName = "Open";
       String openButton  = "Button1";
       String filenameInput = "Edit1";

       autoItX.winWait(windowClass);
       autoItX.winActivate(windowClass);
       autoItX.controlSend(windowClass, "", filenameInput, filePath);
       autoItX.controlClick(windowClass, "", openButton);
       System.out.println("File is uploaded successfully");
   }


   public void closePDF() {
       autoItX.sleep(2000);
       autoItX.winActivate("[CLASS:AcrobatSDIWindow]");
       autoItX.controlClick("[CLASS:AcrobatSDIWindow]", "", "Close");
   }


}

