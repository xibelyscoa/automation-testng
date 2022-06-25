package com.fravega.automation.pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class HomePage extends BasePage{
    private final By CLOSE_BT = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/button[1]");
    private final By CLOSE_DIV = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]");

    private final By SEARCH_INPUT = By.xpath("/html[1]/body[1]/div[1]/div[2]/header[1]/div[2]/form[1]/fieldset[1]/div[1]/input[1]");

    private final By SEARCH_BT = By.xpath("/html[1]/body[1]/div[1]/div[2]/header[1]/div[2]/form[1]/fieldset[1]/div[1]/button[1]");

    private final By ITEMS_GRID = By.xpath("/html/body/div/div/div/div/ul/li/article/a[1]/div[1]/div[1]/span[1]");

    private final By BREADCRUMB = By.xpath("/html[1]/body[1]/div[1]/div[2]/div[3]/div[2]/div[1]/ol[1]/li");

    int TIMEOUT = 10;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Cerrando pop-up de código postal")
    public void closePopUp() throws Exception {
        System.out.println("Cerrando Pop-Up de código postal...");
        try {
            clickElement(CLOSE_BT, TIMEOUT);
        }catch (Exception e){
            clickElement(CLOSE_DIV, TIMEOUT);
            throw e;
        }
        Allure.step("Se cerró exitósamente!", Status.PASSED);
    }

    @Step("Buscando {0}")
    public void search(String words) {
        System.out.println("Searching \"" + words + "\"...");
        driver.findElement(SEARCH_INPUT).sendKeys(words);
        driver.findElement(SEARCH_BT).click();
    }

    @Step("Seleccionando categoría {0}")
    public void selectCategory(String category) throws Exception {
        By categorySearch = By.xpath("//a[contains(text(),'" + category + " (')]");
        clickElement(categorySearch, TIMEOUT);
    }

    @Step("Verificando categoría en Breadcrum")
    public void checkCategoryOnBreadcrumb(String category) {
        List<WebElement> breadcrumb = driver.findElements(BREADCRUMB);
        boolean found = false;
        if(breadcrumb.size()==0){
            Allure.step("No se encontró breadcrumb", Status.FAILED);
        }
        for (int i = 0; i < breadcrumb.size(); i++) {
            String item = breadcrumb.get(i).getText().trim().toLowerCase();
            Allure.step("No se encontró breadcrumb", Status.FAILED);
            if (item.compareTo(category.trim().toLowerCase()) == 0) {
                found = true;
            }
        }
        Assert.assertTrue(found);
        Allure.step("Verificado exitósamente!", Status.PASSED);
    }

    @Step("Seleccionando {0}° marca")
    public void selectBrand(int choice) throws Exception {
        By brandSearch = By.xpath("//*[@name='brandAggregation']");
        By brandTitle = By.xpath("/html[1]/body[1]/div[1]/div[2]/div[3]/div[4]/div[2]/h3[1]");

        scrollToElement(brandTitle);
        sleep(5000);

        List<WebElement> brands = driver.findElements(brandSearch);

        if (brands.size() < choice) {
            Allure.step("No se encontraron marcas", Status.FAILED);
        }

        String brandText = brands.get(choice - 1).getText();

        brands.get(choice - 1).click();

        sleep(5000);

        List<WebElement> titleItems = driver.findElements(ITEMS_GRID);

        Allure.step("Resultados:" + titleItems.size());

        for (int i = 0; i < titleItems.size(); i++) {
            String title = titleItems.get(i).getText();
            String brandName = brandText.substring(0, brandText.indexOf("(") - 1);
            System.out.println("Title: '" + title+"' Brand: "+brandName);
            Assert.assertTrue(title.contains(brandName));
        }
        Allure.step("Seleccionado exitósamente!", Status.PASSED);
    }
}
