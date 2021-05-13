package api;

import org.testng.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_16_Wait_Part_VI {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectLocation = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
//        System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver.exe");
//        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, 15);
    }

    public void TC_01_Only_Implicit() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        driver.findElement(By.xpath("//a[text()='16']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and text()='Tuesday, March 16, 2021']")).isDisplayed());
//        Assert.assertTrue(driver.findElement(By.id("ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel")).isDisplayed());
//        Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel")).getText(), "Tuesday, March 16, 2021");
    }

    public void TC_02_Only_Explicit() throws InterruptedException {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='16']")));
        driver.findElement(By.xpath("//a[text()='16']")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='16']/parent::td[@class='rcSelected']")));
//        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='ctl00_ContentPlaceholder1_RadAjaxLoadingPanel1ctl00_ContentPlaceholder1_RadCalendar1']/div[@class='raDiv']")));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']/parent::div[not(@style='display:none;')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and text()='Tuesday, March 16, 2021']")).isDisplayed());
    }


    public void TC_03_Only_Explicit() {
        driver.get("https://www.file.io/");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(projectLocation + "\\uploadFiles\\background.jpg");
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@id='progress-full']")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='download-url']")));
    }

    public void TC_04_Only_Explicit(){
        driver.get("http://demo.guru99.com/v4/");
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "User or Password is not valid");
        alert.accept();
//        driver.switchTo().alert().accept();
    }
    @Test
    public void TC_04_Only_Explicit_UploadMultipleFiles(){
        driver.get("https://filebin.net/");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(projectLocation + "\\uploadFiles\\background.jpg");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(projectLocation + "\\uploadFiles\\background1.jpg");
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='progress'][0]")));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='progress'][1]")));
        List<WebElement> uploadedLinks = driver.findElements(By.xpath("//table[@class='sortable table']//a[contains(@href, 'https://filebin.net/sqantjmkc601kiwg/background')]"));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(uploadedLinks));
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}