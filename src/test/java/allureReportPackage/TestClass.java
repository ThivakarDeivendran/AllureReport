package allureReportPackage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import mainPackage.AllureListener;
import mainPackage.Allure_util;
import mainPackage.BaseClass;

@Listeners({AllureListener.class})
public class TestClass extends BaseClass{

	public static WebDriver dr;
	static SoftAssert sf  = new SoftAssert();

	@Test(priority = 1, description =" login vaildation ")
	@Description("Verify the Title of the Application")
	@Epic("EP001")
	@Feature("Feature 1")
	@Story("Test Story 1")
	@Step("To verify the Title of the Application")
	@Severity(SeverityLevel.BLOCKER)
	public static void setUp() {
		
		dr = BaseClass.launchBrowserMethod("Chrome");
		dr.get("https://artoftesting.com/samplesiteforselenium");
		dr.manage().window().maximize();
		dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String title = dr.getTitle();
		sf.assertEquals(title, "Samp Webpage for Selenium Automation Practice | ArtOfTesting");
	}

	@Test(priority = 2)
	@Description("Verify the Button of the Application")
	@Epic("EP001")
	@Feature("Feature 2")
	@Story("Test Story 2")
	@Step("To verify the button colour change of the Application")
	@Severity(SeverityLevel.NORMAL)
	public static void testMethod() throws InterruptedException {
		dr.findElement(By.id("fname")).sendKeys("Thivakar");
		Thread.sleep(3000);
		dr.findElement(By.id("idOfButton")).click();
		Thread.sleep(3000);
		Assert.assertTrue((dr.findElement(By.id("idOfButton")).getAttribute("style")).contains("blue"),
				"ButtonColour Not Changed");
	}

	@Test(priority = 3)
	@Description("Verify the DoubleClick of the Application")
	@Epic("EP001")
	@Feature("Feature 3")
	@Story("Test Story 3")
	@Step("To verify the Double Click Functionality of the Application")
	@Severity(SeverityLevel.NORMAL)
	public static void alertMethod() {
		Actions action = new Actions(dr);
		WebElement doubleClickButton = dr.findElement(By.id("dblClkBtn"));
		action.doubleClick(doubleClickButton).build().perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dr.switchTo().alert().accept();
	}

	@Test(priority = 4)
	@Description("Verify the RadioButton of the Application")
	@Epic("EP001")
	@Feature("Feature 4")
	@Story("Test Story 4")
	@Step("To verify the Radio Button Functionality of the Application")
	@Severity(SeverityLevel.TRIVIAL)
	public static void radioCheckBoxMethod() throws InterruptedException {
			dr.findElement(By.id("male")).click();
			Thread.sleep(5000);
			dr.findElement(By.xpath("//input[@class='Performance']")).click();
			Thread.sleep(3000);
	}
	@Test(priority = 5)
	@Description("Verify the CheckBox of the Application")
	@Epic("EP001")
	@Feature("Feature 5")
	@Story("Test Story 5")
	@Step("To verify the Radio Button Functionality of the Application")
	@Severity(SeverityLevel.TRIVIAL)
	public static void dropdownMethod() {
		Select variableName = new Select(dr.findElement(By.id("testingDropdown")));
		variableName.selectByValue("Manual");
		sf.assertAll();
	}
	@Test(priority = 6)
	@Description("Verify the Skip Functionality")
	@Epic("EP001")
	@Feature("Feature 6")
	@Story("Test Story 6")
	@Step("To verify the Skip Functionality")
	@Severity(SeverityLevel.TRIVIAL)
	public static void skipMethod() {
			throw new SkipException("This method need to skip");
		}
	
	@AfterSuite
	public static void tearDown() throws IOException, InterruptedException {
		dr.quit();
		Allure_util au = new Allure_util();
		au.runAllureCommand("allure generate --clean");
		File sourceFile = new File("allure-report/history");
		File targetFile = new File("allure-results/history");
		FileUtils.copyDirectory(sourceFile, targetFile);
		au.runAllureReportCommand("allure serve");
	}
}
