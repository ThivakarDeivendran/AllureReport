package mainPackage;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class AllureListener implements ITestListener{
	
	private static String getMethodName(ITestResult iResult) {
		return iResult.getMethod().getConstructorOrMethod().getName();
	}
	
	@Attachment
	public byte[] screenShotMethod(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);	
	}
	
	@Attachment(value ="{0}", type ="text/plain")
	public static String textLogMethod(String message) {
		return message;
	}
	@Override
	public void onTestStart(ITestResult result) {
	    System.out.println("Method Start "+getMethodName(result) + " start");
	  }
	@Override
	public void onStart(ITestContext icontext) {
		icontext.setAttribute("WebDriver", BaseClass.getDriver());
	}
	@Override
	 public void onTestFailure(ITestResult result) {
		   System.out.println("onTestFailure " + getMethodName(result) +" failed");
		   Object testClass = result.getInstance();
		   WebDriver driver = BaseClass.getDriver();
		   if(driver instanceof WebDriver) {
			   screenShotMethod(driver);
		   }
		   textLogMethod(getMethodName(result) +"failed and screenshot taken ");
  }
	@Override
	public void onTestSuccess(ITestResult result) {
	    System.out.println("onTestSuccess " + getMethodName(result)+" success");
	  }
	
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("onTestSkipped " + getMethodName(result) +" skipped");
	  }
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("onTestFailedButWithinSuccessPercentage " + getMethodName(result));
	  }
	@Override
	  public void onTestFailedWithTimeout(ITestResult result) {
		System.out.println("onTestFailedWithTimeout " + getMethodName(result));
	  }

		@Override
	  public void onFinish(ITestContext context) {
			
			
	  }
}
