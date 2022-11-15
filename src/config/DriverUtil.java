package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverUtil {

	public WebDriver driver;
	public WebDriverWait wait;

	public WebDriver setupWebDriver() throws IOException {

		Properties properties = new Properties();

		FileInputStream fileInputStream = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\config\\GlobalData.properties");

		properties.load(fileInputStream);

		String browserName = properties.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TimeOut.MID.getValue()));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TimeOut.MID.getValue()));
		driver.get("https://www.saucedemo.com/");
		return driver;
	}

	public void waitForElementVisibility(WebDriver driver, By by) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOut.MID.getValue()));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForButtonClick(WebDriver driver, By by) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOut.MID.getValue()));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by))));
	}

}
