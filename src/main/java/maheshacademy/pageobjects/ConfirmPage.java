package maheshacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmPage {
	WebDriver driver;
	
	public ConfirmPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy (css=".hero-primary")
	WebElement text;
	
	By dropdownBy = By.cssSelector(".ta-results");
	
	public String getTextPresent() {
		String confirmText = text.getText();
		return confirmText;
		
	}
	

}
