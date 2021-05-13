package api;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;

public class RobotDragDrop {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void drag_the_and_drop() throws Exception {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        String sourceLocator = "//div[@id='column-a']";
        String targetLocator = "//div[@id='column-b']";
        drag_the_and_drop_html5_by_xpath(sourceLocator, targetLocator);
    }
    public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws Exception {
        Point sourceLocation = driver.findElement(By.xpath(sourceLocator)).getLocation();
        Point targetLocation = driver.findElement(By.xpath(targetLocator)).getLocation();

        Robot robot = new Robot();
        robot.setAutoDelay(10);

        robot.mouseMove(sourceLocation.x, sourceLocation.y+ 200);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        for (int i=0; i<=100; i++){
            int move = sourceLocation.x + (targetLocation.x - sourceLocation.x) * i/100;
            robot.mouseMove(move, targetLocation.y + 200);
        }
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(3000);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
