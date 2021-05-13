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

public class Topic_16_Wait_Part_I {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void TC_01_Displayed_Visible() {
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
    }
    public void TC_02_Undisplayed_Invisible_In_DOM(){
        // TH1: vẫn có trong DOM: tìm element, apply điều kiện, không cần chờ hết timeout
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']")));
    }
    public void TC_03_Undisplayed_Invisible_Out_DOM(){
        // TH2: không có trong DOM: không tìm thấy element, tìm đi tìm lại cho tới khi hết timeout của implicitWait
        // Hết thời gian, apply điều kiện của explicitWait vào (invisibility)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='edit_account_error']")));
    }
    public void TC_04_Presence(){
        // Wait cho element có trong DOM
        // TH1: element có trong DOM + hiển thị trên UI
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        // TH2: element có trong DOM + không hiển thị trên UI
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='create_account_error']")));
    }
    public void TC_05_Clickable_Element_Enabled(){
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        //Button / Radio / Checkbox / Link / Dropdown --> Stable trước khi thao tác
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitLogin")));

        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.id("email")).sendKeys("automation123@gmail.com");
        driver.findElement(By.id("new_username")).sendKeys("Test");
        driver.findElement(By.id("new_password")).sendKeys("Aa@12345");

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("create-account")));

    }
    @Test
    public void TC_06_Staleness(){
        driver.get("https://shopee.vn/");

        // Step 1 - Thao tacs vs 1 element --> Error msg xuất hiện (*) - hiển thị
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='home_popup_banner']")));
        WebElement popup = driver.findElement(By.xpath("//img[@alt='home_popup_banner']"));

        // Step 2 - Theo tác vs... --> (*) không còn xuất hiên
        driver.findElement(By.cssSelector(".shopee-popup__close-btn")).click();

        // Step 3 - Cần chờ cho (*) không còn trong DOM
        explicitWait.until(ExpectedConditions.stalenessOf(popup));

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}