package testCases;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.registrationPage;
import testBase.commonBase;
import utilities.exelUtils;

public class TC1_registration extends commonBase {
	String expectedMsg="Your Account Has Been Created!";
	@Test
	public void registration() throws Exception {
		homePage ac=new homePage(driver);
		ac.clickmyAccount();
		logger.info("------clicked on my account------");
		
		ac.clickRegistration();
		logger.info("------clicked on Registration------");
		
		registrationPage rp=new registrationPage(driver);
		logger.info("------Getting user details------");
		rp.setfirstName(randomAlpha());
		rp.setlastName(randomAlpha());
		String ran=randomAlpha()+"@gmail.com";
		rp.setMailId(ran);
		
        //String path=(".\\testData\\openHRM_LoginData.xlsx");
		//exelUtils exutils= new exelUtils(path);
		//exutils.setCellData("sheet1", 4, 0, ran);
		System.out.println(ran);
		
		
		rp.setMobNum(randomNum());
		String password=randomAlphaNum();
		
		//exutils.setCellData("sheet1", 4, 1, password);
		
		rp.setPassword(password);
		rp.setcnfPassword(password);
		rp.clickPrivacybtn();
		rp.clickSubmit();
		Thread.sleep(2000);
		//Assert.assertEquals(rp.lastMsg(),expectedMsg);
		
		if(rp.lastMsg().equals(expectedMsg)) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test fail");
			logger.debug("Debug Masage");
			Assert.fail();
			
		}
		
		ac.clickLogout();
		
		
	}
	
	
	
}
