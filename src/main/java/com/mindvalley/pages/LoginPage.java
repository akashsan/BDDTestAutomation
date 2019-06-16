package com.mindvalley.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.SeleniumDriver;

public class LoginPage {

	public LoginPage() {

		PageFactory.initElements(SeleniumDriver.getDriver(), this);
	}

	// Locators
	@FindBy(css = ("[data-testid=royal_email]"))
	WebElement email_txtbox;

	@FindBy(css = ("[data-testid=royal_pass]"))
	WebElement password_txtbox;

	@FindBy(css = ("[data-testid=royal_login_button]"))
	WebElement login_bttn;

	// Actions on locators
	public void enterEmailID(String emailid) {
		email_txtbox.sendKeys(emailid);
	}

	public void enterPassword(String password) {
		password_txtbox.sendKeys(password);
	}

	public void clickOnLogIn_Bttn() {
		login_bttn.click();
	}

}
