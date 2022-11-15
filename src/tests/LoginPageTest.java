package tests;

import org.testng.annotations.Test;

import InputData.InputDataProvider;
import pages.BasePage;
import pages.LoginPage;

public class LoginPageTest extends BasePage {

	LoginPage loginPage;

	@Test(description = "verify login :: standard_user")
	public void login() throws InterruptedException {

		loginPage = new LoginPage(driver); 

		loginPage.wait_for_login_page_load();

		loginPage.validate_username_textbox_is_enabled();
		loginPage.send_valid_key_to_username_textbox(InputDataProvider.getLoginData(0).get(0)); //InputDataProvider.getLoginData(0).get(0)
		loginPage.validate_password_textbox_is_enabled();
		loginPage.send_valid_key_to_password_textbox(InputDataProvider.getLoginData(0).get(1));//InputDataProvider.getLoginData(0).get(1)
		loginPage.validate_login_button_is_enabled();
		loginPage.click_login_button();
		loginPage.page_refresh();
	}

	@Test(description = "verify login :: locked_out_user")
	public void loginWithLockedOutUser() throws InterruptedException {
		loginPage = new LoginPage(driver);

		loginPage.wait_for_login_page_load();

		loginPage.validate_username_textbox_is_enabled();
		loginPage.send_valid_key_to_username_textbox(InputDataProvider.getLoginData(1).get(0));   //InputDataProvider.getLoginData(1).get(0)
		loginPage.validate_password_textbox_is_enabled();
		loginPage.send_valid_key_to_password_textbox(InputDataProvider.getLoginData(1).get(1));  //InputDataProvider.getLoginData(1).get(1)
		loginPage.validate_login_button_is_enabled();
		Thread.sleep(2000);
		loginPage.click_login_button();
		Thread.sleep(2000);
	    loginPage.get_lock_out_msg();
		loginPage.page_refresh();
		//Thread.sleep(2000);

	}

	@Test(description = "verify login :: problem_user")
	public void loginWithProblemUser()  {
		loginPage = new LoginPage(driver);

		loginPage.wait_for_login_page_load();

		loginPage.validate_username_textbox_is_enabled();
		loginPage.send_valid_key_to_username_textbox(InputDataProvider.getLoginData(2).get(0));   //InputDataProvider.getLoginData(2).get(0)
		loginPage.validate_password_textbox_is_enabled();
		loginPage.send_valid_key_to_password_textbox(InputDataProvider.getLoginData(2).get(1));  //InputDataProvider.getLoginData(2).get(1)
		loginPage.validate_login_button_is_enabled();
		//Thread.sleep(2000);
		loginPage.click_login_button();
		//Thread.sleep(2000);
	}

	@Test(description = "verify login :: performance_glitch_user")
	public void loginWithGlitchUser() { 
		loginPage = new LoginPage(driver);

		loginPage.wait_for_login_page_load();

		loginPage.validate_username_textbox_is_enabled();
		loginPage.send_valid_key_to_username_textbox(InputDataProvider.getLoginData(3).get(0));  //InputDataProvider.getLoginData(3).get(0)
		loginPage.validate_password_textbox_is_enabled();
		loginPage.send_valid_key_to_password_textbox(InputDataProvider.getLoginData(3).get(1));  //InputDataProvider.getLoginData(3).get(1)
		loginPage.validate_login_button_is_enabled();
		//Thread.sleep(2000);
		loginPage.click_login_button();
		
		//Thread.sleep(2000);
	}

}
