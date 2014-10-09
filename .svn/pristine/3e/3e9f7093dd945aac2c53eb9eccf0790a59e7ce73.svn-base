package com.pearson.tests.Accessibility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;
import com.pearson.tests.MediaPlayerTest;

/**
 * 
 * @author prasanna.b 
 * 
 * Test Description : 
 *         
 *         a)Selecting the volume button through
 *         tabbing and pressing enter mutes the video when volume is on
 * 
 *         b)Selecting the volume button through tabbing and pressing enter
 *         returns the volume to preselected level when volume is muted.
 *         
 *         c)Selecting the volume slider through tabbing and pressing right
 *         arrow increases volume 
 *         
 *         d)Selecting the volume slider through tabbing
 *         and pressing left arrow decreases volume
 * 
 */

public class TestPearson_VolumeMute extends MediaPlayerTest {

    public static final int TAB_PRESS_RELEASE_ITERATIONS = 18;
    static GenericVideoPage genericVideoPage;

    @Test(groups = {"testVolumeMute", "regression"})
    public void testVolumeMute() throws InterruptedException, AWTException {

        uiDriver.get("http://player.media.dev.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
        Thread.sleep(40000);

        uiDriver.switchTo().frame(0);
        Robot robot = new Robot();

        logger.info("Test123");

        tabPressRelease(robot, 300, TAB_PRESS_RELEASE_ITERATIONS);

        /* Get the value of Default value of VolumeButton before going to mute */

        String getVolumeValuebeforeMute = uiDriver.findElement(By.className("redBarHandle"))
                .getAttribute("aria-valuenow");

        logger.info(getVolumeValuebeforeMute);

        /* Press Enter to Go to Mute */

        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(800);
        robot.keyRelease(KeyEvent.VK_ENTER);

        /* Get the Value value of VolumeButton now */

        String mute_button = uiDriver.findElement(By.id("muteBtn")).getAttribute("aria-valuenow");

        logger.info("Mute Button value is " + mute_button);

        /* Check whether the value is "Mute Off" */
        Assert.assertEquals(mute_button, null, "Test value of Mute Button");

        /* Press Enter to check the Volume slider moves to Preselected Value */
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(800);
        robot.keyRelease(KeyEvent.VK_ENTER);

        String getVolumeValuebeafterPressinEnter = uiDriver.findElement(
                By.className("redBarHandle")).getAttribute("aria-valuenow");

        Assert.assertEquals(
                getVolumeValuebeafterPressinEnter,
                getVolumeValuebeforeMute,
                "Tests Whether Volume level are same");

        int current_volume_value = Integer.parseInt(getVolumeValuebeafterPressinEnter);

        logger.info("The current Volume value is " + current_volume_value);

        /*
         * Pass the current Volume level to the following method to check the
         * following tests
         * 
         * a)Selecting the volume slider through tabbing and pressing right
         * arrow increases volume b)Selecting the volume slider through tabbing
         * and pressing left arrow decreases volume.
         */
        checkIncreaseVol_PressingRightandLeft(robot, current_volume_value);
    }

    private static void tabPressRelease(Robot robot, int sleep, int iterations)
            throws InterruptedException {
        for (int i = 0; i < iterations; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            Thread.sleep(300);
            robot.keyRelease(KeyEvent.VK_TAB);
        }
    }

    private static void checkIncreaseVol_PressingRightandLeft(Robot robot, int volume_value)
            throws InterruptedException {

        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_TAB);

        robot.keyPress(KeyEvent.VK_RIGHT);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_RIGHT);

        /* Get the value of the Volume Slider AFter Pressing Right */
        int volume_value_afterscrollingrightarrow = Integer.parseInt(uiDriver.findElement(
                By.className("redBarHandle")).getAttribute("aria-valuenow"));

        /*
         * On Pressing right arrow once, Volume increases by value of '10',
         * Calculate the difference between the current volume value and value
         * obtained after pressing Right arrow
         */

        int diff_Bef_After_pres_right = volume_value_afterscrollingrightarrow - volume_value;

        logger.info("The value diff_Bef_After_pres_right " + " " + diff_Bef_After_pres_right);

        /* Validate that the increased Volumevalue is 10 */
        Assert.assertEquals(
                diff_Bef_After_pres_right,
                10,
                "Tests Whether Volume level have increased");

        /*
         * Above logic applies to Left arrow, the difference in decreased volume
         * on pressing Left Arrow once is 10 Here we press LEFT Arrow twice ,
         * First press LEFT Arrow is to returns the the slider to Default
         * position Second press LEFT Arrow is to decrease the slider once from
         * the default position
         */
        robot.keyPress(KeyEvent.VK_LEFT);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_LEFT);

        robot.keyPress(KeyEvent.VK_LEFT);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_LEFT);

        int getVolumeValuebeafterPressinLeft = Integer.parseInt(uiDriver.findElement(
                By.className("redBarHandle")).getAttribute("aria-valuenow"));

        int diff_Bef_After_pres_left = volume_value - getVolumeValuebeafterPressinLeft;

        logger.info("The value diff_Bef_After_pres_left " + " " + diff_Bef_After_pres_left);

        Assert.assertEquals(
                diff_Bef_After_pres_left,
                10,
                "Tests Whether Volume level have decreased");
    }

}
