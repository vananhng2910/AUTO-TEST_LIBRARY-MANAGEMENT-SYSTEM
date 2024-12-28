package extentreports;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import setupbase.setupbase;

public class ExtentTestManager{
//	WebDriver driver;
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    
    static ExtentReports extent = ExtentManager.getExtentReports();

    //ExtentTest - lớp xác định 1 trường hợp kiểm thử
    
    public static synchronized ExtentTest getTest() {
    	//thêm id của thread hiện tại vào map
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest saveToReport(String testName, String desc) {
    	//tạo 1 test với testname và desc
        ExtentTest test = extent.createTest(testName, desc);
        //thêm id thread và test vào map để tạo report
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }
   
    public WebDriver getDriver() {
        WebDriver driver = getDriver();
        return driver;
    }

    public static void addScreenShot(String message) {
    	//chụp toàn màn hình trình duyệt đang mở và lưu lại file dưới type base64 (string)
    	String bugimage = ((TakesScreenshot) setupbase.getDriver()).getScreenshotAs(OutputType.BASE64);
    	//với mỗi case test: lấy kết quả trả về, msg lỗi, và thêm ảnh chụp màn hình 
    	getTest().log(Status.INFO, message, MediaEntityBuilder.createScreenCaptureFromBase64String(bugimage).build());
    	
    }

    public static void addScreenShot(Status status, String message) {
        String bugimage = ((TakesScreenshot)setupbase.getDriver()).getScreenshotAs(OutputType.BASE64);
        getTest().log(status, message, MediaEntityBuilder.createScreenCaptureFromBase64String(bugimage).build());
    }

    public static void logMessage(String message) {
        getTest().log(Status.INFO, message);
    }

    public static void logMessage(Status status, String message) {
        getTest().log(status, message);
    }

}