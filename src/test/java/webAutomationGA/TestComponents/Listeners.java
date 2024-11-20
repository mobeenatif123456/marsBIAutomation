package webAutomationGA.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import webAutomationGA.resources.ExtentReporterNG;

public class Listeners extends baseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent= ExtentReporterNG.getreportObject();
	
	ThreadLocal<ExtentTest> extenttest= new ThreadLocal<ExtentTest>(); //thread safe
	
	public Listeners() {
		
		
	}
	
	
    @Override
    public void onTestStart(ITestResult result) {
        
    	test= extent.createTest(result.getMethod().getMethodName());
    	extenttest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        
    	
    	extenttest.get().log(Status.PASS, "Test Passed");
    	
    }

    @Override
    public void onTestFailure(ITestResult result) {
        
    	//screenshot taking
    	extenttest.get().fail(result.getThrowable());
    	String filepath= null;
    	
    	try {
			driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extenttest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        // Your implementation
    	extenttest.get().log(Status.SKIP, "Test Skipped");
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Your implementation
    }
    
    @Override
    public void onStart(ITestContext context) {
        // Your implementation
    	extent.flush();
    	
    }
    
    
    @Override
    public void onFinish(ITestContext context) {
        // Your implementation
    	extent.flush();
    }
    
    

    
}
