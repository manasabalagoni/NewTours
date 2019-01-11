package com.demoaut.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FindFlightPage {

	private WebDriver findFlightPageDriver;
	
	@FindBy(css="input[value='Business']")
	@CacheLookup
	private WebElement travelClass;
	
	@FindBy(name="airline")
	@CacheLookup
	private WebElement carrier;
	
	@FindBy(xpath="//input[@name='findFlights']")
	@CacheLookup
	private WebElement continueButton;

	
	public FindFlightPage(WebDriver driver) {
		findFlightPageDriver=driver;
		PageFactory.initElements(driver, this);
		
	}
	public void findFlight(){
		
		travelClass.click();
		Select selectCarrier= new Select(carrier);
		selectCarrier.selectByIndex(2); // Unified Airlines
		continueButton.click();
	}
	
		
	
}
