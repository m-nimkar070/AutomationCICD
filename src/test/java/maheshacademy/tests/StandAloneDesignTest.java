package maheshacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import maheshacademy.pageobjects.LandingPage;

public class StandAloneDesignTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromiumdriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		String productName = "ZARA COAT 3";
		
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landing = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("test@exp.com");
		driver.findElement(By.id("userPassword")).sendKeys("Pass@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement zara =  products.stream().filter(product -> product.findElement(By.cssSelector(".card-body h5")).getText().equals(productName)).findFirst().orElse(null);
		System.out.println(zara.findElement(By.cssSelector("b")).getText());
//		System.out.println();
		
		zara.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cart = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		
		Boolean match =cart.stream().anyMatch(prod -> prod.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
//		driver.findElement(By.xpath("//input[@placeholder = 'Select Country']")).sendKeys("ind");
//		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//		
//		List<WebElement>suggestions = driver.findElements(By.cssSelector(".ta-item span"));
//		List<WebElement> ind= suggestions.stream().filter(x->x.getText().equalsIgnoreCase("India")).collect(Collectors.toList());
//		
//		ind.forEach(x->x.click());
//		
//		driver.findElement(By.cssSelector(".actions a")).click();
		
		// ************ USING ACTIONS CLASS ***********
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder = 'Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmText =driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmText.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
		}

}
