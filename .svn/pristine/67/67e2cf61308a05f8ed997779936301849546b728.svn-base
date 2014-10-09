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
 * @author prasanna.b
 * 
 *         Pressing Shift + Tab will start with full screen, large screen, normal
 *         screen, volume scrubber, volume toggle, captions options (if multiple
 *         caption files exist), caption toggle (if captions exist), play, time
 *         scrubber, and off the player
 * 
 */
public class Test_ShifTabOrder extends MediaPlayerTest {

    public static final int TAB_PRESS_RELEASE_ITERATIONS = 5;
    // static FirefoxDriver driver = new FirefoxDriver();
    static GenericVideoPage genericVideoPage;

    @Test(groups = {"testShiftTabOrder", "regression"})
    public void testShiftTabOrder() throws InterruptedException, AWTException {

        /* Load the page */

        genericVideoPage = new GenericVideoPage(uiDriver);
        genericVideoPage
                .checkingElementsInVideoUrl(
                        "http://player.media.dev.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC",
                        "ChapteringMenu",
                        "QA Test - Sample Player Chapters");

        uiDriver.sleep(30000);

        Robot robot = new Robot();

        /* TAb to the last Element */

        tabPressRelease(robot, 300, TAB_PRESS_RELEASE_ITERATIONS);

        logger.info("Test123");

        /* Check the element of ful screen */
        String fs = uiDriver.findElement(By.id("fs")).getAttribute("class");
        logger.info("Then value of fs button is  " + fs.equals("ss-icon boxable off highlight"));

        shiftTabPress(robot);

        /* Check the element of Caption button */

        String captionbutton = uiDriver.findElement(By.id("captionBtn")).getAttribute("class");
        logger.info("Then value of Caption button is  " + captionbutton
                .equals("caption off boxable highlight"));

        shiftTabPress(robot);

        /* Check the element of Volume Slider */

        WebElement redbarhandle = uiDriver.findElement(By
                .cssSelector(".redBarHandle.boxable.ss-icon.ui-draggable.highlight"));
        logger.info("Is the element redbarhandle Displayed " + redbarhandle.isDisplayed());

        shiftTabPress(robot);

        /* Check the element of Mute Button */

        String mutebutton = uiDriver.findElement(By.id("muteBtn")).getAttribute("class");
        logger.info("Is the element mutebutton Enabled " + mutebutton
                .equals("ss-icon off boxable highlight"));

        shiftTabPress(robot);

        /* Check the element of Progress Bar Handle */

        WebElement progressbarhandle = uiDriver.findElement(By
                .cssSelector(".progressBarHandle.boxable.ss-icon.ui-draggable.highlight"));
        logger.info("Is the element progressbar Enabled " + progressbarhandle.isDisplayed());

        shiftTabPress(robot);

        /* Chekc the element of Play button */

        String playbutton = uiDriver.findElement(By.id("playBtn")).getAttribute("class");

        logger.info(playbutton);
        logger.info("IS the element playbutton highlighted " + playbutton
                .equals("ss-icon off boxable highlight"));

        tabPressRelease(robot, 300, 11);

        /* Check the element of Chapters present or not */

        WebElement chapters = uiDriver.findElement(By
                .cssSelector(".btn.chapters.img-menu.active.highlight"));
        logger.info("IS the element chapters Enabled " + chapters.isEnabled());

        shiftTabPress(robot);

        /* Check the element of the Menu info Button */
        WebElement element = uiDriver.findElement(By.cssSelector(".btn.minfo.highlight"));

        logger.info("IS the element Enabled " + element.isEnabled());

        logger.info("IS the element Dispyaed " + element.isDisplayed());

    }

    private static void tabPressRelease(Robot robot, int sleep, int iterations)
            throws InterruptedException {
        for (int i = 0; i < iterations; i++) {

            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_TAB);
            Thread.sleep(300);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        }

    }

    /* Method to simulate the SHIFT + TAB key stroke from Keyboard */

    private static void shiftTabPress(Robot robot) throws InterruptedException {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_SHIFT);
    }
}
