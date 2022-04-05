package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Home {
	private WebDriver webDriver;
	
	private String url="https://www.google.com/ncr";
	
	private By GoogleLogo = By.xpath("//img[@alt='Google']");
	
	private By GoogleSearch = By.xpath("//input[@title='Search']");
	
	
	public Home(WebDriver webDriver) {
		// TODO Auto-generated constructor stub
		this.webDriver=webDriver;
	}
	
	public void navigate() {
		webDriver.navigate().to(url);
	}
	public String getTitle() {
		return webDriver.getTitle();
	}
	public boolean isGoogleLogoDisplayed() {
		return webDriver.findElement(GoogleLogo).isDisplayed();
	}
	
	public void SearchInGoogle(String keySearch) {
		
		WebElement searchElement = webDriver.findElement(GoogleSearch);
		
		searchElement.sendKeys(keySearch+ Keys.ENTER);
		
	}
}
