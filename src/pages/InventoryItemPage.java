package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import config.DriverUtil;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class InventoryItemPage {

	public WebDriver driver;
	public SoftAssert softAssert;

	public DriverUtil driverUtil;

	private int cartCount;

	private By itemName = By.xpath("//*[contains (@ class,'name')]");
	private By itemView = By.xpath("//*[contains (@ class, 'desc_container')]");
	private By cartIcon = By.xpath("//*[contains(@ id, 'shopping_cart_container')]"); 
	private By button = By.xpath("//*[contains(@class,'btn_inventory')]");
	private By backToProductButton = By.xpath("//*[contains(@ class,'btn_secondary ')]");

	public InventoryItemPage(WebDriver driver) {

		this.driver = driver;

		this.softAssert = new SoftAssert();

		this.driverUtil = new DriverUtil();
		

	}

	public void wait_for_inventory_item_page_load() {

		driverUtil.waitForElementVisibility(driver, itemView);

	}

	public void validate_item_cart_count() {

		driverUtil.waitForElementVisibility(driver, cartIcon);

		if (!(driver.findElement(cartIcon).getText().toString()).isEmpty()) {

			String cartCountText = driver.findElement(cartIcon).getText().toString();
			cartCount = Integer.parseInt(cartCountText);

		} else {

			cartCount = 0;
		}

	}

	public void validate_button() {

		driverUtil.waitForElementVisibility(driver, button);
		WebElement itemButton = driver.findElement(button);

		softAssert.assertTrue(itemButton.isDisplayed() && itemButton.isEnabled(),
				"Button in invemntory item page is disabled");
		softAssert.assertAll();

	}

	public void validate_item_view( String name) throws InterruptedException {

		wait_for_inventory_item_page_load();
		
		driverUtil.waitForElementVisibility(driver, itemName);
		
		
		
		Thread.sleep(2000);
		
		System.out.println("current url : "+driver.getCurrentUrl());
 
		WebElement itemButton = driver.findElement(button);
		
		validate_item_cart_count();
		int initialCount = cartCount;
		


		if (itemButton.getText().equalsIgnoreCase("ADD TO CART")) {

			itemButton.click();
			itemButton = driver.findElement(button);

			validate_item_cart_count();
			
			softAssert.assertEquals(cartCount, initialCount + 1,
					"The button click function is not working, count isn't incremented :");

			softAssert.assertEquals(itemButton.getText(), "REMOVE",
					"The button text is not as expected 'REMOVE', button click is not working :");
			
			
		} else {

			itemButton.click();
			itemButton = driver.findElement(button);
			
			validate_item_cart_count();
			
			softAssert.assertEquals(cartCount, initialCount - 1,
					"The button click function is not working, count isn't decriment :");
			
			softAssert.assertEquals(itemButton.getText(), "ADD TO CART",
					"The button text is not as expected 'Add to cart', button click is not working :");
			
			itemButton.click();
			itemButton = driver.findElement(button);

		}
		
		System.out.println("Item Added to cart : after click: button name :"+itemButton.getText());

		softAssert.assertAll();
		
		

	}
	
	public void validate_back_to_product_button () {
		
		driverUtil.waitForElementVisibility(driver, backToProductButton);
		
		WebElement backButton = driver.findElement(backToProductButton);

		softAssert.assertTrue(backButton.isDisplayed() && backButton.isEnabled(),
				"Back Button in invemntory item page is disabled");
		softAssert.assertAll();
		
	}
	
	public void click_back_to_product_button () {
		
		driverUtil.waitForButtonClick(driver, backToProductButton);
		
	}
	

}
