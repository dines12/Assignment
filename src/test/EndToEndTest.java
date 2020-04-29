package test;

import org.testng.annotations.Test;
import base.BaseTestCase;
import base.FileInput;
import pages.AmazonCheckoutPage;
import pages.AmazonHomePage;
import pages.AmazonLoginPage;
import pages.AmazonProductDetailsPage;
import pages.AmazonSearchResultPage;
import pages.AmazonSignInPage;;

public class EndToEndTest extends BaseTestCase {
	
	FileInput files= new FileInput();
	
	@Test
	public void testSearchAndCompare() throws Exception {
		
		AmazonSignInPage signInObj = new AmazonSignInPage();
		signInObj.verifyAmazonSignInPageDisplayed();
		signInObj.clickSignInButton();
		
		AmazonLoginPage loginObj = new AmazonLoginPage();
		loginObj.verifyAmazonLoginPageDisplayed();
		loginObj.userLogIn(); 		AmazonHomePage AmazonHomePageObj = new AmazonHomePage();
		AmazonHomePageObj.verifyAmazonHomePageDisplayed();
		AmazonHomePageObj.EnterKeywordAndSearchItem(); 		
		AmazonSearchResultPage searchResultObj = new AmazonSearchResultPage();
		searchResultObj.verifyAmazonSearchResultPageDisplayed();
		String expectedItemName = searchResultObj.getItemName();

		AmazonProductDetailsPage AmazonProductDetailsPage = new AmazonProductDetailsPage();
		AmazonProductDetailsPage.verifyAmazonProductDetailsPageDisplayed();
		AmazonProductDetailsPage.clickBuyNowButton();
		
		AmazonCheckoutPage checkoutObj = new AmazonCheckoutPage();
		checkoutObj.verifyCheckOutPaymentsPageDisplayed();
		checkoutObj.clickNetBankingRadioButton();
		checkoutObj.selectBankName();
		checkoutObj.clickContinueButton();
		
		String actualItemName = checkoutObj.getItemNameOnCheckOut();
		checkoutObj.compareItemNames(actualItemName, expectedItemName);	//Comparing the item name from product search and checkout page
	}
}
