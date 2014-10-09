package com.pearson.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
/**
 * @author prasanna.b
 * 
 * Pressing Shift + Tab will stat with
 *  full screen, large screen, 
 *  normal screen, volume scrubber, volume toggle, captions options (if multiple caption files exist), caption toggle (if captions exist), play, time scrubber, and off the player
 * 
 */
public class Test_ShifTabOrder {
	
	public static final int TAB_PRESS_RELEASE_ITERATIONS = 5;
	static FirefoxDriver driver = new FirefoxDriver();

	public static void main(String args[]) throws InterruptedException, AWTException{
	
		/* Load the page*/
		
		driver.get("http://player.media.dev.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
			Thread.sleep(65000);
	
			driver.switchTo().frame(0);
			Robot robot = new Robot();
	
			/* TAb to the last Element*/
	
			tabPressRelease(robot, 300, TAB_PRESS_RELEASE_ITERATIONS);
	
			System.out.println("Test123");
			
			/* Check the element of ful screen*/
			String fs = driver.findElement(By
					.id("fs")).getAttribute("class");
			System.out.println("Then value of fs button is  "
					+ fs.equals("ss-icon boxable off highlight"));
			
			shiftTabPress(robot);
	 
			/* Check the element of Caption button*/
			
			String captionbutton = driver.findElement(By
					.id("captionBtn")).getAttribute("class");
			System.out.println("Then value of Caption button is  "
					+ captionbutton.equals("caption off boxable highlight"));
			
			
			shiftTabPress(robot);
			
			/* Check the element of Volume Slider*/
			
			WebElement redbarhandle = driver.findElement(By
					.cssSelector(".redBarHandle.boxable.ss-icon.ui-draggable.highlight"));
			System.out.println("Is the element redbarhandle Displayed "
					+ redbarhandle.isDisplayed());
			
			shiftTabPress(robot);
			
			/* Check the element of Mute Button*/
			
			String mutebutton = driver.findElement(By
					.id("muteBtn")).getAttribute("class");
			System.out.println("Is the element mutebutton Enabled "
					+ mutebutton.equals("ss-icon off boxable highlight"));
			
			shiftTabPress(robot);
			
			/* Check the element of Progress Bar Handle*/
			
			WebElement progressbarhandle = driver.findElement(By
					.cssSelector(".progressBarHandle.boxable.ss-icon.ui-draggable.highlight"));
			System.out.println("Is the element progressbar Enabled "
					+ progressbarhandle.isDisplayed());
			
			shiftTabPress(robot);
			
			/* Chekc the element of Play button*/
			
			String playbutton = driver.findElement(By.id("playBtn")).getAttribute(
					"class");

			System.out.println(playbutton);
			System.out.println("IS the element playbutton highlighted "
					+ playbutton.equals("ss-icon off boxable highlight"));
			
			tabPressRelease(robot, 300, 11);
			
			/* Check the element of Chapters present or not*/
			
			WebElement chapters = driver.findElement(By
					.cssSelector(".btn.chapters.img-menu.active.highlight"));
			System.out.println("IS the element chapters Enabled "
					+ chapters.isEnabled());
			
			shiftTabPress(robot);
			
			/* Check the elemen of the Menu info Button*/
			WebElement element = driver.findElement(By
					.cssSelector(".btn.minfo.highlight"));

			System.out.println("IS the element Enabled " + element.isEnabled());

			System.out.println("IS the element Dispyaed " + element.isDisplayed());
			
			
			
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
		 /* Method to simulate the SHIFT + TAB key stroke from Keyboard*/
		 
		 private static void shiftTabPress(Robot robot) throws InterruptedException {
			 robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_TAB);
				Thread.sleep(300);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}
}
