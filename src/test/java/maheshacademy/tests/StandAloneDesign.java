package maheshacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import maheshacademy.TestComponents.BaseTest;
import maheshacademy.pageobjects.CartPage;
import maheshacademy.pageobjects.ConfirmPage;
import maheshacademy.pageobjects.OrderHistory;
import maheshacademy.pageobjects.OrderPage;
import maheshacademy.pageobjects.ProductCatalogue;

public class StandAloneDesign extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException {
		// TODO Auto-generated method stub

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

//		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();

//		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.isProductPresentInCart(input.get("productName"));
//		
//		Boolean match =cart.stream().anyMatch(prod -> prod.getText().equalsIgnoreCase(productName));
//		
		Assert.assertTrue(match);
		OrderPage orderPage = cartPage.goToCheckout();

//		OrderPage orderPage = new OrderPage(driver);
		orderPage.fillDetails("india");
		ConfirmPage confirmPage = orderPage.goToConfirmPage();

//		ConfirmPage confirmPage = new ConfirmPage(driver);
		String confirmText = confirmPage.getTextPresent();
//		String confirmText =driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmText.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("testinguser@gmail.com", "Pass@123");
		OrderHistory ordersHistoryPage = productCatalogue.goToOrderHistoryPage();
		Assert.assertTrue(ordersHistoryPage.isProductPresentInHistoryPage(productName));

	}

	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String,String> user1 = new HashMap<String,String>();
//		user1.put("email", "test@exp.com");
//		user1.put("password", "Pass@123");
//		user1.put("productName", "ZARA COAT 3");
//		
//		HashMap<String,String> user2 = new HashMap<String,String>();
//		user2.put("email", "testinguser@gmail.com");
//		user2.put("password", "Pass@123");
//		user2.put("productName", "IPHONE 13 PRO");

		List<HashMap<String, String>> data = getJsonToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\maheshacademy\\data\\PurchaseOrder.json");
//		System.out.println("data" + data.get(0));
		return new Object[][] { { data.get(0) }, { data.get(1) } };
//		return new Object [][] {{"test@exp.com", "Pass@123" ,"ZARA COAT 3"} , {"testinguser@gmail.com", "Pass@123","IPHONE 13 PRO"}};
	}

}
