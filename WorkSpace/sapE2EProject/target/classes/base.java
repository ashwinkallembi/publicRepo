	package resources;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.firefox.FirefoxOptions;
	import org.openqa.selenium.ie.InternetExplorerDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.remote.CapabilityType;
	import org.openqa.selenium.remote.DesiredCapabilities;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import io.github.bonigarcia.wdm.WebDriverManager;


	public class base {
			public WebDriver driver;
			public WebDriver initializeDriver() 
			{ 
				String propertyFilePath=System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties";
				String BrowserName = base.readProperty(propertyFilePath,"Browser");
				if(BrowserName.equals("Chrome"))
				{
					WebDriverManager.chromedriver().setup();
					DesiredCapabilities ch= DesiredCapabilities.chrome();
					ch.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
					ch.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
					ChromeOptions c=new ChromeOptions();
					c.merge(ch);

					driver=new ChromeDriver();
				}
				else if (BrowserName.equals("firefox"))
				{
					WebDriverManager.firefoxdriver().setup();
					DesiredCapabilities ff= DesiredCapabilities.firefox();
					ff.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
					ff.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
					FirefoxOptions c=new FirefoxOptions();
					c.merge(ff);
					driver=new FirefoxDriver();
				}
				else if (BrowserName.equals("IE"))
				{
					WebDriverManager.iedriver().setup();
					DesiredCapabilities ie= DesiredCapabilities.internetExplorer();
					ie.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
					ie.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
					FirefoxOptions c=new FirefoxOptions();
					c.merge(ie);
					driver=new InternetExplorerDriver();
				}
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				return driver;
				
			}
		
			public WebDriver discardDriver(WebDriver driver)
			{ driver=this.driver;
				if(driver!=null)
					driver.quit();
				return driver;
			}
			
			public static String readProperty(String pfPath, String Key)
			{
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(pfPath);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Properties pf=new Properties();
				try {
					pf.load(fis);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String browser = pf.getProperty(Key);
				return browser;
			}
			
			
			public static String getUrl(String key) {
				String propertyFilePath=System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties";
				String URL = base.readProperty(propertyFilePath,key);
				return URL;
			}
			
			public static String getCellValue(String SheetName, int Row, int Col)
			{try {
				String path= "./TestData/Data.xlsx";
				FileInputStream fis =new FileInputStream(path);

			Workbook wb=WorkbookFactory.create(fis);

			Sheet sheet=wb.getSheet(SheetName);

				Cell cell=sheet.getRow(Row).getCell(Col);
				wb.close();
				return cell.getStringCellValue();
			}
			catch(Exception e)
			{return " ";}
			}
			
			
		
			
			public static void killProcessByName(String processName) throws IOException
			{
				Runtime rt = Runtime.getRuntime();
				rt.exec("taskkill /f /im "+processName+".exe");

				}

			public static String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
			{
				TakesScreenshot ts=(TakesScreenshot) driver;
				File source =ts.getScreenshotAs(OutputType.FILE);
				String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
				FileUtils.copyFile(source,new File(destinationFile));
				return destinationFile;


			}
			public static void waitForElementVisibility(WebElement element, WebDriver driver)
			{
				try {
			WebDriverWait wait= new WebDriverWait(driver, 6);
		wait.until(ExpectedConditions.visibilityOf(element));
				}
				catch(Exception e) 
				{e.printStackTrace();}
			}
			
			public static void actionMouseMoveToElement(WebElement element, WebDriver driver)
			{
				Actions action=new Actions(driver);
				try {
				action.moveToElement(element).build().perform();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				}
			
			public static void actionSelectRadioButton(WebElement element, WebDriver driver)
			{
				Actions action=new Actions(driver);
				try {
				action.click(element).build().perform();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				}

			public void jsClick(WebElement element,WebDriver driver)
			{
			JavascriptExecutor js=( JavascriptExecutor)driver;
			 js.executeScript("arguments[0].click();", element);
			}
		
			public static boolean isElementPresent(WebElement element) {
				boolean flag = false;
				try {
					if (element.isDisplayed()|| element.isEnabled())
						flag = true;
				} catch (Exception e) {
					flag = false;
				
				}
				return flag;
			}
public static void switchToFrameByNmae(WebElement element, WebDriver driver)
{
	try {
	driver.switchTo().frame(element);
}
catch(Exception e)
{
	e.printStackTrace();
	}
	}
public static void scrollToElement(WebElement element, WebDriver driver)
{
	JavascriptExecutor j1=((JavascriptExecutor)driver);
j1.executeScript("arguments[0].scrollIntoView(true);", element);

}
}