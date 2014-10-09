/**
 * 
 */
package com.pearson.tests;

import junit.framework.Assert;

import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;

/**
 * 
 * @author ranjit.brar
 * @date July 16, 2013
 * 
 *       Turning CC on will highlight the CC button MP -387
 */

public class ClosedCaptionOption extends MediaPlayerTest {

    String Expectedresult = "caption off boxable has-captions";
    String Actualresult;

    @Test(groups = {"VerifyHighlightedCCButton", "regression"})
    private void VerifyHighlightedCCButton() {
        GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
        uiDriver.get(httpPrefix + "/assets/tH6IgtX_wW1isZkRq5sYgKcVTIqMJOXl");
        uiDriver.sleep(20000);
        myRobot.calibrateAndEnterFrame(myRobot);
        uiDriver.sleep(20000);
        genericvideopage.playButton.click();
        uiDriver.sleep(20000);
        genericvideopage.clickCCButton();
        Actualresult = genericvideopage.captionButton.getAttribute("class");
        uiDriver.sleep(10000);
        Assert.assertEquals(Expectedresult, Actualresult);
        logInstruction(Actualresult);
    }

    /*
     * CC button should not be available if video does not have CC mp-1028
     */
    @Test(groups = {"VerifyCCButton", "Regression"})
    private void VerifyCCButton() {
        GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
        uiDriver.get("http://player.media.dev.pearsoncmg.com/assets/MATH_CONCEPT_CHECK");
        uiDriver.sleep(20000);
        logInstruction("Media Player Displayed");
        myRobot.calibrateAndEnterFrame(myRobot);
        Assert.assertFalse(genericvideopage.captionButton.isDisplayed());
        logInstruction("CC Button is not present");
    }

}
