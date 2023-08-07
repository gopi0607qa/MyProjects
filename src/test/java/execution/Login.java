package execution;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import base.DriverFactory;
import base.Testbase;
import pageModule.Login_Test;
import util.ReadExcel;

public class Login extends Testbase {

	public Login_Test login = new Login_Test();
//	public Invalid_Login_page invalid_login = new Invalid_Login_page();
	public static ExtentTest test;
	public static ExtentReports report;

	@BeforeSuite
	public void beforesuite() {
		System.out.println("This is before suite");
	}

	@BeforeClass
	public void beforeclass() throws IOException {
		System.out.println("This is before class");

		report = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/src/test/resource/output/ExtentReport.html");

		report.setSystemInfo("User Name", "GopiNath");
		report.setSystemInfo("Host Name", InetAddress.getLocalHost().getHostName().toString());
		report.setSystemInfo("Operating System", System.getProperty("os.name").toString());
		report.setSystemInfo("Environment", "Staging");

		final File CONF = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\config\\extent-config.xml");
		spark.loadXMLConfig(CONF);

		report.attachReporter(spark);

	}

	@BeforeMethod
	@Parameters({ "browsername" })
	public void beforemethod(String browsername) throws IOException, InterruptedException {
		System.out.println("Before Method");
		test = report.createTest(browsername + "Browser Intialization");
		browserinitialization(browsername, test);
	}

	@DataProvider(name = "LoginValidData")
	public Object[][] readExcel() throws InvalidFormatException, IOException {
		return ReadExcel.readExcel(
				System.getProperty("user.dir") + "/src/test/resources/excel/Joyfulchild_credentials.xlsx",
				"LoginValidData");
	}

	@DataProvider(name = "LoginInvalidData", parallel = true)
	public Object[][] readExcel1() throws InvalidFormatException, IOException {
		return ReadExcel.readExcel(
				System.getProperty("user.dir") + "/src/test/resources/excel/Joyfulchild_credentials.xlsx",
				"LoginInvalidData");

	}

	@Test(dataProvider = "LoginValidData", enabled = false, threadPoolSize = 3)
	@Parameters({ "browsername" })
	public void Logintest(String UserName, String Password) throws InterruptedException, IOException, AWTException {
		System.out.println("Login Test case");
		test = report.createTest("Login Test Case");
		WebDriver driver = DriverFactory.getInstance().getDriver();
		login.Login_Page(test, driver, UserName, Password);
		login.Login_valid(test, driver, UserName, Password);

	}

	@Test(dataProvider = "LoginInvalidData", enabled = true, threadPoolSize = 3)
	public void Invalid_Logintest(String UserName, String Password)
			throws InterruptedException, IOException, AWTException {
		System.out.println("Invalid Login Test case");
		test = report.createTest("Invalid Login Test Case");
		WebDriver driver = DriverFactory.getInstance().getDriver();
		login.Login_Page(test, driver, UserName, Password);
		login.Login_validation(test, driver, UserName, Password);

	}

//	@Test(dataProvider = "LoginInvalidData", enabled = false)
//	public void Invalid_Logintest(String UserName, String Password)
//			throws InterruptedException, IOException, AWTException {
//		System.out.println("Invalid Login Test case");
//		test = report. createTest("InvalidLogin Test Case");
//		WebDriver driver = DriverFactory.getInstance().getDriver();
//		invalid_login.Invalid_Login(test,driver, UserName, Password);
//
//	}
	@AfterMethod
	public void aftermethod() {
		DriverFactory.getInstance().Close();
		System.out.println("This is after method");

	}

	@AfterClass
	public void afterclass() {
		System.out.println("This is after class");
		report.flush();

	}

	@AfterSuite
	public void aftersuite() {
		System.out.println("This is after suite");

	}

}