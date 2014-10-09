/**
 * 
 */
package com.pearson.tests;

import java.awt.AWTException;
import java.awt.Color;

import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;

/**
 * @author pallavi.mishra
 * @date Apr 6, 2013
 */

public class ImageIT extends MediaPlayerTest {

    /**
     * This would test
     * "http://player.media.prod.pearsoncmg.com/assets/tH6IgtX_wW1isZkRq5sYgKcVTIqMJOXl" url and
     * verify the presence of video Title , play button , left and right play counter , normal
     * screen button , large screen button , caption buttons, mute button and volume slider
     * 
     **/

    @Test(groups = {"PrePlayStateTwoCaptionVideo","regression"})
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

    @Test(groups = {"PrePlayStateOneCaptionVideo","regression"})
    public void PrePlayStateOneCaptionVideo() {
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        genericVideoPage.checkingElementsInVideoUrl(
                "http://player.media.dev.pearsoncmg.com/assets/j8ezH8hmSeJpnfi_KYqowb5NZ15WItsc",
                "PrePlayStateOneCaptionVideo",
                "QA Test - One Set of Captions");
    }

    // }

    /**
     * This Test would test
     * "http://player.media.prod.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC" url,
     * where it will verify the presence of play button, Video Title, menu button, left and right
     * counters, normal size button, full screen button, mute button and volume slider
     * 
     * @throws AWTException
     * **/

    @Test(groups = {"ChapteringMenu","regression"})
    public void ChapteringVideo() {
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        genericVideoPage
                .checkingElementsInVideoUrl(
                        "http://player.media.dev.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC",
                        "ChapteringMenu",
                        "QA Test - Sample Player Chapters");

    }

    @Test(groups = {"PrePlayStateTwoCaptionVideo","regression"})
    public void PauseVerification() throws AWTException {
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        genericVideoPage
                .checkingElementsInVideoUrl(
                        "http://player.media.dev.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC",
                        "ChapteringMenu",
                        "QA Test - Sample Player Chapters");
        genericVideoPage.playPauseButton.waitToBePresent(4000);
        genericVideoPage.playPauseButton.click();
        uiDriver.sleep(40000);// 00:36 seconds
        String currentTimeCode = genericVideoPage.leftPlayCounter.getWebElement().getText();
        System.out.println("sleep time " + currentTimeCode);
        System.out.println(genericVideoPage.playPauseButton.getAttribute("title"));
        System.out.println(genericVideoPage.playPauseButton.getAttribute("value"));
        System.out.println(genericVideoPage.playPauseButton.getAttribute("class"));
        Assert.assertTrue(
                "Pause Video".equals(genericVideoPage.playPauseButton.getAttribute("title")),
                "expected  \"Pause Video\" found \"" + genericVideoPage.playPauseButton
                        .getAttribute("title") + "\"");

        Assert.assertTrue(genericVideoPage.pauseButton.isDisplayed());
        logger.info("pause displayed");
        // genericVideoPage.playPauseButton.click();

        Point pointToVerify = genericVideoPage.playPauseButton.getWebElement().getLocation();
        System.out.println(pointToVerify);

    }

    Color expectedColorOfPixel = new Color(174, 166, 151);// some shade of white

}
