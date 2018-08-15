package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class PaymentPage extends PageObject {

    public PaymentPage(RemoteWebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='phoneTextField']")
    WebElement phoneTextField;

    @FindBy(xpath = "//*[@id='nameTextField']")
    WebElement nameTextField;

    @FindBy(xpath = "//*[@id='amountTextField']")
    WebElement amountTextField;

    @FindBy(xpath = "//*[@id='countryButton']")
    WebElement countryButton;

    @FindBy(xpath = "//*[@text='USA']")
    WebElement selectUS;

    @FindBy(xpath = "//*[@id='sendPaymentButton']")
    WebElement sendPaymentButton;

    @FindBy(xpath = "//*[@text='Yes']")
    WebElement yesButton;

    public PaymentPage enterPhoneNumber(String phoneNumber) {
        wait.until(ExpectedConditions.elementToBeClickable(phoneTextField));
        phoneTextField.sendKeys(phoneNumber);
        return this;
    }

    public PaymentPage enterName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(nameTextField));
        nameTextField.sendKeys(name);
        return this;
    }

    public PaymentPage enterAmount(String amount) {
        wait.until(ExpectedConditions.elementToBeClickable(amountTextField));
        amountTextField.sendKeys(amount);
        return this;
    }

    public PaymentPage clickCountryButton() {
        wait.until(ExpectedConditions.elementToBeClickable(countryButton));
        countryButton.click();
        return this;
    }

    public PaymentPage selectCountry(String country) {
        wait.until(ExpectedConditions.elementToBeClickable(selectUS));
        selectUS.click();
        return this;
    }

    public PaymentPage clickSendPaymentButton() {
        try {
            if (driver.findElement(By.xpath("//*[@id='sendPaymentButton' and @enabled='false']")).isDisplayed()) {
                client.report("Send Payment Button Disabled, Not Sending Payment", true);
            }
        } catch (Exception e) {
            sendPaymentButton.click();
            client.report("Send Payment Button Enabled, Sending Payment", true);
            wait.until(ExpectedConditions.elementToBeClickable(yesButton));
            yesButton.click();
            assertTrue(new MainPage(driver).verifyUserOnMainPage());
        }
        return this;
    }
//
//    public PaymentPage clickSendPaymentButton() {
//        sendPaymentButton.click();
//        try {
//            sendPaymentButton.click();
//            client.report("Send Payment Button Enabled, Sending Payment", true);
//            wait.until(ExpectedConditions.elementToBeClickable(yesButton));
//            yesButton.click();
//            assertTrue(new MainPage(driver).verifyUserOnMainPage());
//        } catch (Exception e) {
//            client.report("Send Payment Button Disabled, Not Sending Payment", true);
//        }
//        return this;
//    }

    public boolean verifyUserOnPaymentPage() {
        wait.until(ExpectedConditions.elementToBeClickable(phoneTextField));
        if (phoneTextField.isDisplayed()) {
            client.report("User on Payment Page", true);
            return true;
        }
        client.report("User not on Payment Page", false);
        return false;
    }
}
