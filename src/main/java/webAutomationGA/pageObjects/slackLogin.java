package webAutomationGA.pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import webAutomationGA.abstractComponents.abstractComponent;

public class slackLogin extends abstractComponent {

    WebDriver driver;

    public slackLogin(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//input[@name='email'])[1]")
    WebElement email;
    
    @FindBy(xpath = "//button[text()='Sign In With Email']")
    WebElement signInButton;
    
    @FindBy(xpath = "//h1[text()='Check your email for a code']")
    WebElement emailForCodeHeading;
    
    @FindBy(xpath = "(//a/img)[2]")
    WebElement openGmail;
    
    @FindBy(xpath = "//span[text()='Create account']")
    WebElement gmailEmail;
    
    @FindBy(xpath = "//input[@type='password']")
    WebElement gmailPassword;
    
    @FindBy(xpath = "//table[@class='F cf zt']/tbody/tr[1]")
    WebElement getCodeEmail;
    
    @FindBy(xpath = "//h2[contains(text(),'Slack confirmation code:')]")
    WebElement slackCode;
    
    @FindBy(xpath = "(//div[@class='split_input_item']/input)[1]")
    WebElement codeInputField;
    
    @FindBy(xpath = "//a[text()='use Slack in your browser']")
    WebElement useSlackInBrowser;
    
    @FindBy(xpath = "//span[text()='HL Pro Tools']")
    WebElement slackloginVerification;
    
    @FindBy(xpath = "(//span[text()='platinum-hl-support-test-agency'])[2]")
    WebElement testAgencyVerification;
    
   
    public void goTo() throws InterruptedException {
    	
    	
        driver.get("https://hlprotools.slack.com/");
        Thread.sleep(1000);
        
        waitforElementDisplayed(By.xpath("(//input[@name='email'])[1]"));
		Thread.sleep(1000);
		
		email.sendKeys("mobeen.atif@baam.com");
		
		email.sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		
		waitforElementDisplayed(By.xpath("//h1[text()='Check your email for a code']"));
		
		waitforElementDisplayed(By.xpath("(//a/img)[2]"));

		openGmail.click();
		
		Thread.sleep(1000);
		
		String originalTab = driver.getWindowHandle();
		
		System.out.println(originalTab);
		
		Set<String> windowHandles = driver.getWindowHandles();
		
		List<String> handlesList = new ArrayList<>(windowHandles);
		
		System.out.println(handlesList.size());
		
		driver.switchTo().window(handlesList.get(2));
		
		String newTab = driver.getWindowHandle();
		
		System.out.println(newTab);
		
		Thread.sleep(5000);
		
		driver.switchTo().activeElement().sendKeys("mobeen.atif@baam.com");
		
		driver.switchTo().activeElement().sendKeys(Keys.ENTER);
		
		Thread.sleep(5000);
		
        driver.switchTo().activeElement().sendKeys("Kabul@123");
		
		driver.switchTo().activeElement().sendKeys(Keys.ENTER);
		
		Thread.sleep(5000);
		
		waitforElementDisplayed(By.xpath("//table[@class='F cf zt']/tbody/tr[1]"));
		
		getCodeEmail.click();
		
		Thread.sleep(5000);
		
		waitforElementDisplayed(By.xpath("//h2[contains(text(),'Slack confirmation code:')]"));
		
		String text= slackCode.getText();
		
		System.out.println(text);
		
		String code = extractConfirmationCode(text);
		
		System.out.println(code);
		
		driver.switchTo().window(handlesList.get(1));
		
		Thread.sleep(5000);
		
		codeInputField.sendKeys(code);
		
		Thread.sleep(5000);
		
		waitforElementDisplayed(By.xpath("//a[text()='use Slack in your browser']"));
	
		useSlackInBrowser.click();
		
		Thread.sleep(2000);
		
		waitforElementDisplayed(By.xpath("//span[text()='HL Pro Tools']"));
		
		driver.get("https://app.slack.com/client/T02348ZHZAB/C067JSBLEUB");
		
        Thread.sleep(1000);
		
        waitforElementDisplayed(By.xpath("(//span[text()='platinum-hl-support-test-agency'])[2]"));
        
        
    }
    
    
     public void createMessageInSlacks(List<chatInfo> userEmails) throws InterruptedException {
    	 
    	 
    	 for (chatInfo chat : userEmails) {
    	        String message = String.format(
    	            "Hey Team,\n\n" +
    	            "This user has left the queue:\n" +
    	            "Email: %s\n" +
    	            "Name: %s\n" +
    	            "Question: %s\n" +
    	            "Entered Queue On: %s\n" +
    	            "Total Waiting Time: %s\n",
    	            chat.getEmail(),       // Assuming getEmail() method
    	            chat.getName(),        // Assuming getName() method
    	            chat.getQuestion(),     // Assuming getQuestion() method
    	            chat.getQueueEntryTime(), // Assuming getQueueEntryTime() method
    	            chat.getQueueWaitedTime() // Assuming getQueueWaitedTime() method
    	        );
    	        
    	        driver.switchTo().activeElement().sendKeys(message);
    	        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
    	        
    	        Thread.sleep(2000);
    	 }
   
    }
    
    public static String extractConfirmationCode(String text) {
        // Regular expression to match the code pattern (e.g., M4P-QQN)
        Pattern pattern = Pattern.compile("([A-Z0-9]+-[A-Z0-9]+)");
        Matcher matcher = pattern.matcher(text);

        // If a match is found, return it
        if (matcher.find()) {
            return matcher.group(1);
        }

        return null; // Return null if no code is found
    }
    
    
    
   
    
}