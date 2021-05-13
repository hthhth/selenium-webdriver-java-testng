package api;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_16_Wait_Part_II {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
    }
//    @Test
    public void TC_01_FindElement(){
        // Trả về 1 element (WebElement)

        // 0 matching node
//        if (driver.findElement(By.xpath("//input[@id='FirstName']")).isDisplayed()){
//            System.out.println("Go to if");
//        } else {
//            System.out.println("Go to else");
//        }

        // 1 matching node
//        boolean status = driver.findElement(By.xpath("//input[@id='Email']")).isDisplayed();
//        System.out.println(status);
//        if (status){
//            System.out.println("Go to if");
//        } else {
//            System.out.println("Go to else");
//        }

        // n matching node --> Lấy element đầu tiên để thao tác
//        driver.findElement(By.xpath("//a[@title='My Account']")).click();
    }

    @Test
    public void TC_02_FindElements(){
        // Trả về >= 1 element (List<WebElement>)

        // 0 matching node: trả về list empty (không chứa element nào hết)
//        List<WebElement> radioButton = driver.findElements(By.xpath("//input[@input='radio']"));
//        System.out.println("Size = " + radioButton.size());
//        Assert.assertTrue(radioButton.isEmpty());

        // 1 matching node
//        List<WebElement> emailTextbox = driver.findElements(By.xpath("//input[@id='Email']"));
//        System.out.println("Size = " + emailTextbox.size());
//        Assert.assertFalse(emailTextbox.isEmpty());
//        emailTextbox.get(0).sendKeys("a@a.com");

        // n matching node
        List<WebElement> textboxes = driver.findElements(By.xpath("//input[@type='text']"));
        System.out.println("Size = " + textboxes.size());
        for (WebElement textbox : textboxes) {
            textbox.sendKeys("List WebElement");
        }
    }

//    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}