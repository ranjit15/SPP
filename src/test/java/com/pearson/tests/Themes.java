package com.pearson.tests;

/**
 * Meant to test the different themes in the player
 */

import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;

public class Themes extends MediaPlayerTest {

    /**
     * Verifies the copyright theme changes as expected Note, it'd be a good idea for dev to introduce an id to the captions.. meh.
     * Not on Jira.
     */
    @Test(groups = {"themes", "copyrightTheme", "acceptance", "regression"})
    private void copyrightTheme() {
        uiDriver.get(httpPrefix + "/assets/_copyright.Copyright-Replaced/tH6IgtX_wW1isZkRq5sYgKcVTIqMJOXl");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);

        uiDriver.waitToBeDisplayed(genericVideoPage.copyRightText, 20000);

        genericVideoPage.verifyCopyrightText("Copyright-Replaced");
    }
}