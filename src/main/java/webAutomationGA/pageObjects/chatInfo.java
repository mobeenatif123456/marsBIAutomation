package webAutomationGA.pageObjects;

public class chatInfo {
	
	private String name;
    private String email;
    private String question;
    private String queueEntryTime;
    private String queueWaitedTime; // In minutes

    public chatInfo(String name, String email, String question, String queueEntryTime, String queueWaitedTime) {
        this.name = name;
        this.email = email;
        this.question = question;
        this.queueEntryTime = queueEntryTime;
        this.queueWaitedTime = queueWaitedTime;
    }
    
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getQuestion() {
        return question;
    }

    public String getQueueEntryTime() {
        return queueEntryTime;
    }

    public String getQueueWaitedTime() {
        return queueWaitedTime;
    }

}
