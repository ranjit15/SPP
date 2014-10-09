/**
 * 
 */
package com.pearson.tests;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;

/**
 * @author pallavi.mishra
 * @date Apr 6, 2013
 */

public class PrePlayState extends MediaPlayerTest {

    /**
     * This would test
     * "http://player.media.prod.pearsoncmg.com/assets/tH6IgtX_wW1isZkRq5sYgKcVTIqMJOXl" url and
     * verify the presence of video Title , play button , left and right play counter , normal
     * screen button , large screen button , caption buttons, mute button and volume slider
     * 
     **/

    @Test(groups = {"PrePlayStateTwoCaptionVideo", "regression"})
    public void PrePlayStateTwoCaptionVideo() {
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        genericVideoPage.checkingElementsInVideoUrl(
                "http://player.media.dev.pearsoncmg.com/assets/tH6IgtX_wW1isZkRq5sYgKcVTIqMJOXl",
                "PrePlayStateTwoCaptionVideo",
                "QA Test - Two Captions");
    }

    /**
     * This test would test for
     * "http://player.media.prod.pearsoncmg.com/assets/j8ezH8hmSeJpnfi_KYqowb5NZ15WItsc" (One
     * Caption video) and verify the presence of play button , caption button , left and right
     * counters, normal size button, full screen button, video Title, mute button and volume slider
     * **/

    @Test(groups = {"PrePlayStateOneCaptionVideo", "regression"})
    public void PrePlayStateOneCaptionVideo() {
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        genericVideoPage.checkingElementsInVideoUrl(
                "http://player.media.dev.pearsoncmg.com/assets/j8ezH8hmSeJpnfi_KYqowb5NZ15WItsc",
                "PrePlayStateOneCaptionVideo",
                "QA Test - One Set of Captions");

    }

    /**
     * This Test would test
     * "http://player.media.prod.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC" url,
     * where it will verify the presence of play button, Video Title, menu button, left and right
     * counters, normal size button, full screen button, mute button and volume slider
     * 
     * @throws AWTException
     * **/

    @Test(groups = {"ChapteringMenu", "regression"})
    public void ChapteringVideo() {
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        genericVideoPage
                .checkingElementsInVideoUrl(
                        "http://player.media.dev.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC",
                        "ChapteringMenu",
                        "QA Test - Sample Player Chapters");

    }

    /**
     * Verifies that 10 chapters can show on one page for a standard size video player, without the
     * user having to scroll Don't do these UI tests in the future
     */
    @Test(groups = {"showsTenChapters", "regression"})
    public void showsTenChapters() {
        uiDriver.get(httpPrefix + "/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);

        // Wait for chapter menu item to show
        uiDriver.waitToBeDisplayed(genericVideoPage.chapter1, 10000);

        genericVideoPage.verifyTenChaptersAppearOnMenu();

    }

    /*
     * mp-382 Title of video displayed after video ends
     */
    @Test(groups = {"TitleAppears", "chaptering", "regression"})
    private void TitleAppears() {
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        uiDriver.get(httpPrefix + "/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
        myRobot.calibrateAndEnterFrame(myRobot);
        uiDriver.sleep(20000);
        uiDriver.waitToBeDisplayed(genericVideoPage.videoTitle);
        uiDriver.sleep(10000);
        genericVideoPage.playPauseButton.click();
        uiDriver.sleep(20000);
        myRobot.mouseMove(615, 431);// moves to 4:13
        myRobot.mouseClick();
        uiDriver.sleep(20000);
        String ActualResult = genericVideoPage.videoTitle.getText();
        String ExpectedResult = "QA Test - Sample Player Chapters";
        Assert.assertEquals(ExpectedResult, ActualResult);
        logInstruction(" The title of the video is diplayed when the video ends ");
    }

}
