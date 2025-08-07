package maheshacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import maheshacademy.TestComponents.BaseTest;
import maheshacademy.pageobjects.CartPage;
import maheshacademy.pageobjects.ConfirmPage;
import maheshacademy.pageobjects.LandingPage;
import maheshacademy.pageobjects.OrderPage;
import maheshacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest{
	public LandingPage  landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmPage confirmPage;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_Page () throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^I logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password (String username , String password) {
		productCatalogue = landingPage.loginApplication(username , password);
	}
	
	@When("^I add product (.+) into cart$")
	public void i_add_product_to_cart(String productName) {
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkou_submit_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();

//		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.isProductPresentInCart(productName);
//		
//		Boolean match =cart.stream().anyMatch(prod -> prod.getText().equalsIgnoreCase(productName));
//		
		Assert.assertTrue(match);
		OrderPage orderPage = cartPage.goToCheckout();

//		OrderPage orderPage = new OrderPage(driver);
		orderPage.fillDetails("india");
		confirmPage = orderPage.goToConfirmPage();
	}
	
	@Then("{string} message displayed on the confirmation page")
	public void message_disply_confirmation_page(String string) {
		String confirmText = confirmPage.getTextPresent();
		Assert.assertTrue(confirmText.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displyed(String string1) throws Throwable {
		System.out.println("String value" + string1);
		Assert.assertEquals(string1 , landingPage.getErrorMessage());
		driver.close();
	}
	

}
