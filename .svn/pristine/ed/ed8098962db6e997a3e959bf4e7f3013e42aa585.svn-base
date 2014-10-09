package com.pearson.tests.Accessibility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;
import com.pearson.tests.MediaPlayerTest;

public class TestVolume_onVideoMutes extends MediaPlayerTest {

    public static final int TAB_PRESS_RELEASE_ITERATIONS = 20;
    GenericVideoPage genericVideoPage;

    @Test(groups = {"testVideoScrubbing", "regression"})
    public void testVideoScrubbing() throws AWTException {

        genericVideoPage = new GenericVideoPage(uiDriver);
        genericVideoPage
                .checkingElementsInVideoUrl(
                        "http://player.media.dev.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC",
                        "ChapteringMenu",
                        "QA Test - Sample Player Chapters");

        uiDriver.sleep(2000, "Currently Sleeping for 2 seconds");
        Robot robot = new Robot();

        /*
         * Tab to Play button and press Enter, then capture the value of Play
         * button
         */
        tabPressRelease(robot, 300, TAB_PRESS_RELEASE_ITERATIONS);
        /* Press Enter to Play the video */
        robot.keyPress(KeyEvent.VK_ENTER);
        uiDriver.sleep(8000);
        robot.keyRelease(KeyEvent.VK_ENTER);

        uiDriver.sleep(8000);
        // checkPlayandPause_PKey(robot);

    }

    private static void tabPressRelease(Robot robot, int sleep, int iterations) {
        for (int i = 0; i < iterations; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            uiDriver.sleep(sleep);
            robot.keyRelease(KeyEvent.VK_TAB);
        }
    }

    /* Check if the vidoe is Paused and played by Pressing P Key */

    private void checkPlayandPause_PKey(Robot robot) {
        robot.keyPress(KeyEvent.VK_P);
        uiDriver.sleep(300);
        robot.keyPress(KeyEvent.VK_P);

        String PKey_playbutton = genericVideoPage.playPauseButton.getAttribute("title");

        logger.info(" the value of Pause button is  " + PKey_playbutton);

        Assert.assertEquals(
                PKey_playbutton,
                "Pause Video",
                "Test Whether Video is played by Pressing 'P' Key");

        /* Make the driver sleep for 3 seconds till the video gets loaded */
        uiDriver.sleep(3000);

        robot.keyPress(KeyEvent.VK_P);
        uiDriver.sleep(300);
        robot.keyPress(KeyEvent.VK_P);

        String PKey_pausebutton = genericVideoPage.playPauseButton.getAttribute("title");

        logger.info(" the value of Pause button is  " + PKey_pausebutton);

        Assert.assertEquals(
                PKey_pausebutton,
                "Play Video",
                "Test Whether Video is paused by Pressing 'P' Key");

    }
}
