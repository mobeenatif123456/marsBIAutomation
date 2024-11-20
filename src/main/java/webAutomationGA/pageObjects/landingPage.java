package webAutomationGA.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webAutomationGA.abstractComponents.abstractComponent;

public class landingPage extends abstractComponent {
	
	
	WebDriver driver;
	
	public landingPage(WebDriver driver){
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath="//input[@id='email']")
	WebElement userEmail;

	@FindBy(xpath="//input[@value='Verify']")
	WebElement verify;
	
	@FindBy(xpath="(//a[text()='Select'])[1]")
	WebElement select;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement continueButton;
	
	@FindBy(xpath="//input[@value='Yes']")
	WebElement yesSignInButton;
	
	By emailVisible= By.xpath("//input[@type='email']");
	
	@FindBy(xpath="//button[text()='Submit']")
	WebElement nextButton;
	
	@FindBy(xpath="//input[@type='password']")
	WebElement userPassword;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement emailNextButton;
	
	
	@FindBy(xpath="//span[text()='Home']")
	WebElement powerBIHome;
	
	public void login(String email,String password) throws InterruptedException {
		
		
		waitforElementDisplayed(By.xpath("//input[@id='email']"));
		userEmail.sendKeys(email);
		Thread.sleep(1000);
		waitforElementDisplayed(By.xpath("//button[text()='Submit']"));
		Thread.sleep(1000);
		nextButton.click();
		
		waitforElementDisplayed(By.xpath("//input[@type='submit']"));
		emailNextButton.click();
		Thread.sleep(20000);
		
		waitforElementDisplayed(By.xpath("//input[@type='password']"));
		userPassword.sendKeys(password);
		
		waitforElementDisplayed(By.xpath("//input[@value='Verify']"));
		verify.click();
		
		waitforElementDisplayed(By.xpath("(//a[text()='Select'])[1]"));
		select.click();
		
		Thread.sleep(20000);
		
		waitforElementDisplayed(By.xpath("//input[@value='Verify']"));
		verify.click();
		
		waitforElementDisplayed(By.xpath("//input[@value='Continue']"));
		continueButton.click();
		
		waitforElementDisplayed(By.xpath("//input[@value='Yes']"));
		yesSignInButton.click();
		
		waitforElementDisplayed(By.xpath("//span[text()='Home']"));
		
		
	}
	
	public void goTo() {
		driver.get("https://app.powerbi.com/singleSignOn?experience=power-bi&ru=https%3A%2F%2Fapp.powerbi.com%2Fgroups%2F2d069fe6-e945-4e4b-922f-0fdc9b348db9%2Freports%2F4437ad1f-578c-46f1-95fa-d324c5ccd18d%2FReportSection%3Fexperience%3Dpower-bi%26noSignUpCheck%3D1");
	}
	

}
