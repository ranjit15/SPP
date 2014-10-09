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
 * A) Selecting the play button through tabbing and pressing enter starts video
 * B) Selecting the pause button through tabbing and pressing enter pauses video
 *     
 */

public class Test_PlayPause_Accessiblity extends MediaPlayerTest {

    public static final int TAB_PRESS_RELEASE_ITERATIONS = 15;
    GenericVideoPage genericVideoPage;

    @Test(groups = {"testPlayPauseAccessiblity", "regression"})
    public void testPlayPauseAccessiblity() throws AWTException {

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

        uiDriver.sleep(2000);

        /* Get the value of Play button */
        String value_playbutton = genericVideoPage.playPauseButton.getAttribute("title");

        logger.info(" the value of Play button is  " + value_playbutton);

        Assert.assertEquals(value_playbutton, "Pause Video", "Test Whether Video is played");

        /* Press ENTER to Pause the video and Get the value of Pause button */

        robot.keyPress(KeyEvent.VK_ENTER);
        uiDriver.sleep(3000);
        robot.keyRelease(KeyEvent.VK_ENTER);

        String value_Pausebutton = genericVideoPage.playPauseButton.getAttribute("title");

        logger.info(" the value of Pause button is  " + value_Pausebutton);

        Assert.assertEquals(value_Pausebutton, "Play Video", "Test Whether Video is paused");

    }

    private static void tabPressRelease(Robot robot, int sleep, int iterations) {
        for (int i = 0; i < iterations; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            uiDriver.sleep(sleep);
            robot.keyRelease(KeyEvent.VK_TAB);
        }
    }

}
