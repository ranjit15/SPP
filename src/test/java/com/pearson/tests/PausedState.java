/**
 * So this will test pause functionality
 * TODO in retrospect it was a bad idea to divide play/pause functionality into two classes since they overlap. Combine them.
 */
package com.pearson.tests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;

public class PausedState extends MediaPlayerTest {

    /**
     * Tests that if you click the hover play button in pause state that the video plays Deal with
     * these throws eventually by having good logs
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    @Test(groups = {"playState", "hoverPlayButton", "regression"})
    public void hoverPlayButton() throws IOException, InterruptedException {
        //uiDriver.get(httpPrefix + "/assets/tH6IgtX_wW1isZkRq5sYgKcVTIqMJOXl");
    	uiDriver.get("http://mediaplayer-cert.pearsoncmg.com/assets/tH6IgtX_wW1isZkRq5sYgKcVTIqMJOXl");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);
        // generally (not always) the caption button will load last, so we can expect the wrapper to
        // have loaded when the caption button loads
        //uiDriver.waitToBeDisplayed(genericVideoPage.captionButton, 10000);
        genericVideoPage.clickToolbarPlay();

        // Before we pause, the video has to start playing
        myRobot.verifyVideoIsPlaying();

        // Try to pause the video
        genericVideoPage.clickToolbarPlay();

        // Verify the video has paused
        genericVideoPage.verifyVideoPaused();

        // TODO if mouse actions aren't fast enough and fail
        // then put try catch around the clicks and mouse movement and try again
        // Move mouse to center of video to get the hover replay button to show up
        myRobot.mouseMove(320, 240);

        /*
         * Now that the video has paused I want to verify that clicking the hover play button causes
         * video to resume These hover buttons do not stay for very long and depending on delays
         * between robot it may be impossible to find them
         */
        // uiDriver.waitToBeDisplayed(genericVideoPage.hoverPlayButton, 5000);

        // click on the hover play button
        myRobot.mouseClick();
        uiDriver.sleep(110000);
        //Reduce to 40000 if we r using Jenkins
        
        myRobot.verifyVideoIsPlaying();
        
    }

    /*
     * Verifies When video is paused, a play button will appear in the toolbar Clicking on the
     * toolbar play button in paused state will continue the video from a paused point mp-376,mp-377
     */

    //@Test(groups = {"PrePlayStateTwoCaptionVideo", "acceptance", "regression"})
    public void PauseVerification() throws AWTException {
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);

        genericVideoPage
                .checkingElementsInVideoUrl(
                        "http://player.media.dev.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC",
                        "ChapteringMenu",
                        "QA Test - Sample Player Chapters");
        genericVideoPage.playPauseButton.waitToBePresent(4000);
        genericVideoPage.playPauseButton.click();// for playing video
        uiDriver.sleep(40000);// 00:36 seconds
        myRobot.verifyVideoIsPlaying();
        genericVideoPage.playPauseButton.click();
        String currentTimeCode = genericVideoPage.leftPlayCounter.getWebElement().getText();
        System.out.println("sleep time " + currentTimeCode);

        logger.info(genericVideoPage.playPauseButton.getAttribute("title"));
        logger.info(genericVideoPage.playPauseButton.getAttribute("value"));
        logger.info(genericVideoPage.playPauseButton.getAttribute("class"));

        Assert.assertTrue(
                "Play Video".equals(genericVideoPage.playPauseButton.getAttribute("title")),
                "expected  \"Play Video\" found \"" + genericVideoPage.playPauseButton
                        .getAttribute("title") + "\"");

        Assert.assertFalse(genericVideoPage.pauseButton.isDisplayed());
        logger.info("Pause button displayed");
    }

    /**
     * mp-376,mp-377
     */
    //@Test(groups = {"PrePlayStateTwoCaptionVideo", "regression"})
    public void TitleVerification() {
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        genericVideoPage
                .checkingElementsInVideoUrl(
                        "http://player.media.dev.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC",
                        "ChapteringMenu",
                        "QA Test - Sample Player Chapters");
        genericVideoPage.playPauseButton.waitToBePresent(4000);
        String videotitle = genericVideoPage.videoTitle.getText();
        logger.info("Title before the video plays = " + videotitle);
        genericVideoPage.playPauseButton.click();
        logger.info("AFter Click");

        uiDriver.sleep(20000);// plays for
        genericVideoPage.playPauseButton.click();// pauses video
        String videotitle1 = genericVideoPage.videoTitle.getText();
        logger.info("Title of the video in paused state = " + videotitle1);
        // Assert.assertTrue(videotitle.equals(videotitle1),
        // "Title remain same in the paused state");
        // Assert.assertEquals(videotitle, videotitle1);
        Assert.assertEquals(videotitle, videotitle1, "Title remain same in the paused state");

    }

}
