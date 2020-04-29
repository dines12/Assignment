fpackage pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import base.BaseTestPage;
import base.FileInput;
import base.TestReporter;
import io.appium.java_client.MobileElement;

public class AmazonAmazonLoginPage extends BaseTestPage{
	WebDriverWait wait=new WebDriverWait(driver, 20);
	
	FileInput files= new FileInput();

	@FindBy(xpath = "//*[@resource-id=\"ap_email_login\"]")
    private MobileElement textBoxMobileNumber;
	
	@FindBy(xpath = "//*[@resource-id=\"ap_password\"]")
    private MobileElement textBoxPassword;
	
	@FindBy(xpath = "//*[@resource-id=\"signInSubmit\"]")
    private MobileElement buttonLogin;
	
	@FindBy(xpath = "//*[@resource-id=\"continue\"]")
    private MobileElement buttonContinue;
	
	@Override
	public void waitForPageToLoad() {
		wait.until(ExpectedConditions.visibilityOf(buttonContinue));
	}

	public AmazonLoginPage verifyAmazonLoginPageDisplayed() {
		
		try {
			waitForPageToLoad();
			TestReporter.AssertTrueWithScreenshot(textBoxMobileNumber.isDisplayed(),"Verify Login page is loaded");
		} catch (NoSuchElementException e) {
			Assert.fail("Failed to load amazon LogIn Page");
		}
		return this;
	}
	
	/**
	 * login
	 */
	public AmazonLoginPage userLogIn() {
		try {
			String username = files.Username(); //Fetching login username from TestData.xls
			String password = files.Password(); //Fetching login password from TestData.xls
			signIn(username, password); //Calling login method
			Assert.assertTrue(true,"Login Successful");
		} catch (Exception e) {
			Assert.fail("Sign in failed");
		}
		return this;
	}	
	
	/**
	 * Enter username and password for Sign In
	 * @param mobile_no
	 * @param pass
	 */
	public void signIn(String mobile_no,String pass) {
		try {
			textBoxMobileNumber.sendKeys(mobile_no);
			buttonContinue.click();
			wait.until(ExpectedConditions.visibilityOf(textBoxPassword));
			textBoxPassword.sendKeys(pass);
			TestReporter.logWithScreenShot("Before Login");
			buttonLogin.click();
			Reporter.log("Successfully entered login details and clicked Login button");
		} catch (Exception e) {
			Assert.fail("Failed to enter login details and Continue");
		}
	}
}
