package com.sap.utilities;

import com.sap.config.GeneralTestConfig;
import com.google.gson.*;
import okhttp3.MediaType;
import org.apache.commons.io.FileUtils;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.sap.properties.FilePaths.reportFolder;
import static com.sap.properties.FilePaths.*;


public class FileUtility extends GeneralTestConfig {

    public static final Gson gson = new Gson();
    public static final JsonParser parser       = new JsonParser();
    public static final JSONParser jsonParser   = new JSONParser();
    public static final MediaType MediaTypeJSON = MediaType.parse("application/json; charset=utf-8");

    private static String date;



    //***   Folder max size
    public static void setFolderMaxSize(File dir, int actualSize) throws IOException {
        int maximumSize = 150;

        if (actualSize >= maximumSize) {
            deleteFolderContent(dir.toString());
            System.out.println("Report archive data reach maximum size !\nReport archive objects and related objects from database are deleted");
        }
    }


    //***   Returns folder size in bytes
    public static int getFolderSize(File directory) {
        long size = FileUtils.sizeOfDirectory(directory);
        double sizeMB = (double)size / (1024 * 1024);
        int mb = (int) sizeMB;
        System.out.println("Size of " + directory + " is " + mb + " MB\n");

        return mb;
    }


    //***   Delete folder content
    public static void deleteFolderContent(String dir) throws IOException {
        File folder = new File(dir);
        System.out.println("Perform delete folder: " + folder);

        if (folder.exists() && folder.isDirectory()) {
            FileUtils.deleteDirectory(folder);
            FileUtils.forceMkdir(folder);
        } else {
            FileUtils.forceMkdir(folder);
        }
    }


    //***   Zip folder
    public static void zipReport(String fileName) throws IOException, URISyntaxException {
        String sourceDirPath = reportFolder;
        Path zipFilePath = Paths.get(new File(fileName).getPath());
        Path p = Files.createFile(Paths.get(String.valueOf(zipFilePath)));
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
            Path pp = Paths.get(sourceDirPath);
            Files.walk(pp)
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
                        try {
                            zs.putNextEntry(zipEntry);
                            Files.copy(path, zs);
                            zs.closeEntry();
                        } catch (IOException e) {
                            System.err.println(e);
                        }
                    });
        }
        System.out.println("Zip folder completed");
    }


    //***   Create date folder
    public static void createDateFolder(String dir) {
        File folder = new File(dir);

        if (!folder.exists()){
            folder.mkdirs();
            System.out.println("Create report folder " + folder + "\n");
        }
   }

    //***   Get current date
    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        return currentDate;
    }


    //***   Get current time
    public static String getTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:ss");
        LocalTime localDate = LocalTime.now();
        String time = dateTimeFormatter.format(localDate);
        return time;
    }


    //***   Returns method as string
    public static String getFileName(Method method) throws Exception {
        String fileName = method.getName() + ".json";
        return fileName;
    }


    //***   Save string input into .txt file
    public static void saveInputInFile(String content, String fileName) throws IOException {
            File file = new File(reportFolder + fileName + ".log");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
    }
}