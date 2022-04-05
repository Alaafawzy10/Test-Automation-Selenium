package seleniumTestAutomationTasks;

import org.testng.annotations.Test;

import Pages.Home;
import Pages.Results;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

public class FireFoxTasks {
	WebDriver webDriver;
	Home home;
	Results result;

	@Test(description = "Given I am on the FireFox Home Page, Then navigate to Google Home Page , Then Search For 'TestNG',"
			+ "Then Show The Results Page And Check that the text of the second result is [Selenium - Web Browser Automation]",priority = 1)
	public void AssertFourthResultTextContainsTestNG() {

		home.navigate();

		home.SearchInGoogle("TestNG");

		result = new Results(webDriver);

		Assert.assertTrue(result.SearchInResultPage(4).contains("TestNG Tutorial"));

	}

	@BeforeMethod
	public void beforeMethod() {

		WebDriverManager.firefoxdriver().setup();

		webDriver = new FirefoxDriver();

		home = new Home(webDriver);

		webDriver.manage().window().maximize();

	}

	
	  @AfterMethod public void afterMethod() { webDriver.close(); }
	 

}
