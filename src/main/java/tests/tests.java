package tests;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class tests extends BaseClass{

    @Test
    public void login(){
        ExtentTest extentTest = extent.createTest("Test login","Testing login");
        WebDriverWait wait = new WebDriverWait(driver, 15);

        extentTest.info("Waiting for first name");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.uharris.wedding:id/nameEditText")));
        extentTest.pass("First name located");

        MobileElement firstName = driver.findElement(By.id("com.uharris.wedding:id/nameEditText"));
        MobileElement lastName = driver.findElement(By.id("com.uharris.wedding:id/lastNameEditText"));
        MobileElement code = driver.findElement(By.id("com.uharris.wedding:id/codeEditText"));
        MobileElement enter = driver.findElement(By.id("com.uharris.wedding:id/logInButton"));

        firstName.sendKeys("Salvador");
        extentTest.pass("First Name filling ok");
        lastName.sendKeys("Barahona");
        extentTest.pass("Last Name filling ok");
        code.sendKeys("230219");
        extentTest.pass("Code filling ok");
        enter.click();
        extentTest.pass("Enter clicking ok");

        extentTest.info("Waiting for title");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.uharris.wedding:id/toolbar_title")));
        extentTest.pass("Title located");

        MobileElement title = driver.findElement(By.id("com.uharris.wedding:id/toolbar_title"));
        Assert.assertEquals(title.getText(),"ASO Conference");
        extentTest.pass("Login successful");
    }

    @Test
    public void viewLocationDetails(){
        ExtentTest extentTest = extent.createTest("Test viewLocationDetails","Allows the user to see the location details for the event.");
        WebDriverWait wait = new WebDriverWait(driver, 15);

        if(!isElementPresent(By.id("com.uharris.wedding:id/toolbar_title"))){
            extentTest.info("Login start");
            login();
            extentTest.pass("Login successful");
        }

        MobileElement viewLocations = driver.findElement(By.id("com.uharris.wedding:id/navigation_sites"));

        viewLocations.click();

        extentTest.info("Waiting for first location");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.TextView[1]")));
        extentTest.pass("First location located");

        MobileElement firstLocation = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.TextView[1]"));
        Assert.assertEquals(firstLocation.getText(),"San Jose McEnery Convention Center");

        firstLocation.click();

        extentTest.info("Waiting for image");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.uharris.wedding:id/siteImage")));
        extentTest.pass("Image located");

        MobileElement siteDescription = driver.findElement(By.id("com.uharris.wedding:id/siteDescription"));

        Assert.assertEquals(siteDescription.getText(),"The San Jose McEnery Convention Center is a convention center in Downtown San Jose, California, in Silicon Valley. The 550,000-square-foot facility is the largest convention center in Silicon Valley.");
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]")).click();
    }

    @Test
    public void writeAComment(){
        ExtentTest extentTest = extent.createTest("Test writeAComment","Allows the user to write a public comment about the events.");
        WebDriverWait wait = new WebDriverWait(driver, 15);

        if(!isElementPresent(By.id("com.uharris.wedding:id/toolbar_title"))){
            extentTest.info("Login start");
            login();
            extentTest.pass("Login successful");
        }

        MobileElement commentsSection = driver.findElement(By.id("com.uharris.wedding:id/navigation_wishes"));

        extentTest.info("Enter comment section");
        commentsSection.click();
        extentTest.pass("Comment section successful");

        extentTest.info("New comment");
        MobileElement newComment = driver.findElement(By.id("com.uharris.wedding:id/action_create_wish"));

        newComment.click();

        extentTest.info("Waiting for comment");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.uharris.wedding:id/wishEditText")));
        extentTest.pass("Comment located");

        MobileElement comment = driver.findElement(By.id("com.uharris.wedding:id/wishEditText"));
        MobileElement sendComment = driver.findElement(By.id("com.uharris.wedding:id/sendButton"));

        comment.sendKeys("This is a new comment by sbarahona");
        sendComment.click();
        extentTest.pass("New comment added successfully");

    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
