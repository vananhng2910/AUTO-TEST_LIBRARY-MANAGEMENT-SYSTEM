package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import setupbase.validateFunction;

public class loginPage {
  
	private WebDriver driver;
	
	private validateFunction validateFunction;
	
	private userMainPage userMainPage;
	
	private bienmucMainPage bienmucMainPage;
	
	private By username = By.xpath("//*[@id='username']");
	
	private By password = By.xpath("//*[@id='password']");
	
	private By loginBtn = By.xpath("//*[@id=\"form\"]/div[3]/button");
	
	private By error = By.xpath("//*[@id=\"form\"]/div[4]");
	
	public loginPage(WebDriver driver) {
	    this.driver = driver;
	    validateFunction = new validateFunction(this.driver);
	}
	
	public void checknullUsername() throws Exception{
		validateFunction.checkNull(username);	
	}
	
	public void checknullPassword() throws Exception{
		validateFunction.checkNull(password);	
	}
	
	public void checkRequiredUsername() throws Exception{	
		validateFunction.checkRequired(username);
	}
	
	public void checkRequiredPassword() throws Exception{	
		validateFunction.checkRequired(password);
	}
	
	public void checkEnableLoginBtn() throws Exception{
		validateFunction.checkEnable(loginBtn);
	}
	
	public void checkTitlePage() throws Exception{
		validateFunction.checkTitlePage("THƯ VIỆN SỐ - ĐĂNG NHẬP");
	}
	
	public void checkValidFreetext () throws Exception{
		validateFunction.checkValueValid(username, "Abc#/\123");
		validateFunction.checkValueValid(password, "Abc#/\123");	
	}
	
	
	//Lấy thông báo lỗi
	public String getErrorMessage() {
		String strErrorMsg = null;
		try {
			WebElement errorMsg = driver.findElement(error);
				if (errorMsg.isDisplayed()) {
					strErrorMsg = errorMsg.getText();
				}
				} catch (Exception e) {
				strErrorMsg = null;
			}
		return strErrorMsg;			
	}
	//Kiểm tra thông báo lỗi
	public boolean verifyErrorMsg(String a) {
		return getErrorMessage().equals(a);
	}
	//Lấy đường dẫn hiện tại
	public String getCurUrl() {
		String b = driver.getCurrentUrl();
		return b;
	}
	//Nhập input
	public void enterUsername (String a) {
		WebElement usernameBox = driver.findElement(username);
		if (usernameBox.isDisplayed())
			usernameBox.sendKeys(a);
	}
	
	public void enterPassword (String pass) {
		WebElement passwordBox = driver.findElement(password);
		if (passwordBox.isDisplayed())
			passwordBox.sendKeys(pass);
	}
	
	public void clickSignIn() {
		WebElement signin = driver.findElement(loginBtn);
		if (signin.isDisplayed()) {
			signin.click();
		}
	}
	//Kiểm tra kết quả đăng nhập
	public void signIn (String a, String pass, String errMsg, String curUrl) {
		enterUsername(a);
		enterPassword(pass);
		clickSignIn();	
		if (getErrorMessage()== null) {
			Assert.assertEquals(getCurUrl(), curUrl);
		}
		else 
			Assert.assertEquals(getErrorMessage(), errMsg);
	}
	
	public userMainPage userLogin(String a, String pass) {
		enterUsername(a);
		enterPassword(pass);
		clickSignIn();	
		
		return new userMainPage(driver);
	}
	
	public bienmucMainPage bienmucLogin(String a, String pass) {
		enterUsername(a);
		enterPassword(pass);
		clickSignIn();
		return new bienmucMainPage(driver);
	}
}
