package com.pearson.tests;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RandomTest {
	public static void main(String[] args) throws InterruptedException{
		 WebDriver driver = new FirefoxDriver();
		 driver.get("http://www.samisite.com/test-csb2nf/id108.htm");
		 //driver.manage().window().maximize();
		 WebDriverWait Wait = new WebDriverWait(driver,2);
		 Wait.until(ExpectedConditions.alertIsPresent());
		 Alert alertBox = driver.switchTo().alert();
		 alertBox.accept();
		 driver.close();

	}
}
