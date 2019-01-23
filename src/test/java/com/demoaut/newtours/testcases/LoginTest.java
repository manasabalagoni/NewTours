package com.demoaut.newtours.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.demoaut.newtours.constants.IConstants;
import com.demoaut.newtours.pages.FindFlightPage;
import com.demoaut.newtours.pages.FlightConfirmation;
import com.demoaut.newtours.pages.LoginPage;
import com.demoaut.newtours.pages.SelectFlightPage;

public class LoginTest {

	public WebDriver driver;
	// LoginPage loginPage;

	@BeforeTest
	public void launchBrowser() {

		System.setProperty("webdriver.chrome.driver", IConstants.CHROMEDRIVERPATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(IConstants.IMPLICITWAITSTND, TimeUnit.SECONDS);
		driver.get("http://newtours.demoaut.com");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
	}

	@Test(priority=1, enabled=true)
	public void loginToMercuryTours() {
		LoginPage loginPage = new LoginPage(driver);
		
		SelectFlightPage selectFlight = new SelectFlightPage(driver);
		FlightConfirmation flightConfirmation = new FlightConfirmation(driver);
		
		String currentTitle = loginPage.loginToDemoaut("mercury", "mercury");
		String pageSource = driver.getPageSource();

		if (pageSource.contains("Whitelabel Error")) {
			System.out.println("Login Unsuccessful. Fatal Error");
			driver.quit();

		} else {
			Assert.assertEquals(currentTitle, "Find a Flight: Mercury Tours:");
		}

	}
	
	@Test(dependsOnMethods= {"loginToMercuryTours"}, priority=2)
	public void findFlightTest() {
		
		FindFlightPage findFlightPage = new FindFlightPage(driver);
		findFlightPage.findFlight();
		String title = findFlightPage.verifySelectFlightTitle();
				
				Assert.assertEquals(title, "Select a Flight: Mercury Tours");
	}
	
	@Test(priority=3)
	public void selectFlightTest() {
		
		SelectFlightPage selectFlightPage = new SelectFlightPage(driver);
		
		selectFlightPage.selectFlights();
		String title=selectFlightPage.verifyBookFlightTitle();
		Assert.assertEquals(title, "Book a Flight: Mercury Tours");
		
	}
	
	
	
	@AfterTest
	public void tearDown() {
		driver.close();
		
	}
}
