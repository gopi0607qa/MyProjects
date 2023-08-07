package webElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Testbase;

public class Login_xpath extends Testbase {

	public Login_xpath(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "txtEmailId")
	public WebElement email_field;

	@FindBy(id = "pwdPasswordId")
	public WebElement password_field;

	@FindBy(id = "errEmailId")
	public WebElement error_mail;

	@FindBy(id = "errPasswordId")
	public WebElement error_password;

	@FindBy(id = "btnLoginId")
	public WebElement Login_button;

	@FindBy(xpath = "//div[@class='alert alert-danger fade in']")
	public WebElement alert_msg;

}
