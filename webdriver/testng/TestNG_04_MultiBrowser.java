package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestNG_04_MultiBrowser {
    WebDriver driver;
    @Parameters("browser")
    @BeforeTest
    public void preCondition(String browserName){
        if (browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")){
            System.setProperty("webdriver.gecko.driver", "./browserDrivers/geckodriver.exe");
            driver = new FirefoxDriver();
        } else  if (browserName.equals("headless")) {
            System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver.exe");
            ChromeOptions options=new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size=1366x768");
            driver = new ChromeDriver(options);
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    @Parameters({"username", "password"})
    @Test(invocationCount = 3)
    public void Login_01_LoginWithValidInformation(String userName, String password){
        driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@id='send2']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());

        driver.findElement(By.xpath("//a/span[text()='Account']")).click();
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
    }

    @AfterTest
    public void postCondition(){
        driver.quit();
    }
}
