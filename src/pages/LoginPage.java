package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import InputData.InputDataProvider;

import config.DriverUtil;

public class LoginPage { 

	public WebDriver driver;
	public DriverUtil driverUtil;
	public SoftAssert softAssert;

	
	private By loginForm = By.xpath("//*[contains(@ id,'container')]");
	private By userNameTextBox = By.id("user-name");
	private By passwordTextBox = By.id("password");
	private By loginButton = By.xpath("//*[contains(@ class,'submit')]");
	private By errorMessage = By.xpath("//*[contains (@ class, 'message')]");
	
	String expectedError = "Epic sadface: Sorry, this user has been locked out.";

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		this.driverUtil = new DriverUtil();
		this.softAssert = new SoftAssert();

	}
	
	public void wait_for_login_page_load() {

		driverUtil.waitForElementVisibility(driver, loginForm);
		
	}

	public void validate_username_textbox_is_enabled() {

		driverUtil.waitForElementVisibility(driver, userNameTextBox);
		softAssert.assertTrue(
				(driver.findElement(userNameTextBox).isDisplayed() && driver.findElement(userNameTextBox).isEnabled()),
				"User Name textbox in login page is disabled");
		
		softAssert.assertAll();
	}
	

	public void validate_password_textbox_is_enabled() {

		driverUtil.waitForElementVisibility(driver, passwordTextBox);
		String passwordType = driver.findElement(passwordTextBox).getAttribute("type");

		softAssert.assertTrue(
				(driver.findElement(userNameTextBox).isDisplayed() && driver.findElement(userNameTextBox).isEnabled())
						&& (passwordType.equalsIgnoreCase("password")),
				" The password field type is not 'password' and textbox in loginpage is disabled ");
		softAssert.assertAll();
	}
	
	public void validate_login_button_is_enabled() {
		

		driverUtil.waitForElementVisibility(driver, loginButton);
		softAssert.assertTrue(
				(driver.findElement(loginButton).isDisplayed() && driver.findElement(loginButton).isEnabled()),
				"Login button in login page is disabled");
		softAssert.assertAll();
	}
	
	public void send_valid_key_to_username_textbox(String string) {
		
		driver.findElement(userNameTextBox).sendKeys(string);
	
		
	}
	
	public void send_valid_key_to_password_textbox(String string) {
		
		driver.findElement(passwordTextBox).sendKeys(string);
		
		
	}
	
	public void click_login_button() {
		
		
		
		driverUtil.waitForButtonClick(driver, loginButton);
		
		
		
	}

		// TODO Auto-generated method stub
		
	

	public void get_lock_out_msg() {
		
		driverUtil.waitForElementVisibility(driver, errorMessage);
		
		String currentEMessage = driver.findElement(errorMessage).getText();
		
		softAssert.assertEquals(currentEMessage, expectedError, "Error message for locked user isn't expected one.");
		
		softAssert.assertAll();
		
	}
	
	public void navigate_forward() {

		driver.navigate().forward();
	}

	public void navigate_back() {

		driver.navigate().back();
	}

	public void page_refresh() {
		
		driver.navigate().refresh();

	}

	
	

}
