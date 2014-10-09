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
import com.pearson.test.eselenium.framework.BasicTestObject;
import com.pearson.test.eselenium.framework.core.UIDriver;
import com.pearson.test.eselenium.framework.drivers.DefaultUIDriver;

import com.pearson.smartmediaplayer.GenericVideoPage;

/*@Author : Prasanna Balakrishnan
 * Test Description :
 * 
 *  a) Selecting the scrubber through tabbing and selecting right arrow will scroll the video forward
 *  b) Selecting the scrubber through tabbing and selecting left arrow will scroll the video backward 
 *     
 */


public class TestScrubber extends MediaPlayerTest {
  
	@Test
  public void testVideoScrubbing() throws AWTException {
	  
	  GenericVideoPage genericVideoPage =  new GenericVideoPage(uiDriver);
	  genericVideoPage
      .checkingElementsInVideoUrl(
              "http://player.media.cert.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC",
              "ChapteringMenu",
              "QA Test - Sample Player Chapters");

	 // genericVideoPage.clickMenuButton();
	  uiDriver.sleep(2000, "Currently Sleeping for 2 seconds" );
	  
	  /* Instatiate the Robot Class to simulate the Mouse Press events
	   *  
	   *  Move the TAB to Chapter 4 and move the video forward by selecting right arrow, 
	   *  Measure the time between point where the video started and then stopped.
	   *  
	   * 
	   * */

	  Robot robot = new Robot();
	  
	  
	  robot.keyPress(KeyEvent.VK_TAB);																												
      robot.keyRelease(KeyEvent.VK_TAB);

      
      
      robot.keyPress(KeyEvent.VK_TAB);
      robot.keyRelease(KeyEvent.VK_TAB);
      
      
      robot.keyPress(KeyEvent.VK_TAB);
      robot.keyRelease(KeyEvent.VK_TAB);
      
    
      
      robot.keyPress(KeyEvent.VK_TAB);
      robot.keyRelease(KeyEvent.VK_TAB);
      

      
      robot.keyPress(KeyEvent.VK_TAB);
      uiDriver.sleep(300);
      robot.keyRelease(KeyEvent.VK_TAB);
      
      robot.keyPress(KeyEvent.VK_TAB);
      uiDriver.sleep(300);
      robot.keyRelease(KeyEvent.VK_TAB);
      
      robot.keyPress(KeyEvent.VK_TAB);
      uiDriver.sleep(300);
      robot.keyRelease(KeyEvent.VK_TAB);
    
      robot.keyPress(KeyEvent.VK_TAB);
      uiDriver.sleep(300);
      robot.keyRelease(KeyEvent.VK_TAB);
      
      robot.keyPress(KeyEvent.VK_ENTER);
      uiDriver.sleep(300);
      robot.keyRelease(KeyEvent.VK_ENTER);
      
      /* Wait for the Video to get loaded*/
      // 20 sec is bit long time, since my internet is slow I have added   it more, should reduce it after testing it on
      //different machines
      uiDriver.sleep(20000);
      
      
      
      robot.keyPress(KeyEvent.VK_TAB);
      uiDriver.sleep(3000);
      robot.keyRelease(KeyEvent.VK_TAB);
      
      /*
       * Get the timings of the video from the current timeline and then convert it to seconds.
       */
      
       String text =  genericVideoPage.leftPlayCounter.getText();
       logger.info("the timing is  " +text);
       int time_in_sec_before_sliding = convertTimetoSeconds(text);
       logger.info(" the value in sec after "+time_in_sec_before_sliding);
      
       /*
        * Forward the video by preseeing the RIGHT Arrow of the Keybord
        * 
        */
      
      robot.keyPress(KeyEvent.VK_RIGHT);
      uiDriver.sleep(3000);
      robot.keyRelease(KeyEvent.VK_RIGHT);
      
      
      /*
       * Get the timings of the video from the current timeline after sliding
       *  and then convert it to seconds.
       */
      
      
      String val_after_sliding_right =  genericVideoPage.leftPlayCounter.getText();
    
      logger.info("the timing is  " +val_after_sliding_right);
      
      int time_in_sec_after_slidiing = convertTimetoSeconds(val_after_sliding_right);
      
      logger.info(" the value in sec after "+time_in_sec_after_slidiing);
      
      /*
       * Measure the Timings in seconds after sliding and beflore sliding.
       *  Timings should be greater than 100 seconds.
       */
      
      
      int time_diff_before_and_after_sliding  =  time_in_sec_after_slidiing - time_in_sec_before_sliding ;
      
      logger.info(" the diff in seconds before and after sliding is  "+time_diff_before_and_after_sliding);

      
      /* Make the driver sleep for 5 seconds, so that video moves to the appropirate location*/
      
      uiDriver.sleep(5000);
      
      /*
       *  select left arrow to scroll the video backward 
       */
      
      robot.keyPress(KeyEvent.VK_LEFT);
      uiDriver.sleep(2000);
      robot.keyRelease(KeyEvent.VK_LEFT);
      
      
      /*
       * Measure the Timings in seconds before sliding and after sliding.
       *  Timings should be greater than 100 seconds.
       */
      
      String value_after_sliding_bakcward =  genericVideoPage.leftPlayCounter.getText();
      logger.info("the timing is  " +value_after_sliding_bakcward);
      int time_in_sec_after_sliding_bckward = convertTimetoSeconds(value_after_sliding_bakcward);
      int tim_diff_slding_fwd_bckward = time_in_sec_after_slidiing - time_in_sec_after_sliding_bckward;
     
    		  
     
    		  boolean slider_value_right_arrow = false;
    		  
    /*Check for the slider values for pressing RIGHT ARROW*/		  
      
      if ( time_diff_before_and_after_sliding > 100 )
      {
    	  slider_value_right_arrow = true;
      }
     
      else {
    	  slider_value_right_arrow = false;
      }
      
      Assert.assertEquals(slider_value_right_arrow, true , "Slider Right Arrow Test");
      
      /*Check for the slider values for pressing LEFT ARROW*/		
      
 boolean slider_value_left_arrow = false;
      
      if ( tim_diff_slding_fwd_bckward > 100 )
      {
    	  slider_value_left_arrow = true;
      }
     
      else {
    	  slider_value_left_arrow = false;
      }
      
      Assert.assertEquals(slider_value_left_arrow, true , "Slider Left Arrow Test");
      
      
      
      
      
   
      
      logger.info("Test Passed");
	  
  }
  
	
	/* Conver the String Value of "TIme" into Seconds*/
  
  public int convertTimetoSeconds(String time){
	  String h=time;
	  String[] h1=h.split(":");

	
	  int minute=Integer.parseInt(h1[0]);
	  int second=Integer.parseInt(h1[1]);

	  		int temp;
	  		temp = second + (60 * minute) ;
	  		return temp;
  }
}
