package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import base.BaseTestPage;
import base.TestReporter;
import base.Utility;

public class AmazonAmazonProductDetailsPage extends BaseTestPage{


	@FindBy(xpath = "//*[@resource-id='titleExpander']")
	private WebElement labelProductTitle;
	
	@FindBy(xpath = "//*[@resource-id='buyNowCheckout']")
	private WebElement buttonBuyNow;

	@Override
	public void waitForPageToLoad() {
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(labelProductTitle));
	}	
	
	/**
	 * Verify Product Details page loaded
	 */
	public AmazonProductDetailsPage verifyAmazonProductDetailsPageDisplayed() {
		
		try {
			waitForPageToLoad();
			TestReporter.AssertTrueWithScreenshot(buttonBuyNow.isDisplayed(),"Verify Product Details page is loaded");
		} catch (NoSuchElementException e) {
			Assert.fail("Failed to load Product Details page");
		}
		return this;
	}
	
	
	public AmazonProductDetailsPage clickBuyNowButton() {
		try {
			while(!(buttonBuyNow.isDisplayed())){ 	
			Utility.swipeVeritcal(driver, 0.9, 0.2, 2); 			}
			buttonBuyNow.click();
			Reporter.log("Buy Now button is clicked");
		} catch (Exception e) {
			Assert.fail("Failed to click Buy Now button");
		}
		return this;
	}
}
