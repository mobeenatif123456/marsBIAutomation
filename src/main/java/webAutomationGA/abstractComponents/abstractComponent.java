package webAutomationGA.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class abstractComponent {
	
	
	WebDriver driver;
	
	public abstractComponent(WebDriver driver) {
		
		this.driver= driver;
		
	}
	
	public void waitforElementDisplayed(By findby) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(90));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
		
	}
	
    public void elementDisplayed(WebElement ele) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(90));
		wait.until(ExpectedConditions.visibilityOf(ele));
		
	}
	
   public void waitforElementNotDisplayed(By findby) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(90));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findby));
		
	}
   
   public void elementNotDisplayed(WebElement ele) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(90));
		wait.until(ExpectedConditions.invisibilityOf(ele));
		
	}
	

}
