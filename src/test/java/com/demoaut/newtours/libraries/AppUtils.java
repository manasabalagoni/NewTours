package com.demoaut.newtours.libraries;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.demoaut.newtours.constants.IConstants;

public class AppUtils {

	public static WebDriver OpenBrowser(String browserType, String testName) throws MalformedURLException {
		WebDriver driver = null;
		
		try {
			 if (browserType.equalsIgnoreCase(IConstants.CHROME)) {
				System.setProperty("webdriver.chrome.driver", IConstants.CHROMEDRIVERPATH);
				
	/*			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
	*/			
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--test-type");
				chromeOptions.addArguments("start-maximized");
				chromeOptions.addArguments("--incognito");
				chromeOptions.addArguments("--disable-notifications");
				chromeOptions.addArguments("--disable-popup-blocking");
				
				driver= new ChromeDriver(chromeOptions);
			} 

			driver.manage().timeouts().implicitlyWait(IConstants.IMPLICITWAITSTND, TimeUnit.SECONDS);
				

		} catch (Exception e) {
			System.out.println("*************Error in Setting browser************" + e);
		}
		return driver;
	}
	
}
