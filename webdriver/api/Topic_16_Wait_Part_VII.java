package api;

import org.testng.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_16_Wait_Part_VII {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectLocation = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();


    }

    public void TC_01_Found_Element() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        explicitWait = new WebDriverWait(driver, 15);
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        getDateTimeNow();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
        getDateTimeNow();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());
        getDateTimeNow();
        Thread.sleep(2000);
        getDateTimeNow();
    }

    public void TC_02_Not_Found_Element_Only_Implicit(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        getDateTimeNow();
        try {
            Assert.assertTrue(driver.findElement(By.xpath("//input[@id='emailAddress']")).isDisplayed());
        } catch (Exception e) {
//            e.printStackTrace();
        }
        getDateTimeNow();
    }
    @Test
    public void TC_03_Not_Found_Element_Implicit_Less_Explicit(){
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        explicitWait = new WebDriverWait(driver, 8);
        long time = System.currentTimeMillis();
        getDateTimeNow();
        try {
            Assert.assertTrue(driver.findElement(By.xpath("//input[@id='emailAddress']")).isDisplayed());
        } catch (Exception e) {
//            e.printStackTrace();
        }
        getDateTimeNow();
        System.out.println(System.currentTimeMillis()-time);

        time = System.currentTimeMillis();
        getDateTimeNow();
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='emailAddress']")));
        } catch (Exception e) {
//            e.printStackTrace();
        }
        getDateTimeNow();
        System.out.println(System.currentTimeMillis()-time);
    }
    public void TC_04_Not_Found_Element_Implicit_Equal_Explicit(){
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        explicitWait = new WebDriverWait(driver, 4);
        getDateTimeNow();
        try {
            Assert.assertTrue(driver.findElement(By.xpath("//input[@id='emailAddress']")).isDisplayed());
        } catch (Exception e) {
//            e.printStackTrace();
        }
        getDateTimeNow();

        getDateTimeNow();
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='emailAddress']")));
        } catch (Exception e) {
//            e.printStackTrace();
        }
        getDateTimeNow();
    }
    public void TC_05_Not_Found_Element_Implicit_Greater_Explicit(){

        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        explicitWait = new WebDriverWait(driver, 4);
        getDateTimeNow();
        try {
            Assert.assertTrue(driver.findElement(By.xpath("//input[@id='emailAddress']")).isDisplayed());
        } catch (Exception e) {
//            e.printStackTrace();
        }
        getDateTimeNow();

        getDateTimeNow();
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='emailAddress']")));
        } catch (Exception e) {
//            e.printStackTrace();
        }
        getDateTimeNow();
    }

    public void TC_06_Not_Found_Element_Only_Explicit_Param_By(){

        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        explicitWait = new WebDriverWait(driver, 4);
        long time = System.currentTimeMillis();
        getDateTimeNow();
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='emailAddress']")));
        } catch (Exception e) {
//            e.printStackTrace();
        }
        getDateTimeNow();
        System.out.println(System.currentTimeMillis()-time);
    }

    public void TC_07_Not_Found_Element_Only_Explicit_Param_Element(){

        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        explicitWait = new WebDriverWait(driver, 4);
        getDateTimeNow();
        try {
            explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='emailAddress']"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        getDateTimeNow();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void getDateTimeNow(){
        Date date = new Date();
        System.out.println("Time: " + date.toString());
    }
}