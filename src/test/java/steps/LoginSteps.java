package steps;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.cucumber.listener.Reporter;
import com.mindvalley.pages.HomePage;
import com.mindvalley.pages.LoginPage;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.Load_Properties;
import utility.SeleniumDriver;

public class LoginSteps {

	private LoginPage loginpage;
	private HomePage homepage;

	@Before
	public void setup() {
		SeleniumDriver.setUpDriver();
		SeleniumDriver.openPage("http://facebook.com");
	}

	@Given("^a logged out user on the Login page$")
	public void a_logged_out_user_on_the_Login_page() {

		homepage = new HomePage();

		// Validating if user is already logged in, if yes then logging out
		if (homepage.isUserLoggedIn()) {
			homepage.logout_functionality();
		}
		Reporter.addStepLog("Log: The user is on login screen");

	}

	@When("^the user provides a valid email with an invalid password and selects log in$")
	public void the_user_provides_a_valid_email_with_an_invalid_password_and_selects_log_in() throws IOException {
		loginpage = new LoginPage();
		loginpage.enterEmailID(Load_Properties.getProp().getProperty("username"));
		Reporter.addStepLog("Log: Email ID has been entered successfully");
		loginpage.enterPassword(Load_Properties.getProp().getProperty("password"));
		Reporter.addStepLog("Log: Password has been entered successfully");
		loginpage.clickOnLogIn_Bttn();
		Reporter.addStepLog("Log: Login button has been clicked successfully");

	}

	@Then("^the user shall be redirected to the ?unsuccessful login? page$")
	public void the_user_shall_be_redirected_to_the_unsuccessful_login_page() throws Throwable {

		String actual_result = SeleniumDriver.getDriver().getCurrentUrl();
		String expected_result = "https://www.facebook.com/login/device-based/regular/login/";

		Assert.assertTrue("The User has not been redirected to unsuccessful login page",
				actual_result.contains(expected_result));
		Reporter.addStepLog("Log: The user has been redirected to unsuccessful login page");

	}

	
	//Code to take screenshot on account of failure
	@After(order = 1)
	public void afterScenario(Scenario scenario) {
		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			try {
				// This takes a screenshot from the driver at save it to the specified location
				File sourcePath = ((TakesScreenshot) SeleniumDriver.getDriver()).getScreenshotAs(OutputType.FILE);

				// Building up the destination path for the screenshot to save
				// Also make sure to create a folder 'screenshots' with in the cucumber-report
				// folder
				File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/"
						+ screenshotName + ".png");

				// Copy taken screenshot from source location to destination location
				FileUtils.copyFile(sourcePath, destinationPath);

				// This attach the specified screenshot to the test
				Reporter.addScreenCaptureFromPath(destinationPath.toString());
			} catch (IOException e) {
			}
		}
	}

	@After(order = 0)
	public void teardown() {
		SeleniumDriver.tearDown();
	}

}
