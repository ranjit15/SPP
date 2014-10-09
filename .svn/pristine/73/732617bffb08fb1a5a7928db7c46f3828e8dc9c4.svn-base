/**
 * 
 */
package com.pearson.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;

/**
 * @author Alex Su
 * @date Jun 27, 2013
 * @author Charlie
 * As of Aug 15,2013 ish, the hover buttons no longer seem to be detectable on Win8 Chrome, Firefox23, or Firefox17.
 * Even if they appear to users on FF17, selenium does not detect them. 
 * No changes were made to the code, just be sure to only run the tests on environments that work.
 */

public class HoverOverButtonIT extends MediaPlayerTest {

    boolean mover = true; // do not edit
    int numberOfTries = 20;

    // MP-371, selecting hover pause button will pause the video
    // MP-379, selecting hover replay button will replay the video
    // MP-370, Rolling over the player in play state will make the hover pause button appear
    @Test(groups = {"HoverOverButtonIT", "hoverPausedAndReplayButton", "regression", "acceptance"})
    public void hoverPausedAndReplayButton() {

        try {
            String videoName = "/assets/SCIENCE_CONCEPT_CHECK";

            logInstruction("Going to " + httpPrefix + videoName);
            uiDriver.get(httpPrefix + videoName);
            GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
            myRobot.calibrateAndEnterFrame(myRobot);

            mover = true;
            MouseMover mm = new MouseMover();
            mm.start();
            uiDriver.sleep(15000);
            uiDriver.waitToBeDisplayed(genericVideoPage.hoverPlayButton, 60000);

            if (!(genericVideoPage.hoverPlayButton.isDisplayed() && genericVideoPage.pausedState
                    .isDisplayed()))
                Assert.assertTrue(
                        false,
                        "Warning! Cannot find hover play button after the video loads");

            genericVideoPage.playButton.click();
            uiDriver.sleep(5000);

            while (!genericVideoPage.verifyVideoIsPlaying()) {
                logInstruction("Waiting for the video to load properly ... remaining tries: " + numberOfTries);
                if (genericVideoPage.playButton.isDisplayed())
                    genericVideoPage.playButton.click();
                numberOfTries--;
                if (numberOfTries <= 0)
                    Assert.assertTrue(false, "User ran out of patience");
                // wait 5 seconds before we try to click it again
                uiDriver.sleep(5000);
            }

            logInstruction("Video is playing ... examine existance of hover pause button");
            if (!(genericVideoPage.hoverPlayButton.isDisplayed() && genericVideoPage.playingState
                    .isDisplayed()))
                Assert.assertTrue(
                        false,
                        "Warning! Cannot find hover pause button when video is playing");
            logInstruction("Found hover pause button, clicking on button");
            myRobot.mouseClick();

            uiDriver.sleep(2000);

            logInstruction("Assumed video is paused, examine existance of hover play button");
            if (!(genericVideoPage.hoverPlayButton.isDisplayed() && genericVideoPage.pausedState
                    .isDisplayed()))
                Assert.assertTrue(
                        false,
                        "Warning! Cannot find hover play button when video is paused");

            logInstruction("Resume playing");
            myRobot.mouseClick();

            uiDriver.sleep(2000);

            // stop the mouse mover
            mover = false;
            logInstruction("Disabled mover and scrub to the end of the video");
            myRobot.mouseMove(
                    genericVideoPage.progressBar.getWebElement().getLocation().x + genericVideoPage.progressBar
                            .getWidth() - 20,
                    genericVideoPage.progressBar.getWebElement().getLocation().y + 6);

            myRobot.mouseClick();
            logInstruction("Done scrubbing, re-enable mover");
            mover = true;
            mm = new MouseMover();
            mm.start();

            logInstruction("Examine the existance of play button to make sure the video ends");
            uiDriver.waitToBeDisplayed(genericVideoPage.playButton, 60000);

            logInstruction("Video is finished, examine existance of hover replay button");
            if (!(genericVideoPage.hoverPlayButton.isDisplayed() && genericVideoPage.playButton
                    .isDisplayed()))
                Assert.assertTrue(
                        false,
                        "Warning! Cannot find hover replay button when video is finished");

            logInstruction("Found hover replay button, replaying video now");
            myRobot.mouseClick();

            uiDriver.sleep(5000);
            logInstruction("Examine if the hover replay button works");

            while (!genericVideoPage.verifyVideoIsPlaying()) {
                numberOfTries--;
                if (numberOfTries <= 0)
                    Assert.assertTrue(
                            false,
                            "User ran out of patience, video failed to replay after clicking hover replay button");
                // 5 seconds idle before we attempt next try
                uiDriver.sleep(5000);
            }

            logInstruction("Success! All tests passed");
        } finally {
            // kill the thread
            mover = false;
        }

    }

