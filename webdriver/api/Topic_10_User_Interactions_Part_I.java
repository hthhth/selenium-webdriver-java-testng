package api;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_10_User_Interactions_Part_I {
    WebDriver driver;
    Actions action;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
//        System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver.exe");
//        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, 30);
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }
    // Hover
    public void TC_01_Hover_Mouse() {
        driver.get("https://tiki.vn/");
        Assert.assertFalse(driver.findElement(By.xpath("//button[text()='Đăng nhập']")).isDisplayed());
        WebElement shortCutAccount = driver.findElement(By.xpath("//div[@data-view-id='header_user_shortcut']"));
        action.moveToElement(shortCutAccount).perform();
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(By.xpath("//button[text()='Đăng nhập']")).isDisplayed());
    }
    // Hover bắt tooltip
    public void TC_02_Hover_Mouse() {
        driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
        action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
        sleepInSecond(1);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),
                "We ask for your age only for statistical purposes.");
    }
    // Hover left menu

    public void TC_03_Hover_Mouse() {
        driver.get("https://hn.telio.vn/");
        action.moveToElement(driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//span[text()='Đồ uống']"))).perform();
        action.click(driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//a[text()='Bia']"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//h1[@id='page-title-heading']/span[text()='Bia']")).isDisplayed());
//        Assert.assertEquals(driver.findElement(By.xpath("//h1[@id='page-title-heading']/span[text()='Bia']")).getText(), "BIA");
    }

    public void TC_04_Click_And_Hold() {
        driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
        List<WebElement> allNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
        action.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(10)).release().perform();

        List<WebElement> allSeletedNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
        List<String> allSelectedNumbersToString = new ArrayList<String>();
        for (WebElement number : allSeletedNumbers) {
            allSelectedNumbersToString.add(number.getText());
        }
        Assert.assertTrue(allSelectedNumbersToString.contains("1"));
        Assert.assertTrue(allSelectedNumbersToString.contains("2"));
        Assert.assertTrue(allSelectedNumbersToString.contains("3"));
        Assert.assertTrue(allSelectedNumbersToString.contains("5"));
        Assert.assertTrue(allSelectedNumbersToString.contains("6"));
        Assert.assertTrue(allSelectedNumbersToString.contains("7"));
        Assert.assertTrue(allSelectedNumbersToString.contains("9"));
        Assert.assertTrue(allSelectedNumbersToString.contains("10"));
        Assert.assertTrue(allSelectedNumbersToString.contains("11"));

    }


    public void TC_05_Click_And_Hold_Random() {
        driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
        List<WebElement> allNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
        action.keyDown(Keys.CONTROL).perform();
        // Nhan 1, 3, 6, 12
        action.click(allNumbers.get(0)).click(allNumbers.get(2)).click(allNumbers.get(5)).click(allNumbers.get(11)).perform();
        action.keyUp(Keys.CONTROL).perform();


        List<WebElement> allSeletedNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
        List<String> allSelectedNumbersToString = new ArrayList<String>();
        for (WebElement number : allSeletedNumbers) {
            allSelectedNumbersToString.add(number.getText());
        }
        Assert.assertTrue(allSelectedNumbersToString.contains("1"));
        Assert.assertTrue(allSelectedNumbersToString.contains("3"));
        Assert.assertTrue(allSelectedNumbersToString.contains("6"));
        Assert.assertTrue(allSelectedNumbersToString.contains("12"));

    }


    public void TC_06_Double_Click(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
    }

    @Test
    public void TC_02_Baitap02(){
        driver.get("https://www.myntra.com/");
        sleepInSecond(2);
        action.moveToElement(driver.findElement(By.xpath("//a[text()='Kids']"))).perform();
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text()='Home & Bath' and @class='desktop-categoryName']"))));
        action.click(driver.findElement(By.xpath("//a[text()='Home & Bath' and @class='desktop-categoryName']"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).isDisplayed());
    }

    public void sleepInSecond(int time){
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}