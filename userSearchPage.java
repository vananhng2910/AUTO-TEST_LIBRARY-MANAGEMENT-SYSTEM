package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import setupbase.validateFunction;

public class userSearchPage {
	private WebDriver driver;
	
	public loginPage loginPage;
	
	public userMainPage userMainPage;
	
	private validateFunction validateFunction;
	
	private By searchcbb = By.xpath("//*[@id=\"choose\"]");
	
	private By textbox = By.xpath("//*[@id=\"keyword\"]");
	
	private By searchBtn = By.xpath("//*[@id=\"search-btn\"]");
	
	private By errorMsg = By.xpath("//*[@id=\"result_page\"]/div/h5");
	
	private By saveBtn = By.xpath("//*[@id=\"result_page\"]/div/div[2]/div/div/div[2]/button");
	
	
	public userSearchPage(WebDriver driver) {
		this.driver = driver;
		validateFunction = new validateFunction(this.driver);
	}
	//Kiểm tra giao diện
	public void checkTitlePage() throws Exception{
		validateFunction.checkTitlePage("Tra cứu");
	}
	
	public void checkEnableSearchBtn() throws Exception{
		validateFunction.checkEnable(searchBtn);
	}
	
	public void checkEnableSaveBtn() throws Exception{
		enterKeyword("mắt");
		clickSearchBtn();
		Thread.sleep(2000);
		validateFunction.checkEnable(saveBtn);
	}
	
	public void checkNullTextBox() throws Exception{
		validateFunction.checkNull(textbox);
	}
	public void checkValidTextBox() throws Exception{
		validateFunction.checkValueValid(textbox, "ABC@/1232");
	}
	
//	Kiểm tra combobox
	public void checkDefaultCombobox() {
		WebElement selectElement = driver.findElement(searchcbb);
	    Select select = new Select(selectElement);
		Assert.assertEquals("Nhan đề", select.getFirstSelectedOption().getText());
	}
	
	public void checkSize() {
		WebElement selectElement = driver.findElement(searchcbb);
	    Select select = new Select(selectElement);
		Assert.assertEquals(4, select.getOptions().size());
	}
	
	public void checkMultiSelect() {
		WebElement selectElement = driver.findElement(searchcbb);
	    Select select = new Select(selectElement);
		Assert.assertFalse(select.isMultiple());
	}
	
	public void checkSelectValue () {
		WebElement selectElement = driver.findElement(searchcbb);
	    Select select = new Select(selectElement);
	    select.selectByValue("au");
	    Assert.assertEquals("Tác giả", select.getFirstSelectedOption().getText());
		
	}
	//Chọn giá trị combobox
	public void selectCbb(String a) {
		WebElement selectElement = driver.findElement(searchcbb);
	    Select select = new Select(selectElement);
	    select.selectByValue(a);
	}
	public void enterKeyword(String a) {
		WebElement tb = driver.findElement(textbox);
		tb.sendKeys(a);
	}
	public void clickSearchBtn() {
		WebElement sb = driver.findElement(searchBtn);
		sb.click();
	}
	//Kiểm tra thông báo kết quả tìm kiếm
	public void checkErrorMsg(String a) {
		WebElement em = driver.findElement(errorMsg);
		String b = em.getText();
		Assert.assertEquals(a, b);
	}
	public void checkSaveFunction() throws Exception{
		enterKeyword("mắt");
		clickSearchBtn();
		Thread.sleep(1000);
		WebElement save = driver.findElement(saveBtn);
		String a = save.getText();
		if (a.equals("Lưu tài liệu")) {
			Actions actions = new Actions(driver);
			actions.moveToElement(save).click().perform();
			Thread.sleep(1000);
			String b = driver.switchTo().alert().getText();
			Assert.assertEquals(b, "Lưu tài liệu thành công!");
			driver.switchTo().alert().accept();
			Thread.sleep(2000);
			WebElement save1 = driver.findElement(saveBtn);
			String c = save1.getText();
			Assert.assertEquals(c, "Bỏ lưu tài liệu");
		}else {
			Actions actions = new Actions(driver);
			actions.moveToElement(save).click().perform();
			Thread.sleep(2000);
			String b = driver.switchTo().alert().getText();
			Assert.assertEquals(b, "Tài liệu đã được xóa khỏi danh sách của bạn!");
			driver.switchTo().alert().accept();
			Thread.sleep(2000);
			WebElement save1 = driver.findElement(saveBtn);
			String c = save1.getText();
			Assert.assertEquals(c, "Lưu tài liệu");
		}		
		
	}
	
	
	
	
}
