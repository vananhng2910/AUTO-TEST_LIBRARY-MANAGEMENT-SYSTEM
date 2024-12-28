package TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.loginPage;
import Pages.userChangePasswordPage;
import Pages.userMainPage;
import setupbase.setupbase;

public class userChangePasswordTest extends setupbase{
	private WebDriver driver;
	public loginPage loginPage;
	public userMainPage userMainPage;
	public userChangePasswordPage userChangePasswordPage;
	
	
	@BeforeMethod
	public void setUp() {
		driver = getDriver();
		loginPage = new loginPage(driver);
		String a = "vienhuu";
		String b = "111111";
		userMainPage = loginPage.userLogin(a,b);
		userMainPage = new userMainPage(driver);
		userChangePasswordPage = userMainPage.toChangePassPage();
	}

	
  @Test(description = "Kiểm tra validate giao diện")
  public void checkValidateUI() throws Exception{
	  userChangePasswordPage.checkDefaultValueUsername("vienhuu");
	  userChangePasswordPage.checkDisableUsername();
	  userChangePasswordPage.checkEnableBtn();
	  userChangePasswordPage.checkNullCurPass();
	  userChangePasswordPage.checkNullNewPass();
	  userChangePasswordPage.checkNullCfNewPass();
	  userChangePasswordPage.checkValidFreetext();
  }
  
  @Test (description = "Kiểm tra input null khi đổi mật khẩu")
  public void checkInputNull() throws Exception{
	  userChangePasswordPage.checkInputNull();

  }

  @Test(description = "Tài khoản không chính xác")
  public void checkWrongAcc() throws Exception{
	  userChangePasswordPage.checkMsgAlert("123", "abc", "abc", "Tài khoản không chính xác!");
  }
//  
  @Test (description = "Xác nhận sai mật khẩu mới")
  public void checkWrongCfNewPass() throws Exception{
	  userChangePasswordPage.checkMsgAlert("111111", "abc", "abcdef", "Xác nhận lại mật khẩu mới của bạn!");
  }
  
  @Test(description ="Đổi mật khẩu thành công")
  public void checkChangePassSuccess() throws Exception{
	  userChangePasswordPage.checkMsgAlert("111111", "111111", "111111", "Đổi mật khẩu thành công!");
  }
  
  @AfterMethod
	public void closeDr() throws Exception{

		Thread.sleep(100);
		driver.quit();
	}
  
}
