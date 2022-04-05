package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckDemo {
	private WebDriver webDriver;

	private String url="http://the-internet.herokuapp.com/checkboxes";
	
	private int checkBoxNum;
	
	private By checkBox1, checkBox2;


	public CheckDemo( WebDriver webDriver) {
		// TODO Auto-generated constructor stub
		this.webDriver=webDriver;
	
	}

	public void navigate() {
		webDriver.navigate().to(url);
	}
	
	public void CheckBoxFromForm(int checkBoxNum) {
		
        this.checkBoxNum=checkBoxNum;
        
        checkBox1=By.xpath("//form[@id='checkboxes']//input["+checkBoxNum+"]");
		webDriver.findElement(checkBox1).click();
		
		
	}
	
	public Boolean CheckTwoBoxesIsSelected(int checkBoxNum1,int checkBoxNum2) {
		
      //  this.checkBoxNum=checkBoxNum;
        
        checkBox1=By.xpath("//form[@id='checkboxes']//input["+checkBoxNum1+"]");
        
        checkBox2=By.xpath("//form[@id='checkboxes']//input["+checkBoxNum2+"]");
        
        
		return webDriver.findElement(checkBox1).isSelected()&&webDriver.findElement(checkBox2).isSelected();
		
		
	}
}
