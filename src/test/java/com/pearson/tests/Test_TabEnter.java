package com.pearson.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

/**
 * 
 * @author prasanna.b
 * Test Description :
 * 
 * All the Buttons in Media Player should work after pressing Enter
 *
 */
public class Test_TabEnter {

    public static final int TAB_PRESS_RELEASE_ITERATIONS = 6;
    static FirefoxDriver driver = new FirefoxDriver();

    public static void main(String args[]) throws InterruptedException, AWTException {

        driver.get("http://player.media.dev.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
        Thread.sleep(70000);

        driver.switchTo().frame(0);
        Robot robot = new Robot();

        System.out.println("Test123");

        tabPressRelease(robot, 300, TAB_PRESS_RELEASE_ITERATIONS);

        /* Check if the Menu Info item is displayed*/

        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_ENTER);

        WebElement menu_info = driver.findElement(By.cssSelector(".tpPlayer_minfo_item"));

        System.out.println("Is the Menu info Displayed " + menu_info.isDisplayed());

        /* Check if the Chapter item is displayed*/

        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_TAB);

        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_ENTER);

        WebElement chapter_display = driver.findElement(By
                .cssSelector(".tpPlayer_chaptering_menu_overlay"));

        System.out.println("Is Chapters Displayed " + chapter_display.isDisplayed());

        /* Check if the Video is playing by entering the CHAPTER items*/

        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_TAB);

        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_ENTER);

        String chapter_video = driver.findElement(By

        .id("counter")).getText();

        Thread.sleep(10000);

        System.out.println("Is the Menu info Displayed " + chapter_video.equalsIgnoreCase("00:00"));

        /* Check if the Video is paused by Pressing ENter when the video
         * is playing*/

        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_ENTER);

        String PKey_pausebutton = driver.findElement(By

        .id("playBtn")).getAttribute("title");

        System.out.println("The Value of the Play button is  " + PKey_pausebutton
                .equals("Play Video"));

        Assert.assertEquals(
                PKey_pausebutton,
                "Play Video",
                "Test Whether Video is paused by Pressing  ENTER Key");

        /* Check if the Video is Played by Pressing ENter when the video
         * is Pause state*/

        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_ENTER);

        String PKey_playbutton = driver.findElement(By

        .id("playBtn")).getAttribute("title");

        System.out.println("The Value of the Play button is  " + PKey_playbutton
                .equals("Pause Video"));

        Thread.sleep(2000);

        Assert.assertEquals(
                PKey_playbutton,
                "Pause Video",
                "Test Whether Video is played by Pressing 'P' Key");

        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_TAB);

        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_TAB);

        /* Press Enter to Go to Mute */

        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(800);
        robot.keyRelease(KeyEvent.VK_ENTER);

        /* Get the Value value of VolumeButton now */

        String mute_button = driver.findElement(By.id("muteBtn")).getAttribute("value");

        System.out.println(mute_button);

        /* Check whether the value is "Mute Off" */
        Assert.assertTrue(
                mute_button.equalsIgnoreCase("turn mute off"),
                "Test value of Mute Button");

        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(800);
        robot.keyRelease(KeyEvent.VK_ENTER);

        String mute_button_new = driver.findElement(By.id("muteBtn")).getAttribute("value");

        System.out.println(mute_button_new);

        Assert.assertTrue(
                mute_button_new.equalsIgnoreCase("turn mute on"),
                "Test value of Mute Button");

        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(3000);
        robot.keyRelease(KeyEvent.VK_TAB);

        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(3000);
        robot.keyRelease(KeyEvent.VK_TAB);

        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_TAB);

        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        robot.keyRelease(KeyEvent.VK_ENTER);

        /* Check if the FUll Screen is enabled on pressing the ENTER button*/

        String fs = driver.findElement(By.id("fs")).getAttribute("class");
        System.out.println("Then value of fs button is  " + fs + "  " + fs
                .equals("ss-icon boxable on highlight"));

        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        robot.keyRelease(KeyEvent.VK_ENTER);

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
