package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

import org.testng.annotations.*;

public class BaseClass extends Reporting{
    static AppiumDriver<MobileElement> driver;

    @BeforeTest
    public void setup(){
        try {
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Redmi Note 9 Pro");
            cap.setCapability(MobileCapabilityType.UDID, "51b18245");
            cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12");
            cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
            cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.uharris.wedding");
            cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.uharris.wedding.presentation.sections.register.RegisterActivity");

            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AppiumDriver<MobileElement>(url, cap);
        }
        catch(Exception exp){
            System.out.println("Cause: "+exp.getCause());
            System.out.println("Message: "+exp.getMessage());
        }
    }

    @AfterSuite
    public void tearDown(){
        //driver.close();
        driver.quit();
    }
}
