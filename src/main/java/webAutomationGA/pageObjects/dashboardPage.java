package webAutomationGA.pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import webAutomationGA.abstractComponents.abstractComponent;

public class dashboardPage extends abstractComponent {

    WebDriver driver;

    public dashboardPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Web elements for the group filter
    @FindBy(xpath = "//span[text()='Add filter']")
    WebElement addFilter;

    @FindBy(xpath = "//div[text()='Group']")
    WebElement group;

    @FindBy(xpath = "//h3[text()='Group']")
    WebElement groupHeading;

    @FindBy(xpath = "//span[text()='Platinum']")
    WebElement platinum;

    @FindBy(xpath = "//span[text()='Mastermind']")
    WebElement masterMind;
    
    @FindBy(xpath="//strong[text()='Date']")
	WebElement date;
	
	@FindBy(xpath="//span[text()='Yesterday']")
	WebElement today;

    @FindBy(xpath = "//span[text()='General']")
    WebElement general;

    @FindBy(xpath = "//div[text()='Done']")
    WebElement done;

    @FindBy(xpath = "//span[@data-dismiss-icon='true']")
    WebElement clearAll;

    // Web element to scroll and access chats
    @FindBy(xpath = "//*[@id='reports-grid-content']/p")
    WebElement chatsLocalTime;

    @FindBy(xpath = "//div[@class='css-z3s52w']")
    WebElement downArrow;
    
    
    @FindBy(xpath = "//div[@class='css-1wsdew2 privacy-masker']/div/span[text()='Email']/..")
    WebElement email;
    
    @FindBy(xpath = "//div[@class='css-1wsdew2 privacy-masker']/div/span[text()='Name']/..")
    WebElement nameOfUser;
    
    @FindBy(xpath = "//div[@class='css-1wsdew2 privacy-masker']/div/span[text()='Question']/..")
    WebElement nameOfQuestion;
    
    @FindBy(xpath = "(//div[@class='css-y4u2bi']/div)[2]/div/span[text()='Entered the queue on:']")
    WebElement userEnteredQueueTime;
    
    @FindBy(xpath = "(//div[@class='css-y4u2bi']/div)[2]/div/span[text()='Waited for:']")
    WebElement userWaitedTime;

    public void dashboardVerification() {
        waitforElementDisplayed(By.xpath("//h1[contains(text(),'Lisa!')]"));
    }

    public List<chatInfo> getChats() throws InterruptedException {
        // List to store all the filtered chats from different groups
        List<chatInfo> filteredChats = new ArrayList<>();

        // Define the groups you want to process (Platinum, Gold, Silver)
        String[] groups = {"Mastermind", "General" };

        for (String groupName : groups) {
            // Select group filter and process chats
            selectGroup(groupName);
            filteredChats.addAll(processChatsForCurrentGroup());
            clearGroupSelection(); // Unselect the previously selected group
        }

        return filteredChats;
    }

    // Method to select a group filter based on group name
    private void selectGroup(String groupName) throws InterruptedException {
        addFilter.click();
        Thread.sleep(1000);
        group.click();
        Thread.sleep(1000);
        WebElement selectedGroup = driver.findElement(By.xpath("//span[text()='" + groupName + "']"));
        selectedGroup.click();
        Thread.sleep(1000);
        groupHeading.click();
        Thread.sleep(1000);
        done.click();
        Thread.sleep(1000);
    }

    // Method to clear the group selection
    private void clearGroupSelection() throws InterruptedException {
        clearAll.click();
        Thread.sleep(1000);
    }

    // Method to process chats for the currently selected group
    private List<chatInfo> processChatsForCurrentGroup() throws InterruptedException {
    	
        List<chatInfo> groupFilteredChats = new ArrayList<>();

        // Locate the chats in the queue for the current group
        List<WebElement> allChats = driver.findElements(By.xpath("//div[@class='css-cssveg']/div")); // Update the XPath as needed

        System.out.println(allChats.size());
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", chatsLocalTime);
        Thread.sleep(1000);
        
        // Traverse each chat and filter based on the time waited
        for (int i = allChats.size() - 1; i >= 2; i--) { // Adjust the range as per your need
            WebElement chat = allChats.get(i);
            js.executeScript("arguments[0].scrollIntoView(true);", chat);
            Thread.sleep(5000);

            chat.click();
            Thread.sleep(5000);
            
            // Locate the wait time element in each chat
            WebElement waitTimeElement = chat.findElement(By.xpath("//div/span[text()='Waited for:']/.."));
            String text = waitTimeElement.getText();

            Pattern pattern = Pattern.compile("(\\d+)min");
            Matcher matcher = pattern.matcher(text);
            int minutes = 0;

            if (matcher.find()) {
                String minutesStr = matcher.group(1);
                minutes = Integer.parseInt(minutesStr);
            }

            // Check if the wait time is at least 4 minutes
            if (minutes >= 4) {
            	
            	waitforElementDisplayed(By.xpath("//div[@class='css-1wsdew2 privacy-masker']/div/span[text()='Email']/.."));
            	
                String userEmail = email.getText();
                
                waitforElementDisplayed(By.xpath("//div[@class='css-1wsdew2 privacy-masker']/div/span[text()='Name']/.."));
                
                String userName = nameOfUser.getText();
                
                waitforElementDisplayed(By.xpath("//div[@class='css-1wsdew2 privacy-masker']/div/span[text()='Question']/.."));
                
                String userQuestion = nameOfQuestion.getText();
                
                waitforElementDisplayed(By.xpath("(//div[@class='css-y4u2bi']/div)[2]/div/span[text()='Entered the queue on:']/.."));
                
                String queueEntryTime = userEnteredQueueTime.getText();
                
                waitforElementDisplayed(By.xpath("(//div[@class='css-y4u2bi']/div)[2]/div/span[text()='Waited for:']/.."));
                
                String queueWaitedTime = userWaitedTime.getText();
                
                chatInfo chatInfo = new chatInfo(userName, userEmail, userQuestion, queueEntryTime, queueWaitedTime);
                
                groupFilteredChats.add(chatInfo);
            }

            Thread.sleep(1000);
            downArrow.click();
            Thread.sleep(1000);
        }

        return groupFilteredChats; // Return chats filtered for this group
    }

    public void goTo() throws InterruptedException {
        driver.get("https://my.livechatinc.com/reports/queue-abandonment");
        Thread.sleep(1000);
        waitforElementDisplayed(By.xpath("(//div[text()='Queue abandonment'])[1]"));
        date.click();
        waitforElementDisplayed(By.xpath("//span[text()='Today']"));
        today.click();
        Thread.sleep(5000);
    }
}