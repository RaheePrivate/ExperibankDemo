package pages;

import com.experitest.appium.SeeTestClient;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {

    protected RemoteWebDriver driver;
    protected WebDriverWait wait;
    protected SeeTestClient client;

    public PageObject(RemoteWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
        client = new SeeTestClient(driver);
    }

}
