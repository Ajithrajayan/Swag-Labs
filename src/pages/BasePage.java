package pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import config.DriverUtil;
import config.TimeOut;

public class BasePage {

	public static WebDriver driver;
	
	
	@BeforeTest (alwaysRun = true)
	public void setup() throws IOException {
		
		DriverUtil driverUtil = new DriverUtil();
		driver = driverUtil.setupWebDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TimeOut.HIGH.getValue()));
		
	} 
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File file =new File(System.getProperty("user.dir") + "//reports//" + testCaseName +".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName +".png";
		
	} 
	
	
	@AfterTest (alwaysRun = true)
	public void quitBrowser() {
		
		driver.quit();
	}

}
