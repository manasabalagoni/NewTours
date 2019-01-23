package com.demoaut.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectFlightPage {

	private WebDriver selectFlightDriver;

	@FindBy(xpath = "//input[contains(@value,'Unified')][@name='outFlight']")
	@CacheLookup
	private WebElement selectOutbound;

	@FindBy(xpath = "//input[contains(@value,'Unified')][@name='inFlight']")
	@CacheLookup
	private WebElement selectInbound;

	@FindBy(xpath = "//input[@name='reserveFlights']")
	@CacheLookup
	private WebElement continueButton;

	public SelectFlightPage(WebDriver driver) {
		selectFlightDriver = driver;
		PageFactory.initElements(driver, this);

	}

	public void selectFlights() {
		selectOutbound.click();
		selectInbound.click();
		continueButton.click();

	}

	public String verifyBookFlightTitle() {

		return selectFlightDriver.getTitle();
	}

}
