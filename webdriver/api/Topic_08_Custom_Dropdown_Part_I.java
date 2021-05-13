package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_08_Custom_Dropdown_Part_I {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();


    }

    public void TC_01_JQuery() throws InterruptedException {
        /* Hành vi của 1 dropdown
        - Click vào dropdown
        - Chờ cho các item được hiển thị ra
        - Tìm item cần chọn:
            + Nếu item nằm trong tầm nhìn thấy của user --> click luôn
            + Nếu item không nằm trong tầm nhìn thấy của user (viewport) --> Scroll xuống --> Click
        - Bấm vào
        - Kiểm tra xem chọn đúng chưa */
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
        String dropdownSelectANumberXPath = "//span[@id='number-button']";
        String allItemXpath = "//ul[@id='number-menu']//div";
        String expectedValue = "5";
        selectItemInCustomDropdown(dropdownSelectANumberXPath, allItemXpath, expectedValue);

        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), expectedValue);
    }
    @Test
    public void TC_02_NopCommerce() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/register");
        selectItemInCustomDropdown("//select[@name='DateOfBirthDay']", "//select[@name='DateOfBirthDay']/option","29");
        selectItemInCustomDropdown("//select[@name='DateOfBirthMonth']", "//select[@name='DateOfBirthMonth']/option","August");
        selectItemInCustomDropdown("//select[@name='DateOfBirthYear']", "//select[@name='DateOfBirthYear']/option","1998");
        Thread.sleep(3000);
    }
    public void selectItemInCustomDropdown(String parentXpath, String allItemsXpath, String expectedValue) {
        driver.findElement(By.xpath(parentXpath)).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));

        List<WebElement> allItems = driver.findElements(By.xpath(allItemsXpath));
        for (WebElement item : allItems){
            if (item.getText().equals(expectedValue)){
                item.click();
                break;
            }
        }
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}