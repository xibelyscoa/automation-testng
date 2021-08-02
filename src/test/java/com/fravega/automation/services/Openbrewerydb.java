package com.fravega.automation.services;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class Openbrewerydb {
    private final String BASE_URL = "https://api.openbrewerydb.org/breweries";

    private final String PATH_AUTOCOMPLETE = "/autocomplete?query=";

    public void testServiceDomain() {
        Response response = RestAssured.get(BASE_URL);
        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());
        System.out.println(response.statusLine());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    public List<Map<String, Object>> getAutocomplete(String keyword) {
        Response response = RestAssured.get(BASE_URL + PATH_AUTOCOMPLETE + keyword);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        return response.as(new TypeRef<List<Map<String, Object>>>() {
        });
    }

    public Map<String, Object> getBrewing(String id) {
        Response response = RestAssured.get(BASE_URL + "/" + id);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        return response.as(new TypeRef<Map<String, Object>>() {
        });
    }
}
