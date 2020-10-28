	package pageObjects;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;
	import resources.base;
	public class pageLogin extends pageGeneric{
		public WebDriver driver;
		public pageLogin(WebDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
	@FindBy(xpath="//div[@class='btn--content' and text()='Sign up']")
	private WebElement signUpButton;

	@FindBy(xpath="//iframe[@id='IDS_UI_Window']")
	private WebElement registerFrame;
	
	@FindBy(id="firstName")
	private WebElement firstName;

	@FindBy(id="lastName")
	private WebElement lastName;
	
	@FindBy(id="mail")
	private WebElement mail;
	
	@FindBy(id="newPasswordInput")
	private WebElement pwd1;
	
	@FindBy(id="retypeNewPasswordInput")
	private WebElement RePwd;
	
	@FindBy(xpath="//input[@id='pdAccept' and @class='js-remove-error-on-change']")
	private WebElement chekcBox1;
	
	@FindBy(xpath="//input[@id='touAccept' and @class='js-remove-error-on-change']")
	private WebElement checkbox2;
	
	@FindBy(id="sapStoreRegisterFormSubmit")
	private WebElement RegisterButton;
	
	@FindBy(xpath="//div[contains(text(),'An e-mail with a link to activate your account has been sent to')]")
	private WebElement sucessMsg;

	public void clickSignUpButton()
	{
		base.waitForElementVisibility(signUpButton, driver);
		signUpButton.click();
		}
	public void switchToFrame()
	{
		base.waitForElementVisibility(registerFrame, driver);
		base.switchToFrameByNmae(registerFrame, driver);
	}
public void enterFirstNmae() throws InterruptedException
{
	String fname = base.getCellValue("FormData",0,1);
	firstName.clear();
	firstName.sendKeys(fname);	
}
public void enterLastNmae()
{
	String lname = base.getCellValue("FormData",1,1);
	lastName.clear();
	lastName.sendKeys(lname);
	}
public void enterMail()
{
	String emailId = base.getCellValue("FormData",2,1);
	mail.clear();
	mail.sendKeys(emailId);
}
public void enterPwd()
{
	String pwd = base.getCellValue("FormData",3,1);
	pwd1.clear();
	pwd1.sendKeys(pwd);
}
public void enterRePwd()
{
	String pwdRE = base.getCellValue("FormData",3,1);
	RePwd.clear();
	RePwd.sendKeys(pwdRE);
}

public void checkBox1Click()
{base.scrollToElement(chekcBox1, driver);
	chekcBox1.click();
	}
public void checkbox2Click()
{base.scrollToElement(checkbox2, driver);
	checkbox2.click();
	}

public void clickRegisterButton()
{
	RegisterButton.click();
}
public void waitSucessMessage()
{
	base.waitForElementVisibility(sucessMsg, driver);
}
}


	
	

