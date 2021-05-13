package api;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FluentWaitSelf {
    WebDriver driver;
    WebDriverWait explicitWait;
    String workingDir;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        workingDir = System.getProperty("user.dir");
        driver.get(workingDir + "/HTMLTestPage/fluentWaitCommandDemoPage.html");
    }
    @Test
    public void TC_01_WaitForButtonTitleColor_Return_True_False() {
        FluentWait wait = new FluentWait(driver)
        .pollingEvery(250, TimeUnit.MILLISECONDS)
        .withTimeout(2, TimeUnit.SECONDS);

        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver arg0) {
                if (arg0.findElement(By.id("dynamicColor")).getCssValue("color").equals("rgba(255, 255, 0, 1)")) {
                    return true;
                }
                return false;
            }
        };
        wait.until(function);
    }
    public void TC_02_Wait_For_Dynamic_Text_Return_Element() {
       FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
        wait.withTimeout(2, TimeUnit.MINUTES);
        wait.ignoring(NoSuchElementException.class); // We need to ignore this exception.

        Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver arg0) {
                System.out.println("Checking for the object!!");
                WebElement element = arg0.findElement(By.xpath("//p[@id='dynamicText']/a"));
                if (element != null) {
                    System.out.println("A new dynamic object is found." + element.getText());
                }
                return element;
            }
        };

        wait.until(function);
    }

    public void TC_03_Predicate_Return_True_False() {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
        wait.withTimeout(2, TimeUnit.MINUTES);
        wait.ignoring(NoSuchElementException.class); // We need to ignore this exception.

        Predicate<WebDriver> predicate = new Predicate<WebDriver>() {

            public boolean apply(WebDriver arg0) {
                WebElement element = arg0.findElement(By.id("dynamicColor"));
                String color = element.getCssValue("color");
//                System.out.println("The button text has color :" + color);
                if (color.equals("rgba(255, 255, 0, 1)")) {
                    System.out.println("The button text has color :" + color);
                    return true;
                }
                return false;
            }
        };
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}