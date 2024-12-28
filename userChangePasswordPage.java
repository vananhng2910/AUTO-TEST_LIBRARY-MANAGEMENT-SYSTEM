package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import setupbase.validateFunction;

public class userChangePasswordPage {
	private WebDriver driver;
	public loginPage loginPage;
	public userMainPage userMainPage;
	private validateFunction validateFunction;
	private By username = By.xpath("//*[@id=\"username\"]");
	private By curpass = By.xpath("//*[@id=\"oldpass\"]");
	private By newpass = By.xpath("//*[@id=\"newpass\"]");
	private By cfnewpass = By.xpath("//*[@id=\"cfnewpass\"]");
	private By changeBtn = By.xpath("//*[@id=\"change-btn\"]");

	public userChangePasswordPage(WebDriver driver) {
		this.driver = driver;
		validateFunction = new validateFunction(this.driver);
	}
	
	public void checkTitlePage() throws Exception{
		validateFunction.checkTitlePage("Đổi mật khẩu");
	}
	
	public void checkDisableUsername() {
		WebElement a = driver.findElement(username);
		Assert.assertFalse(a.isEnabled());
	}
	
	public void checkDefaultValueUsername(String b) {
		WebElement a = driver.findElement(username);
		String c = a.getAttribute("value");
		System.out.print(c);
		Assert.assertEquals(c,b);
	}
	
	public void checkNullCurPass() throws Exception{
		validateFunction.checkNull(curpass);
	}
	
	public void checkNullNewPass() throws Exception {
		validateFunction.checkNull(newpass);
	}
	
	public void checkNullCfNewPass() throws Exception{
		validateFunction.checkNull(cfnewpass);
	}
	
	public void checkValidFreetext() throws Exception{
		validateFunction.checkValueValid(curpass, "Abc@/\123");
		validateFunction.checkValueValid(newpass, "Abc@/\123");
		validateFunction.checkValueValid(cfnewpass, "Abc@/\123");
	}
	
	public void checkEnableBtn() throws Exception {
		validateFunction.checkEnable(changeBtn);
	}
	
	public void enterCurPass(String a) {
		WebElement npass = driver.findElement(curpass);
		npass.sendKeys(a);
	}
	
	public void enterNewPass(String a) {
		WebElement npass = driver.findElement(newpass);
		npass.sendKeys(a);
	}
	
	public void enterCfNewPass(String a) {
		WebElement npass = driver.findElement(cfnewpass);
		npass.sendKeys(a);
	}
	
	public void clickChangeBtn() {
		WebElement a = driver.findElement(changeBtn);
		a.click();
	}
	
	public void Input(String a, String b, String c) throws Exception {
		enterCurPass(a);
		enterNewPass(b);
		enterCfNewPass(c);
	}
	
	public void clearInput() {
		WebElement npass = driver.findElement(newpass);
		WebElement opass = driver.findElement(curpass);
		WebElement cfnp = driver.findElement(cfnewpass);
		npass.clear();
		opass.clear();
		cfnp.clear();
		
	}
	
	public void checkMsgAlert(String a, String b, String c, String msg) throws Exception{
		Input(a,b,c);
		Thread.sleep(1000);
		clickChangeBtn();
		Thread.sleep(1000);
		String d = driver.switchTo().alert().getText();
//		System.out.print(d);
		Assert.assertEquals(d, msg);
		Thread.sleep(500);
		driver.switchTo().alert().accept();
		clearInput();
	}
	
	public void checkInputNull () throws Exception{
		checkMsgAlert("", "abc", "abc", "Mật khẩu hiện tại không được để trống!");
		checkMsgAlert("abc", "", "abc", "Mật khẩu mới không được để trống!");
		checkMsgAlert("abc", "abc", "", "Xác nhận mật khẩu mới không được để trống!");
	}
	
	
	
  
}
