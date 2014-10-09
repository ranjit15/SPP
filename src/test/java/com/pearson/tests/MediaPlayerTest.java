package com.pearson.tests;

import static org.testng.Assert.assertNotNull;

import java.awt.AWTException;
import java.io.File;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.pearson.MediaPlayerHelper.MediaPlayerRobot;
import com.pearson.test.eselenium.framework.BasicTestObject;
import com.pearson.test.eselenium.framework.core.UIDriver;
import com.pearson.test.eselenium.framework.drivers.DefaultUIDriver;
import com.pearson.test.tools.ProcessTool;

/**
 * Base test class for Media Player
 *
 */
public class MediaPlayerTest extends BasicTestObject {

    protected static final Logger logger = Logger.getLogger(MediaPlayerTest.class);
    protected static UIDriver uiDriver;
    protected Actions actions;
    protected MediaPlayerRobot myRobot;
    protected String httpPrefix;
    protected WebDriverWait wait;
    /* These are used for calibrating the robot.
    I do not want these to be static because I hear that's not a good practice
    Specifies file, and how far from the left Offset you want to use for calibration
    */
    protected String robotCalibrationFile;
    protected int robotCalibrationLeftOffset;

    /*
     * Enum dev,prod,cert,staging, none_specified (just to initialize environment if not specified)
     * Making this wasn't actually necessary but enums are never necessary
     */
    protected enum Environment {
        dev, prod, cert, staging, archive, ppe, none_specified
    };

    // Run this before any tests to make sure another test didn't leave these processes running.
    // Also run it after the tests to make sure no driver processes are left behind.
    @BeforeSuite(alwaysRun = true)
    @AfterSuite(alwaysRun = true)
    public void cleanupDrivers() {
        ProcessTool.killProcess("iedriver");
        ProcessTool.killProcess("chromedriver");
    }

    /**
     * @param webdriver.path.bin 
     * @throws AWTException
     * @throws UnknownHostException
     */
    @BeforeClass(alwaysRun = true)
    @Parameters({"environment"})
    public void setupClassTest(@Optional("none_specified") String environment)
            throws AWTException, UnknownHostException {
        logger.error("environment is " + environment);

        String browser = config.getValue("browser");
        System.out.println("browser is " + browser);
        // If test is running newest version of FF
        if (browser.equals("firefox")) {
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("layout.css.devPixelsPerPx", "1");
            // Will disable flash I think...
            // profile.setPreference("plugin.state.flash",1);
            WebDriver driver = new FirefoxDriver(new FirefoxBinary(new File(
                    "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe")), profile);
            uiDriver = new DefaultUIDriver(driver);
            robotCalibrationFile = "src/main/resources/FirefoxRobotCalibration.html";
            robotCalibrationLeftOffset = 0;
        } else
            if (browser.equals("firefox_old")) {
                config.setValue("browser", "firefox");
                uiDriver = new DefaultUIDriver();
                robotCalibrationFile = "src/main/resources/FirefoxRobotCalibration.html";
                robotCalibrationLeftOffset = 0;
            } else // start chrome. Note everything is failing on the newest version of Chrome.
            {
            	
                uiDriver = new DefaultUIDriver();
                robotCalibrationFile = "src/main/resources/ChromeRobotCalibration.html";
                robotCalibrationLeftOffset = 200;
            }

        assertNotNull(uiDriver);
        // maximize. helps drag and drop
        uiDriver.manage().window().maximize();
        // Note. In retrospect all the mouse movement could have been implemented by actions. Except
        // screenshots
        actions = new Actions(uiDriver.getWebDriver());
        if (environment.equalsIgnoreCase(Environment.prod.toString())) {
            httpPrefix = "http://mediaplayer.pearsoncmg.com";
        } else
            // This env the same as prod after Badger
            if (environment.equalsIgnoreCase(Environment.staging.toString())) {
                httpPrefix = "http://mediaplayer-test.pearsoncmg.com";
            } else
                if (environment.equalsIgnoreCase(Environment.dev.toString())) {
                    httpPrefix = "http://mediaplayer-dev.pearsoncmg.com";
                } else
                    if (environment.equalsIgnoreCase(Environment.archive.toString())) {
                        httpPrefix = "http://player.media.archive.pearsoncmg.com";
                    } else
                        // Note ppe is not up. Environment being set up
                        if (environment.equalsIgnoreCase(Environment.ppe.toString())) {
                            httpPrefix = "http://mediaplayer-ppe.pearsoncmg.com";
                        }// if environment.equalsIgnoreCase("cert") or environment not set
                        else {
                            //httpPrefix = "http://player.media.cert.pearsoncmg.com";
                        	httpPrefix = "http://mediaplayer.pearsoncmg.com";
                        }
        logger.info("http Prefix is " + httpPrefix);
        myRobot = new MediaPlayerRobot(uiDriver, robotCalibrationFile, robotCalibrationLeftOffset);
        wait = new WebDriverWait(uiDriver, 10); // standard waits are up to 10 seconds long
    }

    // TODO uncomment the quit
    @AfterClass(alwaysRun = true)
    public static void afterClassTest() {
        uiDriver.quit();
    }

    /**
     * Move the cursor to a predefined location so it doesn't affect the next test
     */
    @AfterMethod
    private void sleepAfterTest() {
        myRobot.mouseMove(2000, 0);
        sleep(1 * 1000);
    }
}