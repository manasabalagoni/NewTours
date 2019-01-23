package com.demoaut.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BookFlight {

	private WebDriver bookFlightDriver;

	@FindBy(name = "passFirst0")
	private WebElement firstName;
	
	@FindBy(name = "passLast0")
	private WebElement lastName;
	
	@FindBy(name = "creditnumber")
	private WebElement creditCardNum;
	
	@FindBy(name = "cc_exp_dt_mn")
	private WebElement expiryMonth;
	
	@FindBy(name = "cc_exp_dt_yr")
	private WebElement expiryYear;
	
	@FindBy(xpath="//input[@name='buyFlights']")
	private WebElement purchaseButton;
	
	public BookFlight(WebDriver driver) {
		bookFlightDriver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public String fillBillingDetails() {
		
		firstName.sendKeys("Rohan");
		lastName.sendKeys("Swayambhatla");
		creditCardNum.sendKeys("44556677");
		Select monthSel= new Select(expiryMonth);
		Select yearSel = new Select(expiryYear);
		monthSel.selectByValue("10");
		yearSel.selectByValue("2010");
		purchaseButton.click();
		
		return bookFlightDriver.getTitle();
	}
	
}
