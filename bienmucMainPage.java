package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import setupbase.validateFunction;

public class bienmucMainPage {
	private WebDriver driver;
	
	private validateFunction validateFunction;
	
	private bienmucPage bienmucPage;
	
	private By welcometext = By.xpath("//*[@id=\"hero\"]/div/div/div[1]/h2");
	
	private By toBienmucPage = By.xpath("//*[@id=\"navbar\"]/ul/li[3]/a");
	
	public bienmucMainPage(WebDriver driver) {
		this.driver = driver;
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
	
	public bienmucPage tobienmucPage() {
		WebElement link = driver.findElement(toBienmucPage);
		link.click();
		return new bienmucPage(driver);
	}
}
