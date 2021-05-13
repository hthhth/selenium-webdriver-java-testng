package api;

import com.google.common.base.Function;
import org.testng.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_16_Wait_Part_VIII {
    WebDriver driver;
    FluentWait<WebDriver> fluentDriver;
    FluentWait<WebElement> fluentElement;
    String projectLocation = System.getProperty("user.dir");
    WebElement element;
    long timeoutInSecond = 15;
    long intervalInMilis = 300;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }
    public void TC_01() {
        driver.get("https://automationfc.github.io/fluent-wait/");
        WebElement countdownTime = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));

        fluentElement = new FluentWait<WebElement>(countdownTime);
        //Selenium 3 fluentElement.withTimeout(Duration.ofSeconds(15))
        fluentElement.withTimeout(15, TimeUnit.SECONDS)
                .pollingEvery(300, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class)
                .until(new Function<WebElement, Boolean>() {
                    @Override
                    public Boolean apply(WebElement webElement) {
                        String text = countdownTime.getText();
                        System.out.println(text);
                        return text.endsWith("00");
                    }
                });
    }


    public void TC_02() {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        waitForElementAndClick(By.xpath("//div[@id='start']/button"));
        Assert.assertTrue(isElementDisplayed(By.xpath("//div[@id='finish']/h4")));
    }


    @Test
    public void TC03_BT06_Fluent(){
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        By dateTimePickerBy = By.xpath("//div[@id='ctl00_ContentPlaceholder1_RadCalendar1']");
        Assert.assertTrue(isElementDisplayed(dateTimePickerBy));
        By defaultTextBy = By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and text()='No Selected Dates to display.']");
        Assert.assertTrue(isElementDisplayed(defaultTextBy));
        By selectDateBy = By.xpath("//a[text()='17']");
        waitForElementAndClick(selectDateBy);
        By ajaxLoadingIcon = By.xpath("//div[@id='ctl00_ContentPlaceholder1_RadAjaxLoadingPanel1ctl00_ContentPlaceholder1_RadCalendar1']/div[@class='raDiv']");
        Assert.assertTrue(isElementNotVisible(ajaxLoadingIcon));
        By selectedDateBy = By.xpath("//td[@class='rcSelected']/a[text()='17']");
        Assert.assertTrue(isElementDisplayed(selectedDateBy));
        By selectedTextBy = By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and text()='Wednesday, March 17, 2021']");
        Assert.assertTrue(isElementDisplayed(selectedTextBy));

    }
    public WebElement getElement(By locator){
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(timeoutInSecond, TimeUnit.SECONDS)
                .pollingEvery(intervalInMilis, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        element = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
        return element;
    }
//    public Boolean getElement(By locator){
//        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//                .withTimeout(timeoutInSecond, TimeUnit.SECONDS)
//                .pollingEvery(intervalInMilis, TimeUnit.MILLISECONDS)
//                .ignoring(NoSuchElementException.class);
//        element = wait.until(new Function<WebDriver, Boolean>() {
//            @Override
//            public Boolean apply(WebDriver driver) {
//                return driver.findElement(locator).isDisplayed();
//            }
//        });
//    }
    public void waitForElementAndClick(By locator){
        element = getElement(locator);
        element.click();
    }

    public boolean isElementDisplayed(By locator){
//        element = getElement(locator);
//        FluentWait<WebElement> wait = new FluentWait<WebElement>(element)
//                .withTimeout(timeoutInSecond, TimeUnit.SECONDS)
//                .pollingEvery(intervalInMilis, TimeUnit.MILLISECONDS)
//                .ignoring(NoSuchElementException.class);
//
//        boolean isDisplayed = wait.until(new Function<WebElement, Boolean>() {
//            @Override
//            public Boolean apply(WebElement webElement) {
//                boolean flag = webElement.isDisplayed();
//                return flag;
//            }
//        });
//        return isDisplayed;

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(timeoutInSecond, TimeUnit.SECONDS)
                .pollingEvery(intervalInMilis, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);

        boolean isDisplayed = wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                boolean flag = driver.findElement(locator).isDisplayed();
                return flag;
            }
        });
        return isDisplayed;
    }
    public boolean isElementNotVisible(By locator){
//        element = getElement(locator);
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(timeoutInSecond, TimeUnit.SECONDS)
                .pollingEvery(intervalInMilis, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        boolean isNotVisible = wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                List<WebElement> elementList = new ArrayList<WebElement>();
                elementList = driver.findElements(locator);
                boolean flag = !(elementList.size() == 0);
                return flag;
            }
        });
        return isNotVisible;
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}