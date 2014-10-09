package com.pearson.tests.Accessibility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;
import com.pearson.tests.MediaPlayerTest;

/* @Author : Prasanna Balakrishnan
 * Test Description : Selecting the ESC when the player is in
 * full screen will return the screen to the normal size
 * 
 */

public class Test_Escape_NormalScreenSize extends MediaPlayerTest {

    @Test(groups = {"test_escapeNormalScreen", "regression"})
    public void test_escapeNormalScreen() throws AWTException {

        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        genericVideoPage
                .checkingElementsInVideoUrl(
                        "http://player.media.dev.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC",
                        "ChapteringMenu",
                        "QA Test - Sample Player Chapters");

        uiDriver.sleep(2000, "Currently Sleeping for 2 seconds");

        uiDriver.sleep(5000);

        /*  Get the class name of the Full Screen Size button before Enlarging*/

        String intialstate = genericVideoPage.fullSizeButton.getAttribute("class");

        logger.info("before clicking " + intialstate);

        genericVideoPage.fullSizeButton.click();

        uiDriver.sleep(6000);

        /*  Get the class name of the Full Screen
         *  Size button After Enlarging*/

        String classname = genericVideoPage.fullSizeButton.getAttribute("class");

        logger.info(" the value class is  " + classname);

        Robot robot = new Robot();

        uiDriver.sleep(5000);

        /*  Press Escape to return to Normal Size and check the value of Full Screensize*/

        robot.keyPress(KeyEvent.VK_ESCAPE);
        uiDriver.sleep(50);
        robot.keyRelease(KeyEvent.VK_ESCAPE);

        String classname_afterminimizing = genericVideoPage.fullSizeButton.getAttribute("class");

        logger.info(" the value class after minimizing  is  " + classname_afterminimizing);
        Assert.assertEquals(
                classname,
                "ss-icon boxable highlight on",
                "Test Whether the screen has returned to normal size");
    }
}
