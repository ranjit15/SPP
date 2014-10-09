/**
 * 
 */
package com.pearson.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;

/**
 * @author Alex Su
 * @date Aug 5, 2013
 */

public class LongVideoIT extends MediaPlayerTest {

    // MP-1024, If a video is longer than 1 hour, the timecode should still show the hour sign
    @Test(groups = {"LongVideoIT", "hourTimeCodeCheck", "regression", "acceptance"})
    public void hourTimeCodeCheck() {
        int numberOfTries = 20;

        String videoName = "/assets/JBQ0rsHwHNxlOyuTcBjclWBUQyZol5B9";

        logInstruction("Going to " + httpPrefix + videoName);
        uiDriver.get(httpPrefix + videoName);

        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);

        // play the video to ensure everything is loaded
        genericVideoPage.playButton.click();
        uiDriver.sleep(5000);
        while (!genericVideoPage.verifyVideoIsPlaying() && numberOfTries > 0) {
            logInstruction("Waiting for the video to load properly ... remaining tries: " + numberOfTries);
            if (genericVideoPage.playButton.isDisplayed())
                genericVideoPage.playButton.click();
            numberOfTries--;
            if (numberOfTries <= 0)
                Assert.assertTrue(false, "User ran out of patience");
            uiDriver.sleep(5000);
        }

        logInstruction("Making sure the video is playing");
        uiDriver.waitToBeDisplayed(genericVideoPage.pauseButton, 10000);

        logInstruction("Video is playing ... Fetching leftPlayCounter");

        logInstruction("Validating left time code ... ");
        if (!hourTimeValidator(leftPlayCounterFetcher(genericVideoPage)))
            Assert.assertTrue(
                    false,
                    "Left time code is wrong, left time code reports " + leftPlayCounterFetcher(genericVideoPage));

        logInstruction("Validating right time code ... ");
        if (!hourTimeValidator(rightPlayCounterFetcher(genericVideoPage)))
            Assert.assertTrue(
                    false,
                    "Right time code is wrong, left time code reports " + rightPlayCounterFetcher(genericVideoPage));

        logInstruction("Success! All tests passed!");
    }

    // helper method for fetching left play counter time code including hours
    // It extracts 8 characters from the right of the alt text, supposedly time code dd:dd:dd
    private String leftPlayCounterFetcher(GenericVideoPage gvp) {
        String timeCode = gvp.leftPlayCounter.getText();
        return timeCode.substring(timeCode.length() - 8, timeCode.length());
    }

    // helper method for fetching right play counter time code including hours
    // It extracts 8 characters from the right of the alt text, supposedly time code dd:dd:dd
    private String rightPlayCounterFetcher(GenericVideoPage gvp) {
        String timeCode = gvp.rightPlayCounter.getText();
        return timeCode.substring(timeCode.length() - 8, timeCode.length());
    }

    // regex validator for dd:dd:dd
    private boolean hourTimeValidator(String target) {
        return target.matches("\\d{2}:\\d{2}:\\d{2}");
    }
}
