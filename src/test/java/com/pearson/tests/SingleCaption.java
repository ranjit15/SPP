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
 * Hover Play button - D
 * Tool tips - D
 * Timeline displayed
 * Replay button -D 
 * CC button - Presence and tool tip - D
 * CC button _ Caption validation and tool tip - D
 * More Info - Selection and validation - D
 */

public class SingleCaption extends MediaPlayerTest
{
	 /*
     * Verify the video title of Two caption video
     */
    //@Test(groups = {"TitleVerification", "regression"})
    public void TitleVerification() throws AWTException {
        uiDriver.get("http://mediaplayer-ppe.pearsoncmg.com/assets/j8ezH8hmSeJpnfi_KYqowb5NZ15WItsc");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);
        //uiDriver.sleep(15000);
        myRobot.mouseMove(381, 279);
        String ActualTitle = genericVideoPage.videoTitle.getText();
        String ExpectedTitle = "QA Test - One Set of Captions";
        Assert.assertEquals(ActualTitle, ExpectedTitle);
    }
    /*
     * Verify the Play/Pause functionality and check tool tip as well
     */
    //@Test(groups = {"PlayPause","regression"})
    public void PlayPause() throws AWTException	{
    	uiDriver.get("http://mediaplayer-ppe.pearsoncmg.com/assets/j8ezH8hmSeJpnfi_KYqowb5NZ15WItsc");
    	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
    	myRobot.calibrateAndEnterFrame(myRobot);
    	//uiDriver.sleep(15000);
    	myRobot.mouseMove(381, 279);
    	myRobot.mouseMove(381, 279);
    	//Play the video
    	genericvideopage.clickToolbarPlay();
    	uiDriver.sleep(1000);
    	//Confirming whether video is playing
    	myRobot.mouseMove(381, 279);
    	String ActualTooltip = genericvideopage.checkToolTip("Pause Button");
    	String ExpectedTooltip = "Pause Video";
    	Assert.assertEquals(ActualTooltip, ExpectedTooltip);
    	myRobot.mouseMove(381, 279);
    	//Pause the video
    	genericvideopage.pauseButton.click();
    	uiDriver.sleep(500);
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
    	uiDriver.get("http://mediaplayer-ppe.pearsoncmg.com/assets/j8ezH8hmSeJpnfi_KYqowb5NZ15WItsc");
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
        //uiDriver.sleep(15000);
       }
    /*
     * Volume button on/off state functionality and verify tool tip in both the states
     */
    //@Test(groups = {"VolumeController","regression"})
    public void VolumeController() throws AWTException{
    	uiDriver.get("http://mediaplayer-ppe.pearsoncmg.com/assets/j8ezH8hmSeJpnfi_KYqowb5NZ15WItsc");
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
    	uiDriver.get("http://mediaplayer-ppe.pearsoncmg.com/assets/j8ezH8hmSeJpnfi_KYqowb5NZ15WItsc");
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
    	//uiDriver.sleep(10000);
      }
    
    /*
     * Verify the Full/Normal screen button functionality and verify tool tip
     * Need to handle full screen pop up
     */
    //@Test(groups = {"ScreenMode","regression"})
    public void ScreenMode() throws AWTException{
    	uiDriver.get("http://mediaplayer-ppe.pearsoncmg.com/assets/j8ezH8hmSeJpnfi_KYqowb5NZ15WItsc");
    	//uiDriver.sleep(15000);
    	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
    	myRobot.calibrateAndEnterFrame(myRobot);
    	String ActualTooltip01 = genericvideopage.checkToolTip("Normal screen Button");
    	String ExpectedTooltip01 = "Enter Full Screen";
    	Assert.assertEquals(ActualTooltip01, ExpectedTooltip01);
    	myRobot.mouseMove(381, 279);
    	//Play the video
    	genericvideopage.playButton.click();
    	//uiDriver.sleep(5000);
    	//Changing screen mode to full screen and verifying tool tip
    	genericvideopage.fullSizeButton.click();
    	String ActualTooltip = genericvideopage.checkToolTip("Full screen Button");
    	String ExpectedTooltip = "Exit Full Screen";
    	Assert.assertEquals(ActualTooltip, ExpectedTooltip);
    	//uiDriver.sleep(5000);
    	//Changing screen mode to normal screen and verifying tool tip
    	myRobot.mouseMove(381, 279);
    	genericvideopage.fullSizeButton.click();
    }
    
   /*Verify the presence of CC button and check tool tip also */
    
    //@Test(groups = {"CaptionPresence","regression"})
    public void CaptionPresence() throws AWTException{
   	uiDriver.get("http://mediaplayer-ppe.pearsoncmg.com/assets/j8ezH8hmSeJpnfi_KYqowb5NZ15WItsc");
   	//uiDriver.sleep(15000);
   	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
   	myRobot.calibrateAndEnterFrame(myRobot);
   	//Verify the presence of CC button
   	uiDriver.waitToBeDisplayed(genericvideopage.captionButton, 10000);
   	//Verifying the tool tip of CC button
   	String captionOffcheck = genericvideopage.checkToolTip("CC Button");
   	String ExpectedResult = "Show Closed Captioning";
	Assert.assertEquals(captionOffcheck, ExpectedResult);
   }
   /*Verify that Caption are displayed correctly*/
   
   //@Test(groups = {"CaptionValidation","regression"})
   public void CaptionValidation() throws AWTException{
   	uiDriver.get("http://mediaplayer-ppe.pearsoncmg.com/assets/j8ezH8hmSeJpnfi_KYqowb5NZ15WItsc");
   	//uiDriver.sleep(15000);
   	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
   	myRobot.calibrateAndEnterFrame(myRobot);
   	//Verify the presence of CC button
   	uiDriver.waitToBeDisplayed(genericvideopage.captionButton, 1000);
   	genericvideopage.clickCCButton();
   	String englishCheck = genericvideopage.captionButton.getAttribute("title");
   	genericvideopage.clickToolbarPlay();
   	uiDriver.sleep(3000); 	
   	//Verify that caption is displayed properly
    genericvideopage
    .waitForCaptionText("Make sure you select an appropriate weight for each");
   	String ExpectedResult = "Hide Closed Captioning";
	Assert.assertEquals(englishCheck, ExpectedResult);
   	
   }
   /* 
    * Play/Pause video by using Hover Play button
    */
   //@Test(groups = {"HoverPlayButton","regression"})
   public void HoverPlayButton(){
   	uiDriver.get("http://mediaplayer-ppe.pearsoncmg.com/assets/j8ezH8hmSeJpnfi_KYqowb5NZ15WItsc");
   	//uiDriver.sleep(15000);
   	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
   	myRobot.calibrateAndEnterFrame(myRobot);
   	//Hovering over play button and clicking on it.
   	myRobot.mouseMove(318, 239);
   	myRobot.mouseClick();
   	uiDriver.sleep(5000);
   	//Confirming whether video is playing
   	String ActualTooltip = genericvideopage.checkToolTip("Pause Button");
   	String ExpectedTooltip = "Pause Video";
   	Assert.assertEquals(ActualTooltip, ExpectedTooltip);
   	//Hovering over pause button and clicking on it.
   	myRobot.mouseMove(318, 239);
   	genericvideopage.hoverPlayButton.isDisplayed();
   	myRobot.mouseMove(318, 239);
   	myRobot.mouseClick();
   	uiDriver.sleep(1000);
   	//Confirming whether video is paused
   	String ActualTooltip01 = genericvideopage.checkToolTip("Play Button");
   	String ExpectedTooltip01 = "Play Video";
   	Assert.assertEquals(ActualTooltip01, ExpectedTooltip01);
   }
   //@Test(groups = {"ReplayButton","regression"})
   public void ReplayButton()	{
   	uiDriver.get("http://mediaplayer-ppe.pearsoncmg.com/assets/j8ezH8hmSeJpnfi_KYqowb5NZ15WItsc");
   	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
   	myRobot.calibrateAndEnterFrame(myRobot);
   	//uiDriver.sleep(20000);
   	//Play the video
   	myRobot.mouseMove(381, 279);
   	genericvideopage.playButton.click();
   	uiDriver.sleep(5000);
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
   	String ActualTooltip = genericvideopage.checkToolTip("Pause Button");
   	String ExpectedTooltip = "Pause Video";
   	Assert.assertEquals(ActualTooltip, ExpectedTooltip);
      } 
   
    /*Verify the More Info button's functionality  */
   
   //@Test(groups = {"MoreInfoButton","regression"})
   public void MoreInfoButton() throws AWTException{
   	uiDriver.get("http://mediaplayer-ppe.pearsoncmg.com/assets/j8ezH8hmSeJpnfi_KYqowb5NZ15WItsc");
   	//uiDriver.sleep(15000);
   	GenericVideoPage genericvideopage = new GenericVideoPage(uiDriver);
   	myRobot.calibrateAndEnterFrame(myRobot);
   	//Verify the presence of More Info button
   	uiDriver.waitToBeDisplayed(genericvideopage.moreInfoButton, 10000);
   	genericvideopage.moreInfoButton.click();
   	//Verify that more info text is displayed
    String MoreInfoText = genericvideopage.moreInfoTextArea.getText();
   	String ExpectedText = "Sample Descripton Here";
	Assert.assertEquals(MoreInfoText, ExpectedText);
   	
   }

}