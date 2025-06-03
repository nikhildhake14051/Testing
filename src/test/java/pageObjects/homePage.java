package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homePage extends basedriverClass {
	
	public homePage(WebDriver driver) {
		super(driver);
																																							
	}

	//locators
	
	@FindBy(xpath="//a[@title='My Account']") WebElement icon_myAccount;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement drp_registration;
	@FindBy(xpath="(//a[normalize-space()='Login'])[1]") WebElement drp_login;
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement drp_Logout;
	
	
	public void clickmyAccount() {
		
		icon_myAccount.click();
		}
	public void clickRegistration() {
		drp_registration.click();
	}
	
	public void clickLogin() {
		drp_login.click();
	}
	
	public void clickLogout() {
		drp_Logout.click();
	}
	
	//https://tutorialsninja.com/demo/index.php?route=account/register
	

}
