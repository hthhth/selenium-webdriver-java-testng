package api;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Wait_Part_V {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, 15);
    }

    @Test
    public void TC_01_Clickable_Invisible() {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='start']/button")));
        driver.findElement(By.xpath("//div[@id='start']/button")).click();

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
    }
    @Test
    public void TC_01_Clickable_Visible() {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='start']/button")));
        driver.findElement(By.xpath("//div[@id='start']/button")).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}