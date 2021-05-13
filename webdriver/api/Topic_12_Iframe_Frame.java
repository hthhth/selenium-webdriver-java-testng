package api;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_12_Iframe_Frame {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
//        driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver","./browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }


    public void TC_01_Iframe() {
        driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title, 'Facebook Social Plugin')]")));
        Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Automation FC']")).getText(), "Automation FC");
        String likesString = driver.findElement(By.xpath("//a[@title='Automation FC']/parent::div/following-sibling::div")).getText();
        int likesNumber = Integer.parseInt(likesString.split(" ")[0].replace(",", ""));

        assertThat(likesNumber, greaterThan(1900));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src, 'docs.google.com')]")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'exportFormTitle')]")).getText(), "KHÓA HỌC SELENIUM AUTOMATION TESTING");
    }
    @Test
    public void TC_02_Iframe() {
        driver.get("https://kyna.vn/");
        WebElement iframe = driver.findElement(By.cssSelector("#cs_chat_iframe"));
        Assert.assertTrue(iframe.isDisplayed());
        iframe.click();
        sleepInSecond(3);
//        driver.switchTo().frame(iframe);
        driver.switchTo().frame("cs_chat_iframe");
        driver.findElement(By.xpath("//input[contains(@class,'input_name')]")).sendKeys("Nguyen Van An");
        driver.findElement(By.xpath("//input[contains(@class,'input_name')]")).sendKeys(Keys.ENTER);


        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("#live-search-bar")).sendKeys("Java");
        driver.findElement(By.cssSelector(".search-button")).click();
        List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class='k-box-card-wrap clearfix']//h4"));
        for (WebElement result : searchResults) {
                Assert.assertTrue(result.getText().contains("Java"));
        }
    }
    public void sleepInSecond(int time){
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}