package api;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_08_Custom_Dropdown_Baitap4 {
    WebDriver driver;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        explicitWait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();


    }

    public void TC_01_JQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        // Select "Fast" in dropdown "Select a speed"
        selectItem("//span[@id='speed-button']", "//ul[@id='speed-menu']//div", "Fast");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(), "Fast");

        // Select "Some unknown file" in dropdown "Select a file"
        selectItem("//span[@id='files-button']", "//ul[@id='files-menu']//div", "Some unknown file");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='files-button']/span[@class='ui-selectmenu-text']")).getText(), "Some unknown file");

        // Select "19" in dropdown "Select a number"
        selectItem("//span[@id='number-button']", "//ul[@id='number-menu']//div", "19");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "19");

        // Select "Dr." in dropdown "Select a title"
        selectItem("//span[@id='salutation-button']", "//ul[@id='salutation-menu']//div", "Dr.");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='salutation-button']/span[@class='ui-selectmenu-text']")).getText(), "Dr.");


    }

    public void TC_02_Angular() throws InterruptedException {
        driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
        selectItem("//ejs-dropdownlist[@id='games']", "//div[@class='e-content e-dropdownbase']//li", "Cricket");
        Thread.sleep(2000);
//        Assert.assertEquals(driver.findElement(By.xpath("//select[@name='games']/following-sibling::input")).getAttribute("aria-label"), "Football");
//        WebElement selectedItem = driver.findElement(By.xpath("//select[@name='games']/option"));
//        System.out.println(selectedItem.getAttribute("textContent")); or innerText innerHTML

        System.out.println(getAngularDropdownSelectedText());
        Thread.sleep(2000);
    }

    public void TC_03_ReactJS() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItem("//div[@id='root']", "//span[@class='text']", "Stevie Feliciano");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Stevie Feliciano");
        System.out.println(driver.findElement(By.xpath("//div[@class='divider text']")).getText());
    }

    public void TC_04_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItem("//div[@class='btn-group']", "//ul[@class='dropdown-menu']//a", "Third Option");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='btn-group']/li")).getText(), "Third Option");
    }

    public void TC_05_Editable_TuLam() throws InterruptedException {
        driver.get("http://indrimuska.github.io/jquery-editable-select/");
        selectItemInEditableDropdownThenEnter("//div[@id='default-place']/input", "Smart");
//        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='default-place']/ul/li[contains(@class,'es-visible selected')]")).getText(), "Renault");
//        System.out.println(driver.findElement(By.xpath("//div[@id='default-place']/input")).getAttribute("innerHTML"));
//        System.out.println(driver.findElement(By.xpath("input[@class='form-control es-input']")).getAttribute("textContent"));
        System.out.println(getJQueryDropdownSelectedText());
    }

    public void TC_05_Editable_Sendkey_Select(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemInEditableDropdown("//input[@class='search']", "//div[@role='option']/span", "Belgium");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Belgium");

        selectItemInEditableDropdown("//input[@class='search']", "//div[@role='option']/span", "Benin");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Benin");
    }

    public void TC_05_Editable_Sendkey_Tab() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemInEditableDropdownThenTab("//input[@class='search']", "Belgium");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Belgium");

        selectItemInEditableDropdownThenTab("//input[@class='search']", "Benin");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Benin");
    }

    @Test
    public void TC_08_Multiple_Select() throws InterruptedException {
//        String[] months = {"August", "January", "March", "May", "[Select all]"};
        String[] months = {};
        driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
        selectMultipleItemsInDropdown("(//button[@class='ms-choice'])[1]",
                "(//div[@class='ms-drop bottom'])[1]//span", months);
        Thread.sleep(2000);
        List<WebElement> selectedItems = driver.findElements(By.xpath("//li[@class='selected']//span"));
        String itemList = driver.findElement(By.xpath("(//button[@class='ms-choice'])[1]/span")).getText();

        if (selectedItems.size()<=3 && selectedItems.size()>0){
            for (String month : months){
                if (itemList.contains(month)) {
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            }
        } else if (selectedItems.size()>3 && selectedItems.size()<12) {
            Assert.assertEquals(itemList, selectedItems.size() + " of 12 selected");
        } else if (selectedItems.size() == 13) {
            Assert.assertEquals(itemList,"All selected");
        } else {
            new RuntimeException("hth");
        }
    }

    public void selectMultipleItemsInDropdown(String parentXpath, String allItemsXpath, String[] months){
        driver.findElement(By.xpath(parentXpath)).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));
        List<WebElement> allItems = driver.findElements(By.xpath(allItemsXpath));
        for (String month : months) {
            for (WebElement item : allItems) {
                if (item.getText().equals(month)) {
                    item.click();
                    break;
                }
            }
        }
    }

    public void selectItemInEditableDropdownThenTab(String parentXpath, String expectedValue) throws InterruptedException {
        driver.findElement(By.xpath(parentXpath)).clear();
        driver.findElement(By.xpath(parentXpath)).sendKeys(expectedValue);
        Thread.sleep(2000);
        driver.findElement(By.xpath(parentXpath)).sendKeys(Keys.TAB);

    }

    public void selectItemInEditableDropdownThenEnter(String parentXpath, String expectedValue) throws InterruptedException {
        driver.findElement(By.xpath(parentXpath)).clear();
        driver.findElement(By.xpath(parentXpath)).sendKeys(expectedValue);
        Thread.sleep(2000);
        driver.findElement(By.xpath(parentXpath)).sendKeys(Keys.ENTER);

    }

    public void selectItemInEditableDropdown(String parentXpath, String allItemsXpath, String expectedValue){
        driver.findElement(By.xpath(parentXpath)).clear();
        driver.findElement(By.xpath(parentXpath)).sendKeys(expectedValue);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));
        List<WebElement> allItems = driver.findElements(By.xpath(allItemsXpath));
        for (WebElement item : allItems) {
            if (item.getText().equals(expectedValue)){
                item.click();
                break;
            }
        }
    }

    public void selectItem(String parentXpath, String allItemsXpath, String expectedValue) {
        driver.findElement(By.xpath(parentXpath)).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));
        List<WebElement> allItems = driver.findElements(By.xpath(allItemsXpath));
        for (WebElement item : allItems) {
            if (item.getText().equals(expectedValue)) {
                item.click();
                break;
            }
        }
    }

    public String getAngularDropdownSelectedText(){
        return jsExecutor.executeScript("return document.querySelector(\"select[name='games']>option\").text").toString();
    }

    public String getJQueryDropdownSelectedText(){
        return jsExecutor.executeScript("return document.querySelector(\"div[id='default-place'] li[class='es-visible selected']\").innerText").toString();
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}