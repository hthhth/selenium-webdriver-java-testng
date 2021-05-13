package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Xpath_Css_HTML_PartII {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver=new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
//        driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
    }

    @Test
    public void TC_01_ID() throws InterruptedException {
        driver.findElement(By.id("email")).sendKeys("automationtest@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        Thread.sleep(3000);

    }
    @Test
    public void TC_02_Class() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(3000);
        driver.findElement(By.className("validate-password")).sendKeys("123456");
        driver.findElement(By.className("account-login")).isDisplayed();

    }
    @Test
    public void TC_03_Name() throws InterruptedException {
        driver.findElement(By.name("login[username]")).sendKeys("14141441");
        Thread.sleep(3000);
    }
    @Test
    public void TC_04_Tagname(){
        System.out.println(driver.findElements(By.tagName("a")).size());

    }
    @Test
    public void TC_05_LinkText(){
        driver.findElement(By.linkText("Forgot Your Password?")).click();

    }
    @Test
    public void TC_06_Partial_LinkText(){
        driver.get("https://login.ubuntu.com/");
        driver.findElement(By.partialLinkText("More ›")).click();

    }
    @Test
    public void TC_07_Css_Selector() throws InterruptedException {
        driver.get("https://login.ubuntu.com/");
        driver.findElement(By.cssSelector("input[id='id_email']")).sendKeys("141414");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[id='id_email']")).clear();

        driver.findElement(By.cssSelector("input[placeholder='Your email address']")).sendKeys("555555");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[placeholder='Your email address']")).clear();

    }
    @Test
    public void TC_08_xpath(){
        driver.get("https://login.ubuntu.com/");
        driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).sendKeys("test@gmail.com");
        sleepInSeconds(3);
        driver.findElement(By.xpath("//label[@for='id_email']/following-sibling::input")).clear();

        driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).sendKeys("test@gmail.com");
        sleepInSeconds(3);
        driver.findElement(By.xpath("//label[@for='id_email']/following-sibling::input")).clear();

    }

    public void sleepInSeconds(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }


}

