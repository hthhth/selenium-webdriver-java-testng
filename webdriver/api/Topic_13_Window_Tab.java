package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_13_Window_Tab {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
//        driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void TC_01_Only_2_Windows_Tabs() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        String parentWindowID = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        switchToWindowByID(parentWindowID);
        Assert.assertTrue(driver.findElement(By.xpath("//img[@class='lnXdpd']")).isDisplayed());

        //Lấy windowID của trang hiện tại là trang Google
        String googleWindowID = driver.getWindowHandle();
        // switch về trang trước (là trang automationfc)
        switchToWindowByID(googleWindowID);
        sleepInSeconds(2);

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSeconds(2);

        switchToWindowByID(parentWindowID);

    }


    public void TC_02_Greater_Than_2_Windows_Tabs(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        String parentID = driver.getWindowHandle();
        System.out.println("1. First Automation FC window ID: " + driver.getWindowHandle());
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("Google");
        System.out.println("2. Google window ID: " + driver.getWindowHandle());
        sleepInSeconds(3);
        Assert.assertTrue(driver.findElement(By.xpath("//img[@class='lnXdpd']")).isDisplayed());

        switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
        System.out.println("3. Second Automation FC window ID: " + driver.getWindowHandle());
        sleepInSeconds(3);

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
        System.out.println("4. Facebook window ID: " + driver.getWindowHandle());
        sleepInSeconds(3);
        Assert.assertTrue(driver.findElement(By.xpath("//button[@name='login']")).isDisplayed());

        switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
        System.out.println("5. Third Automation FC window ID: " + driver.getWindowHandle());
        sleepInSeconds(3);

        driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("aLAZADA Vietnam™ - Mua Hàng Trực Tuyến Giá Tốt");
        System.out.println("6. LAZADA window ID: " + driver.getWindowHandle());
        System.out.println(driver.getTitle());
        sleepInSeconds(3);
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='q']")).isDisplayed());

        closeAllWindowExceptParent(parentID);
    }


    public void TC_03_Compare_Product(){
        driver.get("http://live.demoguru99.com/index.php/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());

        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());

        driver.findElement(By.xpath("//span[text()='Compare']")).click();
        String currentWindowId = driver.getWindowHandle();
        switchToWindowByID(currentWindowId);
        Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
        driver.close();
        driver.switchTo().window(currentWindowId);
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        driver.switchTo().alert().accept();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());

    }

    @Test
    public void TC_07_Baitap_Window_Tab(){
        driver.get("https://kyna.vn/");
        String parentWindowID = driver.getWindowHandle();
        driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
        driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='youtube']")).click();
        driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='zalo']")).click();

        switchToWindowByURL("https://www.facebook.com/kyna.vn");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");

        switchToWindowByURL("https://www.youtube.com/user/kynavn");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/user/kynavn");

        switchToWindowByURL("https://zalo.me/1985686830006307471");
        Assert.assertEquals(driver.getCurrentUrl(), "https://zalo.me/1985686830006307471");

        driver.switchTo().window(parentWindowID);
        closeAllWindowExceptParent(parentWindowID);

    }

    public void switchToWindowByURL(String expectedURL){
        Set<String> allWindowID = driver.getWindowHandles();
        for (String windowID : allWindowID){
            driver.switchTo().window(windowID);
            if (driver.getCurrentUrl().equals(expectedURL)){
                break;
            }
        }
    }

    //2 windows/tabs
    public void switchToWindowByID(String parentID){
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String windowID : allWindowIDs) {
            if (!windowID.equals(parentID)){
                driver.switchTo().window(windowID);
                break;
            }
        }
    }

    // >=2 Windows/Tabs
    public void switchToWindowByTitle(String expectedWindowTitle){
        Set<String> allWindowIDs = driver.getWindowHandles();
        for(String windowID : allWindowIDs){
            driver.switchTo().window(windowID);
            String windowTitle = driver.getTitle();
            System.out.println("Switched to " + windowTitle);
            if (windowTitle.equals(expectedWindowTitle)){
                break;
            }
        }
    }

    public void closeAllWindowExceptParent(String parentID){
        Set<String> allWindowIDs = driver.getWindowHandles();
        for(String windowID : allWindowIDs) {
            if (!windowID.equals(parentID)) {
                driver.switchTo().window(windowID);
                driver.close();
                sleepInSeconds(1);
            }
        }
        driver.switchTo().window(parentID);
    }

    public void sleepInSeconds(int time){
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