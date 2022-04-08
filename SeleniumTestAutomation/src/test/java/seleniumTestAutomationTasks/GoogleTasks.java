package seleniumTestAutomationTasks;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.CheckDemo;
import Pages.DragAndDrop;
import Pages.Home;
import Pages.Results;
import Pages.Table;
import Pages.UploadImage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleTasks {
	WebDriver webDriver;
	Home home;
	Results result;
	CheckDemo checkDemo;
	Table table;
	UploadImage Uimg;
	DragAndDrop dragAndDrop;

	@Test(priority = 1,description = "Given I am on the Google Home Page , Then The Page Title Will be 'Google'")
	public void AssertGooglePageTitleIsCorrect() {

		String actual = home.getTitle();

		Assert.assertEquals("Google",actual);
	}

	@Test(priority = 2,description = "Given I am on the Google Home Page , Then Page Logo Will be Display")
	public void AssertGoogleLogoIsDisplay() {

		Assert.assertTrue(home.isGoogleLogoDisplayed());

	}
	@Test(priority = 3,description = "Given I am on the Google Home Page , Then Search For 'Selenium WebDriver' , "
			+ "Then Show The Results Page And check Result Stats Not Null")
	public void AssertResultStatISNotNull() {

		home.SearchInGoogle("Selenium WebDriver");

		result = new Results(webDriver);
		
		Assert.assertNotNull(result.ReturnResultsStatsText());

	}

	@Test
	(priority = 4,description = "Given I am on the Google Home Page , Then Search For 'Selenium WebDriver',"
			+ "Then Show The Results Page And Check that the text of the second result is [Selenium - Web Browser Automation]")
	public void AssertSecondResultTextContainsSelenium() {

		home.SearchInGoogle("Selenium WebDriver");

		result = new Results(webDriver);

		Assert.assertEquals("Selenium - Web Browser Automation", result.SearchInResultPage(2));

	}

	@Test(priority = 5,description = "Given I am on the Google Home Page , Then Search For 'Cucumber IO,Then Navigate to the Second Results Page"
			+ "then Check that the link of the second result contains [https://www.linkedin.com]")
		
	public void AssertSecondResultContainsLink() {

		home.SearchInGoogle("Cucumber IO");

		result = new Results(webDriver, "2");

		result.ResultsClick();

		Assert.assertNotNull(result.FindLinkInResultsPage("https://www.linkedin.com"));

	}

	@Test(priority = 6,description = "Given I am on the Google Home Page ,Then Navigate [http://the-internet.herokuapp.com/checkboxes] , Then Check Checkbox 1 ,"
			+ "then Check that both Checkboxes are checked")
	public void AssertBothCheckboxesChecked() {
		checkDemo = new CheckDemo(webDriver);

		checkDemo.navigate();

		checkDemo.CheckBoxFromForm(1);

		Assert.assertTrue(checkDemo.CheckTwoBoxesIsSelected(1, 2));

	}

	@Test(priority = 7,description = "Given I am on the Google Home Page ,Then Navigate [https://www.w3schools.com/html/html_tables.asp] ,"
			+ "Then Check that the Country for the Company [Ernst Handel] is [Austria]")
	public void AssertCountryISAustria() {
		table.navigate();

		Assert.assertEquals(table.SelectCell("Ernst Handel"), "Austria");
	}

	@Test(priority = 8,description = "Given I am on the Google Home Page ,Then Navigate [https://www.w3schools.com/html/html_tables.asp] ,"
			+ "Then Check that the Country for the Company [Ernst Handel] is [Austria] , The Difference in this Function That get All Rows And split Row By Row")
	public void AssertCountryISAustria_AnotherTypeOfSelectCell() {

		table.navigate();

		List<WebElement> rows = table.SelectRowsTable("//table[@id='customers']/tbody/tr");

		String actual = "";

		for (int i = 1; i < rows.size(); i++) {

			if (rows.get(i).getText().split(" ")[0].equals("Ernst")) {

				actual = rows.get(i).getText().split(" ")[4];

				Assert.assertEquals("Austria", actual);

				break;
			}
		}

	}

	@Test(priority = 9,description = "Given I am on the Google Home Page ,Then Navigate [http://the-internet.herokuapp.com/upload],Then Upload a small image file"
			+ "Then Check that the file was uploaded successfully")
	public void AssertUploadImageFileSuccessfully() {

		Uimg.navigate();

		Uimg.UploadFile("/Users/alaafawzy/Downloads/H.png");

		Assert.assertEquals("File Uploaded!", Uimg.checkUploadFile());

	}

	@Test(priority = 10,description = "Given I am on the Google Home Page ,Then Navigate [https://jqueryui.com/resources/demos/droppable/default.html],"
			+ "Then Drag [Drag me to my target] and drop it on [Drop here] , Then Check that the text has been changed to [Dropped!]")
	public void AssertDragAndDropFileSuccessfully() {

	
		dragAndDrop.navigate();
		
		dragAndDrop.drageAndDropFile();

		Assert.assertEquals("Dropped!",webDriver.findElement(By.id("droppable")).getText());

	}
	@Test(priority = 11,description = "Given I am Read Items from JSON File contains Search Text and Number of Search Result which i will Search With this data on Google Home , Then Assert that the text of the tiltels in the JSONArray are Identical ")
	public void json() {
		
		JSONParser jsonP = new JSONParser();
		
		try {
			SoftAssert softAssert=new SoftAssert();
			
			JSONObject jsonO = (JSONObject) jsonP.parse(new FileReader("src/test/resources/Search Items.json"));
		
			JSONArray Items = (JSONArray) jsonO.get("Items");
			
			String textResults []= {"Selenium - Web Browser Automation","TestNG Tutorial","Cucumber IO"};
			for(int i= 0 ;i<Items.size();i++) {
				home.navigate();
				
				home.SearchInGoogle(Items.get(i).toString().split(":")[0]);
				
				result = new Results(webDriver);

				softAssert.assertEquals(result.SearchInResultPage(Integer.parseInt(Items.get(i).toString().split(":")[1])),textResults[i]);
				
			}
			softAssert.assertAll();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@BeforeMethod
	public void beforeMethod() {
		WebDriverManager.chromedriver().setup();

		webDriver = new ChromeDriver();
		
		webDriver.manage().window().maximize();

		home = new Home(webDriver);

		home.navigate();

		table = new Table(webDriver);

		Uimg = new UploadImage(webDriver);
		
		dragAndDrop = new DragAndDrop(webDriver);

	}

	@AfterMethod
	
	 public void afterMethod() { webDriver.close(); webDriver.quit(); }
	 

}
