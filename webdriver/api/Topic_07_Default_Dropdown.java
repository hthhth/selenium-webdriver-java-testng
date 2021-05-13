package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.jws.WebService;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_07_Default_Dropdown {
    WebDriver driver;
    String firstName, lastName, email, companyName, password;
    String date, month, year;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        firstName = "John";
        lastName = "Smith";

        companyName = "Automation FC";
        password = "123456";

        date = "30";
        month = "September";
        year = "1998";

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");

    }

    @Test(invocationCount = 2)
    public void TC_01_Register() {
        email = "johnsmith" + getRandomNumber() + "@gmail.com";
        System.out.println(email);
        /* 1 - Mở ra trang register */
        driver.findElement(By.cssSelector(".ico-register")).click();
        /* 2 - Điền thông tin vào các field requried */
        checkToCheckboxOrRadio(By.cssSelector("#gender-male"));
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);

        Select select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));

        // Chọn visible text 30
        select.selectByVisibleText(date);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), date);

        select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        select.selectByVisibleText(month);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

        select = new Select(driver.findElement(By.name("DateOfBirthYear")));
        select.selectByVisibleText(year);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Company")).sendKeys(companyName);
        checkToCheckboxOrRadio(By.id("Newsletter"));
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

        /* 3 - Đăng kí */
        driver.findElement(By.id("register-button")).click();
        /* 4 - Kiểm tra xuất hiện message đăng ký thành công */
        Assert.assertEquals(driver.findElement(By.cssSelector(".result")).getText(), "Your registration completed");
        /* 5 - Vào trang My Account */
        driver.findElement(By.cssSelector(".ico-account")).click();
        /* 6 - Kiểm tra đúng với thông tin đã đăng ký */
        Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"),lastName);
        System.out.println(email);
        Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
        Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
        Assert.assertTrue(driver.findElement(By.id("Newsletter")).isSelected());

        select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),date);

        select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),month);

        select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),year);


        driver.findElement(By.cssSelector(".ico-logout")).click();
    }


    public void checkToCheckboxOrRadio(By by){
        WebElement element = driver.findElement(by);
        if (!element.isSelected()){
            element.click();
        }
    }

    public int getRandomNumber(){
        Random rand = new Random();
        return rand.nextInt(99999);
    }

    public void sleepInSecond(int second){
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}