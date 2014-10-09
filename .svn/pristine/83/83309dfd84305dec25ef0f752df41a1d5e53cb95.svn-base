/**
 * 
 */
package com.pearson.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;

/**
 * @author Alex Su
 * @date Aug 6, 2013
 */

public class AudioOnlyIT extends MediaPlayerTest {

    // MP-432 Captions and More Info Button for Audio Only Player
	//Verify that captions work on an audio track and the title does not get cut off
    @Test(groups = {"AudioOnlyIT", "CaptionAndMoreInfo", "regression", "acceptance"})
    public void CaptionAndMoreInfo() {
        String videoName = "/assets/c9_kf45lUjHsK6IOcb9tYdXovi0ztseN";
        int numberOfTries = 40;

        logInstruction("Going to " + httpPrefix + videoName);
        uiDriver.get(httpPrefix + videoName);
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);

        logInstruction("Play the audio");
        genericVideoPage.playAudio.click();

        // complaint if video is not playing
        String firstTime = leftPlayCounterFetcher(genericVideoPage);
        while (firstTime.equals(leftPlayCounterFetcher(genericVideoPage))) {
            if (numberOfTries <= 20)
                Assert.assertTrue(false, "User ran our of patience, the video failed to start");
            numberOfTries--;
            if (genericVideoPage.playAudio.isDisplayed())
                genericVideoPage.playAudio.click();
            uiDriver.sleep(5000);
        }

        logInstruction("Click on more info button");
        genericVideoPage.moreInfoButton.click();
        uiDriver.waitToBeDisplayed(genericVideoPage.moreInfoTextArea);
        if (genericVideoPage.moreInfoTextArea.getText().equals(""))
            Assert.assertTrue(false, "More info text feild is empty, possibly broken");
        logInstruction("More info: " + genericVideoPage.moreInfoTextArea.getText());

        // Close more info button to resume playing
        logInstruction("Close more info button");
        genericVideoPage.moreInfoButton.click();

        logInstruction("Click on enable caption button");
        genericVideoPage.captionButton.click();
        logInstruction("Wait until caption starts showing");
        uiDriver.waitToBeDisplayed(genericVideoPage.captionArea);
        while (genericVideoPage.captionArea.getText().equals("")) {
            if (numberOfTries <= 0)
                Assert.assertTrue(false, "User ran out of patience, caption did not appear");
            numberOfTries--;
            uiDriver.sleep(1000);
        }
        logInstruction("Caption: " + genericVideoPage.captionArea.getText());

        // Start to examine UI visibility
        myRobot.mouseMove(
                genericVideoPage.pauseAudio.getLocation().x + 20,
                genericVideoPage.pauseAudio.getLocation().y + 20);
        myRobot.mouseClick();
        uiDriver.sleep(30000); 
        // Examine if video successfully paused
        firstTime = genericVideoPage.leftPlayCounter.getText();
        uiDriver.sleep(1000);
        if (!firstTime.equals(genericVideoPage.leftPlayCounter.getText()))
            Assert.assertTrue(false, "Mouse click failed to stop the video, the UI may be hidden");

        logInstruction("Success! All tests passed!");
    }

    // helper method for fetching left play counter time code
    // It extracts 5 characters from the right of the alt text, supposedly time code xx:xx
    private String leftPlayCounterFetcher(GenericVideoPage gvp) {
        String timeCode = gvp.leftPlayCounter.getText();
        return timeCode.substring(timeCode.length() - 5, timeCode.length());
    }
}
