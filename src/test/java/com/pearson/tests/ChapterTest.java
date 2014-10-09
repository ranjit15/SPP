package com.pearson.tests;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;
import com.pearson.test.tools.DataGenerator;


/**
 * @author ranjit.brar
 * @date 06/05/2013
 * Title verification - D
 * play/pause with tool tip - D  
 * Dragging video scrubber - D
 * mute on/mute off - D
 * Dragging volume scrubber - D
 * full screen /normal screen - D 
 * Hover Play button - 
 * Tool tips - 
 * Timeline displayed
 * Replay button - 
 * Chapter menu button and tool tip - D
 * Validating every chapter's title - D
 * Play directly chapters -
 * Play particular chapters through video scrubber -
 * Chapter navigation buttons and tool tips -
 * Verifies that 10 chapters appear in one menu without you having to scroll-
 */

public class ChapterTest extends MediaPlayerTest
{
	 /*
     * Verify the video title of Internal Concept Check video
     */
    //@Test(groups = {"TitleVerification", "regression"})
    public void TitleVerification() throws AWTException {
        uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);
        uiDriver.sleep(15000);
        myRobot.mouseMove(381, 279);
        String ActualTitle = genericVideoPage.videoTitle.getText();
        String ExpectedTitle = "QA Test - Sample Player Chapters";
        Assert.assertEquals(ActualTitle, ExpectedTitle);
    }
    /*
     * Verify the Play/Pause functionality and check tool tip as well
     */
    //@Test(groups = {"PlayPause","regression"})
    public void PlayPause() throws AWTException	{
    	uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
    	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
    	myRobot.calibrateAndEnterFrame(myRobot);
    	//uiDriver.sleep(2000);
    	myRobot.mouseMove(381, 279);
    	//Play the video
    	genericvideopage.playButton.click();
    	uiDriver.sleep(5000);
    	//Confirming whether video is playing
    	myRobot.mouseMove(381, 279);
    	String ActualTooltip = genericvideopage.checkToolTip("Pause Button");
    	String ExpectedTooltip = "Pause Video";
    	Assert.assertEquals(ActualTooltip, ExpectedTooltip);
    	myRobot.mouseMove(381, 279);
    	//Pause the video
    	genericvideopage.pauseButton.click();
    	uiDriver.sleep(3000);
    	//Confirming whether video is paused
    	myRobot.mouseMove(381, 279);
    	String ActualTooltip01 = genericvideopage.checkToolTip("Play Button");
    	String ExpectedTooltip01 = "Play Video";
    	Assert.assertEquals(ActualTooltip01, ExpectedTooltip01);
    }
    /*
     * Check user is able to drag video scrubber
     */
    //@Test(groups = {"DragVideoScrubber","regression"})
    public void DragVideoScrubber() throws AWTException	{
    	uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
    	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
    	myRobot.calibrateAndEnterFrame(myRobot);
    	//uiDriver.sleep(15000);
    	//Play the video
    	myRobot.mouseMove(381, 279);
    	genericvideopage.playButton.click();
    	uiDriver.sleep(5000);
    	myRobot.mouseMove(
                genericvideopage.fullSizeButton.getWebElement().getLocation().x,
                genericvideopage.progressBarSlider.getWebElement().getLocation().y + 5);
        myRobot.mouseClick();
        uiDriver.sleep(15000);
       }
    /*
     * Volume button on/off state functionality and verify tool tip in both the states
     */
    //@Test(groups = {"VolumeController","regression"})
    public void VolumeController() throws AWTException{
    	uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
    	//uiDriver.sleep(15000);
    	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
    	myRobot.calibrateAndEnterFrame(myRobot);
    	myRobot.mouseMove(381, 279);
    	genericvideopage.playButton.click();
    	//uiDriver.sleep(5000);
    	//Checking tool tip for volume button
    	String ActualTooltip = genericvideopage.checkToolTip("Volume On Button");
    	String ExpectedTooltip = "Volume On";
    	Assert.assertEquals(ActualTooltip, ExpectedTooltip);
    	//Turning the volume off and verifying tool tip for volume button
    	myRobot.mouseMove(381, 279);
    	genericvideopage.muteButton.click();
    	uiDriver.sleep(500);
    	myRobot.mouseMove(381, 279);
    	String ActualTooltip01 = genericvideopage.checkToolTip("Volume Off Button");
    	String ExpectedTooltip01 = "Volume Off";
    	Assert.assertEquals(ActualTooltip01, ExpectedTooltip01);
    }
    /*
     * Check user is able to drag volume scrubber
     */
    //@Test(groups = {"DragVolume","regression"})
    public void DragVolume() throws AWTException{
    	uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
    	//uiDriver.sleep(15000);
    	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
    	myRobot.calibrateAndEnterFrame(myRobot);
    	myRobot.mouseMove(381, 279);
    	genericvideopage.playButton.click();
    	//uiDriver.sleep(5000);
    	int IntialVolSlider = genericvideopage.getLoadedVolumeSliderWidth();
    	myRobot.dragElementHorizontal(
                genericvideopage.volumeSlider.getWebElement().getLocation(),
                35);
    	myRobot.mouseMove(381, 279);
    	int FinalVolSlider = genericvideopage.getLoadedVolumeSliderWidth();
    	Assert.assertNotEquals(IntialVolSlider, FinalVolSlider);
    	System.out.println(IntialVolSlider);
    	System.out.println(FinalVolSlider);
    	uiDriver.sleep(10000);
      }
    
    /*
     * Verify the Full/Normal screen button functionality and verify tool tip
     * Need to handle full screen pop up
     */
    //@Test(groups = {"ScreenMode","regression"})
    public void ScreenMode() throws AWTException{
    	uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
    	//uiDriver.sleep(15000);
    	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
    	myRobot.calibrateAndEnterFrame(myRobot);
    	myRobot.mouseMove(381, 279);
    	//Play the video
    	genericvideopage.playButton.click();
    	uiDriver.sleep(5000);
    	//Changing screen mode to full screen and verifying tool tip
    	genericvideopage.fullSizeButton.click();
    	String ActualTooltip = genericvideopage.checkToolTip("Full screen Button");
    	String ExpectedTooltip = "Exit Full Screen";
    	Assert.assertEquals(ActualTooltip, ExpectedTooltip);
    	uiDriver.sleep(5000);
    	//Changing screen mode to normal screen and verifying tool tip
    	myRobot.mouseMove(381, 279);
    	genericvideopage.fullSizeButton.click();
    	String ActualTooltip01 = genericvideopage.checkToolTip("Normal screen Button");
    	String ExpectedTooltip01 = "Enter Full Screen";
    	Assert.assertEquals(ActualTooltip01, ExpectedTooltip01);
    }
   
   /* 
    * Replay video by using Replay button
    */
   //@Test(groups = {"ReplayButton","regression"})
   public void ReplayButton()	{
   	uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
   	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
   	myRobot.calibrateAndEnterFrame(myRobot);
   	//uiDriver.sleep(20000);
   	//Play the video
   	myRobot.mouseMove(381, 279);
   	genericvideopage.playButton.click();
   	//uiDriver.sleep(10000);
   	myRobot.mouseMove(
               genericvideopage.fullSizeButton.getWebElement().getLocation().x,
               genericvideopage.progressBarSlider.getWebElement().getLocation().y + 5);
       myRobot.mouseClick();
       uiDriver.sleep(5000);
       genericvideopage.waitForVideoToEnd();
       //Hovering over Replay button and clicking on it.
    myRobot.mouseMove(318, 239);
   	myRobot.mouseClick();
   	uiDriver.sleep(15000);
   	//Confirming whether video is playing
   	String ActualTooltip = genericvideopage.pauseButton.getAttribute("title");
   	String ExpectedTooltip = "Pause Video";
   	Assert.assertEquals(ActualTooltip, ExpectedTooltip);
      } 
   /*
    * Verify the presence of chapter menu button and also check its tool tip 
    */
   //@Test(groups = {"ChapterMenuButton", "regression"})
   public void ChapterMenuButton() throws AWTException {
       uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
       GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
       myRobot.calibrateAndEnterFrame(myRobot);
       uiDriver.sleep(10000);
       myRobot.mouseMove(381, 279);
       genericVideoPage.chapterMenu.isDisplayed();
       String ActualTooltip = genericVideoPage.chapterMenu.getAttribute("title");
       String ExpectedTooltip = "Chapters Menu";
       genericVideoPage.chapterMenu.click();
       genericVideoPage.chapter1.isAbsent();
       Assert.assertEquals(ActualTooltip, ExpectedTooltip);
   }
   /*
    * Validating every chapter's title
    * Hover over chapter title, shows chapter description Mp -389
    * Verifying each chapters description.
    */
   //@Test(groups = {"chapteringTitletipAppearsOnHover", "chaptering", "regression"})
   private void chapteringTitletipAppearsOnHover() {
       GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
       uiDriver.get(httpPrefix + "/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
       //genericVideoPage.chapterMenu.click();
       uiDriver.sleep(10000); 
       myRobot.calibrateAndEnterFrame(myRobot);
       String Actualchapterch1 = genericVideoPage.chapter1.getAttribute("title");
       String Expectedchapterch1 = "Objective 1: Get ready for this course";
       Assert.assertEquals(Expectedchapterch1, Actualchapterch1);

       String Actualchapterch2 = genericVideoPage.chapter2.getAttribute("title");
       String Expectedchapterch2 = "Objective 2: Understand some general tips for success";
       Assert.assertEquals(Expectedchapterch2, Actualchapterch2);

       String Actualchapterch3 = genericVideoPage.chapter3.getAttribute("title");
       String Expectedchapterch3 = "Objective 3: Understand how to use this text";
       Assert.assertEquals(Expectedchapterch3, Actualchapterch3);

       String Actualchapterch4 = genericVideoPage.chapter4.getAttribute("title");
       String Expectedchapterch4 = "Objective 4: Get help as soon as you need it";
       Assert.assertEquals(Expectedchapterch4, Actualchapterch4);

       String Actualchapterch5 = genericVideoPage.chapter5.getAttribute("title");
       String Expectedchapterch5 = "Objective 5: Learn how to prepare for and take an exam";
       Assert.assertEquals(Expectedchapterch5, Actualchapterch5);

       String Actualchapterch6 = genericVideoPage.chapter6.getAttribute("title");
       String Expectedchapterch6 = "Objective 6: Develop good time management";
       Assert.assertEquals(Expectedchapterch6, Actualchapterch6);

       String Actualchapterch7 = genericVideoPage.chapter7.getAttribute("title");
       String Expectedchapterch7 = "Objective 7: Develop good time management";
       Assert.assertEquals(Expectedchapterch7, Actualchapterch7);

       String Actualchapterch8 = genericVideoPage.chapter8.getAttribute("title");
       String Expectedchapterch8 = "Objective 8: Develop good time management";
       Assert.assertEquals(Expectedchapterch8, Actualchapterch8);

       String Actualchapterch9 = genericVideoPage.chapter9.getAttribute("title");
       String Expectedchapterch9 = "Objective 9: Develop good time management";
       Assert.assertEquals(Expectedchapterch9, Actualchapterch9);

       String Actualchapterch10 = genericVideoPage.chapter10.getAttribute("title");
       String Expectedchapterch10 = "Objective 10: Develop good time management";
       Assert.assertEquals(Expectedchapterch10, Actualchapterch10);
   }
   /*
    * Verify that user is able to click on chapter from chapter menu and also validate that video scrubber seeks to
    * correct position (MP-202)
    */
   //@Test(groups = {"ChapterSeekingCorrectly", "regression"})
   public void ChapterSeekingCorrectly(){
       uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
       //uiDriver.sleep(3000);
       GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
       myRobot.calibrateAndEnterFrame(myRobot);
       while(genericVideoPage.playButton.isDisplayed()== true){
    	   if(genericVideoPage.chapter5.isDisplayed()){
        	   genericVideoPage.chapter5.click(); 
           }
    	   else{
           myRobot.mouseMove(381, 279);
           genericVideoPage.chapterMenu.click();
           uiDriver.sleep(3000);
    	   }
    	   
       }
       
       wait.until(new ExpectedCondition<Boolean>() {

           public Boolean apply(WebDriver webDriver) {
               System.out.println("Waiting for video to play at chapter 5...");
               // sometimes test will skip 7:35 and go directly to 7:36 and you don't want to fail it
               return webDriver.findElement(By.id("counter")).getText().contains("07:3");
           }
       });

   }
   /*
    * Verify that user is able to seek to any chapter by clicking chapter Marker from scrubber and also validate that video scrubber seeks to
    * correct position (MP-202)
    */
   //@Test(groups = {"ChapterMarker", "regression"})
   public void ChapterMarker() throws AWTException, InterruptedException {
       uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
       //uiDriver.sleep(3000);
       GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
       myRobot.calibrateAndEnterFrame(myRobot);
       while(genericVideoPage.playButton.isDisplayed()== true){
    	   if(genericVideoPage.chapterMarker5.isDisplayed()){
        	   genericVideoPage.chapterMarker5.click(); 
           }
    	   else{
           myRobot.mouseMove(381, 279);
           genericVideoPage.chapterMarker5.click();
           uiDriver.sleep(2500);
    	   }
       }
       wait.until(new ExpectedCondition<Boolean>() {

           public Boolean apply(WebDriver webDriver) {
        	   myRobot.mouseMove(381, 279);
               System.out.println("Waiting for video to play at chapter 5...");
               // sometimes test will skip 7:35 and go directly to 7:36 and you don't want to fail it
               return webDriver.findElement(By.id("counter")).getText().contains("07:3");
           }
       });

   }
   
   /*
    * Verify the video
    */
   //@Test(groups = {"DotVerification", "regression"})
   public void DotVerification() throws AWTException {
       uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
       GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
       myRobot.calibrateAndEnterFrame(myRobot);
       String ActualText = null;
       while(genericVideoPage.playButton.isDisplayed()== true){
    	   if(genericVideoPage.chapter9.isDisplayed()){
    		   myRobot.mouseMove(373, 272);
    	       ActualText = genericVideoPage.chapterMarker10.getAttribute("class"); 
           }
    	   else{
           myRobot.mouseMove(381, 279);
           genericVideoPage.chapterMenu.click();
           uiDriver.sleep(2500);
    	   }  	   
       }

       String ExpectedText = "chapter-dot chapter-num-10 highlight";
       Assert.assertEquals(ActualText, ExpectedText);
   }

}
