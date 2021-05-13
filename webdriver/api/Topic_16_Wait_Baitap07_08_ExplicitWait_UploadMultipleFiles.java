package api;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_16_Wait_Baitap07_08_ExplicitWait_UploadMultipleFiles {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    String systemSeparator = File.separator;

    String fileName1 = "11.png";
    String fileName2 = "pin.png";
    String fileName3 = "background2.jpg";

    String filePath1 =  projectPath + systemSeparator + "uploadFiles" + systemSeparator + fileName1;
    String filePath2 =  projectPath + systemSeparator + "uploadFiles" + systemSeparator + fileName2;
    String filePath3 =  projectPath + systemSeparator + "uploadFiles" + systemSeparator + fileName3;
    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", "./browserDrivers/geckodriver.exe");
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, 60);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void TC07(){
        driver.get("https://gofile.io/uploadFiles");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(filePath1 + "\n" + filePath2 + "\n" + filePath3);
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + fileName1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + fileName2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + fileName3 + "']")).isDisplayed());

        driver.findElement(By.xpath("//button[text()='Upload']")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Download link']")));
    }

    public void sleepInSeconds(int duration){
        try {
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}