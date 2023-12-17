package allureReportPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestClass {
	
	public static WebDriver dr;
	@Test
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		 dr= new ChromeDriver();
		dr.get("https://www.facebook.com");
		dr.manage().window().maximize();
		dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String title = dr.getTitle();
		Assert.assertEquals(title, "Facebook – log in or sign");
	}
	@Test
	public void testMethod() {
		dr.findElement(By.xpath("//input[@id='email']")).sendKeys("Thivakar");
		dr.findElement(By.xpath("//input[@id='pass']")).sendKeys("Thivakar12");
		dr.findElement(By.xpath("//button[@name ='login']")).click();
		dr.quit();
		System.out.println("Someone add this code");
	}

}
