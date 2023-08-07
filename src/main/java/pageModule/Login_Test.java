package pageModule;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.util.Assert;

import base.BasicFunction;
import base.Testbase;
import webElement.Login_xpath;

public class Login_Test extends Testbase {
	public static BasicFunction fun = new BasicFunction();

	public void Login_Page(ExtentTest test, WebDriver driver, String UserName, String Password)
			throws InterruptedException, IOException, AWTException {
		Login_xpath login_xp = new Login_xpath(driver);

		fun.Sendkeys(driver, login_xp.email_field, UserName);
		fun.Sendkeys(driver, login_xp.password_field, Password);
		System.out.println();
		fun.Click(driver, login_xp.Login_button);
		Thread.sleep(4000);
		test.log(Status.INFO, "Login Button CLicked",
				MediaEntityBuilder.createScreenCaptureFromPath(fun.capturescreenshot(driver)).build());
		System.out.println("Login button clicked");

	}

	public void Login_valid(ExtentTest test, WebDriver driver, String UserName, String Password)
			throws InterruptedException, IOException, AWTException {
		try {
			System.out.println("Login_valid");
			String page_title = driver.getTitle();
			System.out.println(page_title);

			Assert.notEmpty(page_title, "The Joyful Child");
			test.log(Status.PASS, "Valid Login Test passed",
					MediaEntityBuilder.createScreenCaptureFromPath(fun.capturescreenshot(driver)).build());
		} catch (Exception e) {
			System.out.println("Login valid failed");
			test.log(Status.FAIL, "Valid login Test Failed",
					MediaEntityBuilder.createScreenCaptureFromPath(fun.capturescreenshot(driver)).build());
			e.printStackTrace();
		}

	}

	public void Login_validation(ExtentTest test, WebDriver driver, String UserName, String Password)
			throws InterruptedException, IOException, AWTException {
		Login_xpath login_xp = new Login_xpath(driver);
		try {
			if (login_xp.alert_msg.isDisplayed()) {
				fun.Scroll(driver, login_xp.alert_msg);
				String alert_text = login_xp.alert_msg.getText();
				test.log(Status.PASS,
						"Valid email and invalid password  " + "Validation message of error: " + alert_text
								+ "Test data - Username: " + UserName + "   Password: " + Password,
						MediaEntityBuilder.createScreenCaptureFromPath(fun.capturescreenshot(driver)).build());
				System.out.println(UserName + "  " + Password + alert_text);
			}
			if (login_xp.error_mail.isDisplayed()) {
				fun.Scroll(driver, login_xp.error_mail);
				String email_txt = login_xp.error_mail.getText();
				test.log(Status.PASS,
						"Valid email and invalid password  " + "Validation message : " + email_txt
								+ "Test data - Username: " + UserName + "   Password: " + Password,
						MediaEntityBuilder.createScreenCaptureFromPath(fun.capturescreenshot(driver)).build());
				System.out.println(UserName + "  " + Password + email_txt);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block

			test.log(Status.FAIL, " Exception occured:  " + e,
					MediaEntityBuilder.createScreenCaptureFromPath(fun.capturescreenshot(driver)).build());
			e.printStackTrace();

		}
//			if (login_xp.error_password.getText().equalsIgnoreCase("Enter Valid Email Id.")) {
//				String pass_txt = login_xp.error_password.getText();
//				test.log(Status.PASS,
//						"Validation message password field: " + pass_txt + "Test data - Username: " + UserName
//								+ "   Password: " + Password,
//						MediaEntityBuilder.createScreenCaptureFromPath(fun.capturescreenshot(driver)).build());
//				System.out.println(UserName + "  " + Password + pass_txt);
//			}
//			if (login_xp.error_mail.isDisplayed() && login_xp.error_password.isDisplayed() == true) {
//				String email_txt = login_xp.error_mail.getText();
//				String pass_txt = login_xp.error_password.getText();
//				test.log(Status.PASS,
//						"Validation message : " + email_txt + "Validation message password field: " + pass_txt
//								+ "Test data - Username: " + UserName + "   Password: " + Password,
//						MediaEntityBuilder.createScreenCaptureFromPath(fun.capturescreenshot(driver)).build());
//		else {
//
//			test.log(Status.FAIL, "Invalid login failed",
//					MediaEntityBuilder.createScreenCaptureFromPath(fun.capturescreenshot(driver)).build());
//		}
	}

}
