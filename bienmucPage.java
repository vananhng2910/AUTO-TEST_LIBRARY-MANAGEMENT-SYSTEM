package Pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import setupbase.validateFunction;

public class bienmucPage {
	private WebDriver driver;
	
	public loginPage loginPage;
	
	public bienmucMainPage bienmucMainPage;
	
	private validateFunction validateFunction;
	
	private By bookid = By.xpath("//*[@id=\"bookid\"]");
	private By title = By.xpath("//*[@id=\"title\"]");
	private By image = By.xpath("//*[@id=\"image\"]");
	private By creator = By.xpath("//*[@id=\"creator\"]");
	private By nxb = By.xpath("//*[@id=\"nxb\"]");
	private By btype = By.xpath("//*[@id=\"btype\"]");
	private By year = By.xpath("//*[@id=\"year\"]");
	private By isbn = By.xpath("//*[@id=\"isbn\"]");
	private By lang = By.xpath("//*[@id=\"lang\"]");
	private By place = By.xpath("//*[@id=\"place\"]");
	private By status = By.xpath("//*[@id=\"status\"]");
	private By searchBtn = By.xpath("//*[@id=\"search-btn\"]");
	private By saveBtn = By.xpath("//*[@id=\"save-btn\"]");
	private By updateBtn = By.xpath("//*[@id=\"update-btn\"]");
	private By deleteBtn = By.xpath("//*[@id=\"delete-btn\"]");
	private By searchDataTb = By.xpath("//*[@id=\"dstailieu_filter\"]/label/input");
	private By numrowShow = By.xpath("//*[@id=\"dstailieu_info\"]");
	private By nextBtn = By.xpath("//*[@id=\"dstailieu_next\"]");
	private By tbody = By.xpath("//*[@id=\"dstailieu\"]/tbody");
	private By row = By.xpath("//*[@id=\"dstailieu\"]/tbody/tr");
	
	
	public bienmucPage(WebDriver driver) {
		this.driver = driver;
		validateFunction = new validateFunction(this.driver);
	}
	
	public void checkTitlePage() throws Exception{
		validateFunction.checkTitlePage("Quản lý tài liệu");
	}
	
	//Check null các trường textbox
	public void checkNullTextFields() throws Exception{
		validateFunction.checkNull(bookid);
		validateFunction.checkNull(title);
		validateFunction.checkNull(image);
		validateFunction.checkNull(creator);
		validateFunction.checkNull(nxb);
		validateFunction.checkNull(btype);
		validateFunction.checkNull(year);
		validateFunction.checkNull(isbn);
		validateFunction.checkNull(place);
		validateFunction.checkNull(lang);
	}
	
	public void checkValidInput() throws Exception{
		validateFunction.checkValueValid(bookid, "Abc1/@23");
		validateFunction.checkValueValid(title, "Abc1/@23");
		validateFunction.checkValueValid(image, "https://m.media-amazon.com/images/I/71vdN0A0n3L._SL1500_.jpg");
		validateFunction.checkValueValid(creator, "Abc1/@23");
		validateFunction.checkValueValid(nxb, "Abc1/@23");
		validateFunction.checkValueValid(btype, "Abc1/@23");
		validateFunction.checkValueValid(isbn, "Abc1/@23");
		validateFunction.checkValueValid(place, "Abc1/@23");
		validateFunction.checkValueValid(lang, "Abc1/@23");
		validateFunction.checkValueValid(year, "2023");
	}
	
	//Check cbb Trạng thái
	public void checkValCombobox(String a) {
		WebElement selectElement = driver.findElement(status);
	    Select select = new Select(selectElement);
		
		Assert.assertEquals(a, select.getFirstSelectedOption().getText());
	}
	
	public void checkSize() {
		WebElement selectElement = driver.findElement(status);
	    Select select = new Select(selectElement);
		
		Assert.assertEquals(2, select.getOptions().size());
	}
	
	public void checkMultiSelect() {
		WebElement selectElement = driver.findElement(status);
	    Select select = new Select(selectElement);
		
		Assert.assertFalse(select.isMultiple());
	}
	
	public void checkSelectValue () {
		WebElement selectElement = driver.findElement(status);
	    Select select = new Select(selectElement);
	    
	    select.selectByValue("unavailable");
	    Assert.assertEquals("Unavailable", select.getFirstSelectedOption().getText());
		
	}
	
	//Check trạng thái các button
	public void checkDisableBtn(By a) {
		WebElement a1 = driver.findElement(a);
		Assert.assertFalse(a1.isEnabled());
	}
	
	public void checkEnableBtn(By a) {
		WebElement a1 = driver.findElement(a);
		Assert.assertTrue(a1.isEnabled());
	}
	
