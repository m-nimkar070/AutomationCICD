package maheshacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import maheshacademy.pageobjects.OrderHistory;

public class AbstractComponent {

	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy (css="[routerlink*='myorders']")
	WebElement orderHistory;
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement elem) {
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(elem));
	}
	
	public void waitForElementToBeClickable(By elem) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.presenceOfElementLocated(elem));
	}
	
	
	public void safeClick(WebElement element, By locator) {
	    try {
	        waitForElementToAppear(locator); // Synchronization
	        new Actions(driver).moveToElement(element).click().perform(); // Primary click
	    } catch (Exception e) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView(true);", element);
	        js.executeScript("arguments[0].click();", element); // Fallback click
	        System.out.println("Fallback JS click triggered on: " + locator);
	    }
	}

	
	public OrderHistory goToOrderHistoryPage() {
		orderHistory.click();
		OrderHistory orderHistoryPage = new OrderHistory(driver);
		return orderHistoryPage;
	}
}
