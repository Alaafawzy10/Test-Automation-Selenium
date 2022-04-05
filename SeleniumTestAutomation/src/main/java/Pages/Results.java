package Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Results {
	private WebDriver webDriver;
	
	private String numPage ;
	
	private By resultsInPage=By.xpath("//*[@id='rso']//h3");
	
	private By resultStats =By.id("result-stats");
	
	private By ResultNumBar;
			
	public Results(WebDriver webDriver) {
		// TODO Auto-generated constructor stub
		this.webDriver=webDriver;
	}
	public Results(WebDriver webDriver,String numPage) {
		// TODO Auto-generated constructor stub
		this.webDriver=webDriver;
		this.numPage=numPage;
		ResultNumBar=By.xpath("//a[@aria-label='Page "+numPage+"']");
	}
	public void ResultsClick() {
	webDriver.findElement(ResultNumBar).click();
	}
	
	public String ReturnResultsStatsText() {
	return webDriver.findElement(resultStats).getText();
	}
	
	public String SearchInResultPage(int numRsult) {
		
		List<WebElement> resultElements = webDriver.findElements(resultsInPage);
		
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		return resultElements.get(numRsult).getText();
	}

	public List<WebElement> FindLinkInResultsPage(String link) {
		
		String xpath =  "//a[contains(@href," +"'"+link+"'"+")]";
		

		return webDriver.findElements(By.xpath(xpath));

	}
	
}
