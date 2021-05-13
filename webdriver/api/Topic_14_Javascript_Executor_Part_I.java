package api;

import com.sun.deploy.security.JarAsBLOBVerifier;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_14_Javascript_Executor_Part_I {
    WebDriver driver;
//    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    int timeout = 20;

    @BeforeClass
    public void beforeClass() {
//        driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
//        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }


    public void TC_01_Browser_Element() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location='http://live.demoguru99.com/'");
        Assert.assertEquals(jsExecutor.executeScript("return document.domain"), "live.demoguru99.com");
        Assert.assertEquals(jsExecutor.executeScript("return document.URL"), "http://live.demoguru99.com/");
        WebElement linkMobile = driver.findElement(By.xpath("//a[text()='Mobile']"));

        highlightElement(driver, "//a[text()='Mobile']");
        jsExecutor.executeScript("arguments[0].click();", linkMobile);
        WebElement buttonAddToCart = driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button[@title='Add to Cart']"));

        highlightElement(driver, "//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button[@title='Add to Cart']");
        jsExecutor.executeScript("arguments[0].click();", buttonAddToCart);

        highlightElement(driver, "//li[@class='success-msg']");
        Assert.assertEquals(jsExecutor.executeScript("return document.documentElement.innerText.match('Samsung Galaxy was added to your shopping cart.')[0]"), "Samsung Galaxy was added to your shopping cart.");

        jsExecutor.executeScript("window.scrollBy(0, document.body.scrollHeight)");
        highlightElement(driver, "//a[text()='Customer Service']");
        WebElement linkCustomerService = driver.findElement(By.xpath("//a[text()='Customer Service']"));
        jsExecutor.executeScript("arguments[0].click();", linkCustomerService);
        Assert.assertEquals(jsExecutor.executeScript("return document.title"), "Customer Service");

        WebElement textboxNewsletter = driver.findElement(By.id("newsletter"));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", textboxNewsletter);
        highlightElement(driver, "//input[@id='newsletter']");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1])", textboxNewsletter, generateEmail());

        sleepInSecond(1);
        highlightElement(driver,"//span[text()='Subscribe']");
        WebElement buttonSubscribe = driver.findElement(By.xpath("//span[text()='Subscribe']"));
        jsExecutor.executeScript("arguments[0].click();", buttonSubscribe);

        highlightElement(driver, "//li[@class='success-msg']");
        Assert.assertEquals(jsExecutor.executeScript("return document.documentElement.innerText.match('Thank you for your subscription.')[0]"), "Thank you for your subscription.");

    }


    public void TC_02_HTML5_Validation_Message(){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        driver.get("https://automationfc.github.io/html5/index.html");

        WebElement buttonSubmit = driver.findElement(By.xpath("//input[@name='submit-btn']"));
        WebElement textboxName = driver.findElement(By.xpath("//input[@id='fname']"));
        WebElement textboxPassword = driver.findElement(By.xpath("//input[@id='pass']"));
        WebElement textboxEmail = driver.findElement(By.xpath("//input[@id='em']"));
        WebElement dropdownAddress = driver.findElement(By.xpath("//select"));

        submitWithoutInputData(jsExecutor, buttonSubmit, textboxName, "Please fill out this field.");

        textboxName.sendKeys("test name");

        submitWithoutInputData(jsExecutor, buttonSubmit, textboxPassword, "Please fill out this field.");

        textboxPassword.sendKeys("123456");
        submitWithoutInputData(jsExecutor, buttonSubmit, textboxEmail, "Please fill out this field.");

        String textSendToEmailAddress = "test";
        textboxEmail.sendKeys(textSendToEmailAddress);

        submitWithoutInputData(jsExecutor, buttonSubmit, textboxEmail, "Please include an '@' in the email address. " + "'" + textSendToEmailAddress + "'" + " is missing an '@'.");

        textboxEmail.clear();
        textboxEmail.sendKeys("testtest@gmail.com");
        submitWithoutInputData(jsExecutor, buttonSubmit, dropdownAddress, "Please select an item in the list.");
    }


    public void TC_03_HTML5_Validation_Message(){
        driver.get("https://login.ubuntu.com/");
        WebElement buttonAcceptCookies = driver.findElement(By.xpath("//button[@id='cookie-policy-button-accept']"));
        buttonAcceptCookies.click();
        WebElement buttonDangNhap = driver.findElement(By.xpath("//span[text()='Log in']"));
        WebElement textboxEmail = driver.findElement(By.xpath("//input[@name='email']"));

        buttonDangNhap.click();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Assert.assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage", textboxEmail), "Please fill out this field.");

        String textSendToEmailAddress = "a";
        textboxEmail.sendKeys(textSendToEmailAddress);
        buttonDangNhap.click();
        Assert.assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage", textboxEmail), "Please include an '@' in the email address. " + "'" + textSendToEmailAddress + "'" + " is missing an '@'.");


    }



    @Test
    public void TC_05_Create_An_Account(){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location='http://live.demoguru99.com/'");
        jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
        jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//span[text()='Create an Account']")));
        WebElement textboxFirstName = driver.findElement(By.xpath("//input[@id='firstname']"));
        WebElement textboxLastName = driver.findElement(By.xpath("//input[@id='lastname']"));
        WebElement textboxEmailAddress = driver.findElement(By.xpath("//input[@id='email_address']"));
        WebElement textboxPassword = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement textboxConfirmPassword = driver.findElement(By.xpath("//input[@id='confirmation']"));

        jsExecutor.executeScript("arguments[0].setAttribute('value', 'John')", textboxFirstName);
        jsExecutor.executeScript("arguments[0].setAttribute('value', 'Smith')", textboxLastName);
        jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1])", textboxEmailAddress, generateEmail());
        jsExecutor.executeScript("arguments[0].setAttribute('value', '123456')", textboxPassword);
        jsExecutor.executeScript("arguments[0].setAttribute('value', '123456')", textboxConfirmPassword);

        jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//span[text()='Register']")));
        sleepInSecond(3);
        Assert.assertEquals(jsExecutor.executeScript("return document.documentElement.innerText.match('Thank you for registering with Main Website Store.')[0]"), "Thank you for registering with Main Website Store.");
        jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[text()='Log Out']")));
        WebDriverWait explicitWait = new WebDriverWait(driver, 20);
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site')]"))));
    }

    public void submitWithoutInputData(JavascriptExecutor jsExecutor, WebElement buttonSubmit, WebElement element, String message){
        buttonSubmit.click();
        sleepInSecond(2);
        Assert.assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage", element), message);
    }
    public Object executeForBrowser(WebDriver driver, String javaScript) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    public void highlightElement(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element= driver.findElement(By.xpath(locator));
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed; background-color: yellow;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
//        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//        WebElement element = getElement(driver, locator);
//        String originalStyle = element.getAttribute("style");
//        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
//        sleepInSecond(1);
//        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    private void sleepInSecond(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
    }

    public void scrollToElement(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
    }

    public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, timeout);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locator));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isImageLoadedSuccess(String javaScript, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath(locator));
        return (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", element);
    }

    public WebElement getElement(WebDriver driver, String locator){
        return driver.findElement(By.xpath(locator));
    }
    public String generateEmail(){
        Random rand = new Random();
        return "automation" + rand.nextInt(99999) + "@automation.gmail";
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}