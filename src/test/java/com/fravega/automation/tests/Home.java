package com.fravega.automation.tests;

import com.fravega.automation.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class Home extends Base{

    @Test()
    public void searchHeladera() throws Exception {
        String searchKeyqords = "heladera";
        String searchCategory = "Heladeras";
        search(searchKeyqords,searchCategory);
    }

    @Test()
    public void searchSamsungTV() throws Exception {
        String searchKeyqords = "samsung";
        String searchCategory = "TV";
        search(searchKeyqords,searchCategory);
    }

    @Test()
    public void searchNotebook() throws Exception {
        String searchKeyqords = "core i7";
        String searchCategory = "Notebooks";
        search(searchKeyqords,searchCategory);
    }


    @Description("Buscar '{0}  {1}'")
    @Severity(SeverityLevel.BLOCKER)
    private void search(String keyword, String category) throws Exception {
        HomePage homepage = new HomePage(driver);
        homepage.closePopUp();
        homepage.search(keyword);
        homepage.selectCategory(category);
        homepage.sleep(5000);
        homepage.selectBrand(1);
        homepage.checkCategoryOnBreadcrumb(category);
    }
}
