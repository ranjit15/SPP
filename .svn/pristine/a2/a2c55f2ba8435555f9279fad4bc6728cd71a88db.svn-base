package com.pearson.tests.Accessibility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;
import com.pearson.tests.MediaPlayerTest;

/**
 * 
 * @author prasanna.b
 * 
 *         Test Description :
 * 
 *       Using tab can get to the elements on the player in the following order: 
i) Chapter Menu - 1
ii) More Info - 2
iii) Chapters - 3
iv) Time scrubber - 4
v) Play - 5
vi) captions toggle - 6
vii) caption options - 7
viii) volume toggle - 8
ix) volume scrubber - 9
x) Vol down - 10
xi) Vol up - 11
xii) normal screen - 12 
xiii) large screen - 13
xiv) full screen - 14 
xv) and return off the player

 * 
 */

public class Test_TabOrder_Mediaplayer extends MediaPlayerTest {

    public static final int TAB_PRESS_RELEASE_ITERATIONS = 6;
    static GenericVideoPage genericVideoPage;

    @Test(groups = {"testTabOrderMediaPlayer", "regression"})
    public void testTabOrderMediaPlayer() throws InterruptedException, AWTException {

        genericVideoPage = new GenericVideoPage(uiDriver);
        genericVideoPage
                .checkingElementsInVideoUrl(
                        "http://player.media.dev.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC",
                        "ChapteringMenu",
                        "QA Test - Sample Player Chapters");
        uiDriver.sleep(30000);

        Robot robot = new Robot();

        logger.info("Test123");

        tabPressRelease(robot, 300, TAB_PRESS_RELEASE_ITERATIONS);

        /* Check if Menu Item info is Highlighted*/
        WebElement element = uiDriver.findElement(By.cssSelector(".btn.minfo.highlight"));

        logger.info("IS the element Enabled " + element.isEnabled());

        logger.info("IS the element Dispyaed " + element.isDisplayed());

        /* Check if CHAPTERING MENU  is Highlighted and Chapters are displayed*/
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_TAB);

        WebElement chapters = uiDriver.findElement(By
                .cssSelector(".btn.chapters.img-menu.active.highlight"));
        logger.info("IS the element chapters Enabled " + chapters.isEnabled());

        /*Iterate over th Chapters in the Media Player*/

        tabPressRelease(robot, 300, 11);

        /* CHeck if the Play button is highlighted*/
        String playbutton = uiDriver.findElement(By.id("playBtn")).getAttribute("class");

        logger.info(playbutton);
        logger.info("IS the element playbutton highlighted " + playbutton
                .equals("ss-icon off boxable highlight"));

        /* Check if the Progress Bar is Highlighted*/
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_TAB);

        WebElement progressbarhandle = uiDriver.findElement(By
                .cssSelector(".progressBarHandle.boxable.ss-icon.ui-draggable.highlight"));
        logger.info("Is the element progressbar Enabled " + progressbarhandle.isDisplayed());

        /* Chekc if the Mute Button is Highlighted*/
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_TAB);

        String mutebutton = uiDriver.findElement(By.id("muteBtn")).getAttribute("class");
        logger.info("Is the element mutebutton Enabled " + mutebutton
                .equals("ss-icon off boxable highlight"));

        /* Check if the Volume Slider is Highlighted*/
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_TAB);

        WebElement redbarhandle = uiDriver.findElement(By
                .cssSelector(".redBarHandle.boxable.ss-icon.ui-draggable.highlight"));
        logger.info("Is the element redbarhandle Displayed " + redbarhandle.isDisplayed());

        /* Check if the Caption button is Highlighted*/

        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_TAB);

        String captionbutton = uiDriver.findElement(By.id("captionBtn")).getAttribute("class");
        logger.info("Then value of Caption button is  " + captionbutton
                .equals("caption off boxable highlight"));

        /* Check if the full screen is Highlighted*/
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_TAB);

        String fs = uiDriver.findElement(By.id("fs")).getAttribute("class");
        logger.info("Then value of fs button is  " + fs.equals("ss-icon boxable off highlight"));

    }

    private static void tabPressRelease(Robot robot, int sleep, int iterations)
            throws InterruptedException {
        for (int i = 0; i < iterations; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            Thread.sleep(300);
            robot.keyRelease(KeyEvent.VK_TAB);
        }
    }

}
