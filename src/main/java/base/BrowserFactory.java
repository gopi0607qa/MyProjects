package base;

import java.time.Duration;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {

	public WebDriver createbrowserInstance(String browsername) {

		WebDriver driver = null;


		if (browsername.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver(options);
		} else if (browsername.equalsIgnoreCase("Firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("-private");
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			options.setPageLoadTimeout(Duration.ofSeconds(10));
			driver = new FirefoxDriver(options);
		} else if (browsername.equalsIgnoreCase("Edge")) {
			EdgeOptions options = new EdgeOptions();
			options.addArguments("InPrivate");
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new EdgeDriver(options);

		}

		return driver;

	}

}
