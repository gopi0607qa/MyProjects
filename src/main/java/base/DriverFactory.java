package base;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
	
	private DriverFactory() {

	}
	
	private static DriverFactory instance = new DriverFactory();
	
	public static DriverFactory getInstance() {
		
		return instance;
		
	}
	
	
	static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public WebDriver getDriver(){
		return driver.get(); 
		
	}

	public void setDriver(WebDriver webdriv){
		driver.set(webdriv);
	}

	public void Close() {
		driver.get().close();
		driver.remove();
	}
}
