package Tests;

import Utils.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static SoftAssert softAssert;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;

    @Parameters("driverName")
    @BeforeTest(alwaysRun = true)
    public void setDriver(String driverName){

        driver= Driver.getDriver(driverName);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        softAssert=new SoftAssert();
        htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/myReport.html");
        //htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\test-output\\myReport.html");
        htmlReporter.config().setDocumentTitle("OpenMRS Automation Report");
        htmlReporter.config().setReportName("Registration Functionality Validation");
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports=new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("OS","MAC OS");
        extentReports.setSystemInfo("Browser","Chrome");
        extentReports.setSystemInfo("Environment","Test Environment");
        extentReports.setSystemInfo("Domain","https://demo.openmrs.org/");

    }

    @AfterTest
    public void tearDown(){

        if(driver!=null){
            extentReports.flush();
           // driver.quit();
        }
    }
}
