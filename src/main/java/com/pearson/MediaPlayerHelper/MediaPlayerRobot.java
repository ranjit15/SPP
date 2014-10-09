package com.pearson.MediaPlayerHelper;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;
import com.pearson.test.eselenium.framework.core.UIDriver;
import com.pearson.test.eselenium.framework.core.UIElement;

/**
 * Results may be be dependent on your browser You must be in full screen. (tests will move you to
 * full screen) Also your version of selenium will determine how you get the coordinates of an
 * element You must be in 100% zoom (firefox profile is 100% zoom by default so don't zoom in
 * manually) You should avoid clicking on a specific pixel on your screen and instead use relative
 * positions so that tests will run on all screen resolution If you do want to go to a specific
 * pixel, eg. dragElementHorizontal, using myRobot you will either need to subtract static Point
 * offset from the point you want to click Or you will have to use myRobot.robot.mouseMove
 */

public class MediaPlayerRobot {

    // TODO private robot and get robot method
    public Robot robot;
    private Point offset;
    private Point browserToolbarOffset;
    private static final Logger logger = Logger.getLogger(MediaPlayerRobot.class);
    private GenericVideoPage genericVideoPage;
    private WebDriverWait wait;
    private UIDriver uiDriver;
    private String robotCalibrationFile;
    private int robotCalibrationLeftOffset;

    /**
     * @param robotCalibrationOffset TODO
     * @param mediaPlayerCalibrationHTML TODO
     * @param driver
     * @throws AWTException 
     */
    /*I get an error when I extend BasicPageObject and use this. I think there's a bug in ZapProxyListener
     * So the only way to get tests to work is to use the below constructor instead
     * TODO figure out if it is a bug and fix in the future
     */
    /*public MediaPlayerRobot(UIDriver uiDriver) throws AWTException {
        super(uiDriver);
        robot = new Robot();
        offset = calibrate();
        // Please DO NOT set delay too high because this will break hover over automation tests
        robot.setAutoDelay(100); // wait 500 ms after each action
        genericVideoPage = new GenericVideoPage(uiDriver);
        // standard waits are up to 10 seconds long
        wait = new WebDriverWait(uiDriver, 10, 1000);
    }*/

    public MediaPlayerRobot(
            UIDriver uiDriver,
            String robotCalibrationFile,
            int robotCalibrationLeftOffset) throws AWTException {
        robot = new Robot();
        this.uiDriver = uiDriver;
        this.robotCalibrationFile = robotCalibrationFile;
        this.robotCalibrationLeftOffset = robotCalibrationLeftOffset;
        offset = calibrate();
        // Please DO NOT set delay too high because this will break hover over automation tests
        robot.setAutoDelay(100); // wait 500 ms after each action
        genericVideoPage = new GenericVideoPage(uiDriver);
        // standard waits are up to 10 seconds long
        wait = new WebDriverWait(uiDriver, 10, 1000);
    }

    public Point getOffset() {
        return offset;
    }

    /**
     * Checks if a number is in a range inclusive
     * 
     * @param n
     *            , the number to test
     * @param low
     *            , the low range that you will accept
     * @param high
     *            , the high number to check against
     * @return boolean, true if in range
     */
    public static boolean inRange(int n, int low, int high) {
        return n >= low && n <= high;
    }

    /**
     * @return
     */
    public Point calibrate() {
        uiDriver.get(Paths.get(robotCalibrationFile).toUri().toString());
        browserToolbarOffset = RobotCalibration.calibrate(
                uiDriver.getWebDriver(),
                robotCalibrationFile,
                robotCalibrationLeftOffset);

        System.out
                .println("Browser Toolbar Left offset: " + browserToolbarOffset.x + ", top offset: " + browserToolbarOffset.y);
        return browserToolbarOffset;
    }

