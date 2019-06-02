package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CalculateMedianTest {

    static WebDriver driver;

    @Test(description = "VerifyCalculating Median Value",alwaysRun = true,priority = 1)
    public void testCalculatingMedianValue() throws MalformedURLException {

        File appDir = new File(System.getProperty("user.dir") + "/src/main/resources/Apps/");
        File app = new File(appDir + "/CalculaMediaFinal.apk");


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APP, app);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "GalaxyJ7");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Android");

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        driver.findElement(By.id("com.exemplo.calculamediafinal:id/txtNota1")).sendKeys("3");
        driver.findElement(By.id("com.exemplo.calculamediafinal:id/txtNota2")).sendKeys("3");
        driver.findElement(By.id("com.exemplo.calculamediafinal:id/txtNota3")).sendKeys("3");
        driver.findElement(By.id("com.exemplo.calculamediafinal:id/btnCalcular")).click();


        String median = driver.findElement(By.id("com.exemplo.calculamediafinal:id/txtMediaFinal")).getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(median, "3.0", "Median Value is Incorrect");
        softAssert.assertAll();
        driver.quit();
    }

        @Test(description = "VerifyInsertEmptyValuesAndReturnAnErrorMessage",alwaysRun = true,priority = 2)
        public void InsertEmptyValuesAndReturnAnErrorMessage() throws MalformedURLException {

            File appDir = new File(System.getProperty("user.dir")+"/src/main/resources/Apps/");
            File app = new File(appDir+"/CalculaMediaFinal.apk");


            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.APP, app);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "GalaxyJ7");
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Android");

            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);

            driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

            driver.findElement(By.id("com.exemplo.calculamediafinal:id/btnCalcular")).click();


            String error=driver.findElement(By.id("android:id/alertTitle")).getText();
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(error,"CalculaMediaFinal has stopped","Median Value is Incorrect");
            driver.findElement(By.id("android:id/aerr_close")).click();
            softAssert.assertAll();
            driver.quit();


    }
}