package api;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Topic_09_Alert {
    WebDriver driver;
    WebDriverWait explicitWait;
    Alert alert;
    By resultText;
    String username = "admin";
    String password = "admin";
    String firefoxAuthen;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        resultText = By.cssSelector("#result");
        explicitWait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }


    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        explicitWait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        sleepInSecond(2);
        alert.accept();
        Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked an alert successfully");

    }

    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        explicitWait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        sleepInSecond(2);
        alert.dismiss();
        Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");
    }

    public void TC_03_Prompt_Alert() {
        String loginValue = "Automation testing";
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        explicitWait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS prompt");
        alert.sendKeys(loginValue);
        sleepInSecond(1);
        alert.accept();
        sleepInSecond(1);
        Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: " + loginValue);
    }
    public void TC_04_Authentication_Alert() {
        driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());

    }
    public void TC_05_Authentication_Alert() {
        driver.get("http://the-internet.herokuapp.com/");
        String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        driver.get(getAuthenticationUrl(basicAuthenLink, username, password));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

    @Test
    public void TC_06_Authentication_Alert_AutoIT() throws IOException {
        driver.get("http://the-internet.herokuapp.com");
        driver.findElement(By.xpath("//a[text()='Basic Auth']")).click();
        firefoxAuthen = "./autoIT/authen_firefox.exe";
        Runtime.getRuntime().exec(new String[] {firefoxAuthen, username, password});
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }
    public String getAuthenticationUrl(String url, String username, String password){
        String[] urlValues = url.split("//");
        url = urlValues[0] + "//" + username + ":" + password + "@" + urlValues[1];
        return url;
    }

    public void sleepInSecond(int time) {
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