package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class DragAndDrop {

	private WebDriver webDriver;

	private String url="https://jqueryui.com/resources/demos/droppable/default.html";
	
	private By ElementFrom=By.id("draggable");
	
	private By ElementTo=By.id("droppable");
	
	public DragAndDrop(WebDriver webDriver) {
		// TODO Auto-generated constructor stub
		this.webDriver=webDriver;
	}
	public void navigate() {
		webDriver.navigate().to(url);
		}
	
	public void drageAndDropFile() {
		
		Actions builder = new Actions(webDriver);
		
		builder.dragAndDrop(webDriver.findElement(ElementFrom), webDriver.findElement(ElementTo)).perform();

		
		
		}

}
