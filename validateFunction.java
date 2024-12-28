package setupbase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;

public class validateFunction {
	
	 private WebDriver driver;
	 private WebDriverWait wait;
	 
	 public validateFunction(WebDriver _driver){
		 driver = _driver;
	     wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	 }
	 
	 public void checkTitlePage(String a) throws Exception{
		 String b = driver.getTitle();
		 Assert.assertEquals(a, b);
	 }
	 
	 public void checkNull(By element) throws Exception{
		 wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		 String a = driver.findElement(element).getText();
		 Assert.assertEquals(a,"");
		 Thread.sleep(500);
	 }
	 
	 public void checkRequired (By element) throws Exception{
		 WebElement a = driver.findElement(element);
		 Assert.assertTrue((Boolean)((JavascriptExecutor) driver).executeScript("return arguments[0].required;", a), "Không bắt buộc nhập!");
	 }
	 
	 public void checkEnable(By element) throws Exception {
		 WebElement a = driver.findElement(element);
		 Assert.assertTrue(a.isEnabled());
		 
	 }
	 
	 public void checkValueValid (By element, String inputvalue) {
		 WebElement a = driver.findElement(element);
		 a.sendKeys(inputvalue);
		 //sử dụng interface JavascriptExecutor để truy cập vào cơ chế thực thi javascript của trình duyệt
	     Assert.assertTrue((Boolean)((JavascriptExecutor) driver).executeScript("return arguments[0].validity.valid;", a), "Input không hợp lệ.");
	 }
	 	 
	 

}
