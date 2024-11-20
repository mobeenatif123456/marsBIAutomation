package webAutomationGA.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getreportObject() {
		
		
		String reportPath= System.getProperty("user.dir")+ "//reports//index.html";		
		ExtentSparkReporter reporter= new ExtentSparkReporter(reportPath);		
		reporter.config().setReportName("GAP Automation Report");
		
		ExtentReports extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Mobeen Atif");
		
		return extent;
		
		
		
		
		
		
	}

}
