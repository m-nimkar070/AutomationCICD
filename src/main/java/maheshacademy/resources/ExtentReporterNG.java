package maheshacademy.resources;

import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	
	
	@BeforeTest
	public static ExtentReports getReportObject() {
		
		String path =System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setReportName("web Automation Report");
		reporter.config().setDocumentTitle("Test Reports");
		
		ExtentReports extent =new ExtentReports();
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Tester", "Mahesh N");
		return extent;
	}
	
//	@Test
//	public void initialDemo() {
//		ExtentTest test = extent.createTest("initial Demo Teat");
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://rahulshettyacademy.com/");
//		System.out.println(driver.getTitle());
//		
//		driver.quit();
//		test.fail("Result do not match");
//		extent.flush();
//	}

}
