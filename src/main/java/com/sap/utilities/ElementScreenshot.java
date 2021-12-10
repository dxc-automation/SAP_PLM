package com.sap.utilities;

import com.sap.config.GeneralTestConfig;
import com.sap.properties.FilePaths;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;


public class ElementScreenshot extends GeneralTestConfig {

    public static void elementScreenshot(By locator, String image) throws Exception {
        WebElement element = driver.findElement(locator);
        String textFile  = element.toString();
        File file = element.getScreenshotAs(OutputType.FILE);
        File dest = new File(FilePaths.screenshotsActualFolder + image + ".png");
        FileUtils.copyFile(file, dest);

        try {
            Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.scaling(1)).takeScreenshot(driver, element);
            ImageIO.write(screenshot.getImage(), "png", new File(FilePaths.screenshotsActualFolder + image + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}