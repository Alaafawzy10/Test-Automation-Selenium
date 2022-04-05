package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UploadImage {

	private WebDriver webDriver;

	private String url="http://the-internet.herokuapp.com/upload";
	
	private By fileUpload = By.id("file-upload");
	
	private By fileSubmit = By.id("file-submit");
	
	private By UploadContent =By.tagName("h3");
	
	public UploadImage(WebDriver webDriver) {
		// TODO Auto-generated constructor stub
		this.webDriver=webDriver;
	}
	public void navigate() {
		webDriver.navigate().to(url);
		}
	
	public void UploadFile(String FilePath) {
		
		webDriver.findElement(fileUpload).sendKeys(FilePath);
		
		webDriver.findElement(fileSubmit).click();
		}
	
	public String checkUploadFile() {
		
		return webDriver.findElement(UploadContent).getText();
		}
}
