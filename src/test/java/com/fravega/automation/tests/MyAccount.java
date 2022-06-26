package com.fravega.automation.tests;

import com.fravega.automation.pages.HomePage;
import com.fravega.automation.pages.MyAccountPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class MyAccount extends Base{

    @Test()
    @Description("Login whit user and pass")
    @Severity(SeverityLevel.BLOCKER)
    private void loginOk() throws Exception {
        MyAccountPage myAccountPage = new MyAccountPage (driver);
        myAccountPage.closePopUp();
        myAccountPage.clickMyAccount();
        myAccountPage.loginAccount();
        myAccountPage.entryMailPass();
        myAccountPage.loginMailPass("fravegatest@mailinator.com","Escalera1");
        myAccountPage.checkAccount("fravegatest@mailinator.com");
    }
}
