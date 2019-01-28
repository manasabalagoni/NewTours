package com.demoaut.newtours.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.demoaut.newtours.constants.IConstants;
import com.demoaut.newtours.pages.FindFlightPage;
import com.demoaut.newtours.pages.FlightConfirmation;
import com.demoaut.newtours.pages.LoginPage;
import com.demoaut.newtours.pages.SelectFlightPage;

public class LoginTest {

	public WebDriver driver;

	// LoginPage loginPage;

	@DataProvider(name = "users")
	public Object[][] usersList() {

		Object[][] data = new Object[2][2];

		data[0][0] = "mercury";
		data[0][1] = "mercury";
		data[1][0] = "username2";
		data[1][1] = "password2";
		return data;
	};

	@BeforeTest
	@Parameters({ "browserName" })
	public void launchBrowser(String browserName) {

		if (browserName.toLowerCase().equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", IConstants.CHROMEDRIVERPATH);
			driver = new ChromeDriver();

			// these logs are visible in test=output/old/index.html
			Reporter.log("Browser Opened", true); // setting to true will print in console too

			driver.manage().timeouts().implicitlyWait(IConstants.IMPLICITWAITSTND, TimeUnit.SECONDS);
			driver.get("http://newtours.demoaut.com");
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		}
	}

	@Test(priority = 1, enabled = true, description = "Description: Testing if user is abole to login to Mecrcury Tours")
	public void loginToMercuryTours() {
		LoginPage loginPage = new LoginPage(driver);

		SelectFlightPage selectFlight = new SelectFlightPage(driver);
		FlightConfirmation flightConfirmation = new FlightConfirmation(driver);

		String currentTitle = loginPage.loginToDemoaut("mercury","mercury");

		// checking if there was any error thrown
		String pageSource = driver.getPageSource();

		if (pageSource.contains("Whitelabel Error")) {
			System.out.println("Login Unsuccessful. Fatal Error");
			driver.quit();

		} else {
			Assert.assertEquals(currentTitle, "Find a Flight: Mercury Tours:");
		}

	}

	@Test(dependsOnMethods = { "loginToMercuryTours" }, priority = 2, invocationCount = 1)
	public void findFlightTest() {

		FindFlightPage findFlightPage = new FindFlightPage(driver);
		findFlightPage.findFlight();
		String title = findFlightPage.verifySelectFlightTitle();

		Assert.assertEquals(title, "Select a Flight: Mercury Tours");
	}

	@Test(priority = 3)
	public void selectFlightTest() {

		SelectFlightPage selectFlightPage = new SelectFlightPage(driver);

		selectFlightPage.selectFlights();
		String title = selectFlightPage.verifyBookFlightTitle();
		Assert.assertEquals(title, "Book a Flight: Mercury Tours");

	}

	@AfterTest
	public void tearDown() {
		driver.close();

	}
}
