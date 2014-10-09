package com.pearson.tests;

import java.awt.Color;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;

public class PlayState extends MediaPlayerTest {

    /**
     * Verifies that you can use the hover replay button to replay a video MP-380
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(groups = {"hoverReplayButton", "regression"})
    private void hoverReplayButton() throws IOException, InterruptedException {
        uiDriver.get(httpPrefix + "/assets/tH6IgtX_wW1isZkRq5sYgKcVTIqMJOXl");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);
        // generally (not always) the caption button will load last, so we can expect the wrapper to
        // have loaded when the caption button loads
        uiDriver.sleep(10000);
        uiDriver.waitToBeDisplayed(genericVideoPage.captionButton, 15000);
        genericVideoPage.clickToolbarPlay();

        // Before we scrub to the end, the video has to start playing
        myRobot.verifyVideoIsPlaying();

        // Let's scrub to the end of the video
        // +5y because I want to click on an area 5 below the time scrubber's top left point
        myRobot.mouseMove(
                genericVideoPage.fullSizeButton.getWebElement().getLocation().x,
                genericVideoPage.progressBarSlider.getWebElement().getLocation().y + 5);
        myRobot.mouseClick();
        genericVideoPage.waitForVideoToEnd();

        // TODO if mouse actions aren't fast enough and fail
        // then put try catch around the clicks and mouse movement and try again
        // Move mouse to center of video to get the hover replay button to show up
        myRobot.mouseMove(320, 240);

        /*
         * Now that the video has ended I want to verify that replaying the video with the replay
         * button causes Video to continue to play genericVideoPage.hoverPlayButton.click(); Does
         * not work with long robot timeouts on slow machines because after 2 seconds the element
         * will go back to hiding.
         */
        myRobot.mouseClick();
        uiDriver.sleep(90000);
        // Verify the video has started playing
        myRobot.verifyVideoIsPlaying();
    }

    /**
     * Verifies that you can use the hover replay button MP-382
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    //@Test(groups = {"acceptance", "endState", "toolbarReplayButton", "regression"})
    private void toolbarReplayButton() throws IOException, InterruptedException {
        uiDriver.get(httpPrefix + "/assets/tH6IgtX_wW1isZkRq5sYgKcVTIqMJOXl");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);
        // generally (not always) the caption button will load last, so we can expect the wrapper to
        // have loaded when the caption button loads
        uiDriver.waitToBeDisplayed(genericVideoPage.captionButton, 10000);
        genericVideoPage.clickToolbarPlay();

        // Before we scrub to the end, the video has to start playing
        myRobot.verifyVideoIsPlaying();

        // Let's scrub to the end of the video
        // +5y because I want to click on an area 5 below the time scrubber's top left point
        myRobot.mouseMove(
                genericVideoPage.fullSizeButton.getWebElement().getLocation().x,
                genericVideoPage.progressBarSlider.getWebElement().getLocation().y + 5);
        myRobot.mouseClick();
        genericVideoPage.waitForVideoToEnd();

        /*
         * Now that the video has ended I want to verify that replaying the video with the replay
         * button causes Video to continue to play mp-383
         */
        genericVideoPage.playPauseButton.click();
        genericVideoPage.verifyVideoPaused();
        uiDriver.sleep(10000);
        // Verify the video has started playing
        myRobot.verifyVideoIsPlaying();
    }

    /**
     * Test will go to a link and play back video Then it will open the english captions and verify
     * that some captions work After the video has finished playing it will play again and check
     * that captions are still working This is a regression test to make sure that captions not
     * working never comes back again. Also verifies that captions run on the 1 captions video
     * Currently Runs in ChromeDriver, works in FF (!ESR 17!), does not work in IE. Good to know
     * that most buttons do not work in IE9 as buttons often can not be clicked on either ever or
     * intermittently. Using the JSExecutor works for IE MP-988 as well
     */
    //@Test(groups = {"captionsTest", "regression", "acceptance"})
    private void captionsTest() {
        String videoLink = httpPrefix + "/assets/tH6IgtX_wW1isZkRq5sYgKcVTIqMJOXl";
        logInstruction("Go to " + videoLink);
        uiDriver.get(videoLink);
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);
        // generally (not always) the caption button will load last, so we can expect the wrapper to
        // have loaded when the caption button loads
        logInstruction("Wait for video to fully load - Ranjit");
        uiDriver.sleep(10000);
        uiDriver.waitToBeDisplayed(genericVideoPage.captionButton, 10000);
        //System.out.println("Video image size before captions turn on ... " + uiDriver.findElement(
                //By.id("_playerPdkSwfObject")).getSize().getWidth() + " * " + uiDriver.findElement(
                //By.id("_playerPdkSwfObject")).getSize().getHeight());
        genericVideoPage.clickToolbarPlay();
        genericVideoPage.clickCCButton();
        // generally english languages will be displayed before it can be clicked on
        uiDriver.waitToBeDisplayed(genericVideoPage.captionsEnglishLink);
        genericVideoPage.clickEnglishLanuageOption();

        // Check that captions match eventually
        genericVideoPage.waitForVideoSizeToShrink();
        genericVideoPage
                .waitForCaptionText("In our next example we are going to look at sketching a graph by plotting points.");

        // Let's scrub to the end of the video
        // +5y because I want to click on an area 5 below the time scrubber's top left point
        logInstruction("Scrub to end of video");
        myRobot.mouseMove(
                genericVideoPage.fullSizeButton.getWebElement().getLocation().x,
                genericVideoPage.progressBarSlider.getWebElement().getLocation().y + 5);
        myRobot.mouseClick();
        // wait 30 seconds for video to scrub and finish
        myRobot.waitForVideoToFinishPlaying();
      
        // Now that the video has replayed, I want to test captions again.
        genericVideoPage.clickToolbarPlay();
      
        genericVideoPage.clickCCButton();
        // generally english languages will be displayed before it can be clicked on
        
        uiDriver.waitToBeDisplayed(genericVideoPage.captionsEnglishLink);
        genericVideoPage.clickEnglishLanuageOption();

        // Clicking on a point on the timeline
        // +5 of the scrubber allows you to drag on the timeline
        // +60 means 60 right of the scrubber
        logInstruction("Scrub the video to around 20 seconds");
        WebElement weProgressBarSlider = genericVideoPage.progressBarSlider.getWebElement();
        myRobot.mouseMove(weProgressBarSlider.getLocation().x + 60, weProgressBarSlider
                .getLocation().y + 5);
    myRobot.mouseClick();

        // Check that captions match eventually
        genericVideoPage.waitForVideoSizeToShrink();
        genericVideoPage
                .waitForCaptionText("And so to do so we are going to set up different columns and we are going to take column 1 and we are just going to take some common values for x.");

        // Log that the two captions video has been verified
        logInstruction("Go to " + httpPrefix + "/assets/j8ezH8hmSeJpnfi_KYqowb5NZ15WItsc");
        uiDriver.get(httpPrefix + "/assets/j8ezH8hmSeJpnfi_KYqowb5NZ15WItsc");
        myRobot.calibrateAndEnterFrame(myRobot);
        // generally (not always) the caption button will load last, so we can expect the wrapper to
        // have loaded when the caption button loads
        uiDriver.waitToBeDisplayed(genericVideoPage.captionButton, 10000);
        genericVideoPage.clickToolbarPlay();
        genericVideoPage.clickCCButton();

        // Check that captions match eventually
        genericVideoPage.waitForVideoSizeToShrink();
        genericVideoPage.waitForCaptionText("Make sure you select an appropriate weight for each");
    }

    /**
     * Tests that the video can scrub Will scrub to the halfway point and verify that the video
     * timebar changes within a given amount of time MP-195
     */
    //@Test(groups = {"playState", "scrubbing", "regression", "acceptance"})
    private void scrubbing() {
        logInstruction("Go to " + httpPrefix + "/assets/tH6IgtX_wW1isZkRq5sYgKcVTIqMJOXl");
        uiDriver.get(httpPrefix + "/assets/tH6IgtX_wW1isZkRq5sYgKcVTIqMJOXl");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);

        // generally (not always) the caption button will load last, so we can expect the wrapper to
        // have loaded when the caption button loads
        logInstruction("Wait for video to fully load");
        uiDriver.waitToBeDisplayed(genericVideoPage.captionButton, 15000);
        genericVideoPage.clickToolbarPlay();
        //myRobot.verifyVideoIsPlaying();

        WebElement weProgressBarSlider = genericVideoPage.progressBarSlider.getWebElement();

        // Clicking on a point on the timeline
        // +5 of the scrubber allows you to drag on the timeline
        // +300 means 300 right of the scrubber
        myRobot.mouseMove(weProgressBarSlider.getLocation().x + 300, weProgressBarSlider
                .getLocation().y + 5);
        myRobot.mouseClick();

        // Move to a specific point
        uiDriver.sleep(1000);// wait 1 second for scrubber to actually move

        // Get some color on the video to verify video image changed. The pixel color should be
        // white
        // The point 530 right and 137 down from the iframe's top left corner should be yellow
        Point pointToVerify = new Point(530, 137);
        myRobot.mouseMove(pointToVerify.x, pointToVerify.y);
        Color expectedColorOfPixel = new Color(185, 180, 160);// some shade of white
        uiDriver.sleep(100000);
        myRobot.isPixelColorCorrect(pointToVerify, expectedColorOfPixel);
    }

    /**
     * Video continues to play when toggling to full screen Just checks that the video timecode
     * keeps ticking when you enter full screen mode Then switches back to normal screen and
     * verifies video is still playing MP-373
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    //@Test(groups = {"playState", "videoContinuesInFullScreen", "acceptance", "regression"})
    private void videoContinuesInFullScreen() throws IOException, InterruptedException {
        uiDriver.get(httpPrefix + "/assets/_copyright.Copyright-Replaced/tH6IgtX_wW1isZkRq5sYgKcVTIqMJOXl");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);

        uiDriver.waitToBeDisplayed(genericVideoPage.captionButton, 15000);

        genericVideoPage.clickToolbarPlay();
        genericVideoPage.fullSizeButton.click();
        // TODO verify that video does enter full screen. Size of elements will increase

        // text displays indicating that video has gotten past 9 seconds
        uiDriver.waitForTextToDisplay("00:1", 25000);

        // TODO verify that the full screen popup disappears.
        // TODO create a profile in firefox and have a setting for the popup so it never appears
        /*
         * If the full screen alert shows up (as it does on firefox), then click the accept full
         * screen dialog Tab two times to get to "allow" button, sleeping half a second between each
         * key, then hit enter to accept This button is to hard to find and click with a mouse so
         * keyboard is more reliable
         */
        if (uiDriver.getWebDriver().toString().contains("firefox")) {
            actions.sendKeys(Keys.TAB).build().perform();
            uiDriver.sleep(500);
            actions.sendKeys(Keys.TAB).build().perform();
            uiDriver.sleep(500);
            actions.sendKeys(Keys.ENTER).build().perform();
            uiDriver.sleep(500);
        }
        uiDriver.sleep(5000);
        myRobot.verifyVideoIsPlaying();

        // exit full screen
        actions.sendKeys(Keys.ESCAPE).build().perform();

        // Verify that the video image changes over 5 seconds
        myRobot.verifyVideoIsPlaying();
    }

    /**
     * Tests whether the volume of the video will change. Only using html5. Java does not have a way
     * to redirect speaker output unless you Have a hardware or software loop (eg. linein cable) so
     * sound verification is... very difficult with it Tested on Chrome. So far so good. Works on
     * FF. Volume was never broken in the past. MP-197
     */
    //@Test(groups = {"volume", "regression", "acceptance"})
    private void volume() {
        uiDriver.get(httpPrefix + "/assets/tH6IgtX_wW1isZkRq5sYgKcVTIqMJOXl");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);
        uiDriver.sleep(10000);
        // sometimes captions button does not show up quickly.
        uiDriver.waitToBeDisplayed(genericVideoPage.captionButton, 15000);
        genericVideoPage.clickToolbarPlay();
        uiDriver.sleep(5000);
        // Note I've seen the video not play after the button was "clicked"
       // myRobot.verifyVideoIsPlaying();

        // Test that upon video load the video sound level is where it should be at. Mute button
        // value is right
        // I think the volume level changes when you unmute a video(46) from when you loaded it(47)
        Assert.assertTrue(
                genericVideoPage.getMuteButtonTitle().equalsIgnoreCase("turn mute on"),
                "Volume did not load correctly on video load.");
        Assert.assertEquals(
                genericVideoPage.getLoadedVolumeSliderWidth(),
                47,
                "Volume did not load correctly on video load.");

        // Test that muting causes volume slider to go to far left
        // And that the value of the mute button element changes to "off"
        genericVideoPage.clickMuteButton();

        uiDriver.sleep(500);// Sleep for slider to complete moving before proceeding with
                            // verification
        Assert.assertTrue(
                genericVideoPage.getMuteButtonTitle().equalsIgnoreCase("turn mute off"),
                "Volume did not load correctly on mute.");
        Assert.assertEquals(
                genericVideoPage.getLoadedVolumeSliderWidth(),
                0,
                "Volume did not load correctly on mute.");

        // And then that unmuting causes the slider to go back.
        // And the mute button element value changes to "on"
        genericVideoPage.clickMuteButton();
        uiDriver.sleep(500);// Sleep for slider to complete moving before proceeding with
                            // verification
        Assert.assertTrue(
                genericVideoPage.getMuteButtonTitle().equalsIgnoreCase("turn mute on"),
                "Volume did not load correctly on unmute.");
        // The width of the volume slider depends on browser but falls in a range between 40 and 50
        // (usu 46)
        Assert.assertTrue((genericVideoPage.getLoadedVolumeSliderWidth() > 40 && genericVideoPage
                .getLoadedVolumeSliderWidth() < 50), "Volume did not load correctly on unmute.");

        // Then test that dragging the volume slider elsewhere causes the volume slider to actually
        // move
        myRobot.dragElementHorizontal(
                genericVideoPage.volumeSlider.getWebElement().getLocation(),
                15);
        Assert.assertTrue(
                genericVideoPage.getMuteButtonTitle().equalsIgnoreCase("turn mute on"),
                "Volume did not load correctly on drag.");
        Assert.assertEquals(
                genericVideoPage.getLoadedVolumeSliderWidth(),
                46,
                "Volume did not load correctly on drag.");
    }

    /**
     * Generic test that any video will play It should be run with a parameter specifying what video
     * to test. Not an optional parameter Then it should verify that said video's timecode changes
     * And TODO that its video image changes over time Important to note that some videos do not
     * have any video(audio only) And others do not change their video image or don't change it
     * much(test videos with only one image) TODO talk on Friday if we need this on demand type
     * testing. Lots of coding effort small communication effort STUB Do not Delete.
     */
    /*
     * @Test(groups={"regression"}) private void genericPlayTest(){ }
     */

    /**
     * @Author Charlie G This is not part of the regression suite. At one point there was a critical
     *         user facing defect where the player stopped displaying any elements
     *         http://jira.pearsoncmg.com/jira/browse/MP-1143 This job is here to make sure that the
     *         player is always playable, and if it is not, it sends a text to people
     */
    //@Test(groups = {"playerLoadsElements"})
    private void playerLoadsElements() {
        // If getting the page fails then selenium will throw an exception BUT the test will not
        // fail
        // We test a link up to 5 times. Try to get the page to not load. Then if it loads, we
        // If it does load 5 times then it fails and an email can get sent starting a text
        // If there are too many network issues on one machine, and you get too many texts,
        // you can start a build on another machine after this one if this one fails
        // and have only that one send texts
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        boolean isElementLoadedFlag = false;
        int countTries = 0;

        while (!isElementLoadedFlag && countTries < 5) {
            uiDriver.get(httpPrefix + "/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
            logInstruction("Navigate to page" + httpPrefix + "/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
            myRobot.calibrateAndEnterFrame(myRobot);// TODO meh, this line doesn't need to run every
                                                    // time

            try {
                // using webdriver.findelement works
                // uiDriver.getWebDriver().findElement(By.cssSelector(".btn.chapters.img-menu"));
                // Maybe the change I needed was to catch any exception
                uiDriver.waitToBePresent(genericVideoPage.chapterMenu, 20000);
                uiDriver.waitToBePresent(genericVideoPage.playButton, 2000);
                genericVideoPage.playButton.click();
                isElementLoadedFlag = myRobot.verifyVideoIsPlaying();
            } // TODO . Timeout exceptions are not caught here. Another type of exception is
              // obviously thrown catch (TimeoutException e) {
            catch (Exception e) {
                System.out.println(e.toString());
                logInstruction("Element failed to load in time: " + e.toString());
            }

            if (isElementLoadedFlag) {
                break;
            }

            countTries++;
        }

        // If video failed to load 5 times, then fail test
        if (!isElementLoadedFlag) {
            Assert.assertTrue(
                    false,
                    "Video failed to load required elements in player 5 times in a row");
        }
    }
}