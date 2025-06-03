package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class registrationPage extends basedriverClass {

	//setup
	
	public registrationPage(WebDriver driver) {
		super(driver);
	}
	
//	locators
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txt_userfirstName;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txt_userlastName;
	@FindBy(xpath="//input[@id='input-email']") WebElement txt_email;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txt_mobnum;
	@FindBy(xpath="//input[@id='input-password']") WebElement txt_password;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txt_cnfPsssword;
	@FindBy(xpath="//input[@name='agree']") WebElement btn_cnf;
	@FindBy(xpath="//input[@value='Continue']") WebElement btn_submit;
	@FindBy(xpath="//div[@id='content']//h1") WebElement msg_approvel;
	
	String expectedMsg="Your Account Has Been Created!";
//	methods
	
	
	public void setfirstName(String first) {
		txt_userfirstName.sendKeys(first);
	}
	
	public void setlastName(String last) {
		txt_userlastName.sendKeys(last);
	}
	
	public void setMailId(String Id) {
		txt_email.sendKeys(Id);
	}
	
	public void setMobNum(String num) {
		txt_mobnum.sendKeys(num);
	}
	
	public void setPassword(String password) {
		txt_password.sendKeys(password);
	}
	
	public void setcnfPassword(String cnfpassword) {
		txt_cnfPsssword.sendKeys(cnfpassword);
	}
	
	public void clickPrivacybtn() {
		btn_cnf.click();;
	}
	
	public void clickSubmit() {
		btn_submit.click();
	}
	
	public String lastMsg() {
		try {
			return (msg_approvel.getText());
		}
		catch(Exception e) {
			return (e.getMessage());
		}
	}
	
	
	
}
