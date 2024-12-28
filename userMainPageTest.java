package TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.loginPage;
import Pages.userMainPage;
import setupbase.setupbase;

public class userMainPageTest extends setupbase{
	private WebDriver driver;
	public loginPage loginPage;
	public userMainPage userMainPage;
	
	
	
	
	@BeforeMethod
	public void setUp() {
		driver = getDriver();
		loginPage = new loginPage(driver);
		String a = "vienhuu";
		String b = "111111";
//		userMainPage = new userMainPage(driver);
		userMainPage = loginPage.userLogin(a,b);
		
	}

	@Test
	public void checkValidateUser() throws Exception{
		String a = userMainPage.getWelcomeMsg();
//		System.out.println(a);
		AssertJUnit.assertEquals(a, "Chào mừng Trần Nguyên Vũ đến với trang thư viện điện tử!");
		
		String a1 = userMainPage.getUserLogin();
		AssertJUnit.assertEquals(a1, "Trần Nguyên Vũ");
	}
	
  
}
