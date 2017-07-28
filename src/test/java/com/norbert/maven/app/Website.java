package com.norbert.maven.app;

import org.openqa.selenium.WebDriver;

public class Website {
	
	WebDriver driver;
	
	public Website(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	public void openUrl(String url) {
		driver.get(url);
	}
}