package api;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_03_Run_On_Browser {
    WebDriver driver;
    String projectLocation = System.getProperty("user.dir");
    @Test
    public void TC_01_Run_On_Firefox(){
        driver = new FirefoxDriver();
        driver.get("https://google.com");
        driver.quit();
    }
    @Test
    public void TC_02_Run_On_Chrome(){
        System.setProperty("webdriver.chrome.driver", projectLocation + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://google.com");
        driver.quit();
    }
}
