package config;

import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	@BeforeTest
	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter extentSparkReporter =new ExtentSparkReporter(path);
		extentSparkReporter.config().setReportName("Swag Lab Test Report");
		extentSparkReporter.config().setDocumentTitle("Swag Lab Test Results");
		
		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("Software Tester :: ", "Ajithra Jayan J");
		
		return extentReports;
	
	}

}
