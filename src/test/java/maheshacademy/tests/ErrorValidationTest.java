package maheshacademy.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import maheshacademy.TestComponents.BaseTest;
import maheshacademy.pageobjects.CartPage;
import maheshacademy.pageobjects.ProductCatalogue;
import maheshacademy.TestComponents.Retry;

public class ErrorValidationTest extends BaseTest {

	@Test (groups = {"ErrorHandeling"} , retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException {

		landingPage.loginApplication("test@exp.com1", "Pass@123");
//		div[aria-label='Incorrect email or password.']
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void productErrorValidation() throws IOException {

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("testinguser@gmail.com", "Pass@123");
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.isProductPresentInCart("ZARA COAT 33");
		Assert.assertFalse(match);

	}

}
