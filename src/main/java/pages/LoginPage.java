package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends PageObject {

    public LoginPage(RemoteWebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='usernameTextField']")
    WebElement usernameField;

    @FindBy(xpath = "//*[@id='passwordTextField']")
    WebElement passwordField;

    @FindBy(xpath = "//*[@id='loginButton']")
    WebElement loginButton;

    @FindBy(xpath = "//*[contains(text(), 'Invalid username or password')]")
    WebElement invalidCredentialsMessage;

    public LoginPage enterUsername(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameField));
        usernameField.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        return this;
    }

    public boolean verifyInvalidCredentials() {
        wait.until(ExpectedConditions.elementToBeClickable(invalidCredentialsMessage));
        if (invalidCredentialsMessage.isDisplayed()) {
            client.report("Login Validation Passed", true);
            return true;
        }
        client.report("Login Validation Failed", false);
        return false;
    }

}
