package api;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_15_Upload_File_Baitap {
    WebDriver driver;

    String projectPath = System.getProperty("user.dir");
    String systemSeparator = File.separator;
    String fileName1 = "11.png";
    String fileName2 = "pin.png";
    String fileName3 = "background2.jpg";
//    String filePath1 =  projectPath + systemSeparator + "uploadFiles" + systemSeparator + fileName1;
//    String filePath2 =  projectPath + systemSeparator + "uploadFiles" + systemSeparator + fileName2;
    String filePath3 =  projectPath + systemSeparator + "uploadFiles" + systemSeparator + fileName3;

    String filePath1 =  projectPath + "\\uploadFiles\\" + fileName1;
    String filePath2 =  projectPath + "\\uploadFiles\\" + fileName2;

    @BeforeClass
    public void beforeClass() {
//        driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", ".//browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void TC_01_BT1_UploadFileBySendKey() throws InterruptedException {
        driver.get("http://blueimp.github.com/jQuery-File-Upload/");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(filePath1 + "\n" + filePath2 + "\n" + filePath3);
        List<WebElement> buttonStart = driver.findElements(By.xpath("//span[text()='Start']"));
        for (WebElement start:buttonStart) {
            start.click();
        }
        // Verify file uploaded
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName3 + "']")).isDisplayed());
        sleepInSecond(3);

        // Verify image not broken
        Assert.assertTrue(isImageLoaded(By.xpath("//a[@title='" + fileName1 + "']/img")));
        Assert.assertTrue(isImageLoaded(By.xpath("//a[@title='" + fileName2 + "']/img")));
        Assert.assertTrue(isImageLoaded(By.xpath("//a[@title='" + fileName3 + "']/img")));
    }
    public boolean isImageLoaded(By locator){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(locator);
        return (boolean) javascriptExecutor.executeScript("return arguments[0].complete && " +
                "typeof arguments[0].naturalWidth!='undefined' && arguments[0].naturalWidth >0", element);
    }

    @Test
    public void TC_04_BaiTap4_UploadFile() throws InterruptedException {
        driver.get("https://gofile.io/?t=uploadFiles");
        WebDriverWait explicitWait = new WebDriverWait(driver, 30);
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(filePath1 + "\n" + filePath2 + "\n" + filePath3);
        driver.findElement(By.xpath("//button[text()='Upload']")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Your files have been successfully uploaded']")));
        driver.findElement(By.xpath("//td[text()='Download link']/following-sibling::td/a")).click();

        String currentWindowHandle = driver.getWindowHandle();
        switchToWindowByID(currentWindowHandle);

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='dialog']")));
        driver.findElement(By.xpath("//button[text()='I have a VPN already']")).click();
        sleepInSecond(3);
        verifyButtonDisplayed(fileName1, "Download");
        verifyButtonDisplayed(fileName2, "Download");
        verifyButtonDisplayed(fileName3, "Download");

        verifyButtonDisplayed(fileName1, "Play / Info");
        verifyButtonDisplayed(fileName2, "Play / Info");
        verifyButtonDisplayed(fileName3, "Play / Info");

    }
    public void verifyButtonDisplayed(String fileName, String action){
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + fileName + "']/ancestor::tr/td//button[@data-original-title='" + action + "']")).isDisplayed());
    }

    public void sleepInSecond(int duration) throws InterruptedException {
        Thread.sleep(duration*1000);
    }

    public void switchToWindowByID(String parentWindowHandle){
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String windowHandle:allWindowHandles) {
            if (!windowHandle.equals(parentWindowHandle)){
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}