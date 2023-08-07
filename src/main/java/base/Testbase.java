package base;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class Testbase {

	public static BasicFunction fun = new BasicFunction();
	static BrowserFactory bf = new BrowserFactory();

	public static void browserinitialization(String browsername, ExtentTest test)
			throws IOException, InterruptedException {

		DriverFactory.getInstance().setDriver(bf.createbrowserInstance(browsername));
		WebDriver driver = DriverFactory.getInstance().getDriver();

		driver.manage().window().maximize();
		String url = "http://dev.datanetiix.com/thejoyfulchild/";
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		System.out.println("Navigated to URL : " + url);

//		String Title = driver.getTitle();
		if (driver.getTitle().equalsIgnoreCase("The Joyful Child | Log in")) {
			System.out.println("PASS: Page navigated successfully");
			test.log(Status.PASS, "Page Navigated Successfully",
					MediaEntityBuilder.createScreenCaptureFromPath(fun.capturescreenshot(driver)).build());

		} else {
			System.out.println("FAIL: Page is not navigated");
			test.log(Status.FAIL,
					"Page is not navigated " + "Expected Title " + "The Joyful Child | Log in" + " Actual Title is :  "
							+ driver.getTitle()
							+ MediaEntityBuilder.createScreenCaptureFromPath(fun.capturescreenshot(driver)).build());

		}

	}

}
