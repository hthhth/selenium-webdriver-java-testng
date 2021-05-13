package api;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_05_Element_Method_PartIII {
    WebDriver driver;
    By email = By.cssSelector("#mail");
    By radioButtonUnder18 = By.cssSelector("#under_18");
    By textareaEducation = By.cssSelector("#edu");

    By jobRole1 = By.cssSelector("#job1");
    By jobRole2 = By.cssSelector("#job2");
    By checkboxDevelopment = By.cssSelector("#development");
    By slider1 = By.cssSelector("#slider-1");

    By password = By.xpath("//input[@name='user_pass']");
    By radioButtonDisabled = By.cssSelector("#radio-disabled");
    By biography = By.cssSelector("#bio");
    By jobRole3 = By.cssSelector("#job3");
    By interestCheckbox = By.cssSelector("#check-disbaled");
    By slider2 = By.cssSelector("#slider-2");

    By javaCheckbox = By.cssSelector("#java");

    By emailMailChimp = By.cssSelector("#email");
    By newUserMailChimp = By.cssSelector("#new_username");
    By newPasswordMailChimp = By.cssSelector("#new_password");
    By checkboxMarketingNewsletter = By.xpath("//input[@name='marketing_newsletter']");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        driver.get("https://automationfc.github.io/basic-form/index.html");
    }

    public void TC_01_isDisplayed() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        isDisplayed(email);
        isDisplayed(radioButtonUnder18);
        isDisplayed(textareaEducation);

        sendKey(email, "Automation Testing");
        sendKey(textareaEducation, "Automation Testing");

        clickElement(radioButtonUnder18);
    }

    public void isDisplayed(By by){
        System.out.println(driver.findElement(by).isDisplayed() ? "Element is displayed" : "Element is not displayed");
    }
    public void sendKey(By by, String text) throws InterruptedException {
        driver.findElement(by).sendKeys(text);
        Thread.sleep(2000);
    }
    public void clickElement(By by) throws InterruptedException {
        driver.findElement(by).click();
        Thread.sleep(2000);
    }
    public void isEnabled(By by, String nameElement){
        System.out.println(driver.findElement(by).isEnabled() ? nameElement + " is displayed" : nameElement + " is not displayed");
    }
    public void isSelected(By by, String nameElement){
        System.out.println(driver.findElement(by).isSelected() ? nameElement + " is selected" : nameElement + " is not selected");
    }

    public void isDisabled(By by, String nameElement){
        System.out.println(driver.findElement(by).isEnabled() ? nameElement + " is disabled" : nameElement + " is enabled");
    }
    public void TC_02_isEnabled() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        isEnabled(email, "Email");
        isEnabled(radioButtonUnder18, "Radio button Age under 18");
        isEnabled(textareaEducation, "Text area Education");
        isEnabled(jobRole1, "Job role 01");
        isEnabled(jobRole2, "Job role 02");
        isEnabled(checkboxDevelopment, "Interests (Development) checkbox");
        isEnabled(slider1, "Slider 01");
        isEnabled(password, "Password");
        isEnabled(radioButtonDisabled, "Age (Radiobutton is disabled");
        isEnabled(biography, "Biography");
        isEnabled(jobRole3, "Job role 03");
        isEnabled(interestCheckbox, "Interests (Checkbox is disabled)");
        isEnabled(slider2, "Slider 02");

    }
    public void TC_03_isSelected() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        clickElement(radioButtonUnder18);
        clickElement(javaCheckbox);

        isSelected(radioButtonUnder18, "Radio Button");
        isSelected(javaCheckbox, "Java Checkbox");

        clickElement(radioButtonUnder18);
        clickElement(javaCheckbox);

        isSelected(radioButtonUnder18, "Radio Button");
        isSelected(javaCheckbox, "Java Checkbox");
    }
    @Test
    public void TC_04_mailChimp() throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/");

        sendKey(emailMailChimp, "autonmationfc@gmail.com");
        sendKey(newUserMailChimp, "Automation");
        sendKey(newPasswordMailChimp, "1");

        isDisabled(By.xpath("//li[@class='number-char completed' and text()='One number']"),"Line One number");

        sendKey(newPasswordMailChimp, "a");

        isDisabled(By.xpath("//li[@class='lowercase-char completed']"),"Line One lower case character");

        sendKey(newPasswordMailChimp, "A");

        isDisabled(By.xpath("//li[@class='uppercase-char completed']"),"Line One upper case character");

        sendKey(newPasswordMailChimp, "@");

        isDisabled(By.xpath("//li[@class='special-char completed']"),"Line One special character");

        sendKey(newPasswordMailChimp, "23456");



        Assert.assertTrue(driver.findElement((By.xpath("//h4[text() = \"Your password is secure and you're all set!\"]"))).isDisplayed());

        driver.findElement(newPasswordMailChimp).clear();
        driver.findElement(newPasswordMailChimp).sendKeys("123456789");

        Assert.assertFalse(driver.findElement(By.cssSelector("#create-account")).isEnabled());

        clickElement(checkboxMarketingNewsletter);
        isSelected(checkboxMarketingNewsletter, "Checkbox Marketing Newsletter");


    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}