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

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_11_Popup {
    WebDriver driver;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
//        driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, 30);
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }


    public void TC_01_Fixed_Popup() {
        driver.get("https://www.zingpoll.com/");
        driver.findElement(By.id("Loginform")).click();
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//form[@id='loginForm']"))));
        Assert.assertTrue(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
        driver.findElement(By.xpath("//button[@class='close' and @onclick='ResetForm()']")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
    }

    public void TC_02_Fixed_Popup() {
        driver.get("https://bni.vn/");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sgpb-popup-dialog-main-div")));
        Assert.assertTrue(driver.findElement(By.id("sgpb-popup-dialog-main-div")).isDisplayed());

        WebElement buttonJoinWithUs = driver.findElement(By.xpath("//input[@value='JOIN WITH US']"));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true)", buttonJoinWithUs);
        explicitWait.until(ExpectedConditions.elementToBeClickable(buttonJoinWithUs));
        buttonJoinWithUs.click();

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='sgpb-popup-close-button-1']")));
        driver.findElement(By.xpath("//img[@class='sgpb-popup-close-button-1']")).click();

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("sgpb-popup-dialog-main-div")));
        Assert.assertFalse(driver.findElement(By.id("sgpb-popup-dialog-main-div")).isDisplayed());
    }

    public void TC_03_Popup_Random_In_DOM() throws InterruptedException {
        driver.get("https://blog.testproject.io/");
        Thread.sleep(8000);
        if (driver.findElement(By.xpath("//div[@class='mailch-wrap']")).isDisplayed()) {
            explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("close-mailch"))));
            driver.findElement(By.id("close-mailch")).click();
            explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='mailch-wrap']")));
        }
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='search-2']//input[@class='search-field']")));
        driver.findElement(By.xpath("//section[@id='search-2']//input[@class='search-field']")).sendKeys("Selenium");
        driver.findElement(By.xpath("//section[@id='search-2']//input[@class='search-field']")).sendKeys(Keys.ENTER);
        List<WebElement> titles = driver.findElements(By.xpath("//h3[@class='post-title']/a"));
        for (WebElement title : titles){
            Assert.assertTrue(title.getText().contains("Selenium"));
        }
    }
    @Test
    public void TC_04_Popup_Random_Not_In_DOM() throws InterruptedException {
        driver.get("https://oldnavy.gap.com/");
//        Thread.sleep(10000);
        List<WebElement> popup = driver.findElements(By.xpath("//div[@data-testid='dynamic-modal-content']"));

        if (popup.size()>0){
            explicitWait.until(ExpectedConditions.visibilityOf(popup.get(0)));
            explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='close email sign up modal']")));
            driver.findElement(By.xpath("//button[@aria-label='close email sign up modal']")).click();
            System.out.println("Popup available");
        } else {
            System.out.println("Popup không tồn tại");
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}