	public void checkDefaulBtn() {
		checkDisableBtn(updateBtn);
		checkDisableBtn(deleteBtn);
		checkEnableBtn(searchBtn);
		checkEnableBtn(saveBtn);
	}
	
	//Nhập input các trường
	
	public void enterInput (By a, String b) {
		WebElement a1 = driver.findElement(a);
		a1.sendKeys(b);
	}

	public void selectStatus(String a) {
		WebElement selectElement = driver.findElement(status);
	    Select select = new Select(selectElement);
	    select.selectByValue(a);
	}
	
	//Click các button
	public void clickBtn(By a) {
		WebElement a1 = driver.findElement(a);
		a1.click();
	}
	
	//Check dữ liệu các trường
	public void checkValInputText(By a, String b) {
		WebElement a1 = driver.findElement(a);
		String b1 = a1.getAttribute("value");
		Assert.assertEquals(b, b1);
	}
	
	//Datetime
	public String StrDate() {
		Date date = new Date(); 
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
		return dateFormat.format(date);
	}
	
	//Clear giá trị các trường input
	
	public void clearInput(By a) {
		WebElement a1 = driver.findElement(a);
		a1.clear();
	}
	
	//Check DataTable
	//Kiểm tra số dòng
	public void checkNumofRows(int a) {
		WebElement str=driver.findElement(numrowShow);
		String info = str.getText();
		ArrayList < String > arrayListString = new ArrayList < > ();
		for (String s: info.split(" ")) {
			arrayListString.add(s);
			}
		int itemTotal = Integer.parseInt(arrayListString.get(5));
		Assert.assertEquals(a, itemTotal);
	}
	//Nhập tìm kiếm trong bảng 
	public void enterSearchDatatable(String a) {
		WebElement a1 = driver.findElement(searchDataTb);
		a1.sendKeys(a);
	}
	
	//Kiểm tra tìm kiếm dữ liệu theo cột
	public void checkSearchTableByColumn(int col, String value) throws Exception{
        List<WebElement> row1 = driver.findElements(row);
        int rowTotal = row1.size();
        for (int i = 1; i <= rowTotal; i++) {
            WebElement elementCheck = driver.findElement(By.xpath("//*[@id=\"dstailieu\"]/tbody/tr["+ i +"]/td[" + col +"]"));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", elementCheck);
//            System.out.print(elementCheck.getText());
            Assert.assertTrue(elementCheck.getText().contains(value), "Dòng số " + i + " không chứa giá trị tìm kiếm.");
        }

    }
	
	
	//Check chức năng Tìm kiếm
	public void checkInputNullBookId() throws Exception{
		clickBtn(searchBtn);
		Thread.sleep(1000);
		String b = driver.switchTo().alert().getText();
//		driver.switchTo().alert().accept();
		Assert.assertEquals(b, "Mã tài liệu không được để trống!");
	}
	
	public void checkBookNotExist() throws Exception{
		enterInput(bookid, "ABC");
		Thread.sleep(1000);
		clickBtn(searchBtn);
		Thread.sleep(1000);
		String b = driver.switchTo().alert().getText();
//		driver.switchTo().alert().accept();
		Assert.assertEquals(b, "Không tìm thấy tài liệu!");
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		checkDisableBtn(updateBtn);
		checkDisableBtn(deleteBtn);
	}
	
	public void checkSuccessSearch() throws Exception{
		enterInput(bookid,"TT002");
		Thread.sleep(1000);
		clickBtn(searchBtn);
		Thread.sleep(2000);
		checkValInputText(bookid, "TT002");
		checkValInputText(title, "Mắt biếc");
		checkValInputText(image, "https://www.nxbtre.com.vn/Images/Book/nxbtre_full_01372019_043734.jpg");
		checkValInputText(nxb, "NXB. Trẻ");
		checkValInputText(creator, "Nguyễn Nhật Ánh");
		checkValInputText(btype, "Sách đơn");
		checkValInputText(year, "2022");
		checkValInputText(isbn, "978-604-1-14078-3");
		checkValInputText(lang, "Tiếng Việt");
		checkValInputText(place, "P.304");
		checkValCombobox("Available");
		checkDisableBtn(saveBtn);
		checkEnableBtn(updateBtn);
		checkEnableBtn(deleteBtn);
		checkDisableBtn(bookid);
	}
	
