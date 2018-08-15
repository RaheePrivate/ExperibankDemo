package tests;

import base.BaseUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.PaymentPage;
import utils.ConfigFileReader;

import static org.testng.Assert.assertTrue;

public class PaymentPageTest extends BaseUtil {

    private LoginPage loginPage;
    private MainPage mainPage;
    private PaymentPage paymentPage;

    ConfigFileReader configFileReader;

    @BeforeMethod
    public void classInitialization() {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        paymentPage = new PaymentPage(driver);

        configFileReader = new ConfigFileReader();

        loginPage
                .enterUsername("company")
                .enterPassword("company")
                .clickLoginButton();

        assertTrue(mainPage.verifyUserOnMainPage());
    }

    @Test
    public void enterEmptyAmount() {

        mainPage.clickMakePayment();

        paymentPage
                .enterPhoneNumber("12345")
                .enterName("Rahee")
                .clickCountryButton()
                .selectCountry("USA");

        paymentPage.clickSendPaymentButton();

    }

    @Test
    public void enterEmptyPhoneNumber() {

        mainPage.clickMakePayment();

        paymentPage
                .enterName("Rahee")
                .enterAmount("10")
                .clickCountryButton()
                .selectCountry("USA");

        paymentPage.clickSendPaymentButton();

    }

    @Test
    public void makeValidPayment() {

        mainPage.clickMakePayment();

        paymentPage
                .enterPhoneNumber("12345")
                .enterName("Rahee")
                .enterAmount("10")
                .clickCountryButton()
                .selectCountry("USA");

        paymentPage.clickSendPaymentButton();

    }

}
