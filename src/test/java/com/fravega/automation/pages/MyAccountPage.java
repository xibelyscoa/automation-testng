package com.fravega.automation.pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class MyAccountPage extends HomePage {
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    private final By MY_DATA = By.xpath("/html[1]/body[1]/div[4]/div[2]/section[1]/a[1]");
    private final By ENTRY_USER_PASS = By.id("loginWithUserAndPasswordBtn");
    private final By INPUT_USER = By.xpath("/html[1]/body[1]/div[12]/div[1]/div[2]/div[3]/div[1]/form[1]/div[2]/div[1]/div[1]/input[1]");
    private final By INPUT_PASS = By.xpath("/html[1]/body[1]/div[12]/div[1]/div[2]/div[3]/div[1]/form[1]/div[2]/div[2]/div[1]/input[1]");
    private final By LOGIN_BT = By.xpath("/html[1]/body[1]/div[12]/div[1]/div[2]/div[3]/div[1]/form[1]/div[3]/button[1]");

    private final By ACCOUNT_EMAIL = By.xpath("/html[1]/body[1]/div[4]/div[2]/div[1]/div[1]/div[1]/section[1]/main[1]/main[1]/div[1]/div[1]/article[1]/main[1]/div[1]/div[2]/div[1]/div[1]");



    @Step("Iniciar sesion")
    public void loginAccount() throws Exception {
        System.out.println("abriendo las opciones para hacer el registro");
        clickElement(MY_DATA, TIMEOUT);
    }

    @Step("Ingresar con mail y contraseña")
    public void entryMailPass() throws Exception {
        System.out.println("Registrarse con mail y contraseña");
        clickElement(ENTRY_USER_PASS, TIMEOUT);
        sleep(5000);
    }

    @Step("Ingresar mail y contraseña")
    public void loginMailPass(String user, String pass) throws Exception {
        System.out.println("Agregar entrada de mail y contraseña");
        driver.findElement(INPUT_USER).sendKeys(user);
        sleep(500);
        driver.findElement(INPUT_PASS).sendKeys(pass);
        sleep(500);
        clickElement(LOGIN_BT, TIMEOUT);
        sleep(5000);
    }

    @Step("Verificar el Email de la cuenta")
    public void checkAccount(String email) throws Exception {
        System.out.println("Verificar Email de la cuenta existente");
        String accountEmail = getText(ACCOUNT_EMAIL, TIMEOUT);
        Assert.assertTrue(accountEmail.compareTo(email) == 0);
        Allure.step("Se verificó Ok el mail de la cuenta!", Status.PASSED);
    }

}
