package com.pearson.tests;

/**
 * Verifies that parameters can be passed in the URL for test cases
 * Should probably test playerids
 * title & description
 * feeds
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;

public class AdditionalParameters extends MediaPlayerTest {

    /**
     * Verifies that if you pass a title and description, the video takes on those titles and descriptions
     * Known issue, this test does not run in cert because cert does not support these params
     * MP-439, MP-440
     **/
    @Test(groups = {"titleAndDescription", "regression"})
    private void titleAndDescription() {
        uiDriver.get(httpPrefix + "/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC" + "?title=arbitraryTitle&description=arbitraryDescription");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);

        myRobot.calibrateAndEnterFrame(myRobot);

        // wait for video title to load
        wait.until(new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver webDriver) {
                System.out.println("Waiting for video title to load... " + webDriver.findElement(
                        By.cssSelector("strong.video-title")).getText());
                return !webDriver.findElement(By.cssSelector("strong.video-title")).getText()
                        .equalsIgnoreCase("Loading...");
            }
        });

        Assert.assertEquals(
                genericVideoPage.videoTitle.getText(),
                "arbitraryTitle",
                "The title of the video did not change to what you specified");

        genericVideoPage.moreInfoButton.click();

        Assert.assertEquals(
                genericVideoPage.moreInfoTextArea.getText(),
                "arbitraryDescription",
                "The title of the video did not change to what you specified");
    }

    /**
     * Tests that feeds can be filtered to streaming and download 
     * It is not environment parameter dependent
     * MP-514
     * No way to test feedFiltering at present
     */
   // @Test(groups = {"feedFiltering"})
    private void feedFiltering() {
        // This video should play back because currently in prod, we are not filtering out download
        // urls
        uiDriver.get("http://player.media.prod.pearsoncmg.com/assets/BA7C21C8-8D48-E0CA-5F04-A94A876F0E83?feedAccount=PearsonDev&feedId=Rf5roPus7Lrc");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);
        // generally (not always) the video image will load last, so we can expect the wrapper to
        // have loaded when the video image loads
        uiDriver.waitToBeDisplayed(genericVideoPage.videoImageArea, 10000);

        // play video
        genericVideoPage.clickToolbarPlay();
        // Verify video is playing
        myRobot.verifyVideoIsPlaying();

        // In dev we are filtering out download urls so videos should play
        // TODO Post release we just have to test that link does not play in any environment to
        // verify feeds are working

        uiDriver.get("http://player.media.dev.pearsoncmg.com/assets/BA7C21C8-8D48-E0CA-5F04-A94A876F0E83?feedAccount=PearsonDev&feedId=Rf5roPus7Lrc");
        myRobot.calibrateAndEnterFrame(myRobot);

        // Verify that the correct error message is given and we do not default to public urls.
        Assert.assertTrue(
                genericVideoPage.errorRequestMessage.getText().equalsIgnoreCase(
                        "There was a problem processing your request."),
                "There was a problem verifying that feed filtering was working. Expected an error message on page but instead did not find it.");

    }
}