    /**
     * Clicks and drags an element a specified distance in the X direction given The element's
     * original position As well as how far to drag in the x direction Only drags by 5 pixels at a
     * time
     * 
     * @param elementLocation
     *            just the location of the element as the robot would see it
     * @param distance
     *            the distance you want to drag the element in the X direction. positive multiples
     *            of 5 only
     */
    public void dragElementHorizontal(Point elementLocation, int distance) {
        robot.mouseMove(elementLocation.x + offset.x, elementLocation.y + offset.y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        for (int i = 0; i < distance; i = i + 5) {
            robot.mouseMove(elementLocation.x + offset.x + i, elementLocation.y + 147);
        }
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    /**
     * Given a location and a color, verify that the pixel at the location has a specific color
     * 
     * @param location
     *            location of pixel known by robot
     * @param expectedColor
     *            color of pixel expected
     */
    public boolean isPixelColorCorrect(final Point location, final Color expectedColor) {
        // used to see the pixel I am checking
        robot.mouseMove(location.x + offset.x, location.y + offset.y);
        // Wait until color changes to the expected color
        return wait.until(new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver webDriver) {
                Color colorOfPoint = robot.getPixelColor(
                        location.x + offset.x,
                        location.y + offset.y);
                logger.info("The actual color of the point is: " + colorOfPoint.getRed() + " red " + colorOfPoint
                        .getGreen() + " green " + colorOfPoint.getBlue() + " blue.");
                return inRange(colorOfPoint.getRed(), expectedColor.getRed() - 10, expectedColor
                        .getRed() + 10) && inRange(colorOfPoint.getGreen(), expectedColor
                        .getGreen() - 10, expectedColor.getGreen() + 10) && inRange(colorOfPoint
                        .getBlue(), expectedColor.getBlue() - 10, expectedColor.getBlue() + 10);
            }
        });
    }

    /**
     * Moves the mouse with the browserOffsetGiven Note: If trying to move to the progressBarSlider,
     * pass a larger value in the x
     * 
     * @param location
     *            just the location of the point to click
     */
    public void mouseMove(int x, int y) {
        robot.mouseMove(x + offset.x, y + offset.y);
    }

    /**
     * Simple left click at point given below
     * 
     * @param x
     * @param y
     */
    public void mouseClick() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    /**
     * Will enter video frame and figure out where to move mouse given frame It is preferable if
     * users only calibrate if they enter the video frame
     * 
     */
    public void calibrateAndEnterFrame(MediaPlayerRobot myRobot) {
        myRobot.robot.mouseMove(
                genericVideoPage.iFrame.getLocation().x + browserToolbarOffset.x,
                genericVideoPage.iFrame.getLocation().y + browserToolbarOffset.y);

        offset = new Point(
                genericVideoPage.iFrame.getLocation().x + browserToolbarOffset.x,
                genericVideoPage.iFrame.getLocation().y + browserToolbarOffset.y);
        System.out
                .println("Video Frame Left offset from top left of browser window: " + offset.x + ", top offset: " + offset.y);
        genericVideoPage.enterFrame();
    }

