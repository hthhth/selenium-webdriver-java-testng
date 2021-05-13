package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class hth_test {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://demo.guru99.com/v4/");

    }

    @Test
    public void TC_01_ValidateCurrentUrl() {
        WebElement buttonLogin = driver.findElement(By.name("btnLogin"));
        driver.navigate().refresh();
        buttonLogin = driver.findElement(By.name("btnLogin"));
        explicitWait.until(ExpectedConditions.stalenessOf(buttonLogin));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}