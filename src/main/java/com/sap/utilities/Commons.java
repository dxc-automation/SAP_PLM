package com.sap.utilities;

import static com.sap.config.BrowserManager.wait;

import com.jacob.activeX.ActiveXComponent;
import com.sap.config.GeneralTestConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Commons extends GeneralTestConfig {

    private static SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss:SSS");
    private static boolean status;
    public static Map<String, Object> vars = new HashMap<String, Object>();


    //***   Password
    public static String passwordEncryption(String password) throws UnsupportedEncodingException {
        byte[] decodedStr = Base64.getDecoder().decode(password);
        String pwd = new String(decodedStr, "utf-8");
        return pwd;
    }


    //***   Execution Time
    public static void printExecutionTime(long startTime, long endTime) {
        long time_ns = endTime - startTime;
        long time_ms = TimeUnit.NANOSECONDS.toMillis(time_ns);
        long time_sec = TimeUnit.NANOSECONDS.toSeconds(time_ns);
        long time_min = TimeUnit.NANOSECONDS.toMinutes(time_ns);
        long time_hour = TimeUnit.NANOSECONDS.toHours(time_ns);

        System.out.print("\nExecution Time: ");
        if (time_hour > 0)
            System.out.print(time_hour + " Hours, ");
        if (time_min > 0)
            System.out.print(time_min % 60 + " Minutes, ");
        if (time_sec > 0)
            System.out.print(time_sec % 60 + " Seconds, ");
        if (time_ms > 0)
            System.out.print(time_ms % 1E+3 + " MicroSeconds, ");
        if (time_ns > 0)
            System.out.print(time_ns % 1E+6 + " NanoSeconds\n");
    }


    //***   Kill process
    public void killProcess() {
        try {
            String command = "cmd.exe npx kill-port 9999";
            log(command);
            Process process = Runtime.getRuntime().exec(command);
            logOutput(process.getInputStream(), "");
            logOutput(process.getErrorStream(), "Error: ");
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void logOutput(InputStream inputStream, String prefix) {
        new Thread(() -> {
            Scanner scanner = new Scanner(inputStream, "UTF-8");
            while (scanner.hasNextLine()) {
                synchronized (this) {
                    log(prefix + scanner.nextLine());
                }
            }
            scanner.close();
        }).start();
    }

    private synchronized void log(String message) {
        System.out.println(format.format(new Date()) + ": " + message);
    }


    //***   Print number of iframes
    public void iframesPrint() {
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("Total number of iframes on the page are: " + iframes.size());
    }


    //***   Wait for iframe
    public String waitForWindow(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<String> whNow = driver.getWindowHandles();
        Set<String> whThen = (Set<String>) vars.get("window_handles");
        if (whNow.size() > whThen.size()) {
            whNow.removeAll(whThen);
        }
        return whNow.iterator().next();
    }


    //***   Element Highlighting
    public void elementHighlighting(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style,'border: solid 2px red'');", element);
    }


    //***   Mask user password
    public String getMasketPassword(String password) throws IOException {
        String maskedPassword =
                password.replaceAll("[A-Z]", "*")
                        .replaceAll("[a-z]", "*")
                        .replaceAll("[0-9]", "*")
                        .replace("!", "*")
                        .replace("?", "*")
                        .replace("@", "*")
                        .replace("#", "*")
                        .replace("$", "*");

        return maskedPassword;
    }


    public void waitForElementLocatedBy(By locator) {
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    //***   Switch tabs
    public void focusOnWindow(int tabNumber) {
        String mainWindowHandle = driver.getWindowHandle();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabNumber));
    }


    //***   Click on text field
    public void clickOnTextField(String objectLocator) {
        Obj = new ActiveXComponent(Session.invoke("FindById", objectLocator).toDispatch());
        Obj.invoke("setFocus");

        Obj = new ActiveXComponent(Session.invoke("FindById", objectLocator).toDispatch());
        Obj.invoke("caretPosition", "0");
    }


    //***   Wait for specific element to be displayed
    private void waitForControlActive(String title, String text, String control, long maxWaitTime) throws InterruptedException {
        long timeWaited = 0;
        while (true) {
            if (timeWaited >= maxWaitTime) {
                Assert.fail("waited " + timeWaited + " milliseconds but control with id " + control + " is not active");
            } else if (autoItX.controlCommandIsEnabled(title, text, control)) {
                break;
            } else {
                Thread.sleep(2000L);
                timeWaited += 2000L;
            }
        }
    }

    //***   Is checkbox selected
    public boolean isCheckboxSelected(By locator) {
        WebElement checkbox = driver.findElement(locator);
        if (checkbox.isSelected()) {
            return true;
        } else {
            return false;
        }
    }


    //***   Returns timestamp as string
    public String getTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String time = dateFormat.format(timestamp);
        return time;
    }

    //***   Used for check which tests are enabled and disabled
    public static boolean checkTestStatus(String scenarioType, int testCaseNumber) throws Exception {
        String enabledDisabled = testDataReader.getTestStatus(scenarioType, testCaseNumber);

        switch (enabledDisabled) {
            case "FALSE":
                status = false;
                break;

            case "TRUE":
                status = true;
                break;
        }
        return status;
    }




    //***   Returns current date as string
    public String getCurrentDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = date.format(formatter);
        return currentDate;
    }


    //***   Returns current date plus day
    public String getCurrentDatePlusDays(int days) {
        LocalDate date = LocalDate.now().plusDays(days);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String datePlus = date.format(dateFormatter);
        return datePlus;
    }


    //***   Returns current month as string
    public String getMonthNow() {
        Calendar calendar = Calendar.getInstance();
        String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        return month;
    }


    public String getMonthFromDate(String date) throws ParseException {
        String str[] = date.split("/");
        String month = String.valueOf(Integer.parseInt(str[0]));
        String day   = String.valueOf(Integer.parseInt(str[1]));
        String year  = String.valueOf(Integer.parseInt(str[2]));
        return month;
    }


    //***   Returns current time minus hours
    public String getCurrentTimeMinusHours(int hours) {
        LocalTime time = LocalTime.now().minusHours(hours);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(timeFormatter);
        return formattedTime;
    }


    //***   Returns randomly generated string
    public String getRandomGeneratedString() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }


    //***   Perform scroll into element view
    public void scrollIntoView(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView()", element);
    }


    //***   Perform click action
    public void clickAction(By locator) {
        WebElement element = driver.findElement(locator);
        Actions action = new Actions(driver);
        action.moveToElement(element).click().perform();
    }


    //***   Performs scroll and click
    public void scrollClick(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js =(JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0,",element.getLocation().x+")");
        element.click();
    }


    //***   Click on specific web element
    public void clickElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }


    //***   Scroll to specific web element
    public void scrollTo(By locator) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(locator));

    }


    //***   Select dropdown option by index
    public void selectOptionByIndex(By locator, int index) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByIndex(index);
    }


    //***   Send character sequence
    public void addText(By locator, String text) {
        WebElement element = driver.findElement(locator);
        String js = "arguments[0].setAttribute('value','" + text + "')";
        ((JavascriptExecutor) driver).executeScript(js, element);
    }

    public void sendKeysJavascript(By element, String keysToSend) {
        WebElement el = driver.findElement(element);
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].value='"+ keysToSend +"';", el);
    }

    public void driverWait(WebElement element) {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(element));
    }

    //***   Click on specific web element
    public void jsClickElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public static void delay(int time) throws InterruptedException {
        Thread.sleep(time);
    }
}
