package pages;

import java.awt.Desktop.Action;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import config.DriverUtil;

public class InventoryPage {

	public WebDriver driver;
	public SoftAssert softAssert;
	public Action action;

	public DriverUtil driverUtil;
	//public InventoryItemPage inventoryItemPage;

	private List<WebElement> productList;
	private List<WebElement> buttonList;
	private List<Integer> selectedPositions;
	private int cartCount;

	final String inventoryPageURL = "https://www.saucedemo.com/inventory.html";
	private By inventoryPageList = By.xpath("//*[contains (@ class,'inventory_container')]");
	private By itemList = By.xpath("//*[@ class = 'inventory_item']");
	private By button = By.xpath("//*[contains(@class,'btn_inventory')]");
	private By cartIcon = By.xpath("//*[contains(@ id, 'shopping_cart_container')]");
	private By itemName = By.xpath("//*[contains (@ class,'name')]");
	private By menuButton = By.id("react-burger-menu-btn");
	private By logoutButton = By.id("logout_sidebar_link");
	

	public InventoryPage(WebDriver driver) {

		this.driver = driver;
		this.softAssert = new SoftAssert();

		this.driverUtil = new DriverUtil();
		//this.inventoryItemPage = new InventoryItemPage(driver);

	}

	public void wait_for_inventory_page_load() {

		driverUtil.waitForElementVisibility(driver, inventoryPageList);

	}

	public void confirm_inventory_page() {

		softAssert.assertEquals(inventoryPageURL, driver.getCurrentUrl(), "Expected page not loaded");
		System.out.println("expected page: Inventory loaded");
		softAssert.assertAll();

	}

	public void validate_cart_count() {

		driverUtil.waitForElementVisibility(driver, cartIcon);

		if (!(driver.findElement(cartIcon).getText().toString()).isEmpty()) {

			String cartCountText = driver.findElement(cartIcon).getText().toString();
			cartCount = Integer.parseInt(cartCountText);

		} else {
			cartCount = 0;
		}

	}

	public int get_current_cart_count() {

		int currentCartCount = 0;
		driverUtil.waitForElementVisibility(driver, cartIcon);

		if (!(driver.findElement(cartIcon).getText().toString()).isEmpty()) {

			String cartCountText = driver.findElement(cartIcon).getText().toString();
			currentCartCount = Integer.parseInt(cartCountText);

		} else {
			currentCartCount = 0;
		}
		return currentCartCount;

	}

	public void get_product_list() {

		driverUtil.waitForElementVisibility(driver, itemList);

		productList = driver.findElements(itemList);

	}

	public void validate_and_get_button_list() {

		driverUtil.waitForElementVisibility(driver, button);

		buttonList = driver.findElements(button);

		for (int i = 0; i < buttonList.size(); i++) {

			softAssert.assertTrue((buttonList.get(i)).isDisplayed() && (buttonList.get(i)).isEnabled(),
					"Button in invemntory page is disabled");

			softAssert.assertAll();
		}

	}

	public void get_button_value() {

		driverUtil.waitForElementVisibility(driver, button);

		buttonList = driver.findElements(button);
	}

	public String select_item_by_click_on_name(int itemPosition) throws InterruptedException {

		String name = productList.get(itemPosition).getText();
		
		List<WebElement> itemNameList = driver.findElements(itemName);
		itemNameList.get(itemPosition).click();

//		productList.get(itemPosition).findElement(itemName).click();

		return name;

	}

	public void select_item_by_click_on_image() {

	}

	public void select_random_products() {

		// if (cartCount == 0) {

		selectedPositions = new ArrayList<>();

		if (productList.size() > 3) {

			for (int i = 0; i < 3; i++) {

				Random random = new Random();
				int position = random.nextInt(productList.size());

				if (selectedPositions.contains(position)) {

					do {

						position = random.nextInt(productList.size());
						buttonList = driver.findElements(button);

					} while (selectedPositions.contains(position));

				}
				selectedPositions.add(position);

				if (cartCount != 0) {
					if (buttonList.get(position).getText().equalsIgnoreCase("REMOVE"))

					{
						buttonList.get(position).click();
						buttonList = driver.findElements(button);

						softAssert.assertEquals(buttonList.get(position).getText(), "ADD TO CART",
								"The button text is not valid , Expected 'Add to cart' got :"
										+ buttonList.get(position).getText());

					}

				}

				addProductToCart(position);

			}

		}

	}

	private void addProductToCart(int position) {
		int currentCartCount = get_current_cart_count();

		System.out.println(
				"Before button click(ADD)::Button text of selected item is:" + buttonList.get(position).getText());

		softAssert.assertEquals(buttonList.get(position).getText(), "ADD TO CART",
				"The button text is not valid, Expected 'Add to cart' got :" + buttonList.get(position).getText());

		buttonList.get(position).click();
		buttonList = driver.findElements(button);

		System.out.println(
				"After button click(ADD)::Button text of selected item is:" + buttonList.get(position).getText());

		softAssert.assertEquals(buttonList.get(position).getText(), "REMOVE",
				"The button text is not valid after click 'add to cart', Expected 'Remove' got :"
						+ buttonList.get(position).getText());

		validate_cart_count();

		System.out.println("After button click(ADD)::Cart count is: " + cartCount);

		softAssert.assertEquals(cartCount, currentCartCount + 1,
				"The button click function is not working:the cart count must be incremnted : result got: "
						+ cartCount);

		currentCartCount = get_current_cart_count();

		buttonList.get(position).click();
		buttonList = driver.findElements(button);

		System.out.println(
				"After button click(Remove)::Button text of selected item is:" + buttonList.get(position).getText());

		softAssert.assertEquals(buttonList.get(position).getText(), "ADD TO CART",
				"The button text is not valid, Expected 'Add to cart' got :" + buttonList.get(position).getText());

		validate_cart_count();

		System.out.println("After button click(Remove)::Cart count is: " + cartCount);

		softAssert.assertEquals(cartCount, currentCartCount - 1,
				"The button click function is not working:the cart count must be decreased: result got: " + cartCount);

		softAssert.assertAll();
	}

	public List<Integer> get_selected_positions() {
		// TODO Auto-generated method stub
		return selectedPositions;
	}
	
	public void validate_menu_button()
	{
		driverUtil.waitForElementVisibility(driver, menuButton);
		Assert.assertTrue(
				(driver.findElement(menuButton).isDisplayed() && driver.findElement(menuButton).isEnabled()),
				"Login button in login page is disabled");
		softAssert.assertAll();
	}
	
	public void click_menu_button() {
		
		driverUtil.waitForButtonClick(driver, menuButton);
	}
	
	public void validate_logout_button()
	{
		driverUtil.waitForElementVisibility(driver, logoutButton);
		Assert.assertTrue(
				(driver.findElement(logoutButton).isDisplayed() && driver.findElement(logoutButton).isEnabled()),
				"Login button in login page is disabled");
		softAssert.assertAll();
	}
	
public void click_logout_button() {
		
		driverUtil.waitForButtonClick(driver, logoutButton);
	}
	

}
