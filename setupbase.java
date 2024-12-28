package setupbase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import extentreports.ReportListener;

@Listeners(ReportListener.class)

public class setupbase {
	private static WebDriver driver;
	//Đường dẫn đến thư mục chứa chromedriver.exe
	static String driverPath = "D:\\DATN\\DATN\\";
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static WebDriver setDriver(String appURL) {
		
			return setupChromeDriver(appURL);
	}
	//Setup chromedriver
	private static WebDriver setupChromeDriver(String appURL) {
		System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//truy cập địa chỉ tham số appURL
		driver.navigate().to(appURL);
		//tgian chờ 1 page load
		
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		//set thời gian driver sẽ chờ để tìm 1 phần tử trong trang nếu nó chưa hiển thị ngay khi mở trang
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}
	
	@Parameters({"appURL"})
	@BeforeMethod
	public void setupTestbase(String appURL) {
		try {
			// Setup driver & browser
			driver = setDriver(appURL);	    
		} catch (Exception e) {
			System.out.println("Error: " + e.getStackTrace());
		}
	}
	
	@AfterMethod
	public void closeDriver() throws Exception {
		Thread.sleep(100);
		driver.quit();
	}
}

