package com.pearson.tests;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;


/**
 * @author ranjit.brar
 * @date 06/05/2013
 * Title verification - D 
 * play/pause with tool tip -D  
 * Dragging video scrubber - D
 * mute on/mute off - D
 * Dragging volume scrubber -D
 * full screen /normal screen -D 
 * Hover Play button - 
 * Tool tips - 
 * Timeline displayed
 * Replay button - 
 * Concept check options -D
 * Concept Check close button -D
 */

public class ExternalConceptCheck extends MediaPlayerTest
{
	 /*
     * Verify the video title of Internal Concept Check video
     */
    //@Test(groups = {"TitleVerification", "regression"})
    public void TitleVerification() throws AWTException {
        uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/MATH_CONCEPT_CHECK");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);
        uiDriver.sleep(15000);
        Robot robot = new Robot();
        robot.mouseMove(381, 279);
        String ActualTitle = genericVideoPage.videoTitle.getText();
        String ExpectedTitle = "MATH_CONCEPT_CHECK";
        Assert.assertEquals(ActualTitle, ExpectedTitle);
    }
    /*
     * Verify the Play/Pause functionality and check tool tip as well
     */
    //@Test(groups = {"PlayPause","regression"})
    public void PlayPause() throws AWTException	{
    	uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/MATH_CONCEPT_CHECK");
    	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
    	myRobot.calibrateAndEnterFrame(myRobot);
    	//uiDriver.sleep(2000);
    	Robot robot = new Robot();
    	robot.mouseMove(381, 279);
    	//Play the video
    	genericvideopage.playButton.click();
    	uiDriver.sleep(5000);
    	//Confirming whether video is playing
    	robot.mouseMove(381, 279);
    	String ActualTooltip = genericvideopage.pauseButton.getAttribute("title");
    	String ExpectedTooltip = "Pause Video";
    	Assert.assertEquals(ActualTooltip, ExpectedTooltip);
    	robot.mouseMove(381, 279);
    	//Pause the video
    	genericvideopage.pauseButton.click();
    	//Confirming whether video is paused
    	robot.mouseMove(381, 279);
    	String ActualTooltip01 = genericvideopage.playButton.getAttribute("title");
    	String ExpectedTooltip01 = "Play Video";
    	Assert.assertEquals(ActualTooltip01, ExpectedTooltip01);
    }
    /*
     * Check user is able to drag video scrubber
     */
    //@Test(groups = {"DragVideoScrubber","regression"})
    public void DragVideoScrubber() throws AWTException	{
    	uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/MATH_CONCEPT_CHECK");
    	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
    	myRobot.calibrateAndEnterFrame(myRobot);
    	//uiDriver.sleep(15000);
    	//Play the video
    	Robot robot = new Robot();
    	robot.mouseMove(381, 279);
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
    	uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/MATH_CONCEPT_CHECK");
    	//uiDriver.sleep(15000);
    	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
    	myRobot.calibrateAndEnterFrame(myRobot);
    	Robot robot = new Robot();
    	robot.mouseMove(381, 279);
    	genericvideopage.playButton.click();
    	//uiDriver.sleep(5000);
    	//Checking tool tip for volume button
    	String ActualTooltip = genericvideopage.getMuteButtonTitle();
    	String ExpectedTooltip = "Volume On";
    	Assert.assertEquals(ActualTooltip, ExpectedTooltip);
    	//Turning the volume off and verifying tool tip for volume button
    	robot.mouseMove(381, 279);
    	genericvideopage.muteButton.click();
    	String ActualTooltip01 = genericvideopage.getMuteButtonTitle();
    	String ExpectedTooltip01 = "Volume Off";
    	Assert.assertEquals(ActualTooltip01, ExpectedTooltip01);
    }
    /*
     * Check user is able to drag volume scrubber
     */
    //@Test(groups = {"DragVolume","regression"})
    public void DragVolume() throws AWTException{
    	uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/MATH_CONCEPT_CHECK");
    	//uiDriver.sleep(15000);
    	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
    	myRobot.calibrateAndEnterFrame(myRobot);
    	Robot robot = new Robot();
    	robot.mouseMove(381, 279);
    	genericvideopage.playButton.click();
    	//uiDriver.sleep(5000);
    	int IntialVolSlider = genericvideopage.getLoadedVolumeSliderWidth();
    	myRobot.dragElementHorizontal(
                genericvideopage.volumeSlider.getWebElement().getLocation(),
                35);
    	robot.mouseMove(381, 279);
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
  // @Test(groups = {"ScreenMode","regression"})
    public void ScreenMode() throws AWTException{
    	uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/MATH_CONCEPT_CHECK");
    	//uiDriver.sleep(15000);
    	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
    	myRobot.calibrateAndEnterFrame(myRobot);
    	Robot robot = new Robot();
    	robot.mouseMove(381, 279);
    	//Play the video
    	genericvideopage.playButton.click();
    	uiDriver.sleep(5000);
    	//Changing screen mode to full screen and verifying tool tip
    	genericvideopage.fullSizeButton.click();
    	String ActualTooltip = genericvideopage.fullSizeButton.getAttribute("title-hidden");
    	String ExpectedTooltip = "Exit Full Screen";
    	Assert.assertEquals(ActualTooltip, ExpectedTooltip);
    	uiDriver.sleep(5000);
    	//Changing screen mode to normal screen and verifying tool tip
    	robot.mouseMove(381, 279);
    	genericvideopage.fullSizeButton.click();
    	String ActualTooltip01 = genericvideopage.fullSizeButton.getAttribute("title-hidden");
    	String ExpectedTooltip01 = "Enter Full Screen";
    	Assert.assertEquals(ActualTooltip01, ExpectedTooltip01);
    }
   /*Verify that user is able to select 1st option and submit it*/
    
   //@Test(groups = {"ConceptCheck","regression"})
   public void ConceptcheckOne() throws AWTException{
   	uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/MATH_CONCEPT_CHECK");
   	//uiDriver.sleep(15000);
   	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
   	myRobot.calibrateAndEnterFrame(myRobot);
   	myRobot.mouseMove(381, 279);
	//Play the video
	genericvideopage.playButton.click();
   	uiDriver.sleep(5000);
   	myRobot.mouseMove(
            genericvideopage.ConceptCheck.getWebElement().getLocation().x,
            genericvideopage.progressBarSlider.getWebElement().getLocation().y + 5);
    myRobot.mouseClick();
    uiDriver.sleep(5000);
    //uiDriver.findElement(By.xpath(".//*[@id='1']/div[2]/ul/li[1]/span")).click();
    genericvideopage.conceptCheckRadioButton1.click();
    uiDriver.sleep(1000);
    genericvideopage.conceptCheckSubmit.click();
    uiDriver.sleep(1000);
    myRobot.mouseMove(381, 279);
    String Time = genericvideopage.LeftTimer.getAttribute("title");
    System.out.println(Time);
    }
   /*Verify that user is able to select 2nd option and submit it*/
   
   //@Test(groups = {"ConceptCheck","regression"})
   public void ConceptCheckTwo() throws AWTException{
   	uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/MATH_CONCEPT_CHECK");
   	//uiDriver.sleep(15000);
   	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
   	myRobot.calibrateAndEnterFrame(myRobot);
   	myRobot.mouseMove(381, 279);
	//Play the video
	genericvideopage.playButton.click();
   	uiDriver.sleep(5000);
   	myRobot.mouseMove(
            genericvideopage.ConceptCheck.getWebElement().getLocation().x,
            genericvideopage.progressBarSlider.getWebElement().getLocation().y + 5);
    myRobot.mouseClick();
    uiDriver.sleep(5000);
    //uiDriver.findElement(By.xpath(".//*[@id='1']/div[2]/ul/li[1]/span")).click();
    genericvideopage.conceptCheckRadioButton2.click();
    uiDriver.sleep(1000);
    genericvideopage.conceptCheckSubmit.click();
    uiDriver.sleep(1000);
    myRobot.mouseMove(381, 279);
    String Time = genericvideopage.LeftTimer.getAttribute("title");
    System.out.println(Time);
    }
   /*Verify that user is able to select 3rd option and submit it*/
   
  // @Test(groups = {"ConceptCheck","regression"})
   public void ConceptcheckThree() throws AWTException{
   	uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/MATH_CONCEPT_CHECK");
   	//uiDriver.sleep(15000);
   	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
   	myRobot.calibrateAndEnterFrame(myRobot);
   	myRobot.mouseMove(381, 279);
	//Play the video
	genericvideopage.playButton.click();
   	uiDriver.sleep(5000);
   	myRobot.mouseMove(
            genericvideopage.ConceptCheck.getWebElement().getLocation().x,
            genericvideopage.progressBarSlider.getWebElement().getLocation().y + 5);
    myRobot.mouseClick();
    uiDriver.sleep(5000);
    //uiDriver.findElement(By.xpath(".//*[@id='1']/div[2]/ul/li[1]/span")).click();
    genericvideopage.conceptCheckRadioButton3.click();
    uiDriver.sleep(1000);
    genericvideopage.conceptCheckSubmit.click();
    uiDriver.sleep(1000);
    myRobot.mouseMove(381, 279);
    String Time = genericvideopage.LeftTimer.getAttribute("title");
    System.out.println(Time);
    }
/*Verify that user is able to Reopen Concept check and able to close it*/
   
   //@Test(groups = {"ConceptCheck","regression"})
   public void ReopenConceptCheck() throws AWTException{
   	uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/MATH_CONCEPT_CHECK");
   	//uiDriver.sleep(15000);
   	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
   	myRobot.calibrateAndEnterFrame(myRobot);
   	myRobot.mouseMove(381, 279);
	//Play the video
	genericvideopage.playButton.click();
   	uiDriver.sleep(5000);
   	myRobot.mouseMove(
            genericvideopage.ConceptCheck.getWebElement().getLocation().x,
            genericvideopage.progressBarSlider.getWebElement().getLocation().y + 5);
    myRobot.mouseClick();
    uiDriver.sleep(5000);
    //uiDriver.findElement(By.xpath(".//*[@id='1']/div[2]/ul/li[1]/span")).click();
    genericvideopage.conceptCheckRadioButton3.click();
    uiDriver.sleep(1000);
    genericvideopage.conceptCheckSubmit.click();
    uiDriver.sleep(1000);
    genericvideopage.ReopenConceptCheck.click();
    uiDriver.sleep(1000);
    genericvideopage.CloseButton.click();
    uiDriver.sleep(1000);
    }

   
   /* 
    * Play/Pause video by using Hover Play button
    */
   @Test(groups = {"HoverPlayButton","regression"})
   public void HoverPlayButton(){
   	uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/MATH_CONCEPT_CHECK");
   	//uiDriver.sleep(15000);
   	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
   	myRobot.calibrateAndEnterFrame(myRobot);
   	//Hovering over play button and clicking on it.
   	myRobot.mouseMove(318, 239);
   	myRobot.mouseClick();
   	uiDriver.sleep(10000);
   	//Confirming whether video is playing
   	String ActualTooltip = genericvideopage.pauseButton.getAttribute("title");
   	String ExpectedTooltip = "Pause Video";
   	Assert.assertEquals(ActualTooltip, ExpectedTooltip);
   	//Hovering over pause button and clicking on it.
   	myRobot.mouseMove(318, 239);
   	myRobot.mouseClick();
   	uiDriver.sleep(5000);
   	//Confirming whether video is paused
   	String ActualTooltip01 = genericvideopage.playButton.getAttribute("title");
   	String ExpectedTooltip01 = "Play Video";
   	Assert.assertEquals(ActualTooltip01, ExpectedTooltip01);
   }
   //@Test(groups = {"ReplayButton","regression"})
   public void ReplayButton()	{
   	uiDriver.get("http://mediaplayer.pearsoncmg.com/assets/MATH_CONCEPT_CHECK");
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
}
