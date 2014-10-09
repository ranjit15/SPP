/**
 * 
 */
package com.pearson.poc_tests;

import static org.testng.Assert.assertNotNull;

import java.awt.AWTException;
import java.awt.Robot;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;
import com.pearson.test.eselenium.framework.core.UIDriver;
import com.pearson.test.eselenium.framework.drivers.DefaultUIDriver;

/**
 * @author pallavi.mishra
 * @date Apr 6, 2013
 */

public class PrePlayState {

    private static final Logger logger = Logger.getLogger(PrePlayState.class);
    protected static UIDriver uiDriver;
    // protected MediaPlayerRobot myRobot;
    protected static Actions actions;

    public Robot robot;

    // protected static POCRobot myRobot;

    @BeforeClass(alwaysRun = true)
    public static void SetUpClassTest() {
        // Note that because I am using a different framework I need to initialize the driver
        // differently
        // uiDriver = DefaultUIDriver.getConfigDriver();
        uiDriver = new DefaultUIDriver();
        assertNotNull(uiDriver);
        uiDriver.manage().window().maximize();
        actions = new Actions(uiDriver.getWebDriver());

    }

    @AfterClass
    public static void afterClassTest() {
        uiDriver.quit();
    }

    @AfterMethod
    private void sleepAfterTest() {
        uiDriver.sleep(1000);
    }

    /**
     * This would test
     * "http://player.media.prod.pearsoncmg.com/assets/tH6IgtX_wW1isZkRq5sYgKcVTIqMJOXl" url and
     * verify the presence of video Title , play button , left and right play counter , normal
     * screen button , large screen button , caption buttons, mute button and volume slider
     * 
     **/

    @Test(groups = {"PrePlayStateTwoCaptionVideo"})
    public void PrePlayStateTwoCaptionVideo() {
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        genericVideoPage.checkingElementsInVideoUrl(
                "http://player.media.cert.pearsoncmg.com/assets/tH6IgtX_wW1isZkRq5sYgKcVTIqMJOXl",
                "PrePlayStateTwoCaptionVideo",
                "QA Test - Two Captions");
    }

    /**
     * This test would test for
     * "http://player.media.prod.pearsoncmg.com/assets/j8ezH8hmSeJpnfi_KYqowb5NZ15WItsc" (One
     * Caption video) and verify the presence of play button , caption button , left and right
     * counters, normal size button, full screen button, video Title, mute button and volume slider
     * **/

    @Test(groups = {"PrePlayStateOneCaptionVideo"})
    public void PrePlayStateOneCaptionVideo() {
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        genericVideoPage.checkingElementsInVideoUrl(
                "http://player.media.cert.pearsoncmg.com/assets/j8ezH8hmSeJpnfi_KYqowb5NZ15WItsc",
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
     * @throws UnknownHostException
     */
    // @throws AWTException
    // @throws InterruptedException
    // @throws IOException

    @Test(groups = {"ChapteringMenu"})
    public void ChapteringVideo() {
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        genericVideoPage
                .checkingElementsInVideoUrl(
                        "http://player.media.cert.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC",
                        "ChapteringMenu",
                        "QA Test - Sample Player Chapters");
    }

}
