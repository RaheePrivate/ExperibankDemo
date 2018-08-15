package base;

import com.experitest.appium.SeeTestClient;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.ConfigFileReader;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseUtil {

    protected RemoteWebDriver driver = null;
    protected SeeTestClient client = null;

    protected ConfigFileReader configFileReader;

    @BeforeMethod
    @Parameters({"OS", "deviceQuery", "environment"})
    public void setUp(@Optional String OS, @Optional String deviceQuery, @Optional String environment, @Optional Method method) throws MalformedURLException {

        configFileReader = new ConfigFileReader();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("accessKey", configFileReader.getAccessKey());
        capabilities.setCapability("testName", method.getName());
        capabilities.setCapability("reportDirectory", "reports");
        //capabilities.setCapability("reportFormat", "html2,xml");
        capabilities.setCapability("reportFormat", "xml");
        capabilities.setCapability("generateReport", true);

        if (OS.equalsIgnoreCase("Android")) {

            if (environment.equalsIgnoreCase("Local")) {

                capabilities.setCapability(MobileCapabilityType.UDID, deviceQuery);
                capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.eribank");
                capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.experitest.ExperiBank.LoginActivity");
                driver = new AndroidDriver<>(new URL(configFileReader.getLocalURL()), capabilities);

            } else if (environment.equalsIgnoreCase("Cloud")) {

                capabilities.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.eribank/com.experitest.ExperiBank.LoginActivity");
                capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.eribank");
                capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.experitest.ExperiBank.LoginActivity");

                capabilities.setCapability("deviceQuery", deviceQuery);
                driver = new AndroidDriver<AndroidElement>(new URL(configFileReader.getCloudURL()), capabilities);
                client = new SeeTestClient(driver);

            }

        } else if (OS.equalsIgnoreCase("IOS")) {

            if (environment.equalsIgnoreCase("Local")) {

                capabilities.setCapability(MobileCapabilityType.UDID, deviceQuery);
                capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBank");
                driver = new IOSDriver<>(new URL(configFileReader.getLocalURL()), capabilities);
                client = new SeeTestClient(driver);

            } else if (environment.equalsIgnoreCase("Cloud")) {

//                capabilities.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank");
//                capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBank");

                capabilities.setCapability("deviceQuery", deviceQuery);
                driver = new IOSDriver<IOSElement>(new URL(configFileReader.getCloudURL()), capabilities);
                client = new SeeTestClient(driver);

                client.install("cloud:com.experitest.ExperiBank", true, false);
                client.launch("com.experitest.ExperiBank", true, true);
            }
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
