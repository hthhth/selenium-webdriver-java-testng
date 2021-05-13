package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_05_Element_Method_PartII {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        driver.get("https://automationfc.github.io/basic-form/index.html");
    }

//    @Test
//    public void TC_01_isDisplayed() throws InterruptedException {
//        WebElement email = driver.findElement(By.cssSelector("#mail"));
//        WebElement radioButtonUnder18 = driver.findElement(By.cssSelector("#under_18"));
//        WebElement textareaEducation = driver.findElement(By.cssSelector("#edu"));
//
//        boolean emailDisplayed = email.isDisplayed();
//        boolean radioButtonUnder18Displayed = radioButtonUnder18.isDisplayed();
//        boolean textareaEducationDisplayed = textareaEducation.isDisplayed();
//
//        Assert.assertTrue(emailDisplayed);
//        Assert.assertTrue(radioButtonUnder18Displayed);
//        Assert.assertTrue(textareaEducationDisplayed);
//        Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed());
//
//        email.sendKeys("Automation Testing");
//        Thread.sleep(2000);
//        textareaEducation.sendKeys("Automation Testing");
//        Thread.sleep(2000);
//        radioButtonUnder18.click();
//        Thread.sleep(2000);
//
//        System.out.println(emailDisplayed ? "Email is displayed" : "Email is not displayed");
//        System.out.println(radioButtonUnder18Displayed ? "RadioButtonUnder18 is displayed" : "RadioButtonUnder18 is not displayed");
//        System.out.println(textareaEducationDisplayed ? "Textarea Education is displayed" : "Textarea Education  is not displayed");
//    }

//    @Test
//    public void TC_02_isEnabled() {
//        boolean emailStatus = driver.findElement(By.cssSelector("#mail")).isEnabled();
//        boolean ageUnder18Status = driver.findElement(By.cssSelector("#under_18")).isEnabled();
//        boolean educationStatus = driver.findElement(By.cssSelector("#edu")).isEnabled();
//        boolean jobRole1Status = driver.findElement(By.cssSelector("#job1")).isEnabled();
//        boolean jobRole2Status = driver.findElement(By.cssSelector("#job2")).isEnabled();
//        boolean checkboxDevelopmentStatus = driver.findElement(By.cssSelector("#development")).isEnabled();
//        boolean slider1Status = driver.findElement(By.cssSelector("#slider-1")).isEnabled();
//
//        boolean passwordStatus = driver.findElement(By.xpath("//input[@name='user_pass']")).isEnabled();
//        boolean radioButtonDisabledStatus = driver.findElement(By.cssSelector("#radio-disabled")).isEnabled();
//        boolean biographyStatus = driver.findElement(By.cssSelector("#bio")).isEnabled();
//        boolean jobRole3dStatus = driver.findElement(By.cssSelector("#job3")).isEnabled();
//        boolean interestCheckboxStatus = driver.findElement(By.cssSelector("#check-disbaled")).isEnabled();
//        boolean slider2Status = driver.findElement(By.cssSelector("#slider-2")).isEnabled();
//
//        System.out.println(emailStatus? "Email is enable" : "Email is disabled");
//        System.out.println(ageUnder18Status? "Age under 18 is enable" : "Age under 18 is disabled");
//        System.out.println(educationStatus? "Education is enable" : "Education is disabled");
//        System.out.println(jobRole1Status? "Job role 1 is enable" : "Job role 1 is disabled");
//        System.out.println(jobRole2Status? "Job role 2 is enable" : "Job role 2 is disabled");
//        System.out.println(checkboxDevelopmentStatus? "Checkbox development is enable" : "Checkbox development is disabled");
//        System.out.println(slider1Status? "Slider 1 is enable" : "Slider 1 is disabled");
//        System.out.println(passwordStatus? "Password is enable" : "Password is disabled");
//        System.out.println(radioButtonDisabledStatus? "Radio button disabled is enable" : "Radio button disabled is disabled");
//        System.out.println(biographyStatus? "Biography is enable" : "Biography is disabled");
//        System.out.println(jobRole3dStatus? "Job role 3 is enable" : "Job role 3 is disabled");
//        System.out.println(interestCheckboxStatus? "Interest Checkbox is enable" : "nterest Checkbox is disabled");
//        System.out.println(slider2Status? "Slider 2 is enable" : "Slider 2 is disabled");
//
//    }
//    @Test
//    public void TC_03_isSelected() {
//        WebElement radioButtonAgeUnder18 = driver.findElement(By.cssSelector("#under_18"));
//        WebElement javaCheckbox = driver.findElement(By.cssSelector("#java"));
//
//        radioButtonAgeUnder18.click();
//        javaCheckbox.click();
//
//        Assert.assertTrue(radioButtonAgeUnder18.isSelected());
//        Assert.assertTrue(javaCheckbox.isSelected());
//
//        javaCheckbox.click();
//        Assert.assertFalse(javaCheckbox.isSelected());
//
//        System.out.println(radioButtonAgeUnder18.isSelected()? "Radio Button is selected" : "Radio Button is not selected");
//        System.out.println(javaCheckbox.isSelected()? "Java Checkbox is selected" : "Java Checkbox is not selected");
//    }
    @Test
    public void TC_04_mailChimp() {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.cssSelector("#email")).sendKeys("autonmationfc@gmail.com");
        driver.findElement(By.cssSelector("#new_username")).sendKeys("Automation");
        driver.findElement(By.cssSelector("#new_password")).sendKeys("1");
        Assert.assertTrue(driver.findElement((By.xpath("//li[@class='number-char completed']"))).isEnabled());

        driver.findElement(By.cssSelector("#new_password")).sendKeys("a");
        Assert.assertTrue(driver.findElement((By.xpath("//li[@class='lowercase-char completed']"))).isEnabled());

        driver.findElement(By.cssSelector("#new_password")).sendKeys("A");
        Assert.assertTrue(driver.findElement((By.xpath("//li[@class='uppercase-char completed']"))).isEnabled());

        driver.findElement(By.cssSelector("#new_password")).sendKeys("@");
        Assert.assertTrue(driver.findElement((By.xpath("//li[@class='special-char completed']"))).isEnabled());

        driver.findElement(By.cssSelector("#new_password")).sendKeys("23456");
        Assert.assertTrue(driver.findElement((By.xpath("//h4[text() = \"Your password is secure and you're all set!\"]"))).isDisplayed());

        driver.findElement(By.cssSelector("#new_password")).clear();
        driver.findElement(By.cssSelector("#new_password")).sendKeys("123456789");

        Assert.assertFalse(driver.findElement(By.cssSelector("#create-account")).isEnabled());

        driver.findElement(By.xpath("//input[@name='marketing_newsletter']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='marketing_newsletter']")).isSelected());


    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}