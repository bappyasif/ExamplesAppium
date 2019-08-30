package com.example.messagingautomation;

import android.view.inputmethod.InputConnection;

import net.bytebuddy.asm.Advice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MessagingAutomation {

    // This will show you Focused App in Your Emulator :
    // adb shell "dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'"

    // This will kill and start adb when faced problem with
    // adb.exe kill-server
    // adb.exe start-server

    AppiumDriver<MobileElement> appium_Driver;

    static String contactNumber = "+8801915645093";
    static String meassageContent = "Hey..Ho...Let's Go";

    @BeforeTest
    public void settingEnvironment() throws MalformedURLException {

        // Created object of DesiredCapabilities class.
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set android deviceName desired capability. Set your device name.
        capabilities.setCapability("deviceName", "emulator-5554");

        // Set BROWSER_NAME desired capability. It's Android in our case here.
        //capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

        // Set android VERSION desired capability. Set your mobile device's OS version.
        capabilities.setCapability(CapabilityType.VERSION, "7.1.1");

        // Set android platformName desired capability. It's Android in our case here.
        capabilities.setCapability("platformName", "Android");

        // Set android appPackage desired capability. It is
        // Set your application's appPackage if you are using any other app.
        capabilities.setCapability("appPackage", "com.google.android.apps.messaging");

        // Set android appActivity desired capability. It is
        // Set your application's appPackage if you are using any other app.
        capabilities.setCapability("appActivity", "com.google.android.apps.messaging.ui.ConversationListActivity");


        //capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);

        // Created object of RemoteWebDriver will all set capabilities.
        // Set appium server address and port number in URL string.

        appium_Driver = new AppiumDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        appium_Driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        System.out.println("Application Launched Is Successful");
    }

    @Test (priority = 0)
    public void CommencingTest(){

        System.out.println("Commencing Test");

        appium_Driver.findElementByAccessibilityId("Start new conversation").click();
        System.out.println("Message Window Is Opened");

        // Using Send Keys Method
        //appium_Driver.findElement(By.id("com.google.android.apps.messaging:id/recipient_text_view")).sendKeys("+8801915645093");
        appium_Driver.findElement(By.id("com.google.android.apps.messaging:id/recipient_text_view")).sendKeys(contactNumber);
        //appium_Driver.findElement(By.id("com.google.android.apps.messaging:id/action_confirm_participants")).click();
        //appium_Driver.findElement(By.id("com.google.android.apps.messaging:id/recipient_text_view")).sendKeys(Keys.ENTER);
        appium_Driver.getKeyboard().sendKeys(Keys.ENTER);

        //appium_Driver.findElement(By.id("com.google.android.apps.messaging:id/compose_message_text")).sendKeys("Please Check Your Inbox");
        appium_Driver.findElement(By.id("com.google.android.apps.messaging:id/compose_message_text")).sendKeys(meassageContent);
        appium_Driver.findElement(By.id("com.google.android.apps.messaging:id/self_send_icon")).click();
        System.out.println("Message Sent");

        // Just Improvising
        appium_Driver.findElementByAccessibilityId("More options").click();
        appium_Driver.findElementsByClassName("android.widget.TextView").get(3).click();
        System.out.println("Message Tinkering Is Done");

    }

    @Test (priority = 1)
    public void CommmencingAnotherTestImprovisation(){

        appium_Driver.findElementByAccessibilityId("Start new conversation").click();

        appium_Driver.findElement(By.id("com.google.android.apps.messaging:id/recipient_text_view")).sendKeys("q");

        Map<String, Object> EnterKeyEvent  = new HashMap<>();
        EnterKeyEvent.put("key", "66");

        //  Key code constant: 'A' key.           Key code constant: 'Z' key.        Key code constant: Enter key.
        //Constant Value: 29 (0x0000001d)         Constant Value: 54 (0x00000036)    Constant Value: 66 (0x00000042)

        // Key code constant: Numeric keypad '0' key.  Key code constant: Numeric keypad '9' key.  Key code constant: '+' key.
        //Constant Value: 144 (0x00000090)             Constant Value: 153 (0x00000099)            Constant Value: 81 (0x00000051)


        //'a' - 'z' --> 29 - 54
        //'0' - '9'--> 7 - 16
        //SPACE --> 62
        //ENTER ---> 66
        //BACKSPACE --> 67
        //BACK --> 4
        //CALL --> 5
        //ENDCALL --> 6

    }


    @AfterTest
    public void tearDown(){
        appium_Driver.quit();
        System.out.print("Test Completed");
    }

}
