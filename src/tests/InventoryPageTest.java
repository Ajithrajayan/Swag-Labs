package tests;

import java.util.List;

import org.testng.annotations.Test;

import pages.BasePage;
import pages.InventoryItemPage;
import pages.InventoryPage;

public class InventoryPageTest extends BasePage {

	InventoryPage inventoryPage;
	InventoryItemPage inventoryItemPage;

	@Test(description = "confirm the login")
	public void confirmInventoryPage() {
		inventoryPage = new InventoryPage(driver);

		inventoryPage.wait_for_inventory_page_load();
		inventoryPage.confirm_inventory_page(); 
	}

	@Test(description = "get product list and select 3 random product")
	public void prodectSelection() throws InterruptedException {

		inventoryPage = new InventoryPage(driver);
		inventoryItemPage = new InventoryItemPage(driver);

		inventoryPage.wait_for_inventory_page_load();
		inventoryPage.validate_cart_count();
		inventoryPage.get_product_list();
		inventoryPage.validate_and_get_button_list();
		inventoryPage.select_random_products(); 
		List<Integer> selectedPositions = inventoryPage.get_selected_positions();
		for(int i=0;i<selectedPositions.size();i++)
		{
			String name = inventoryPage.select_item_by_click_on_name(selectedPositions.get(i)); 
			
			inventoryItemPage.validate_item_view(name);
			inventoryItemPage.validate_back_to_product_button();
			inventoryItemPage.click_back_to_product_button();
			inventoryPage.wait_for_inventory_page_load();
			inventoryPage.get_product_list();
			
		}
		
		inventoryPage.validate_menu_button();
		inventoryPage.click_menu_button();
		inventoryPage.validate_logout_button();
		inventoryPage.click_logout_button();

	}

}
