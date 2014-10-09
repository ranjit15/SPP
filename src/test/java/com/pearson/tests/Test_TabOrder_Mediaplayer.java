package com.pearson.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

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

public class Test_TabOrder_Mediaplayer {

	public static final int TAB_PRESS_RELEASE_ITERATIONS = 6;
	static FirefoxDriver driver = new FirefoxDriver();

	public static void main(String args[]) throws InterruptedException,
			AWTException {

		driver.get("http://player.media.dev.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
		Thread.sleep(60000);

		driver.switchTo().frame(0);
		Robot robot = new Robot();

		System.out.println("Test123");

		tabPressRelease(robot, 300, TAB_PRESS_RELEASE_ITERATIONS);

		
		/* Check if Menu Item info is Highlighted*/
		WebElement element = driver.findElement(By
				.cssSelector(".btn.minfo.highlight"));

		System.out.println("IS the element Enabled " + element.isEnabled());

		System.out.println("IS the element Dispyaed " + element.isDisplayed());

		
		/* Check if CHAPTERING MENU  is Highlighted and Chapters are displayed*/
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(300);
		robot.keyRelease(KeyEvent.VK_TAB);

		WebElement chapters = driver.findElement(By
				.cssSelector(".btn.chapters.img-menu.active.highlight"));
		System.out.println("IS the element chapters Enabled "
				+ chapters.isEnabled());
		
		/*Iterate over th Chapters in the Media Player*/

		tabPressRelease(robot, 300, 11);

		
		/* CHeck if the Play button is highlighted*/
		String playbutton = driver.findElement(By.id("playBtn")).getAttribute(
				"class");

		System.out.println(playbutton);
		System.out.println("IS the element playbutton highlighted "
				+ playbutton.equals("ss-icon off boxable highlight"));

		/* Check if the Progress Bar is Highlighted*/
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(300);
		robot.keyRelease(KeyEvent.VK_TAB);

		WebElement progressbarhandle = driver
				.findElement(By
						.cssSelector(".progressBarHandle.boxable.ss-icon.ui-draggable.highlight"));
		System.out.println("Is the element progressbar Enabled "
				+ progressbarhandle.isDisplayed());

		
		/* Chekc if the Mute Button is Highlighted*/
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(300);
		robot.keyRelease(KeyEvent.VK_TAB);

		String mutebutton = driver.findElement(By.id("muteBtn")).getAttribute(
				"class");
		System.out.println("Is the element mutebutton Enabled "
				+ mutebutton.equals("ss-icon off boxable highlight"));

		
		/* Check if the Volume Slider is Highlighted*/
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(300);
		robot.keyRelease(KeyEvent.VK_TAB);

		WebElement redbarhandle = driver
				.findElement(By
						.cssSelector(".redBarHandle.boxable.ss-icon.ui-draggable.highlight"));
		System.out.println("Is the element redbarhandle Displayed "
				+ redbarhandle.isDisplayed());

		/* Check if the Caption button is Highlighted*/
		
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(300);
		robot.keyRelease(KeyEvent.VK_TAB);

		String captionbutton = driver.findElement(By.id("captionBtn"))
				.getAttribute("class");
		System.out.println("Then value of Caption button is  "
				+ captionbutton.equals("caption off boxable highlight"));

		
		/* Check if the full screen is Highlighted*/
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(300);
		robot.keyRelease(KeyEvent.VK_TAB);

		String fs = driver.findElement(By.id("fs")).getAttribute("class");
		System.out.println("Then value of fs button is  "
				+ fs.equals("ss-icon boxable off highlight"));

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
