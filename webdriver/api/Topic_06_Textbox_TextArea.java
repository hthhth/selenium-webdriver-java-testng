package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_06_Textbox_TextArea {
    WebDriver driver;
    String loginPageUrl;
    String userID;
    String password;
    String customerID;
    String editAddress, editCity, editState, editPIN, editMobile, editEmail;

    By customerNameBy = By.name("name");
    By genderMaleBy = By.xpath("//input[@name='rad1' and @value='m']");
    By genderFemaleBy = By.xpath("//input[@name='rad1' and @value='f']");
    By genderBy = By.name("gender");
    By dateOfBirthBy = By.name("dob");
    By addressBy = By.name("addr");
    By cityBy = By.name("city");
    By stateBy = By.name("state");
    By pinBy = By.name("pinno");
    By mobileNumberBy = By.name("telephoneno");
    By emailBy = By.name("emailid");
    By passwordTextboxBy = By.name("password");

    String name, dob, address, city, state, pin, mobile, email;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://demo.guru99.com/v4/");
        name = "John Wick";
//        dob = "12/24/2000";
        dob = "2000-12-24";
        address = "123 John Address";
        city = "New York";
        state = "Masachuset";
        pin = "982719";
        mobile = "0983457812";
        email = randomEmail();

        editAddress = "456 John Address";
        editCity = "California";
        editState = "New York";
        editPIN = "652522";
        editMobile = "0983457999";
        editEmail = randomEmail();
    }

    @Test
    public void TC_01_Register() {
        loginPageUrl = driver.getCurrentUrl();
        driver.findElement(By.xpath("//a[text()='here']")).click();
        driver.findElement(By.name("emailid")).sendKeys(email);
        driver.findElement(By.name("btnLogin")).click();

        userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

    }

    @Test
    public void TC_02_Login() {
        driver.get(loginPageUrl);
        driver.findElement(By.name("uid")).sendKeys(userID);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("btnLogin")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userID + "']")).isDisplayed());
    }

    @Test
    public void TC_03_New_Customer() {
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();
        driver.findElement(customerNameBy).sendKeys(name);
        driver.findElement(dateOfBirthBy).sendKeys(dob);
        driver.findElement(addressBy).sendKeys(address);
        driver.findElement(cityBy).sendKeys(city);
        driver.findElement(stateBy).sendKeys(state);
        driver.findElement(pinBy).sendKeys(pin);
        driver.findElement(mobileNumberBy).sendKeys(mobile);
        driver.findElement(emailBy).sendKeys(email);
        driver.findElement(passwordTextboxBy).sendKeys(password);
        driver.findElement(By.name("sub")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());

        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobile);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);

        customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();


    }

    @Test
    public void TC_04_Edit_Customer() {
        driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
        driver.findElement(By.name("cusid")).sendKeys(customerID);
        driver.findElement(By.name("AccSubmit")).click();

        Assert.assertFalse(driver.findElement(customerNameBy).isEnabled());
        Assert.assertFalse(driver.findElement(genderBy).isEnabled());
        Assert.assertFalse(driver.findElement(dateOfBirthBy).isEnabled());

        Assert.assertEquals(driver.findElement(customerNameBy).getAttribute("value"), name);
        Assert.assertEquals(driver.findElement(genderBy).getAttribute("value"), "male");
        Assert.assertEquals(driver.findElement(dateOfBirthBy).getAttribute("value"), dob);

        Assert.assertEquals(driver.findElement(addressBy).getAttribute("value"),address);
        Assert.assertEquals(driver.findElement(cityBy).getAttribute("value"),city);
        Assert.assertEquals(driver.findElement(stateBy).getAttribute("value"),state);
        Assert.assertEquals(driver.findElement(pinBy).getAttribute("value"),pin);
        Assert.assertEquals(driver.findElement(mobileNumberBy).getAttribute("value"),mobile);
        Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"),email);

        driver.findElement(addressBy).clear();
        driver.findElement(addressBy).sendKeys(editAddress);
        driver.findElement(cityBy).clear();
        driver.findElement(cityBy).sendKeys(editCity);
        driver.findElement(stateBy).clear();
        driver.findElement(stateBy).sendKeys(editState);
        driver.findElement(pinBy).clear();
        driver.findElement(pinBy).sendKeys(editPIN);
        driver.findElement(mobileNumberBy).clear();
        driver.findElement(mobileNumberBy).sendKeys(editMobile);
        driver.findElement(emailBy).clear();
        driver.findElement(emailBy).sendKeys(editEmail);

        driver.findElement(By.name("sub")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer details updated Successfully!!!']")).isDisplayed());

        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(), customerID);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);

        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPIN);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editMobile);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public String randomEmail(){
        Random random = new Random();
        return "automation" + random.nextInt(999999) + "@gmail.com";
    }
}