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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_07_Default_Dropdown_Bai2 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://automationfc.github.io/basic-form/index.html");
    }

    @Test
    public void TC_01_SingleDropdown() {

        Select select = new Select(driver.findElement(By.id("job1")));
        Assert.assertFalse(select.isMultiple());
        select.selectByVisibleText("Mobile Testing");
        sleepInSecond(3);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");

        select.selectByValue("manual");
        sleepInSecond(3);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");

        select.selectByIndex(9);
        sleepInSecond(3);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");

        Assert.assertEquals(select.getOptions().size(), 10);


    }

    @Test
    public void TC_02_MultipleDropdown() {
        Select select = new Select(driver.findElement(By.id("job2")));
        Assert.assertTrue(select.isMultiple());

        ArrayList<String> values = new ArrayList<String>();
        values.add("Automation");
        values.add("Mobile");
        values.add("Desktop");

        for (String value : values) {
            select.selectByVisibleText(value);
            sleepInSecond(2);
        }
        ArrayList<String> selectedStrings = new ArrayList<String>();
        List<WebElement> selectedOptions = select.getAllSelectedOptions();
        for (WebElement option : selectedOptions) {
            selectedStrings.add(option.getText());
        }

        Assert.assertEquals(values, selectedStrings);
        select.deselectAll();
        Assert.assertEquals(select.getAllSelectedOptions().size(), 0);
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