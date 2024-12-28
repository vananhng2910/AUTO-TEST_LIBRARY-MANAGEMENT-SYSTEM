package extentreports;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import extentreports.ExtentManager;
import extentreports.ExtentTestManager;

public class ReportListener implements ITestListener{

    WebDriver driver;
    ExtentTestManager extent;
    ExtentManager extentManager;

    public String getTestName(ITestResult result) {
    	//nếu @test có testName thì trả về testName, ko có thì trả về tên phương thức
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
    	//nếu test có mô tả thì trả về mô tả, ko thì trả về trả về tên
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    	//khi bắt đầu 1 suite thì in ra dòng này
    	System.out.println("*** Test Suite " + iTestContext.getName() + " started ***");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    	//kết thúc thì tạo report
    	extentManager.getExtentReports().flush();    
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
    	//khi bđ test: lưu vào báo cáo tên và mô tả test
    	extent.saveToReport(iTestResult.getName(), iTestResult.getTestName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    	//test pass thì ghi lại log kết quả pass và mô tả test
    	extent.logMessage(Status.PASS, getTestDescription(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
    	//test fail thì thêm screenshot và ghi lại kết quả
        extent.addScreenShot(Status.FAIL, getTestName(iTestResult));
        extent.logMessage(Status.FAIL, iTestResult.getThrowable().toString());
        extent.logMessage(Status.FAIL, iTestResult.getName() + " is failed.");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    	//test skipped thì ghi lại log
    	extent.logMessage(Status.SKIP, getTestName(iTestResult) + " test is skipped.");
    }

}