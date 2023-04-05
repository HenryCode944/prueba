package io.cucumber.skeleton.libraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
	private WebDriver driver;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

	public DriverManager() {

	}

	public WebDriver getDriver() {
		if (driver != null) {
			return driver;
		}
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	public void closeDriver() {
		driver.close();
		driver.quit();
	}

}