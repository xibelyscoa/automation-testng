package com.fravega.automation.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Base {
    public WebDriver driver;

    @BeforeSuite()
    public void setUp() {
        String resources = "/src/resources/";
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + resources + "chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-default-browser-check");
        options.addArguments("allow-file-access-from-files");
        options.addArguments("use-fake-device-for-media-stream");
        options.addArguments("use-fake-ui-for-media-stream");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-geolocation");
        options.addArguments("disable-popup-blocking");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @BeforeMethod()
    public void irUrl() {
        this.driver.get("https://www.fravega.com/");
    }

    @AfterClass()
    public void finPrueba() {
        System.out.println("Fin de prueba");
    }

    @AfterTest()
    public void cierreNavegador() {
        this.driver.quit();
    }

    @AfterSuite()
    public void finSuite() {
        System.out.println("Fin Suite");
    }
}
