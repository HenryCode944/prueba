package io.cucumber.skeleton.libraries;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.Scenario;




public class TestContext {
	private WebDriver driver;
	public Scenario scenario;
	public HashMap<String, String> data = new HashMap<>();
	
	public TestContext(){
		driver = new DriverManager().getDriver();
	}
	
	public WebDriver getWebDriverManager() {
		return driver;
	}

}