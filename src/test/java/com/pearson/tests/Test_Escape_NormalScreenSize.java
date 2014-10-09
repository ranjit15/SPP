package com.pearson.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.apache.log4j.Logger;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.pearson.MediaPlayerHelper.MediaPlayerRobot;
import com.pearson.smartmediaplayer.GenericVideoPage;
import com.pearson.test.eselenium.framework.BasicTestObject;
import com.pearson.test.eselenium.framework.core.UIDriver;
import com.pearson.test.eselenium.framework.drivers.DefaultUIDriver;

/* @Author : Prasanna Balakrishnan
 * Test Description : Selecting the ESC when the player is in
 *  full screen will return the screen to the normal size
 * 
 */

public class Test_Escape_NormalScreenSize extends MediaPlayerTest {

	@Test
	public void testPlayButton_tabbing() throws AWTException {

		GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
		genericVideoPage
				.checkingElementsInVideoUrl(
						"http://player.media.dev.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC",
						"ChapteringMenu", "QA Test - Sample Player Chapters");

		uiDriver.sleep(2000, "Currently Sleeping for 2 seconds");

		

		uiDriver.sleep(5000);
		
		/*  Get the class name of the Full Screen Size button before Enlarging*/

		String intialstate = genericVideoPage.fullSizeButton
				.getAttribute("class");

		logger.info("before clicking " + intialstate);

		genericVideoPage.fullSizeButton.click();

		uiDriver.sleep(6000);

		
		/*  Get the class name of the Full Screen
		 *  Size button After Enlarging*/

		
		String classname = genericVideoPage.fullSizeButton
				.getAttribute("class");

		logger.info(" the value class is  " + classname);

		Robot robot = new Robot();

		uiDriver.sleep(20000);

		
		/*  Press Escape to return to Normal Size and check the value of Full Screensize*/
		
		
		robot.keyPress(KeyEvent.VK_ESCAPE);
		uiDriver.sleep(50);
		robot.keyRelease(KeyEvent.VK_ESCAPE);

		String classname_afterminimizing = genericVideoPage.fullSizeButton
				.getAttribute("class");

		logger.info(" the value class after minimizing  is  "
				+ classname_afterminimizing);
		Assert.assertEquals(classname, "ss-icon boxable highlight on",
				"Test Whether the screen has returned to normal size");
	}
}
