package com.pearson.tests;

import java.awt.Color;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.annotations.Test;

import com.pearson.smartmediaplayer.GenericVideoPage;

/**
 * 
 * @author pallavi.mishra
 * @date May 16, 2013
 * 
 *       Hover over chapter title, shows chapter description mp -389
 *       Verifying each chapters description.
 */

public class Chaptering extends MediaPlayerTest {

   @Test(groups = {"chapteringTitletipAppearsOnHover", "chaptering", "regression"})
    private void chapteringTitletipAppearsOnHover() {
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        uiDriver.get(httpPrefix + "/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
        //genericVideoPage.chapterMenu.click();
        uiDriver.sleep(30000); 
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

    //@Test(groups = {"chapteringMenu", "chaptering", "regression"})
    private void chapteringMenu() {
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        uiDriver.get(httpPrefix + "/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
        uiDriver.sleep(20000);
        myRobot.calibrateAndEnterFrame(myRobot);
        //genericVideoPage.chapterMenu.click();
        uiDriver.waitToBeDisplayed(genericVideoPage.chapterMenuItems,40000);

    }

    /**
     * Test will go to a link and play back a chaptering video Then it will verify that a tooltip
     * shows up when you hover over the chapter icon This verifies that we don't regress on our
     * chaptering UI changes Works in FF unless you use actions.click . Given coordinates (506, 512)
     * are outside the document MP-200
     */
    @Test(groups = {"chaptering", "chapteringTooltipAppearsOnHover", "regression"})
    private void chapteringTooltipAppearsOnHover() {
        uiDriver.get(httpPrefix + "/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);
        uiDriver.sleep(15000);
        // verify that chaptering icon at the bottom will load
        uiDriver.waitToBePresent(genericVideoPage.chapteringIcon3, 10000);

        /*
         * The actions click method does not actually hover over the button, and therefore does not
         * actually create a tooltip. Here is some sample code:
         * actions.click(genericVideoPage.chapteringIcon3.getWebElement()).build().perform();
         */
        // first move a bit to the left of the chapter icon so the hover causes the tooltip to show
        myRobot.mouseMove(
                genericVideoPage.chapteringIcon3.getWebElement().getLocation().x - 50,
                genericVideoPage.chapteringIcon3.getWebElement().getLocation().y);
        // dev tooltip is smaller
        if (httpPrefix.contains("dev")) {
            myRobot.mouseMove(
                    genericVideoPage.chapteringIcon3.getWebElement().getLocation().x,
                    genericVideoPage.chapteringIcon3.getWebElement().getLocation().y);
        } else {
            myRobot.mouseMove(
                    genericVideoPage.chapteringIcon3.getWebElement().getLocation().x + 5,
                    genericVideoPage.chapteringIcon3.getWebElement().getLocation().y + 5);
        }
        uiDriver.waitToBeDisplayed(genericVideoPage.chapteringIcon3Tooltip, 2000);
    }

    /**
     * Verify that after a user selects a chapter they are brought to the chapter specified in a reasonable amount of time.
     * Tests that selecting a chapter in the menu and then in the timebar will cause users to
     * successfully scrub right in the video then verifies users went to the right chapter MP-202
     */
    //@Test(groups = {"chaptering", "toolbarChaptering", "regression", "acceptance"})
    private void toolbarChaptering() {
        // open video
        uiDriver.get(httpPrefix + "/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);
        uiDriver.sleep(20000);
        //genericVideoPage.chapter5.getUIActions().click();
        genericVideoPage.chapter5.click();
        uiDriver.sleep(5000);
        // wait for video timecode to get to 7:30 ish
        wait.until(new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver webDriver) {
                System.out.println("Waiting for video to play at chapter 5...");
                // sometimes test will skip 7:35 and go directly to 7:36 and you don't want to fail
                // it
                return webDriver.findElement(By.id("counter")).getText().contains("07:3");
            }
        });

        // Get some color on the video to verify video image changed. The pixel color should be
        // yellow
        //Point pointToVerify = new Point(530, 325); // I want a point at 530, 325 from top left of
                                                   // iframe
        //Color expectedColorOfPixel = new Color(235, 240, 134); // some shade of yellow
        //myRobot.isPixelColorCorrect(pointToVerify, expectedColorOfPixel);
    }

    /**
     * Verifies that 10 chapters appear in one menu without you having to scroll
     */
    //@Test(groups = {"chaptering", "tenChaptersAppearInMenu", "regression", "acceptance"})
    private void tenChaptersAppearInMenu() {
        uiDriver.get(httpPrefix + "/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);
        System.err.println(genericVideoPage.chapterTitles.length());
        genericVideoPage.verifyTenChaptersAppearOnMenu();
    }

    /*
     * This verifies there is a chapter dot for the first chapter mp - 397
     */
    //@Test(groups = {"chaptering", "FirstChapterDot", "regression"})
    private void FirstChapterDot() {
        uiDriver.get(httpPrefix + "/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);
        // verify that chaptering icon at the bottom will load
        uiDriver.sleep(1000);
        uiDriver.waitToBeDisplayed(genericVideoPage.playPauseButton);
        uiDriver.sleep(1000);
        genericVideoPage.clickToolbarPlay();
        uiDriver.sleep(60000);
        // System.out.println("The Value" + genericVideoPage.FirstDot.getAttribute("class"));
        Assert.assertTrue("chapter-dot chapter-num-1".equals(genericVideoPage.FirstDot
                .getAttribute("class")));

    }

    /*
     * When you stream past a chapter, you see the chapter title. mp - 396
     */
    //@Test(groups = {"chaptering", "ChapterTitleVerification", "regression", "acceptance"})
    private void ChapterTitleVerification() {
        uiDriver.get(httpPrefix + "/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);
        uiDriver.sleep(20000);
        uiDriver.waitToBeDisplayed(genericVideoPage.playPauseButton);
        genericVideoPage.clickToolbarPlay();
        uiDriver.sleep(40000);
        String actualText1 = genericVideoPage.videoTitle.getText();
        System.out.println("Video Title " + actualText1);
        String expectedText1 = "QA Test - Sample Player Chapters";
        Assert.assertEquals(expectedText1, actualText1);
        //myRobot.mouseMove(67, 428);
        //uiDriver.sleep(2000);
        //String actualText = genericVideoPage.videoTitle.getText();
        //String expectedText = "Objective 2";
        //logger.info("Chapter Title " + actualText);
        //Assert.assertEquals(expectedText, actualText);
        //uiDriver.sleep(1000);
        //myRobot.mouseMove(265, 428);
        //uiDriver.sleep(2000);
        //String actualText11 = genericVideoPage.videoTitle.getText();
        //String expectedText1 = "Objective 5";
        //logger.info("Chapter Title " + actualText);
        //Assert.assertEquals(expectedText1, actualText11);
    }

    /*
     * When you hover over a chapter in the menu, the corresponding dot in the timeline darkens mp -
     * 833
     */
    //@Test(groups = {"chaptering", "DotVerification", "regression"})
    private void DotVerification() {
        uiDriver.get(httpPrefix + "/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);
        uiDriver.sleep(2000);
        uiDriver.waitToBeDisplayed(genericVideoPage.muteButton);
        genericVideoPage.clickMenuButton();
        genericVideoPage.clickMenuButton();
        uiDriver.sleep(10000);
        // moving nouse to objective 2 in chaptering menu
        myRobot.mouseMove(115, 103);
        uiDriver.sleep(10000);
        String expectedTextch2 = "chapter-dot chapter-num-2 highlight";
        String actualTextch2 = uiDriver.findElement(
                (By.xpath(".//*[@class='chapter-dot chapter-num-2 highlight']"))).getAttribute(
                "class");
        logger.info("chapter 2 " + actualTextch2);
        Assert.assertEquals(actualTextch2, expectedTextch2);
        myRobot.mouseMove(115, 222);
        uiDriver.sleep(10000);
        String expectedTextch5 = "chapter-dot chapter-num-5 highlight";
        String actualTextch5 = uiDriver.findElement(
                (By.xpath(".//*[@class='chapter-dot chapter-num-5 highlight']"))).getAttribute(
                "class");
        logger.info("chapter 5 " + actualTextch5);
        Assert.assertEquals(actualTextch5, expectedTextch5);
    }

    /*
     * Hovering over dot in timeline displays chapter name at the top of the video mp - 392
     */
    //@Test(groups = {"chaptering", "TitleVerification", "regression"})
    private void TitleVerification() {
        uiDriver.get(httpPrefix + "/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);
        uiDriver.sleep(1000);
        uiDriver.waitToBeDisplayed(genericVideoPage.playPauseButton);
        genericVideoPage.clickToolbarPlay();
        uiDriver.sleep(40000);
        String actualText1 = genericVideoPage.videoTitle.getText();
        System.out.println("Video Title " + actualText1);
        myRobot.mouseMove(67, 428);
        uiDriver.sleep(2000);
        String actualText = uiDriver.findElement((By.xpath(".//*[@class='chapter-title']")))
                .getText();
        String expectedText = "Objective 2";
        logger.info("Chapter Title " + actualText);
        Assert.assertEquals(expectedText, actualText);
        uiDriver.sleep(1000);
        myRobot.mouseMove(265, 428);
        uiDriver.sleep(2000);

        String actualText11 = uiDriver.findElement((By.xpath(".//*[@class='chapter-title']")))
                .getText();
        String expectedText1 = "Objective 5";
        logger.info("Chapter Title " + actualText);
        Assert.assertEquals(expectedText1, actualText11);
    }

    /*
     * Player will advance to next chapter when it reaches the end of the previous chapter mp-393
     */

    //@Test(groups = {"chaptering", "chapterPlayVerification", "regression"})
    private void chapterPlayVerification() {
        uiDriver.get(httpPrefix + "/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC");
        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        myRobot.calibrateAndEnterFrame(myRobot);
        uiDriver.sleep(12000);
        uiDriver.waitToBePresent(genericVideoPage.playPauseButton);
        uiDriver.sleep(2000);
        genericVideoPage.chapterMenu.click();
        uiDriver.sleep(1000);
        genericVideoPage.clickToolbarPlay();
        uiDriver.sleep(2000);
        // myRobot.mouseMove(60, 428);
        myRobot.mouseMove(genericVideoPage.progressBar.getWebElement().getLocation().x + 53,// objective
                                                                                            // 2
                genericVideoPage.progressBar.getWebElement().getLocation().y + 3);
        uiDriver.sleep(10000);// letting the video play for some time
        myRobot.mouseClick();
        uiDriver.sleep(25000);
        myRobot.verifyVideoIsPlaying();
        myRobot.mouseMove(genericVideoPage.progressBar.getWebElement().getLocation().x + 196,// objective4
                genericVideoPage.progressBar.getWebElement().getLocation().y + 3);
        uiDriver.sleep(10000);// letting the video play for some time
        myRobot.mouseClick();
        uiDriver.sleep(25000);
        myRobot.verifyVideoIsPlaying();
        myRobot.mouseMove(genericVideoPage.progressBar.getWebElement().getLocation().x + 389,// objective
                                                                                             // 7
                genericVideoPage.progressBar.getWebElement().getLocation().y + 3);
        uiDriver.sleep(10000);// letting the video play for some time
        myRobot.mouseClick();
        uiDriver.sleep(25000);
        myRobot.verifyVideoIsPlaying();

    }

    // MP-394 When the video is playing, and a user selects a chapter in the menu, the video should
    // jump to the chapter selected without stopping.
    /*
     * This test simply play the video, select chapter 3, and examine if the video is still playing
     * after jumped to chapter 3
     * 
     * @author Alex Su
     * 
     * @date Jul 8, 2013
     */
    //@Test(groups = {"chapteringWithoutStopping", "chaptering", "regression"})
    private void chapteringWithoutStopping() {
        String testLink = httpPrefix + "/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC";

        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        uiDriver.get(testLink);

        myRobot.calibrateAndEnterFrame(myRobot);

        logInstruction("Going to " + testLink);
        logInstruction("Play the video");

        genericVideoPage.playButton.click();
        uiDriver.sleep(10000);

        logInstruction("Turn on the chapter menu");

        genericVideoPage.chapterMenu.click();

        logInstruction("Click on chapter 3");

        genericVideoPage.chapter3.click();
        uiDriver.sleep(10000);

        logInstruction("Examine if the video is still playing");

        if (genericVideoPage.playButton.isDisplayed())
            Assert.assertTrue("Video failed to play after chapter selection", false);

        logInstruction("Success! Test Passed!");

    }

    // MP-390 Select chapter brings user to chapter in video
    /*
     * This test simple clicks on each chapter and validate the time code to make sure they jump to
     * the correct position
     * 
     * @author Alex Su
     * 
     * @date Jul 8, 2013
     */
   // @Test(groups = {"chapteringBringsUserToChapter", "chaptering", "regression"})
    private void chapteringBringsUserToChapter() {
        String testLink = httpPrefix + "/assets/8B6DEF0F-6F9C-5CBF-C402-AE349B071BDC";

        GenericVideoPage genericVideoPage = new GenericVideoPage(uiDriver);
        uiDriver.get(testLink);

        myRobot.calibrateAndEnterFrame(myRobot);

        logInstruction("Going to " + testLink);

        uiDriver.waitToBeDisplayed(genericVideoPage.chapterMenu, 60000);
        genericVideoPage.chapterMenu.click();
        uiDriver.sleep(2000);

        chapteringToDesignatedChapter(genericVideoPage.chapter2, "01:37", "01:38", "01:39", "2");
        chapteringToDesignatedChapter(genericVideoPage.chapter3, "03:31", "03:32", "03:33", "3");
        chapteringToDesignatedChapter(genericVideoPage.chapter4, "06:02", "06:03", "06:04", "4");
        chapteringToDesignatedChapter(genericVideoPage.chapter5, "07:37", "07:38", "07:39", "5");
        chapteringToDesignatedChapter(genericVideoPage.chapter6, "09:40", "09:41", "09:42", "6");
        chapteringToDesignatedChapter(genericVideoPage.chapter7, "11:48", "11:49", "11:50", "7");
        chapteringToDesignatedChapter(genericVideoPage.chapter8, "13:37", "13:38", "13:39", "8");
        chapteringToDesignatedChapter(genericVideoPage.chapter9, "15:34", "15:35", "15:36", "9");
        chapteringToDesignatedChapter(genericVideoPage.chapter10, "17:29", "17:30", "17:31", "10");

        logInstruction("Success! Test passed!");
    }

    // helper method for chapteringBringsUserToChapter()
    private String leftPlayCounterFetcher(GenericVideoPage gvp) {
        String timeCode = gvp.leftPlayCounter.getText();
        return timeCode.substring(timeCode.length() - 5, timeCode.length());
    }

    // helper method for chapteringBringsUserToChapter()
    private void chapteringToDesignatedChapter(
            WebElement chapterButton,
            String expectedResult,
            String expectedResult2,
            String expectedResult3,
            String chapterNumber) {
        GenericVideoPage gvp = new GenericVideoPage(uiDriver);
        gvp.chapterMenu.click();
        uiDriver.sleep(2000);
        chapterButton.click();
        int counter = 0;
        while (!(leftPlayCounterFetcher(gvp).equals(expectedResult) || leftPlayCounterFetcher(gvp)
                .equals(expectedResult2) || leftPlayCounterFetcher(gvp).equals(expectedResult3))) {
            uiDriver.sleep(100);
            counter++;
            if (counter > 600)
                Assert.assertTrue(
                        "The video failed to scrub to the right position after chapter selection",
                        false);
        }
        logInstruction("chapter" + chapterNumber + " works");
    }

}
