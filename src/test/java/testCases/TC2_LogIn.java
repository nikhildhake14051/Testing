package testCases;

import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginPage;
import testBase.commonBase;

public class TC2_LogIn extends commonBase {

	
	@Test(dataProvider="loginData",dataProviderClass=utilities.DataProviderUtils.class)
	public void login(String email,String password) {
		 
		homePage hm=new homePage(driver);
		hm.clickmyAccount();
		hm.clickLogin();
		
		loginPage lg=new loginPage(driver);
		lg.setEmailId(email);
		lg.setPassword(password);
		lg.clickSubmit();
		
		if(driver.getTitle().equals("My Account")) {
			hm.clickLogout();
		}
		
	}
	
	
	
	
}
