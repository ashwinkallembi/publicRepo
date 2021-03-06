package testScripts;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.pageLogin;
import resources.base;
public class testSignUp extends base {
public WebDriver driver;
	@BeforeTest
	public void preCondition()
	{
		driver=initializeDriver();
			}
		
	@Test
	public void testSignUpSap() throws InterruptedException
	{
		
		driver.get(base.getUrl("url"));
		Assert.assertEquals(driver.getTitle(), "SAP Conversational AI | Automate Customer Service With AI Chatbots", "Both a are matching");
		pageLogin pl=new pageLogin(driver);
		pl.clickSignUpButton();
		pl.switchToFrame();
		pl.enterFirstNmae();
		pl.enterLastNmae();
		pl.enterMail();
		pl.enterPwd();
		pl.enterRePwd();
		pl.checkBox1Click();
		pl.checkbox2Click();
		pl.clickRegisterButton();
		pl.waitSucessMessage();
		}

	
	@AfterTest
	public void afterCondition()
	{
	discardDriver(driver);
		}
	}
