package tests;

import com.experitest.client.Client;
import com.experitest.client.GridClient;
import com.experitest.client.MobileListener;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class Untitled {

    private String cloudURL = "";
    private String cloudAccessKey = "";

    private String deviceQuery = "";

    private String emailAddress = "";
    private String accessKey = "";

    long startTime, endTime, totalTime, totalTimeInSeconds = 0L;

    boolean flag = true;

    @Test
    public void testing() {

        GridClient gridClient = new GridClient(cloudAccessKey, cloudURL);
        Client client = null;

        client = gridClient.lockDeviceForExecution("BB Work Flow", deviceQuery, 10, 30000);

        client.launch("bbworkapp.id", false, true);

        Client finalClient = client;
        client.addMobileListener("NATIVE", "//xpath[contains(text(), 'Connected')]", new MobileListener() {
            @Override
            public boolean recover(String s, String s1) {
                // If statement reaches here, user is connected and test ends
                finalClient.report("User connected and mailbox updated, took " + totalTimeInSeconds + " seconds", true);
                finalClient.generateReport();
                finalClient.releaseClient();
                return true;
            }
        });

        if (client.waitForElement("NATIVE", "//*[@id='BBDActivationEmailFieldID']", 0, 10000)) {
            client.elementSendText("NATIVE", "//*[@id='BBDActivationEmailFieldID']", 0, emailAddress);
            client.elementSendText("NATIVE", "", 0, accessKey);
            client.click("NATIVE", "//*[@XCElementType='XCUIElementTypeButton' and @id='Go']", 0, 1);
        }

        startTime = System.currentTimeMillis();

        int maximumTimeInSeconds = 120;

        while (flag) {

            endTime = System.currentTimeMillis();

            // Continuously adding value to totalTime
            totalTime = endTime - startTime;

            // Infinite loop here, do nothing

            // Converting totalTime from Milliseconds to Seconds
            totalTimeInSeconds = TimeUnit.MILLISECONDS.toSeconds(totalTime);

            // Checking if totalTime is equals to or exceeding desired Maximum Time
            if (totalTimeInSeconds >= maximumTimeInSeconds) {
                // Perform steps to go and collect the logs

                // Final steps
                client.report("Collecting logs as time has reached " + maximumTimeInSeconds + " seconds", true);
                client.generateReport();
                client.releaseClient();

                // Setting flag value as false, and exiting the while loop
                flag = false;
            }

        }
    }

}