    /**
     * Given a driver and a genericVideoPage Capture a screenshot of the video image Must first
     * enter the frame Saves images to screenshot folder Known bug: Takes a screen of the wrong
     * image area in full screen
     * 
     * @param genericVideoPage
     *            used for figuring out the area of the video Image to take a picture of
     * @return the name of the returned file with the relative path(screenshots folder)
     */
    public String captureVideoImageScreen() {
        String fileName = null;

        // Specify area to capture
        Dimension videoImageSize = new Dimension(
                genericVideoPage.videoImageArea.getWidth(),
                genericVideoPage.videoImageArea.getHeight());
        Rectangle videoImageArea = new Rectangle(
                genericVideoPage.videoImageArea.getLocation().x + offset.x,
                genericVideoPage.videoImageArea.getLocation().y + offset.y,
                videoImageSize.width,
                videoImageSize.height);

        // capture area and save descriptively
        try {
            File fileLocation = new File("tmp");
            if (!fileLocation.exists())
                fileLocation.mkdir();
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMM_HH_mm_ss");
            fileName = "tmp\\screenshot_" + Thread.currentThread().getStackTrace()[1]
                    .getMethodName() + "_" + sdf.format(cal.getTime()) + ".png";
            BufferedImage bfIimage = robot.createScreenCapture(videoImageArea);
            ImageIO.write(bfIimage, "png", new File(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Screenshot has been saved in " + fileName);
        return fileName;
    }

    /**
     * Compares and verify that two images are the same
     * 
     * @param firstImage
     *            directory of first image
     * @param secondImage
     *            directory of second image
     * @return Are the images the same?
     */
    @Test
    public boolean compareImages(String firstImage, String secondImage) {
        // Verify two images are the same
        boolean matchFlag = false;
        File fileInput = new File(firstImage);
        File fileOutPut = new File(secondImage);

        BufferedImage bufileInput;
        try {
            bufileInput = ImageIO.read(fileInput);
            DataBuffer dafileInput = bufileInput.getData().getDataBuffer();
            int sizefileInput = dafileInput.getSize();
            BufferedImage bufileOutPut = ImageIO.read(fileOutPut);
            DataBuffer dafileOutPut = bufileOutPut.getData().getDataBuffer();
            int sizefileOutPut = dafileOutPut.getSize();
            matchFlag = true;
            if (sizefileInput == sizefileOutPut) {
                for (int j = 0; j < sizefileInput; j++) {
                    if (dafileInput.getElem(j) != dafileOutPut.getElem(j)) {
                        matchFlag = false;
                        break;
                    }
                }
            } else {
                matchFlag = false;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return matchFlag;
    }

    /**
     * Verifies with time code and images that video is playing To be used after clicking the play
     * button or otherwise causing video to resume playback
     */
    public boolean verifyVideoIsPlaying() {
        final String currentTime = genericVideoPage.leftPlayCounter.getText();
        // Verify that the video image changes over 5 seconds
        final String firstScreenShot = captureVideoImageScreen();
        // Wait for video to resume playing
        boolean isPlaying = wait.until(new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver webDriver) {

                String secondScreenShot = captureVideoImageScreen();

                String currentTimeCode = genericVideoPage.leftPlayCounter.getWebElement().getText();
                if (currentTimeCode.equalsIgnoreCase("00:00")) {
                    System.out
                            .println("Waiting for video to start playing: " + currentTimeCode + " .");
                } else {
                    System.out
                            .println("Waiting for video to resume playback: " + currentTimeCode + " .");
                }

                return (!currentTimeCode.equalsIgnoreCase(currentTime) && !compareImages(
                        firstScreenShot,
                        secondScreenShot));
            }
        });
        return isPlaying;
    }

    /**
     * Verifies that the video has finished playing in the next 30 seconds To be used after an
     * action that makes you think the video will finish
     */
    public void waitForVideoToFinishPlaying() {
        boolean isFinishedPlaying = false;
        int counter = 0;// 5 seconds * 6 = 30 seconds max
        String firstScreenShot = captureVideoImageScreen();
        do {
            uiDriver.sleep(5000);
            String secondScreenShot = captureVideoImageScreen();
            System.out.println("Waiting for video to finish playback ...");
            // toolbar play has changed to pause and video images are the same
            isFinishedPlaying = uiDriver.findElement(By.id("playBtn")).getAttribute("value")
                    .equalsIgnoreCase("Play Video") && compareImages(
                    firstScreenShot,
                    secondScreenShot);
            counter++;
            firstScreenShot = secondScreenShot;
        } while (!isFinishedPlaying && counter < 6);
    }

    /**
     * This method will keep tabbing until a given uielement is selected or It has tabbed 40 times
     * and not hilighted the desired element If it has not found the hilighted element after 40
     * tabs, then it throws a hissy fit
     * It does not work on all elements and should therefore not be used.
     */
    public void tabUntilUIElementIsSelected(UIElement uiElement) {
        int counter = 0;
        // TODO figure out more elegant implementation than flag (ask online)
        boolean isHilighted = false;
        robot.setAutoDelay(250);// I can't wait more than 250 ms for each tab
        while (counter < 40) {
            logger.info("Tabbing to desired element" + uiElement.toString() + ".");
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            // logger.error(uiElement.getAttribute("class"));// never changes to hilight
            // logger.error(uiElement.getClass());
            if (uiElement.getAttribute("class").contains("highlight")) {
                isHilighted = true;
                break;
            }

            counter++;
        }

        // Checking that the element was tabbed to
        if (!isHilighted) {
            Assert.fail("You were not able to tab to the " + uiElement.toString() + " element.");
        } else {
            logger.info("You were able to tab to the " + uiElement.toString() + " element.");
        }
    }
}