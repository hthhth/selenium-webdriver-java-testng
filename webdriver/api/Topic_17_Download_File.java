package api;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_17_Download_File {
    WebDriver driver;
    String fileSeparator = File.separator;
    String projectLocation = System.getProperty("user.dir");
    String downloadFilePath = projectLocation + fileSeparator + "downloadFiles";

    @BeforeClass
    public void beforeClass() {
//        System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver.exe");
//        driver = new ChromeDriver();
        System.setProperty("webdriver.gecko.driver", "./browserDrivers/geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.dir", downloadFilePath);
        options.addPreference("browser.download.useDownloadDir", true);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf, image/png, image/pjpeg, image/jpeg, image/jpg");
        options.addPreference("pdfjs.disabled", true);

        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_DownloadFile(){

        deleteAllFilesInFolder();
        sleepInSeconds(3);
        driver.get("https://unsplash.com/photos/JsjXnWlh8-g");
        driver.findElement(By.xpath("//span[text()='Download free']")).click();
        sleepInSeconds(5);
        waitForDownloadFileContainNameCompleted("unsplash.jpg");
    }

    public void sleepInSeconds(int duration){
        try {
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllFilesInFolder(){
        File file = new File(downloadFilePath);
        File[] listOfFiles = file.listFiles();
        System.out.println("Number of files = " + listOfFiles.length);
        for (int i=0; i<listOfFiles.length; i++){
            if (listOfFiles[i].isFile()){
                new File(listOfFiles[i].toString()).delete();
            }
        }
    }

    public boolean isFileContain(String fileName){
        boolean flag = false;
        File file = new File(downloadFilePath);
        File[] listOfFiles = file.listFiles();
        if (listOfFiles == null || listOfFiles.length == 0){
            flag = false;
        }
        for (int i=0; i<listOfFiles.length; i++){
            if (listOfFiles[i].getName().endsWith(fileName)){
                flag = true;
            }
        }
        return flag;
    }
    public void waitForDownloadFileContainNameCompleted(String fileName){
        int i=0;
        while (i<60){
            boolean exist = isFileContain(fileName);
            if (exist == true){
                break;
            }
            sleepInSeconds(1);
            i=i+1;
        }
    }

    public boolean isFileExists(String file) {
        try {
            File files = new File(downloadFilePath + file);
            boolean exists = files.exists();
            return exists;
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        }
    }

    public int countFilesInDirectory() {
        File file = new File(downloadFilePath);
        int i = 0;
        for (File listOfFiles : file.listFiles()) {
            if (listOfFiles.isFile()) {
                i++;
            }
        }
        return i;
    }

    public void waitForDownloadFileFullnameCompleted(String fileName) {
        int i = 0;
        while (i < 60) {
            boolean exist = isFileExists(fileName);
            if (exist == true) {
                i = 60;
                break;
            }
            sleepInSeconds(1);
            i = i + 1;
        }
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}