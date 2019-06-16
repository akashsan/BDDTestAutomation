package com.mindvalley.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.SeleniumDriver;

public class HomePage {
	boolean result;

	public HomePage() {

		PageFactory.initElements(SeleniumDriver.getDriver(), this);
	}

	// Locators
	@FindBy(id = "userNavigationLabel")
	WebElement acc_setting;

	@FindBy(xpath = "//span[contains(text(), 'Log Out')]")
	WebElement logout_bttn;

	@FindBy(xpath = "//a[contains(text(), 'Home')]")
	List<WebElement> home_displayed;

	// Actions on locators
	public void logout_functionality() {
		acc_setting.click();
		logout_bttn.click();
	}

	public boolean isUserLoggedIn() {
		return home_displayed != null && home_displayed.size() > 0;
	}

}
