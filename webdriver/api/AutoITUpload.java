package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.io.IOException;

public class AutoITUpload {
    @Test
    public static void uploadFile() throws Exception {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://tinyupload.com/");
        driver.findElement(By.xpath("//input[@name='uploaded_file']")).click();
        Thread.sleep(2000);
        Runtime.getRuntime().exec("./autoIT/FileUploadScript.exe");
        driver.findElement(By.xpath("//input[@name='uploaded_file']")).click();
        Thread.sleep(8000);
        driver.quit();
    }
}