    // This mouse mover is a separate thread that will run simultaneously with the test to ensure
    // hover button will always be visible
    // do not modify unless necessary
    private class MouseMover extends Thread {

        public void run() {
            while (mover) {
                //myRobot.mouseMove(60, 60);
            	myRobot.mouseMove(84, 71);
                myRobot.mouseMove(50, 50);
            }

        }
    }

    // helper method for fetching left play counter time code
    // It extracts 5 characters from the right of the alt text, supposedly time code xx:xx
    private String leftPlayCounterFetcher(GenericVideoPage gvp) {
        String timeCode = gvp.leftPlayCounter.getText();
        return timeCode.substring(timeCode.length() - 5, timeCode.length());
    }

    // MP-368, On hover over, time tool tip shows displaying any time on the progress bar
    //@Test(groups = {"HoverOverButtonIT", "hoverTimeToolTip", "regression", "acceptance"})
    public void hoverTimeToolTip() {
        int numberOfTries = 10;
        String testLink = httpPrefix + "/assets/MATH_CONCEPT_CHECK";
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        logInstruction("Connecting to " + testLink);
        uiDriver.get(testLink);
        myRobot.calibrateAndEnterFrame(myRobot);

        logInstruction("Attempt to play the video");
        genericVideoPage.playButton.click();
        while (!genericVideoPage.verifyVideoIsPlaying()) {
            logInstruction("Examining video is in fact playing ... remainging tries: " + numberOfTries);
            if (genericVideoPage.playButton.isDisplayed())
                genericVideoPage.playButton.click();
            numberOfTries--;
            uiDriver.sleep(5000);
            if (numberOfTries <= 0)
                Assert.assertTrue(
                        false,
                        "User ran out of patience, the video failed to start playing");
        }
        logInstruction("Video is playing");

        logInstruction("Pause the video before attempting time tool tip verification");

        genericVideoPage.pauseButton.click();
        while (genericVideoPage.verifyVideoIsPlaying()) {
            logInstruction("Examining video is paused properly ... remaining tries: " + numberOfTries);
            if (genericVideoPage.pauseButton.isDisplayed())
                genericVideoPage.pauseButton.click();
            numberOfTries--;
            uiDriver.sleep(5000);
            if (numberOfTries <= 0)
                Assert.assertTrue(false, "User ran out of patience, the video failed to pause");
        }
        logInstruction("Video is paused");

        // Environment is set, now try to verify time tool tip
        // Random numbers to make robot to click on random area in scrubber
        for (int i = 200; i <= 400; i += 50) {
            myRobot.mouseMove(
                    genericVideoPage.progressBar.getWebElement().getLocation().x + i,
                    genericVideoPage.progressBar.getWebElement().getLocation().y + 6);
            String timeToolTip = genericVideoPage.timeTooltip.getText();
            myRobot.mouseClick();
            uiDriver.sleep(3000);
            // validating if time in time tool tip matches left play counter
            if (!(leftPlayCounterFetcher(genericVideoPage).equals(timeToolTip)))
                Assert.assertTrue(false, "Time tool tip and left time counter do not match");
        }

        logInstruction("Success! All tests passed!");

    }