	public void checkInputNullSave() throws Exception{
		clickBtn(saveBtn);
		Thread.sleep(1000);
		String b = driver.switchTo().alert().getText();
		Assert.assertEquals(b, "Mã tài liệu không được để trống!");
		driver.switchTo().alert().accept();
		enterInput(bookid, "ABC");
		Thread.sleep(1000);
		clickBtn(saveBtn);
		Thread.sleep(1000);
		String b1 = driver.switchTo().alert().getText();
		Assert.assertEquals(b1, "Nhan đề tài liệu không được để trống!");
		driver.switchTo().alert().accept();
		enterInput(title, "ABC");
		Thread.sleep(1000);
		clickBtn(saveBtn);
		Thread.sleep(1000);
		String b2 = driver.switchTo().alert().getText();
		Assert.assertEquals(b2, "Hình bìa tài liệu không được để trống!");
		driver.switchTo().alert().accept();
		enterInput(image, "ABC");
		Thread.sleep(1000);
		clickBtn(saveBtn);
		Thread.sleep(1000);
		String b3 = driver.switchTo().alert().getText();
		Assert.assertEquals(b3, "Tên tác giả không được để trống!");
	}
	
	public void checkSaveExists() throws Exception{
		enterInput(bookid, "TT001");
		enterInput(title, "ABC");
		enterInput(image,"abc");
		enterInput(creator, "Abc");
		Thread.sleep(1000);
		clickBtn(saveBtn);
		Thread.sleep(1500);
		String b2 = driver.switchTo().alert().getText();
		Assert.assertEquals(b2, "Mã tài liệu đã tồn tại!");
	}
	
	public void checkSaveSuccess() throws Exception{
		enterInput(bookid, "TT008");
		enterInput(title, "ABC");
		enterInput(image,"https://m.media-amazon.com/images/I/71vdN0A0n3L._SL1500_.jpg");
		enterInput(creator, "Dr. Seuss");
		Thread.sleep(1000);
		clickBtn(saveBtn);
		Thread.sleep(3000);
		String b2 = driver.switchTo().alert().getText();
		Assert.assertEquals(b2, "Thêm mới tài liệu thành công!");
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		checkNumofRows(13);
		enterSearchDatatable("TT008");
		checkSearchTableByColumn(7, StrDate());
		checkSearchTableByColumn(6, "CREATE");
		checkSearchTableByColumn(8, "cbbm");
	}
	
	public void checkUpdateNull() throws Exception{
		enterInput(bookid, "TT002");
		clickBtn(searchBtn);
		Thread.sleep(1000);
		clearInput(title);
		clickBtn(updateBtn);
		Thread.sleep(1000);
		String b = driver.switchTo().alert().getText();
		Assert.assertEquals(b, "Nhan đề tài liệu không được để trống!");
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		enterInput(title, "Mắt biếc");
		clearInput(image);
		clickBtn(updateBtn);
		Thread.sleep(1000);
		String b1 = driver.switchTo().alert().getText();
		Assert.assertEquals(b1, "Hình bìa tài liệu không được để trống!");
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		enterInput(image, "https://www.nxbtre.com.vn/Images/Book/nxbtre_full_01372019_043734.jpg");
		clearInput(creator);
		clickBtn(updateBtn);
		Thread.sleep(1000);
		String b2 = driver.switchTo().alert().getText();
		Assert.assertEquals(b2, "Tên tác giả không được để trống!");
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
	}
	
	public void checkUpdateSuccess(String a) throws Exception{
		enterInput(bookid, "TT008");
		clickBtn(searchBtn);
		Thread.sleep(1000);
		clearInput(place);
		enterInput(place, a);
		clickBtn(updateBtn);
		Thread.sleep(1000);
		String b1 = driver.switchTo().alert().getText();
		Assert.assertEquals(b1, "Cập nhật tài liệu thành công!");
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		enterSearchDatatable("TT008");
		checkSearchTableByColumn(5, a);
		checkSearchTableByColumn(7, StrDate());
		checkSearchTableByColumn(6, "UPDATE");
		checkSearchTableByColumn(8, "cbbm");
	}	
	
	public void checkDelSuccess() throws Exception{
		enterInput(bookid, "TT008");
		clickBtn(searchBtn);
		Thread.sleep(1000);
		clickBtn(deleteBtn);
		Thread.sleep(1000);
		String b1 = driver.switchTo().alert().getText();
		Assert.assertEquals(b1, "Xóa tài liệu thành công!");
		driver.switchTo().alert().accept();
		enterSearchDatatable("TT008");
		Thread.sleep(1000);
		checkNumofRows(0);
	}
	
	public void checkFailDel() throws Exception{
		enterInput(bookid, "TC002");
		clickBtn(searchBtn);
		Thread.sleep(1000);
		clickBtn(deleteBtn);
		Thread.sleep(1000);
		String b1 = driver.switchTo().alert().getText();
		Assert.assertEquals(b1, "Tài liệu đang được cho mượn - Không được xóa!");
	}
	
	
	
}
