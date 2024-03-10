package mainPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	
	public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();

	public static WebDriver launchBrowserMethod(String browser) {
		try {
			switch (browser) {
			case "Chrome":
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--remote-allow-origins=*");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(option);
				break;
			case "Edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
			case "Firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("browser not installed in your system");
			}
		} catch (Exception e) {

		}
		threadLocalDriver.set(driver);
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver() {
		return threadLocalDriver.get();
	}

}
