package com.fravega.automation.tests;

import com.fravega.automation.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

@Epic("Búsqueda de artículos")
public class Home extends Base{

    @Test()
    public void searchHeladera() throws Exception {
        search("heladera","Heladeras");
    }

    @Test()
    public void searchSamsungTV() throws Exception {
        search("samsung","TV");
    }

    @Test()
    public void searchNotebook() throws Exception {
        search("core i7","Notebooks");
    }

    @Test()
    public void searchColchon() throws Exception {
        search("colchon","Colchones y sommiers");
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
