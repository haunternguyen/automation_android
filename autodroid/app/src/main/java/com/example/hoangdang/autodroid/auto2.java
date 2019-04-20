package com.example.hoangdang.autodroid;

import android.view.KeyEvent;

import org.junit.After;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;

public class auto2 {
    WebDriver driver;
    Workbook wb;
    Sheet sh1;
    int numrow;
    DesiredCapabilities capabilities;
    @BeforeTest
    public void setUp() {
        try {

            capabilities = new DesiredCapabilities();
            capabilities.setCapability("appium-version", "1.5.3");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("platformVersion", "9");// Bạn phải điền lại version của máy Android bạn đang dùng, máy mình là Android 5.0.1
            capabilities.setCapability("deviceName", "emulator-5554"); // 0217da38 là deviceName mình đã lấy lúc trước

            capabilities.setCapability("app", "E:\\Study_IT\\HC\\Tester\\automation android\\autodroid\\app\\build\\outputs\\apk\\debug\\app-debug.apk"); // Mục đích của đoạn code này là tìm đường dẫn đến file apk của bạn, ở đây file cài của mình  là "flipkart.apk"
            capabilities.setCapability("appPackage", "com.example.hoangdang.autodroid"); //Bạn điền Package name của app đã lấy được ở trên vào đây
            capabilities.setCapability("appActivity", "com.example.hoangdang.autodroid.MainActivity"); //Bạn điền Activity name của app đã lấy được ở trên vào đây

        } catch (Exception ex) {

        }
    }

//     @Test(dataProvider = "testdata")
//    public void flipkart_TC01(String uname, String password1) throws IOException {
//        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities); //Các bạn điền server address và port đã note lại ở bước trước
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //Là dòng lệnh để set timeout
//        System.out.println("Run Test Case 01 ");
//        System.out.println(uname);
//
//
//        driver.findElements(By.xpath("//android.widget.EditText[contains(@resource-id,'txtTaiKhoan')]")).get(0).sendKeys("captain");
//        driver.findElements(By.xpath("//android.widget.EditText[contains(@resource-id,'txtMatKhau')]")).get(0).sendKeys("america");
//        driver.findElements(By.xpath("//android.widget.EditText[contains(@resource-id,'btnLogin')]")).get(0).click();
//
//        Assert.assertTrue(driver.findElements(By.xpath("//android.widget.EditText[contains(@resource-id,'login_title')]")).get(0).isEnabled());
//
//        driver.quit();
//    }

    @Test(dataProvider = "testdata")
    public void flipkart_TC01(String uname, String password1) throws IOException {
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities); //Các bạn điền server address và port đã note lại ở bước trước
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //Là dòng lệnh để set timeout
        System.out.println("Run Test Case 01 ");


        driver.findElements(By.xpath("//android.widget.EditText[contains(@resource-id,'txtTaiKhoan')]")).get(0).sendKeys(uname);
        driver.navigate().back();

        driver.findElements(By.xpath("//android.widget.EditText[contains(@resource-id,'txtMatKhau')]")).get(0).sendKeys(password1);
        driver.navigate().back();

        //driver.findElements(By.xpath("//android.widget.EditText[contains(@resource-id,'btnLogin')]")).get(0).click();
        driver.findElements(By.xpath("//android.widget.Button[contains(@resource-id,'btnLogin')]")).get(0).click();

        String expected = "Steve Rogers Login";

        String actual = driver.findElements(By.xpath("//android.widget.TextView[contains(@resource-id,'login_title')]")).get(0).getText();

        // Assert.assertTrue(driver.findElements(By.xpath("//android.widget.Button[contains(@resource-id,'btnLogin')]")).get(0).isDisplayed());
        Assert.assertEquals(actual,expected);
        driver.quit();
    }

    @DataProvider(name = "testdata")
    public Object[][] TestDataFeed() {
        try {

            wb = Workbook.getWorkbook(new File("e:\\Study_IT\\HC\\Tester\\automation android\\Du lieu test\\test.xls"));

            sh1 = wb.getSheet(0);

            numrow = sh1.getRows();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object[][] facebookdata = new Object[numrow][sh1.getColumns()-1];

        for (int i = 0; i < numrow; i++) {

            facebookdata[i][0] = sh1.getCell(0, i).getContents();
            facebookdata[i][1] = sh1.getCell(1, i).getContents();
        }

        return facebookdata;
    }

    @After
    public void End() {
        driver.quit();
    }
}
