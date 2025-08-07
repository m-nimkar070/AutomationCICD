package maheshacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderHistory  {
	WebDriver driver;
	
	
	@FindBy (css="[routerlink*='myorders']")
	WebElement orderHistory;
	
	@FindBy (css = "tr td:nth-child(3)")
	List<WebElement> orderedProduct;

	public OrderHistory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean isProductPresentInHistoryPage(String productName) {
		Boolean match = orderedProduct.stream().anyMatch(prod -> prod.getText().equalsIgnoreCase(productName));
		return match;
	}


}
