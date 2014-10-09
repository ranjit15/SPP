package com.pearson.tests.Accessibility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;
import com.pearson.tests.MediaPlayerTest;

/*@Author : Prasanna Balakrishnan
 * Test Description :
 * 
 * A) Selecting "p" on keyboard will pause the video in play state
 * B) Selecting "p" on keyboard will resume play when video is paused
 *     
 */

public class TestPlayPause_PKey extends MediaPlayerTest {

    public static final int TAB_PRESS_RELEASE_ITERATIONS = 9;
    GenericVideoPage genericVideoPage;

    @Test(groups = {"testPlayPause_PKey", "regression"})
    public void testPlayPause_PKey() throws AWTException {

        genericVideoPage = new GenericVideoPage(uiDriver);
        genericVideoPage
                .checkingElementsInVideoUrl(
                        "http://player.media.dev.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC",
                        "ChapteringMenu",
                        "QA Test - Sample Player Chapters");

        uiDriver.sleep(2000, "Currently Sleeping for 2 seconds");
        Robot robot = new Robot();

        /* Click on the Video and wait till the video gets loaded*/
        genericVideoPage.playPauseButton.click();

        uiDriver.sleep(8000);

        checkPlayandPause_PKey(robot);

    }

    /* Check if the vidoe is Paused and played by Pressing P Key */

    private void checkPlayandPause_PKey(Robot robot) {

        /* Click on 'P' button to Pause the video*/

        robot.keyPress(KeyEvent.VK_P);
        uiDriver.sleep(300);
        robot.keyRelease(KeyEvent.VK_P);

        logger.info("video to get Paused");

        /* Retrieve the values of Play button during Play state*/

        String PKey_pausebutton = genericVideoPage.playPauseButton.getAttribute("title");

        logger.info(" the value of Pause button is  " + PKey_pausebutton);

        Assert.assertEquals(
                PKey_pausebutton,
                "Play Video",
                "Test Whether Video is paused by Pressing 'P' Key");

        /*Now the video is in Pause state. Press 'P' to play the video */

        robot.keyPress(KeyEvent.VK_P);

        robot.keyRelease(KeyEvent.VK_P);

        /* Make the driver sleep for 3 seconds till the video gets loaded */

        uiDriver.sleep(3000);

        /* Retrieve the values of Play button during Play state*/

        String PKey_playbutton = genericVideoPage.playPauseButton.getAttribute("title");

        logger.info(" the value of Play button is  " + PKey_playbutton);

        uiDriver.sleep(2000);

        Assert.assertEquals(
                PKey_playbutton,
                "Pause Video",
                "Test Whether Video is played by Pressing 'P' Key");
    }

}
