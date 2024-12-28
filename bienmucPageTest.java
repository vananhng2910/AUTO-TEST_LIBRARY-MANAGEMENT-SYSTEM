package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.bienmucMainPage;
import Pages.bienmucPage;
import Pages.loginPage;
import setupbase.setupbase;

public class bienmucPageTest extends setupbase{

	private WebDriver driver;
	public loginPage loginPage;
	public bienmucMainPage bienmucMainPage;
	public bienmucPage bienmucPage;
	
	@BeforeMethod
	public void setUp() {
		driver = getDriver();
		loginPage = new loginPage(driver);
		String a = "cbbm";
		String b = "1";
		bienmucMainPage = loginPage.bienmucLogin(a,b);
		bienmucPage = new bienmucPage(driver);
		bienmucPage = bienmucMainPage.tobienmucPage();
	}
	
	@Test(description = "Check validate các trường trong màn hình chính", priority = 1)
	public void checkUI() throws Exception{
		bienmucPage.checkNullTextFields();
		bienmucPage.checkValCombobox("Available");
		bienmucPage.checkSize();
		bienmucPage.checkMultiSelect();
		bienmucPage.checkSelectValue();
		bienmucPage.checkDefaulBtn();
		bienmucPage.checkNumofRows(12);
		bienmucPage.checkTitlePage();
		bienmucPage.checkValidInput();
	}
	
	@Test(description = "Tìm kiếm khi bookid = null", priority = 2)
	public void checkInputIdNull() throws Exception{
		bienmucPage.checkInputNullBookId();
	}
	
	@Test(description = "Tìm kiếm tài liệu không tồn tại", priority = 3)
	public void checkBookNotExists() throws Exception {
		bienmucPage.checkBookNotExist();
	}
	
	@Test(description = "Tìm kiếm tài liệu tồn tại", priority = 4)
	public void checkBookExists() throws Exception{
		bienmucPage.checkSuccessSearch();
	}
	
	@Test(description = "Thêm mới tài liệu khi để trống các trường bắt buộc nhập", priority = 5)
	public void checkNullInputSave() throws Exception{
		bienmucPage.checkInputNullSave();
	}
	
	@Test(description = "Thêm mới tài liệu đã tồn tại", priority = 6)
	public void checkSaveExists() throws Exception{
		bienmucPage.checkSaveExists();
	}
	
	@Test(description = "Thêm mới tài liệu thành công", priority = 7)
	public void checkSaveSuccess() throws Exception{
		bienmucPage.checkSaveSuccess();
	}
	
	@Test (description = "Cập nhật tài liệu khi để trống các trường bắt buộc nhập", priority = 8)
	public void checkNullInputUpdate() throws Exception{
		bienmucPage.checkUpdateNull();
	}
	
	@Test(description = "Cập nhật thành công", priority = 9)
	public void checkUpdateSuccess() throws Exception{
		bienmucPage.checkUpdateSuccess("P.305");
	}
	
	@Test(description = "Xóa tài liệu thành công", priority = 10)
	public void checkDelSuccess() throws Exception{
		bienmucPage.checkDelSuccess();
	}
	
	@Test(description = "Xóa tài liệu không thành công", priority = 11)
	public void checkFailDel() throws Exception{
		bienmucPage.checkFailDel();
	}
	
}


