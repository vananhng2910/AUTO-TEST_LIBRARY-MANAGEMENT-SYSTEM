package extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	//lớp cho phép người dùng tạo báo cáo
	private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
    	//set đường dẫn báo cáo 
        ExtentSparkReporter reporter = new ExtentSparkReporter("./ExtentReports/ExtentReports.html");
        reporter.config().setReportName("Report");
        //đính kém file báo cáo ở trên vào extentReports cho phép chạy, theo dõi các test
        extentReports.attachReporter(reporter);
        //set thông tin cho báo cáo in ra
        extentReports.setSystemInfo("Website", "Thư viện VALIB");
        extentReports.setSystemInfo("Author", "Nguyễn Thị Vân Anh");
        return extentReports;
    }
}