    // MP-994, Replay button should always work for all answer choices
    // MP-1029, Concept check video does not go into an endless loop
    //@Test(groups = {"HoverOverButtonIT", "replayButtonAlwaysWork", "regression", "acceptance"})
    public void replayButtonAlwaysWork() {
        int numberOfTries = 10;
        String testLink = httpPrefix + "/assets/MATH_CONCEPT_CHECK";
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        logInstruction("Connecting to " + testLink);
        uiDriver.get(testLink);
        myRobot.calibrateAndEnterFrame(myRobot);

        logInstruction("Attempting to play the video");
        genericVideoPage.playButton.click();

        while (!genericVideoPage.verifyVideoIsPlaying()) {
            logInstruction("Examining video is in fact playing ... remainging tries: " + numberOfTries);
            if (genericVideoPage.playButton.isDisplayed())
                genericVideoPage.playButton.click();
            numberOfTries--;
            uiDriver.sleep(5000);
            if (numberOfTries <= 0)
                Assert.assertTrue(
                        false,
                        "User ran out of patience, the video failed to start playing");
        }

        logInstruction("Video is playing");

        videoTimer(genericVideoPage, genericVideoPage.conceptCheckRadioButton1, "1", 42000);
        videoTimer(genericVideoPage, genericVideoPage.conceptCheckRadioButton2, "2", 33000);
        videoTimer(genericVideoPage, genericVideoPage.conceptCheckRadioButton3, "3", 55000);

        logInstruction("Success! All tests passed!");

    }

    // helper method for replayButtonAlwaysWork()
    private void videoTimer(
            GenericVideoPage genericVideoPage,
            WebElement button,
            String answerNumber,
            int answerTime) {
        logInstruction("Wait until the video reaches concept check point");
        uiDriver.sleep(30000);
        button.click();
        logInstruction("Testing answer " + answerNumber);
        genericVideoPage.conceptCheckSubmit.click();
        logInstruction("Submitting answer " + answerNumber);
        // This is the time each answer takes to reach the end, answer dependent
        uiDriver.sleep(answerTime);
        videoReplayer(genericVideoPage);
    }

    // helper method for replayButtonAlwaysWork()
    private void videoReplayer(GenericVideoPage genericVideoPage) {
        try {

            numberOfTries = 30;
            while (genericVideoPage.verifyVideoIsPlaying() || !leftPlayCounterFetcher(
                    genericVideoPage).equals("00:00")) {
                numberOfTries--;
                if (numberOfTries <= 0) {
                    Assert.assertTrue(
                            false,
                            "User ran out of patience, the video failed to finish playing");
                }
                logInstruction("Waiting for video to finish playing, remainging tries: " + numberOfTries);
            }
            logInstruction("The video has stopped playing");

            // recalibrate mouse
            uiDriver.switchTo().defaultContent();
            myRobot.calibrateAndEnterFrame(myRobot);

            mover = true;
            MouseMover mm = new MouseMover();
            mm.start();

            logInstruction("Click on hover replay button");
            genericVideoPage.hoverPlayButton.click();

            // disable mover
            mover = false;

            numberOfTries = 20;
            while (!genericVideoPage.verifyVideoIsPlaying() && numberOfTries > 0) {
                numberOfTries--;
                if (numberOfTries <= 0) {
                    Assert.assertTrue(
                            false,
                            "The video has failed to replay after clicking hover replay button");
                }
                logInstruction("Examining if video is playing, remainging tries: " + numberOfTries);
            }

            logInstruction("Video replayed successfully");
        } finally {
            mover = false;
        }
    }

    /*
     * mp-382 Title of video displayed after video ends
     */
    //@Test(groups = {"TitleAppears", "chaptering", "regression"})
    private void TitleAppears() {
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        uiDriver.get(httpPrefix + "/assets/tH6IgtX_wW1isZkRq5sYgKcVTIqMJOXl");
        myRobot.calibrateAndEnterFrame(myRobot);
        uiDriver.sleep(20000);
        uiDriver.waitToBeDisplayed(genericVideoPage.videoTitle);
        uiDriver.sleep(20000);
        logger.info(genericVideoPage.playPauseButton.getAttribute("title"));
        genericVideoPage.playPauseButton.click();
        uiDriver.sleep(20000);
        myRobot.mouseMove(615, 431);// moves to 4:13
        myRobot.mouseClick();
        uiDriver.sleep(20000);
        String ActualResult = genericVideoPage.videoTitle.getText();
        String ExpectedResult = "QA Test - Two Captions";
        Assert.assertEquals(ExpectedResult, ActualResult);
        logInstruction(" The title of the video is diplayed when the video ends ");
    }
}
