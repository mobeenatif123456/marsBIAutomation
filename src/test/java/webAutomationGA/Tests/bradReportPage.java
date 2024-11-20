package webAutomationGA.Tests;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import webAutomationGA.TestComponents.baseTest;
import webAutomationGA.pageObjects.bradReport;

public class bradReportPage extends baseTest {

    private bradReport test; // Declare bradReport instance

    @BeforeClass(alwaysRun = true)
    public void setup() throws IOException, InterruptedException {
        // Perform login only once
        landingpage.login("mobeen.atif@effem.com", "Kabul@12345678");

        // Initialize bradReport object after login
        test = new bradReport(driver);
    }

    @Test(priority = 0, groups = {"smokeTests"}, description = "Verify brad report dropdowns are present")
    public void dropdownVisible() throws InterruptedException {
        // Verify dropdown visibility
        test.dropdownVisible();
    }

    @Test(priority = 1, groups = {"smokeTests"}, description = "Verify values under available columns are present")
    public void availableColumnValues() throws InterruptedException {
        // Verify values under available columns
        test.valuesUnderAvailableColumns();
    }
}
