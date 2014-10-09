package com.pearson.tests.Accessibility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;
import com.pearson.tests.MediaPlayerTest;

public class Test_HomeButton extends MediaPlayerTest {

    // static FirefoxDriver driver = new FirefoxDriver();

    @Test(groups = {"testHomeButton", "regression"})
    public void testHomeButton() throws AWTException, InterruptedException {

        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        /*
         * genericVideoPage .checkingElementsInVideoUrl(
         * "http://player.media.dev.pearsoncmg.com/assets/tH6IgtX_wW1isZkRq5sYgKcVTIqMJOXl"
         * , "TestVideo", "QA Test - Sample Player Chapters");
         */
        uiDriver.get("http://player.media.dev.pearsoncmg.com/assets/tH6IgtX_wW1isZkRq5sYgKcVTIqMJOXl");
        uiDriver.switchTo().frame(0);

        // driver.get("http://player.media.dev.pearsoncmg.com/assets/tH6IgtX_wW1isZkRq5sYgKcVTIqMJOXl");
        uiDriver.sleep(35000);

        // driver.switchTo().frame(0);
        Robot robot = new Robot();

        genericVideoPage.playPauseButton.click();

        /* Wait for the Video to play for few seconds */
        uiDriver.sleep(15000);

        /* Get the value of the counter after it gets loaded */
        String getTime = genericVideoPage.leftPlayCounter.getText();
        logger.info("The time value is  " + getTime);

        /*
         * Check after Pressing HOME button , the vidoe returns to Starting
         * point
         */

        robot.keyPress(KeyEvent.VK_HOME);
        robot.keyRelease(KeyEvent.VK_HOME);
        Thread.sleep(300);
        String getTimeafterPressingHome = genericVideoPage.leftPlayCounter.getText();
        logger.info("The time value after pressing home  is  " + getTimeafterPressingHome);
        Assert.assertEquals(getTimeafterPressingHome.endsWith("00:00"), true);

    }

}
