package api;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_15_Upload_File {
    WebDriver driver;
    String fileSeparator = File.separator;
    String projectLocation = System.getProperty("user.dir");
    String uploadFileName1 = "11.png";
    String filePath1 = projectLocation + fileSeparator + "uploadFiles" + fileSeparator + uploadFileName1;

    String uploadFileName2 = "background.jpg";
    String filePath2 = projectLocation + fileSeparator + "uploadFiles" + fileSeparator + uploadFileName2;

    String chromeAutoIT = projectLocation + fileSeparator + "autoIT" + fileSeparator + "chromeUploadOneTime.exe";
    String firefoxAutoIT = projectLocation + fileSeparator + "autoIT" + fileSeparator + "firefoxUploadOneTime.exe";

    String chromeMultipleAutoIT = projectLocation + fileSeparator + "autoIT" + fileSeparator + "chromeUploadMultiple.exe";
    String firefoxMultipleAutoIT = projectLocation + fileSeparator + "autoIT" + fileSeparator + "firefoxUploadMultiple.exe";

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void TC_01_Sendkey_One_File_Per_Time() throws InterruptedException {
    //work with element có thẻ input[@type='file']
//        driver.get("https://gofile.io/uploadFiles");
        driver.get("http://blueimp.github.io/jQuery-File-Upload/");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(filePath1);
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(filePath2);
        List<WebElement> startUploadButtons = driver.findElements(By.cssSelector("table .start"));
        for (WebElement button:startUploadButtons) {
            button.click();
        }
//        driver.findElement(By.xpath("//span[text()='Start']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + uploadFileName1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + uploadFileName2 + "']")).isDisplayed());
    }


    public void TC_02_Sendkey_Multiple_File_Per_Time() throws InterruptedException {
        //work with element có thẻ input[@type='file']
//        driver.get("https://gofile.io/uploadFiles");
        driver.get("http://blueimp.github.io/jQuery-File-Upload/");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(filePath1 + "\n" + filePath2);
        List<WebElement> startUploadButtons = driver.findElements(By.cssSelector("table .start"));
        for (WebElement button:startUploadButtons) {
            button.click();
        }
//        driver.findElement(By.xpath("//span[text()='Start']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + uploadFileName1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + uploadFileName2 + "']")).isDisplayed());

        //Verify image not broken
        Assert.assertTrue(isImageLoadedSuccess("//a[@title='" + uploadFileName1 + "']/img"));
        Assert.assertTrue(isImageLoadedSuccess("//a[@title='" + uploadFileName2 + "']/img"));
    }

    public void TC_03_AutoIT_One_File_Per_Time() throws Exception {
        driver.get("http://blueimp.github.io/jQuery-File-Upload/");
        driver.findElement(By.cssSelector(".fileinput-button")).click();
        if (driver.toString().contains("chrome")) {
            Runtime.getRuntime().exec(new String[]{chromeAutoIT, filePath1});
        } else if (driver.toString().contains("firefox")) {
            Runtime.getRuntime().exec(new String[]{firefoxAutoIT, filePath1});
        } else {
            throw new Exception("Please choose your browser!");
        }
    }

    public void TC_04_AutoIT_Multiple_File_Per_Time() throws Exception {
        driver.get("http://blueimp.github.io/jQuery-File-Upload/");
        driver.findElement(By.cssSelector(".fileinput-button")).click();
        if (driver.toString().contains("chrome") || driver.toString().contains("edge") || driver.toString().contains("opera")) {
            Runtime.getRuntime().exec(new String[]{chromeMultipleAutoIT, filePath1, filePath2});
        } else if (driver.toString().contains("firefox")) {
            Runtime.getRuntime().exec(new String[]{firefoxMultipleAutoIT, filePath1, filePath2});
        } else {
            throw new Exception("Please choose your browser!");
        }
    }

    public void TC_05_Java_Robot() throws AWTException, InterruptedException {
        driver.get("http://blueimp.github.io/jQuery-File-Upload/");
        driver.findElement(By.cssSelector(".fileinput-button")).click();

        StringSelection select = new StringSelection(filePath1);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
        Thread.sleep(1000);

        Robot robot = new Robot();
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }

    @Test
    public void TC_06_Sikuli() throws FindFailed {
        driver.get("http://blueimp.github.io/jQuery-File-Upload/");
        driver.findElement(By.cssSelector(".fileinput-button")).click();
        Pattern fileNameInput = new Pattern(projectLocation + fileSeparator + "sikuliImages" + fileSeparator + "Input textbox.png");
        Pattern buttonOpen = new Pattern(projectLocation + fileSeparator + "sikuliImages" + fileSeparator + "Open button.png");

        Screen screen = new Screen();
        screen.type(fileNameInput, filePath1);
        screen.click(buttonOpen);

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + uploadFileName1 + "']")).isDisplayed());
    }

    public boolean isImageLoadedSuccess(String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath(locator));
        return (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", element);
    }


//    @AfterClass
//    public void afterClass() {
//        driver.quit();
//    }
}