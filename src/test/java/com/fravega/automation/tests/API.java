package com.fravega.automation.tests;

import com.fravega.automation.services.Openbrewerydb;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.model.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class API {
    @Test
    @Description("Test domain service")
    public void testServiceDomain() {
        Openbrewerydb service = new Openbrewerydb();
        service.testServiceDomain();
        Allure.step("Openbrewerydb está disponible", Status.PASSED);
    }

    @Test
    @Description("Test Breweries")
    public void openBrewingTest() {
        Openbrewerydb service = new Openbrewerydb();
        List<Map<String, Object>> fastBreweries = service.getAutocomplete("lagunitas");

        try {
            fastBreweries.forEach(brewery->{
                System.out.println(brewery);
                if(brewery.get("name").toString().contains("Lagunitas Brewing Co")){
                    Map<String, Object> brewing = service.getBrewing(brewery.get("id").toString());
                    if(brewing.get("state").toString().contains("California")){
                        Assert.assertEquals(brewing.get("name"),"Lagunitas Brewing Co");
                        Assert.assertEquals(brewing.get("street"),"1280 N McDowell Blvd");
                        Assert.assertEquals(brewing.get("phone"),"7077694495");
                        Assert.assertEquals(brewing.get("id"),"lagunitas-brewing-co-petaluma");
                        Allure.step("'Lagunitas Brewing Co' está OK", Status.PASSED);
                    }
                }else {
                    Allure.step("'Lagunitas Brewing Co' no es de 'California'", Status.SKIPPED);
                }
            });
        }catch (Exception e){
            Allure.step("openBrewingTest FAILED", Status.FAILED);
        }
    }
}
