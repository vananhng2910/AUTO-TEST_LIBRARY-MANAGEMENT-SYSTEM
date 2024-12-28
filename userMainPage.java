package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import setupbase.validateFunction;

public class userMainPage {
	private WebDriver driver;
	
	private validateFunction validateFunction;
	
	private userSearchPage userSearchPage;
	
	private userChangePasswordPage userChangePasswordPage;
	
	private By welcometext = By.xpath("//*[@id=\"hero\"]/div/div/div[1]/h2");
	
	private By fullname = By.xpath("//*[@id=\"navbar\"]/ul/li[3]/ul/li[1]");
	
	private By toSearchPageLink = By.xpath("//*[@id=\"navbar\"]/ul/li[2]/a");
	
	private By toSavedBookLink = By.xpath("//*[@id=\"navbar\"]/ul/li[3]/ul/li[4]/a");
	
	private By toBorrowedBookLink = By.xpath("//*[@id=\"navbar\"]/ul/li[3]/ul/li[3]/a");
	
	private By userProfileLink = By.xpath("//*[@id=\"navbar\"]/ul/li[3]/a/span");
	
	private By changePassLink = By.xpath("//*[@id=\"navbar\"]/ul/li[3]/ul/li[5]/a");
	
	public userMainPage(WebDriver driver) {
		this.driver = driver;
	}
	

	
	public String getUserLogin() throws Exception{
		WebElement toProfile = driver.findElement(userProfileLink);
		Thread.sleep(1000);
		toProfile.click();
		Thread.sleep(1000);
		WebElement name = driver.findElement(fullname);
		return name.getText();	
	}
	
	public String getWelcomeMsg() throws Exception{
		WebElement msg = driver.findElement(welcometext);
		Thread.sleep(1000);
		return msg.getText();
	}
	
	public String gettitlepage() {
		String title = driver.getTitle();
		return title;
	}
	public userSearchPage toSearchPage() {
		WebElement searchpage = driver.findElement(toSearchPageLink);
		searchpage.click();
		return new userSearchPage(driver);
	}
	public userChangePasswordPage toChangePassPage() {
		WebElement toProfile = driver.findElement(userProfileLink);
		toProfile.click();
		WebElement name = driver.findElement(changePassLink);
		name.click();
		return new userChangePasswordPage(driver);
	}	
}
