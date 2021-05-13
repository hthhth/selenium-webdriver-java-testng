package api;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_09_Button_Radio_Checkbox {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }


    public void TC_01_Button() throws InterruptedException {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
        By buttonLogin = By.cssSelector(".fhs-btn-login");
        Assert.assertFalse(driver.findElement(buttonLogin).isEnabled());

        driver.findElement(By.cssSelector("#login_username")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("#login_password")).sendKeys("automation");
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(buttonLogin).isEnabled());

        driver.navigate().refresh();
        driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
        removeAttributeDisableOfButtonByJS(buttonLogin);
        driver.findElement(buttonLogin).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']//following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']//following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");


    }

    public void TC_02_Defaul_Radio_Checkbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");
//        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
//        for (WebElement checkbox : checkboxes) {
//            checkbox.click();
//            sleepInSecond(1);
//        }
//        for (WebElement checkbox : checkboxes) {
//            Assert.assertTrue(checkbox.isSelected());
//        }
        driver.findElement(By.xpath("//input[@value='Diabetes']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Diabetes']")).isSelected());

        driver.findElement(By.xpath("//label[text()=' Diabetes ']/preceding-sibling::input")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()=' Diabetes ']/preceding-sibling::input")).isSelected());
    }


    public void TC_03_Custom_Radio_Checkbox_I() {
        driver.get("https://material.angular.io/components/radio/examples");
//        driver.findElement(By.xpath("//input[@value='Spring']/preceding-sibling::span[@class='mat-radio-outer-circle']")).click();
//        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Spring']")).isSelected());
//        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Spring']/preceding-sibling::span[@class='mat-radio-outer-circle']")).isSelected());
        clickByJS(By.xpath("//input[@value='Spring']"));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Spring']")).isSelected());
    }

    @Test
    public void TC_04_Custom_Radio_Checkbox_II() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='false']")).isDisplayed());
        driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='false']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='true']")).isDisplayed());

    }
    public void removeAttributeDisableOfButtonByJS(By by){
        WebElement element = driver.findElement(by);
        jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
    }
    public void sleepInSecond(int time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void clickByJS(By by){
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}