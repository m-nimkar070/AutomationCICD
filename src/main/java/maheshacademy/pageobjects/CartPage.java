package maheshacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import maheshacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css="[routerlink*='cart']")
	WebElement cartBtn;
	
	@FindBy (xpath = "//div[@class='cartSection']/h3")
	List<WebElement> cart;
	
	@FindBy (css = ".totalRow button")
	WebElement checkoutBtn;
	
	By checkoutBtnBy = By.cssSelector(".totalRow button");
	
	public Boolean isProductPresentInCart(String productName) {
		cartBtn.click();
		Boolean match = cart.stream().anyMatch(prod -> prod.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public OrderPage goToCheckout() {
		safeClick(checkoutBtn , checkoutBtnBy);
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	

}
