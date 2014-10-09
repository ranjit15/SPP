package com.pearson.MediaPlayerHelper;

//http://stackoverflow.com/questions/14655581/selenium-and-html-window-location

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class RobotCalibration {

    /** Time for which to wait for the page response. */
    private static final long TIMEOUT = 1000;

    private final WebDriver driver;
    private final Robot r;

    private final Point browserCenter;
    private int leftX;
    private int rightX;
    private int midX;
    private int topY;
    private int bottomY;
    private int midY;
    private String robotCalibrationFile;
    private int robotCalibrationLeftOffset;

    /**
     * Calibrates the robot to the screen and the browser window.
     * robotCalibrationFile is the file to open for calibration.
     * robotCalibrationLeftOffset tells you how far from the left of the screen to start calibrating.
     *  Chrome has an issue with calibration at 0 offset since the back button is too far on the left.
     *  Pallavi has had issues with the calibrationn being 200 offset on Firefox
     *  Therefore we support calibration on FF and Chrome differently.
     **/
    public static Point calibrate(
            WebDriver driver,
            String robotCalibrationFile,
            int robotCalibrationLeftOffset) {
        return new RobotCalibration(driver).calibrate(
                robotCalibrationFile,
                robotCalibrationLeftOffset);
    }
    //Finds browser center
    private RobotCalibration(WebDriver driver) {
        this.driver = driver;
        try {
            driver.manage().window().getSize();
        } catch (UnsupportedOperationException headlessBrowserException) {
            throw new IllegalArgumentException(
                    "Calibrating a headless browser makes no sense.",
                    headlessBrowserException);
        }

        try {
            this.r = new Robot();
        } catch (AWTException headlessEnvironmentException) {
            throw new IllegalStateException(
                    "Robot won't work on headless environments.",
                    headlessEnvironmentException);
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        org.openqa.selenium.Dimension browserSize = driver.manage().window().getSize();
//        System.err.println("browserSize " + browserSize);
        org.openqa.selenium.Point browserPos = driver.manage().window().getPosition();
//        System.err.println("browserPos " + browserPos);

        // a maximized browser returns negative position
        // a maximized browser returns size larger than actual screen size
        // you can't click outside the screen
        leftX = Math.max(0, browserPos.x);
        rightX = Math.min(leftX + browserSize.width, screenSize.width - 1);
        midX = (leftX + rightX) / 2;

//        System.err.println("leftX = " + leftX + " rightX = " + rightX + " midX = " + midX);

        topY = Math.max(0, browserPos.y);
        bottomY = Math.min(topY + browserSize.height, screenSize.height - 1);
        midY = (topY + bottomY) / 2;

//        System.err.println("topY = " + topY + " bottomY = " + bottomY + " midY = " + midY);

        browserCenter = new Point(midX, midY);
    }

    private Point calibrate(String robotCalibrationFile, int robotCalibrationLeftOffset) {
        this.robotCalibrationFile = robotCalibrationFile;
        this.robotCalibrationLeftOffset = robotCalibrationLeftOffset;
        //Get the Robot Calibration File path
        driver.get(Paths.get(robotCalibrationFile).toUri().toString());

        // find left border
        while (leftX < rightX) {
            click(midX, midY);
//            System.err.println("Click (" + midX + "," + midY + ")");
            if (clickWasSuccessful()) {
                rightX = midX;
//                System.err.println("Success! rightX is now " + rightX);
            } else {
                rightX = midX + 1;
//                System.err.println("Fail! leftX is now " + leftX);
                // close any menu we could have opened
                click(browserCenter.x, browserCenter.y);
            }
            midX = (leftX + rightX) / 2;
//            System.err.println("midX is now " + midX + " after this iteration");
        }

        // find top border
        while (topY < bottomY) {
            // The back button is too close to the left side of chrome
            click(robotCalibrationLeftOffset, midY);
//            System.err.println("Click (" + 200 + "," + midY + ")");
            if (clickWasSuccessful()) {
//                System.err.println("Success! bottomY is now " + bottomY);
                bottomY = midY;
            } else {
                topY = midY + 1;
                // close any menu we could have opened
                click(browserCenter.x, browserCenter.y);
//                System.err.println("Fail! bottomY is now " + bottomY);
            }
            midY = (topY + bottomY) / 2;
//            System.err.println("midY is now " + midY + " after this iteration");
        }

        if (!isCalibrated()) {
            throw new IllegalStateException("Couldn't calibrate the Robot.");
        }
        return new Point(midX, midY);
    }

    /** clicks on the specified location */
    private void click(int x, int y) {
        r.mouseMove(x, y);
        r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        // for some reason, my IE8 can't properly register clicks that are close
        // to each other faster than click every half a second
        if (driver instanceof InternetExplorerDriver) {
            sleep(500);
        }
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
            // nothing to do
        }
    }

    private int counter = 0;

    /** @return whether the click on a page was successful */
    private boolean clickWasSuccessful() {
        counter++;

        long targetTime = System.currentTimeMillis() + TIMEOUT;
        while (System.currentTimeMillis() < targetTime) {
            int pageCounter = Integer.parseInt(driver.findElement(By.id("counter")).getAttribute(
                    "value"));
            if (counter == pageCounter) {
                return true;
            }
        }
        return false;
    }

    /** @return whether the top left corner has already been clicked at */
    private boolean isCalibrated() {
        long targetTime = System.currentTimeMillis() + TIMEOUT;
        while (System.currentTimeMillis() < targetTime) {
            if (driver.findElement(By.id("done")).getAttribute("value").equals("yep")) {
                return true;
            }
        }
        return false;
    }

}