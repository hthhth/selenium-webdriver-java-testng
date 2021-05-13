package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Xpath_Css_HTML_PartI {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver=new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }

    @Test
    public void Topic_02_Xpath_Css_HTML() throws InterruptedException {
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[id='txtFirstname']")).sendKeys("Automation FC");
        Thread.sleep(3000);

        driver.findElement(By.id("txtPassword")).sendKeys("123456789");
        Thread.sleep(3000);


    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }


}

