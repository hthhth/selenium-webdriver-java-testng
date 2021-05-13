package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Browser_Method_Part_I {
    WebDriver driver;
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01(){
        driver.get("https://live.demoguru99.com");
//        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
//        Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/mobile.html");
//        System.out.println(driver.getCurrentUrl());
//        driver.getTitle();
//        driver.getPageSource();
//        driver.getWindowHandle();
//        driver.getWindowHandles();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().window().fullscreen();
        driver.manage().window().getSize();
        driver.navigate().refresh();
        driver.switchTo().alert();
        driver.switchTo().frame(1);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
