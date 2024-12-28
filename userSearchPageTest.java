package TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.loginPage;
import Pages.userMainPage;
import setupbase.setupbase;

public class userSearchPageTest extends setupbase{
	private WebDriver driver;
	public loginPage loginPage;
	public userMainPage userMainPage;
	public Pages.userSearchPage userSearchPage;
	
	
	@BeforeMethod
	public void setUp() {
		driver = getDriver();
		loginPage = new loginPage(driver);
		String a = "vienhuu";
		String b = "111111";
		userMainPage = loginPage.userLogin(a,b);
		userMainPage = new userMainPage(driver);
		userSearchPage = userMainPage.toSearchPage();
	}

  @Test(description = "Kiểm tra giao diện")
  public void checkValidateUI() throws Exception{
	  userSearchPage.checkDefaultCombobox();
	  userSearchPage.checkMultiSelect();
	  userSearchPage.checkSize();
	  userSearchPage.checkSelectValue();
	  userSearchPage.checkNullTextBox();
	  userSearchPage.checkEnableSearchBtn();
	  userSearchPage.checkValidTextBox();
  }
  
  @Test(description = "Tìm kiếm chưa nhập từ khóa")
  public void searchInputNull() throws Exception{
	  userSearchPage.clickSearchBtn();
	  Thread.sleep(1000);
	  userSearchPage.checkErrorMsg("Vui lòng nhập từ khóa tìm kiếm!");
	  Thread.sleep(1000);
  }
  
  @Test(description = "Không tìm thấy tài liệu")
  public void searchNoData() throws Exception{
	  userSearchPage.selectCbb("au");
	  userSearchPage.enterKeyword("abc");
	  userSearchPage.clickSearchBtn();
	  Thread.sleep(1000);
	  userSearchPage.checkErrorMsg("Không tìm thấy tài liệu");
	  Thread.sleep(1000);
	  
  }
  
  @Test(description = "Tìm thấy tài liệu")
  public void searchSuccess() throws Exception{
	  userSearchPage.selectCbb("year");
	  userSearchPage.enterKeyword("2022");
	  userSearchPage.clickSearchBtn();
	  Thread.sleep(2000);
	  userSearchPage.checkErrorMsg("Tìm thấy 2 kết quả");
	  Thread.sleep(1000);
  }
  
  @Test(description = "Kiểm tra chức năng Lưu/Bỏ lưu tài liệu")
  public void checkSavebook() throws Exception{
	 userSearchPage.checkSaveFunction();
  }
  
	@AfterMethod
	public void closeDr() throws Exception{

		Thread.sleep(100);
		driver.quit();
	}
//  
}
