package com.fravega.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver=driver;
    }

    public String getText(final By by, int delay) throws MalformedURLException {
        Duration duration = Duration.ofSeconds(delay);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until((ExpectedConditions.visibilityOfElementLocated(by)));
        return element.getText();
    }

    public void scrollToElement(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement Element = driver.findElement(locator);
        js.executeScript("arguments[0].scrollIntoView();", Element);
    }

    public void sleep(int milliseconds) throws Exception {
        Thread.sleep(milliseconds);
    }

    public void highlightElement(WebElement element) throws Exception {
        for (int i = 0; i < 2; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            sleep(500);
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                    "color: yellow; border: 3px solid yellow;");
            sleep(500);
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
        }
    }

    public void clickElement(By locator, int delay) throws Exception {
        try {
            Duration duration = Duration.ofSeconds(delay);
            System.out.println("clicking element: "+locator.toString());
            WebDriverWait wait = new WebDriverWait(driver, duration);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            wait.until((ExpectedConditions.elementToBeClickable(locator)));
            highlightElement(element);
            element.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
