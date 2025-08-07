package maheshacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import maheshacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css="[placeholder = 'Select Country']")
	WebElement selectCountryBtn;
	
	
	@FindBy (xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	@FindBy (css=".action__submit")
	WebElement submitBtn;
	
	By dropdownBy = By.cssSelector(".ta-results");
	By submitBtnBy = By.cssSelector(".action__submit");
	
	public void fillDetails(String country) {
		Actions a = new Actions(driver);
		a.sendKeys(selectCountryBtn, country).build().perform();
		waitForElementToAppear(dropdownBy);
		selectCountry.click();
	}
	public ConfirmPage goToConfirmPage() {
		submitBtn.click();
		ConfirmPage confirmPage = new ConfirmPage(driver);
		return confirmPage;
	}
	

}
