package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage extends basedriverClass {
	
	public loginPage(WebDriver driver) {
		super(driver);
	}
	
	
	
	@FindBy(xpath="//input[@id='input-email']") WebElement txt_email;
	@FindBy(xpath="//input[@id='input-password']") WebElement txt_password;
	@FindBy(xpath="//input[@value='Login']") WebElement btn_submit;
	
	
	public void setEmailId(String email) {
		txt_email.sendKeys(email);
	}
	
	public void setPassword(String pass) {
		txt_password.sendKeys(pass);
	}
	
	public void clickSubmit() {
		btn_submit.click();
	}
	
	//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']
	
	
	

}
