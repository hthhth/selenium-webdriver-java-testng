package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_05_Element_Method_PartI {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
    }

    @Test
    public void TC_01_WebElement_Command() {
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='email']"));
//        searchBox.sendKeys("LCD");
        searchBox.submit();
    }

//    @AfterClass
//    public void afterClass() {
//        driver.quit();
//    }
}