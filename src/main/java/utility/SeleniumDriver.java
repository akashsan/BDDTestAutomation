package utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumDriver {

	private static SeleniumDriver seleniumDriver;

	// initialize webdriver
	private static WebDriver driver;

	// initialize timeouts
	private static WebDriverWait waitDriver;
	public final static int TIMEOUT = 3;
	public final static int PAGE_LOAD_TIMEOUT = 50;

	private SeleniumDriver() {

		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		waitDriver = new WebDriverWait(driver, TIMEOUT);
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		String window = driver.getWindowHandle();
		System.out.println("Window ->" + window);

	}

	public static void openPage(String url) {
		System.out.println(url);
		System.out.println(driver);
		driver.get(url);
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setUpDriver() {
		if (seleniumDriver == null)
			seleniumDriver = new SeleniumDriver();

	}

	public static void tearDown() {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
		seleniumDriver = null;
	}

	public static void waitForPageToLoad() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
