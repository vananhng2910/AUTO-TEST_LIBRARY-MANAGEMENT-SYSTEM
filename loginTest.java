package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Pages.loginPage;
import setupbase.ExcelHelpers;
import setupbase.setupbase;

public class loginTest extends setupbase{
	private WebDriver driver;
	public loginPage loginPage;
	private ExcelHelpers excel;
	//setup môi trường chạy testcase
	@BeforeMethod
	public void setUp() {
		driver = getDriver();
		excel = new ExcelHelpers();
	}
		
	@Test(description = "Kiểm tra giao diện")
	public void checkTitlepage() throws Exception{
		  loginPage = new loginPage(driver);
		  Thread.sleep(1000);
		  loginPage.checkTitlePage();
		  loginPage.checknullUsername();
		  loginPage.checkRequiredUsername();
		  loginPage.checknullPassword();
		  loginPage.checkRequiredPassword();
		  loginPage.checkEnableLoginBtn();
		  loginPage.checkValidFreetext();
	  }
	
	@Test(description = "Kiểm tra các case đăng nhập")
	public void signIn() throws Exception{
		//Chỉ đến file Excel chứa dữ liệu đăng nhập
		excel.setExcelFile("D:\\DATN\\DATN\\resource\\logindata.xlsx", "Sheet1");
		loginPage = new loginPage(driver);
		int n = excel.rownum();
		for (int i = 1; i<n; i++) {	
			String a = excel.getCellData("username", i);
			String pass = excel.getCellData("password", i);
			String errmsg = excel.getCellData("errorMsg", i);
			String url = excel.getCellData("url", i);
			//Thực hiện đăng nhập và kiểm tra kết quả đăng nhập
			loginPage.signIn(a, pass, errmsg, url);
			Thread.sleep(1000);
			driver.quit();
			driver = new setupbase().setDriver("http://localhost/Arsha/login-page.php");
			loginPage = new loginPage(driver);
		}
	}
	
	@AfterMethod
	public void closeDr() throws Exception{

		Thread.sleep(100);
		driver.quit();
	}
}
