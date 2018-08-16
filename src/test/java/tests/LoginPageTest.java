package tests;

import base.BaseUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import utils.ConfigFileReader;

import static org.testng.Assert.assertTrue;

public class LoginPageTest extends BaseUtil {

    private LoginPage loginPage;
    private MainPage mainPage;

    ConfigFileReader configFileReader;

    @BeforeMethod
    public void classInitialization() {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);

        configFileReader = new ConfigFileReader();
    }

    @Test
    public void validCredentials() {

        loginPage
                .enterUsername("company")
                .enterPassword("company")
                .clickLoginButton();

        assertTrue(mainPage.verifyUserOnMainPage());
    }

    @Test
    public void invalidCredentials() {

        loginPage
                .enterUsername("comp")
                .enterPassword("comp")
                .clickLoginButton();

        assertTrue(loginPage.verifyInvalidCredentials());
    }

    @Test
    public void emptyUsername() {

        loginPage
                .enterPassword("comp")
                .clickLoginButton();

        assertTrue(loginPage.verifyInvalidCredentials());
    }

    @Test
    public void emptyPassword() {

        loginPage
                .enterUsername("comp")
                .clickLoginButton();

        assertTrue(loginPage.verifyInvalidCredentials());
    }
}
