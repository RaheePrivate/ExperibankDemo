package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends PageObject {

    public MainPage(RemoteWebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='makePaymentButton']")
    WebElement makePaymentButton;

    @FindBy(xpath = "//*[@id='Mortgage Request']")
    WebElement mortgageRequestButton;

    @FindBy(xpath = "//*[@id='Expense Report']")
    WebElement expenseReportButton;

    @FindBy(xpath = "//*[@id='logoutButton']")
    WebElement logoutButton;

    public MainPage clickMakePayment() {
        wait.until(ExpectedConditions.elementToBeClickable(makePaymentButton));
        makePaymentButton.click();
        client.report("Clicked on Make Payment Button", true);
        return this;
    }

    public MainPage clickMortgageRequest() {
        wait.until(ExpectedConditions.elementToBeClickable(mortgageRequestButton));
        mortgageRequestButton.click();
        return this;
    }

    public MainPage clickExpenseReport() {
        wait.until(ExpectedConditions.elementToBeClickable(expenseReportButton));
        expenseReportButton.click();
        return this;
    }

    public MainPage clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
        return this;
    }

    public boolean verifyUserOnPaymentPage() {
        return new PaymentPage(driver).verifyUserOnPaymentPage();
    }

    public boolean verifyUserOnMainPage() {
        wait.until(ExpectedConditions.elementToBeClickable(makePaymentButton));
        if (makePaymentButton.isDisplayed()) {
            client.report("User on Main Page", true);
            return true;
        }
        client.report("User not on Main Page", false);
        return false;
    }

}
