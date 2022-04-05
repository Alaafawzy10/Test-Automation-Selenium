package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Table {

	private WebDriver webDriver;

	private String url="https://www.w3schools.com/html/html_tables.asp";
	public Table(WebDriver webDriver) {
		// TODO Auto-generated constructor stub
		this.webDriver=webDriver;
	}
	public void navigate() {
		webDriver.navigate().to(url);
	}

	
	public String SelectCell(String SelectWith) {
		
	String xpath ="//tr[contains(.,'"+SelectWith+"')]/td[3]";
	return webDriver.findElement(By.xpath(xpath)).getText();
		
	}
	
	public List<WebElement> SelectRowsTable(String XpathTable) {
		
		
		return webDriver.findElements(By.xpath(XpathTable));
			
		}
	
	
}
