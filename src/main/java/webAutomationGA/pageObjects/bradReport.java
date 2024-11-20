package webAutomationGA.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webAutomationGA.abstractComponents.abstractComponent;

public class bradReport extends abstractComponent {
	
	
	WebDriver driver;
	
	public bradReport(WebDriver driver){
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath="//h3[text()='Customer ID']")
	WebElement customerID;
	
	@FindBy(xpath="//h3[text()='Available Columns']/../../../..//div[text()='All']")
	WebElement allColumnsDropdown;
	
	
	

	public void dropdownVisible() throws InterruptedException {
		
		waitforElementDisplayed(By.xpath("//h3[text()='Customer ID']"));
		waitforElementDisplayed(By.xpath("//h3[text()='Interaction ID']"));
		waitforElementDisplayed(By.xpath("//h3[text()='Year / Period']"));
		waitforElementDisplayed(By.xpath("//h3[text()='Available Columns']"));
		
	}
	
public void valuesUnderAvailableColumns() throws InterruptedException {
	
	
	waitforElementDisplayed(By.xpath("//h3[text()='Available Columns']/../../../..//div[text()='All']"));
	allColumnsDropdown.click();
	waitforElementDisplayed(By.xpath("//div[@title='Year']"));
	waitforElementDisplayed(By.xpath("//div[@title='Period']"));
	waitforElementDisplayed(By.xpath("//div[@title='Date']"));
	waitforElementDisplayed(By.xpath("//div[@title='Delivery Date']"));
	waitforElementDisplayed(By.xpath("//div[@title='Header Doc Date']"));
	waitforElementDisplayed(By.xpath("//div[@title='Request Delivery Date']"));

		
	}
	
	

}
