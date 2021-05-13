package api;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_10_User_Interactions_Part_II {
    WebDriver driver;
    Actions action;
    WebDriverWait explicitWait;
    Alert alert;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
//        driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        explicitWait = new WebDriverWait(driver, 30);
        action = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void TC_07_Right_Click() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
        sleepInSecond(2);
        action.moveToElement(driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit']"))).perform();
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(
                By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit context-menu-hover context-menu-visible']")).isDisplayed());
        action.click(driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit context-menu-hover context-menu-visible']"))).perform();
        sleepInSecond(2);
        alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "clicked: quit");
        sleepInSecond(2);
        alert.accept();

    }


    public void TC_08_Drag_And_Drop_HTML4() {
        driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='droptarget']"))));
        jsExecutor.executeScript("window.scrollBy(0, 250)");
        WebElement sourceCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement targetCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
        action.dragAndDrop(sourceCircle, targetCircle).perform();
        sleepInSecond(5);
        Assert.assertEquals(targetCircle.getText(), "You did great!");
    }


    public void TC_09_Drag_And_Drop_HTML5_JQuery_Css() throws IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        String sourceSquareCss = "#column-a";
        String targetSquareCss = "#column-b";
        String jsHelperContent = readFile("./dragAndDrop/drag_and_drop_helper.js");
        //Drag A to B
        jsHelperContent = jsHelperContent + "$(\"" + sourceSquareCss + "\").simulateDragDrop({ dropTarget: \"" + targetSquareCss + "\"});";
        jsExecutor.executeScript(jsHelperContent);
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
        //Drag B to A
        jsExecutor.executeScript(jsHelperContent);
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
    }

    @Test
    public void TC_09_Drag_And_Drop_HTML5_Position() throws Exception {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        drag_the_and_drop_html5_by_xpath_tulam("//div[@id='column-a']", "//div[@id='column-b']");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
    }
    public void sleepInSecond(int time){
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String readFile(String file) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(file);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }
    public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.xpath(sourceLocator));
        WebElement target = driver.findElement(By.xpath(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();
        System.out.println(sourceLocation.toString());
        System.out.println(targetLocation.toString());

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        System.out.println(sourceLocation.toString());
        System.out.println(targetLocation.toString());

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public void drag_the_and_drop_html5_by_xpath_tulam(String sourceLocator, String targetLocator) throws Exception {
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