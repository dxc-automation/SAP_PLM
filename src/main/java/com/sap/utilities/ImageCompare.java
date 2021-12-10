package com.sap.utilities;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.sap.config.GeneralTestConfig;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.sap.config.ExtentReport.*;
import static com.sap.properties.FilePaths.screenshotsActualFolder;
import static com.sap.properties.FilePaths.screenshotsBufferFolder;
import static com.sap.properties.FilePaths.screenshotsExpectedFolder;


public class ImageCompare extends GeneralTestConfig {

    private static String textFile;

    /**
     * Used for image comparison.
     * @param actualImage   example: String actualImage = "Item_Details_Actual";
     * @param expectedImage example: String actualImage = "Item_Details_Expected";
     * @throws IOException
     */
    public static ImageCompare imageCompare(String actualImage, String expectedImage) throws IOException {
        long start = System.currentTimeMillis();
        int q = 0;
        File file1 = new File(screenshotsBufferFolder + textFile + ".txt");

        FileWriter fw = new FileWriter(file1.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        File fileA = new File(screenshotsActualFolder + actualImage + ".png");
        BufferedImage image = ImageIO.read(fileA);
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        int[][] clr = new int[width][height];

        File fileB = new File(screenshotsExpectedFolder + expectedImage + ".png");
        BufferedImage images = ImageIO.read(fileB);
        int widthe = images.getWidth(null);
        int heighte = images.getHeight(null);
        int[][] clre = new int[widthe][heighte];
        int smw = 0;
        int smh = 0;
        int p = 0;

        if (width > widthe) {
            smw = widthe;
        } else {
            smw = width;
        }
        if (height > heighte) {
            smh = heighte;
        } else {
            smh = height;
        }

        for (int a = 0; a < smw; a++) {
            for (int b = 0; b < smh; b++) {
                clre[a][b] = images.getRGB(a, b);
                clr[a][b] = image.getRGB(a, b);
                if (clr[a][b] == clre[a][b]) {
                    p = p + 1;
                    bw.write("\t");
                    bw.write(Integer.toString(a));
                    bw.write("\t");
                    bw.write(Integer.toString(b));
                    bw.write("\n");
                } else
                    q = q + 1;
            }
        }

        float w, h = 0;
        if (width > widthe) {
            w = width;
        } else {
            w = widthe;
        }
        if (height > heighte) {
            h = height;
        } else {
            h = heighte;
        }
        float s = (smw * smh);
        float x = (100 * p) / s;

        long stop = System.currentTimeMillis();

        if (x >= 95) {
            test.pass("<pre><b>Image comparison successfully completed</b>"
                            + "<br>"
                            + "Image comparison success rate    = " + x + "%"
                            + "<br>"
                            + "Number of  pixels gets varied    = " + q
                            + "<br>"
                            + "Time(ms) for visualization check = " + (stop - start)
                            + "<br>"
                            + "Number of pixels gets matched    = " + p
                            + "<br>"
                            + "ACTUAL SCREENSHOT"
                            + "<br>",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotsActualFolder + actualImage + ".png").build());
        }
        if (x <= 94 && x >= 90) {
            test.warning("<pre><b>Results from comparison needs to be checked</b>"
                            + "<br><br>"
                            + "Image comparison success rate    = " + x + "%"
                            + "<br>"
                            + "Number of  pixels gets varied    = " + q
                            + "<br>"
                            + "Time(ms) for visualization check = " + (stop - start)
                            + "<br>"
                            + "Number of pixels gets matched    = " + p
                            + "<br>"
                            + "ACTUAL SCREENSHOT"
                            + "<br>",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotsActualFolder + actualImage + ".png").build());
        }
        if (x < 90) {
            test.fail("<pre><b>Compare actual screenshot with screenshot from the data base has failed</b>"
                            + "<br><br>"
                            + "Image comparison success rate  = " + x + "%"
                            + "<br>"
                            + "Number of  pixels gets varied  = " + q
                            + "<br>"
                            + "Time(ms) for visualization     = " + (stop - start)
                            + "<br>"
                            + "Number of pixels gets matched  = " + p
                            + "<br>"
                            + "ACTUAL SCREENSHOT"
                            + "<br>",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotsActualFolder + actualImage + ".png").build());
        }
        return null;
    }
}